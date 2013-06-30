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
		System.out.println("Nombre de la Partida: "
				+ nodoPartida.attributeValue("nombre"));

		puntos = Integer.parseInt(nodoPartida.attributeValue("puntos"));

		ConstructorDeDaniador ConstructorDeDaniador = new ConstructorDeDaniador();
		daniadores = ConstructorDeDaniador.construirDisparos(nodoPartida
				.element("Disparos"));

		manejadorDeElementosDelTablero = new ManejadorDeElementosDelTablero();

	}

	public ArrayList<Barco> crearBarcos(Element nodoPartida) {
		return manejadorDeElementosDelTablero.crearBarcos(nodoPartida
				.element("Barcos"));
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
		return nodoPartida;
	}

	public void guardar(String archivo) throws IOException {
		Document doc = DocumentHelper.createDocument();
		doc.add(this.obtenerNodoPartida());
		FileWriter writer = new FileWriter(archivo);
		doc.write(writer);
		writer.close();
	}

	public static Element obtenerNodoPartida(String dirArchivo)
			throws IOException {
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
		return manejadorDeElementosDelTablero.crearBarcosPorDefault();
	}

	public int getPuntos() {
		return puntos;
	}

	public void colocarDaniador(String nombre, Vector posicion) {
		ConstructorDeDaniador constructorDeDaniador = new ConstructorDeDaniador();
		Disparo unDisparo;
		try {
			unDisparo = constructorDeDaniador
					.construirDisparo(nombre, posicion);
		} catch (DisparoInvalido error) {
			return;
		}
		if (unDisparo.costo() <= this.getPuntos()) {
			daniadores.add(unDisparo);
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
		return manejadorDeElementosDelTablero
				.todosLosDestructiblesEstanDestruidos();
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

}
