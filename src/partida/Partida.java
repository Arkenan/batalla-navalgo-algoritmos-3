package partida;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import barcos.Barco;
import barcos.Vector;
import disparos.Disparo;
import excepciones.DisparoInvalido;

public class Partida {
    private int puntos;
    final int decrecimientoDePuntosPorPasoDeTurno = 10;
    ArrayList<Disparo> disparos;
    ManejadorDeBarcos manejadorDeBarcos;

    public Partida() {
        puntos = 10000;
        disparos = new ArrayList<Disparo>();
        manejadorDeBarcos = new ManejadorDeBarcos();
    }

    // crea una partida a través de un nodo XML. Puede ser tanto un nivel como
    // una partida guardada.
    public Partida(Element nodoPartida) {
        System.out.println("Nombre de la Partida: " + nodoPartida.attributeValue("nombre"));

        puntos = Integer.parseInt(nodoPartida.attributeValue("puntos"));

        ConstructorDeDisparo constructorDeDisparo = new ConstructorDeDisparo();
        disparos = constructorDeDisparo.construirDisparos(nodoPartida.element("Disparos"));

        manejadorDeBarcos = new ManejadorDeBarcos();

    }

    public ArrayList<Barco> crearBarcos(Element nodoPartida) {
        return manejadorDeBarcos.crearBarcos(nodoPartida.element("Barcos"));
    }

    public Element obtenerNodoPartida() {
        Element nodoPartida = DocumentHelper.createElement("Partida");
        ArrayList listaAGuardar = new ArrayList();
        ArrayList<Element> listaNodosAGuardar = new ArrayList<Element>();
        // acá tengo que recorrer las listas de destructibles, movibles y disparos.
        Iterator<Disparo> it = disparos.iterator();
        Element nodoDisparos = DocumentHelper.createElement("Disparos");
        while (it.hasNext()) {
            Disparo disparo = it.next();
            nodoDisparos.add(disparo.generarNodo());
        }
        nodoPartida.add(nodoDisparos);
        // ESTO ES PROVISORIO.
        nodoPartida.add(manejadorDeBarcos.generarNodoBarcos());
        return nodoPartida;
    }

    public void guardar(String archivo) throws IOException {
        Document doc = DocumentHelper.createDocument();
        doc.add(this.obtenerNodoPartida());
        FileWriter writer = new FileWriter(archivo);
        doc.write(writer);
        writer.close();
    }

    public static Element obtenerNodoPartida(String dirArchivo) throws IOException {
        Element nodoPartida;
        SAXReader lector = new SAXReader();
        try {
            // aca buscamos el documento segun su direccion.
            Document documento = lector.read(dirArchivo);
            // el Element es el nodo. Sacamos el nodo raiz (suponemos que es una
            // persona).
            nodoPartida = documento.getRootElement();
            // aca, una vez obtenido el nodo XML que representa a la persona,
            // creamos la persona
            // para devolver.
        } catch (DocumentException ex) {
            throw new IOException("ERROR AL TRABAJAR CON EL ARCHIVO");
        }
        return nodoPartida;
    }

    public ArrayList<Barco> crearBarcosPorDefault() {
        return manejadorDeBarcos.crearBarcosPorDefault();
    }

    public int getPuntos() {
        return puntos;
    }

    public void colocarDisparo(String nombre, Vector posicion) {
        ConstructorDeDisparo constructorDeDisparo = new ConstructorDeDisparo();
        Disparo unDisparo;
        try {
            unDisparo = constructorDeDisparo.construirDisparo(nombre, posicion);
        } catch (DisparoInvalido error) {
            return;
        }
        if (unDisparo.costo() <= this.getPuntos()) {
            disparos.add(unDisparo);
            this.reducirPuntosEn(unDisparo.costo());
            this.realizarCambiosPasoTurno();
        }
    }

    // Paso de turno sin realizar acciones.
    public void pasarTurno() {
        if (this.getPuntos() >= 10) {
            this.reducirPuntosEn(decrecimientoDePuntosPorPasoDeTurno);
            this.realizarCambiosPasoTurno();
        }
    }

    // Nos devuelve un booleano que informa si los puntos son suficientes o no
    // para seguir jugando.
    public boolean faltanPuntosParaSeguir() {
        return (puntos < decrecimientoDePuntosPorPasoDeTurno);
    }

    // Este metodo se ocupa del movimiento de barcos y explosion de minas.

    public boolean gano() {
        return manejadorDeBarcos.todosLosDestructiblesEstanDestruidos();
    }

    public boolean perdio() {
        if (!gano()) {
            return this.faltanPuntosParaSeguir();
        }
        return false;
    }

    // ------------------- Metodos Privados -----------------------------

    private void reducirPuntosEn(int puntosADisminuir) {
        puntos -= puntosADisminuir;
    }

    private void realizarCambiosPasoTurno() {
        this.pasoTurnoDisparos();
        manejadorDeBarcos.moverElementos();
    }

    private void pasoTurnoDisparos() {
        Disparo disparoAuxiliar;
        int i = 0;
        while (i < disparos.size()) {
            if ((disparos.get(i)).debeExplotar()) {
                disparoAuxiliar = disparos.remove(i);
                disparoAuxiliar.explotar();
            } else {

                disparoAuxiliar = disparos.get(i);
                disparoAuxiliar.pasarTurno();
                i++;
            }
        }

    }

}
