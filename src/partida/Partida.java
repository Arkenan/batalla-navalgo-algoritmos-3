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
import constructoresdevistas.AbstractVistasBarcoFactory;
import constructoresdevistas.VistaLanchaFactory;
import constructoresdevistas.VistasBuqueFactory;
import constructoresdevistas.VistasDestructorFactory;
import constructoresdevistas.VistasPortaavionesFactory;
import constructoresdevistas.VistasRompehieloFactory;
import disparos.Daniador;
import disparos.Disparo;
import excepciones.DisparoInvalido;

public class Partida {
    private int puntos;
    final int decrecimientoDePuntosPorPasoDeTurno = 10;
    ArrayList<Daniador> daniadores;
    ManejadorDeElementosDelTablero manejadorDeElementosDelTablero;

    public Partida() {
        puntos = 10000;
        daniadores = new ArrayList<Daniador>();
        manejadorDeElementosDelTablero = new ManejadorDeElementosDelTablero();
    }

    // crea una partida a través de un nodo XML. Puede ser tanto un nivel como
    // una partida guardada.
    public Partida(Element nodoPartida) {
        puntos = Integer.parseInt(nodoPartida.attributeValue("puntos"));

        ConstructorDeDaniador ConstructorDeDaniador = new ConstructorDeDaniador();
        daniadores = ConstructorDeDaniador.construirDisparos(nodoPartida.element("Disparos"));

        manejadorDeElementosDelTablero = new ManejadorDeElementosDelTablero();

    }

    public ArrayList<Barco> crearBarcos(Element nodoPartida) {
        return manejadorDeElementosDelTablero.crearBarcos(nodoPartida.element("Barcos"));
    }

    public Element obtenerNodoPartida() {
        Element nodoPartida = DocumentHelper.createElement("Partida");
        ArrayList listaAGuardar = new ArrayList();
        ArrayList<Element> listaNodosAGuardar = new ArrayList<Element>();
        // acá tengo que recorrer las listas de destructibles, movibles y
        // disparos.
        Iterator<Daniador> it = daniadores.iterator();
        Element nodoDisparos = DocumentHelper.createElement("Disparos");
        while (it.hasNext()) {
            Daniador daniador = it.next();
            nodoDisparos.add(daniador.generarNodo());
        }
        nodoPartida.add(nodoDisparos);
        // ESTO ES PROVISORIO.
        nodoPartida.add(manejadorDeElementosDelTablero.generarNodoBarcos());
        nodoPartida.addAttribute("puntos", Integer.toString(this.getPuntos()));
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
            // aca buscamos el documento segun su direccion y sacamos su nodo raiz.
            Document documento = lector.read(dirArchivo);
            nodoPartida = documento.getRootElement();
        } catch (DocumentException ex) {
            throw new IOException("ERROR AL TRABAJAR CON EL ARCHIVO " + dirArchivo + " " + ex.getMessage());
        }
        return nodoPartida;
    }

    public ArrayList<Barco> crearBarcosPorDefault() {
        return manejadorDeElementosDelTablero.crearBarcosPorDefault();
    }

    public int getPuntos() {
        return puntos;
    }

    public Daniador crearDaniador(String nombre, Vector posicion) {
        ConstructorDeDaniador constructorDeDaniador = new ConstructorDeDaniador();
        Disparo unDisparo;
        try {
            unDisparo = constructorDeDaniador.construirDisparo(nombre, posicion);
        } catch (DisparoInvalido error) {
            // error.printStackTrace();
            return null;
        }
        return unDisparo;
    }

    public void colocarDaniador(Daniador unDaniador) {
        if (unDaniador == null)
            return;
        if (unDaniador.costo() <= this.getPuntos()) {
            daniadores.add(unDaniador);
            this.reducirPuntosEn(unDaniador.costo());
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
        return manejadorDeElementosDelTablero.todosLosDestructiblesEstanDestruidos();
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
        manejadorDeElementosDelTablero.moverElementos();
    }

    private void pasoTurnoDisparos() {
        Daniador daniadorAuxiliar;
        int i = 0;
        while (i < daniadores.size()) {
            if ((daniadores.get(i)).debeDaniarEnEsteTurno()) {
                daniadorAuxiliar = daniadores.remove(i);
                daniadorAuxiliar.daniar();
            } else {

                daniadorAuxiliar = daniadores.get(i);
                daniadorAuxiliar.pasarTurno();
                i++;
            }
        }

    }

    public ArrayList<AbstractVistasBarcoFactory> getConstructoresDeVistasPorDefault() {
        ArrayList<AbstractVistasBarcoFactory> constructoresADevolver = new ArrayList<AbstractVistasBarcoFactory>();
        for (int i = 0; i < 2; i++) {
            constructoresADevolver.add(new VistaLanchaFactory());
            constructoresADevolver.add(new VistasDestructorFactory());
        }
        constructoresADevolver.add(new VistasBuqueFactory());
        constructoresADevolver.add(new VistasPortaavionesFactory());
        constructoresADevolver.add(new VistasRompehieloFactory());

        return constructoresADevolver;
    }

}
