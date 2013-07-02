package barcos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import observador.ObjetoObservable;
import observador.Observador;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import partes.Parte;
import barcos.strategies.MovimientoStrategy;
import escenario.Tablero;
import excepciones.PosicionInvalida;

/*
 * Direccion Cara 1 horizontal 0 vertical
 */

public abstract class Barco extends ObjetoObservable implements Movible,
		Destructible, Observador {
	protected int tamanio, vida;
	protected Vector orientacion, posicionCabeza;
	protected ArrayList<Parte> partesDelBarco;
	protected MovimientoStrategy estrategia;

	/*
	 * los datos vendrian a ser cantidad de partes, la vid y orientación de cara
	 * (hacia donde apunta).
	 */
	public Barco(Vector orient, int tam, int vida, MovimientoStrategy estrategia) {
		super();
		tamanio = tam;
		orientacion = orient;
		partesDelBarco = new ArrayList<Parte>();
		this.vida = vida;
		this.construirPartes();
		this.estrategia = estrategia;
		estrategia.setBarco(this);
		estrategia.agregarObservador(this);
	}

	public Barco(int tam, int vida, MovimientoStrategy estrategia,
			Element nodoBarco) {
		tamanio = tam;
		orientacion = new Vector(nodoBarco.attributeValue("orientacion"));
		partesDelBarco = new ArrayList<Parte>();
		this.vida = vida;
		this.estrategia = estrategia;
		estrategia.setBarco(this);
		// ahora lo que tengo que hacer es buscar cada parte.
		// cuando tenga cada parte la mando a construir con su nodo.
		List<Element> listaNodosPartes = nodoBarco.elements("Parte");
		if (listaNodosPartes.size() != tam) {
			throw new Error("Cantidad de partes: " + listaNodosPartes.size()
					+ " no corresponde al barco."
					+ ((List<Element>) (nodoBarco.elements())).get(0).getName());
		}
		Iterator<Element> it = listaNodosPartes.iterator();
		while (it.hasNext()) {
			Element nodoParte = it.next();
			construirParte(nodoParte);
		}
	}

	@Override
	public void actualizar() {
		if (this.estaDestruido())
			this.notificar();
	}

	public void colocarEnTablero(Vector posicion) throws PosicionInvalida {
		if (this.verificarPosicion(posicion) == false) {
			throw new PosicionInvalida("HAY UNA POSICION INVALIDA");
		}
		this.posicionCabeza = posicion;
		this.colocarPartes();
	}

	/*
	 * // METODOS PUBLICOS // para el principio de cada turno. public void
	 * moverse() { this.cambiarPosicion(); this.moverPartes(); }
	 */

	/* verifica parte por parte que no este fuera de rango. */
	public boolean verificarPosicion(Vector pos) {
		Tablero tablero = Tablero.getTablero();
		for (int i = 0; i < tamanio; i++) {
			Vector vector = pos.sumar(orientacion.porEscalar(i));
			if (tablero.fueraDeRango(vector) == true) {
				return false;
			}
		}
		return true;
	}

	/*
	 * // METODOS PUBLICOS // para el principio de cada turno. public void
	 * moverse() { this.cambiarPosicion(); this.moverPartes(); }
	 */
	abstract void construirPartes();

	abstract void construirParte(Element nodoParte);

	public int obtenerTamanio() {
		return tamanio;
	}

	public Vector obtenerOrientacion() {
		return orientacion;
	}

	public ArrayList<Parte> obtenerPartes() {
		return partesDelBarco;
	}

	public void metodoPrueba() {

	}

	public MovimientoStrategy obtenerEstrategiaDeMovimiento() {
		return estrategia;
	}

	// METODOS PRIVADOS

	public Vector obtenerDireccionMovimiento() {
		return estrategia.obtenerDireccionMovimiento();
	}

	public Vector obtenerPosicion() {
		return posicionCabeza;
	}

	@Override
	public boolean estaDestruido() {
		for (int i = 0; i < tamanio; i++) {
			Parte parte = partesDelBarco.get(i);
			if (parte.estaDestruida() == false) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void moverse() {
		Vector nuevaPosicion = estrategia.ejecutar();
		sacarPartes();
		posicionCabeza = nuevaPosicion;
		colocarPartes();
	}

	/* quita todas las partes de un barco del tablero */
	private void sacarPartes() {
		Tablero tablero = Tablero.getTablero();
		for (int i = 0; i < tamanio; i++) {
			Vector posParte = new Vector(posicionCabeza.sumar(orientacion
					.porEscalar(i)));
			tablero.sacarElemento(posParte, partesDelBarco.get(i));
		}
	}

	/*
	 * Calcula la posicion de cada parte en base a la posicion de la cabeza.
	 */

	public Element generarNodo(String nombreBarco) {
		Element nodoADevolver = DocumentHelper.createElement(nombreBarco);
		nodoADevolver.addAttribute("orientacion", orientacion.toString());
		nodoADevolver.addAttribute("posicion", posicionCabeza.toString());
		nodoADevolver.addAttribute("completo", "no");
		// ahora las partes cada una individualmente.
		Iterator<Parte> it = partesDelBarco.iterator();
		while (it.hasNext()) {
			Parte parteActual = it.next();
			nodoADevolver.add(parteActual.generarNodo());

		}
		return nodoADevolver;

	}

	/*
	 * Calcula la posicion de cada parte en base a la posicion de la cabeza.
	 */
	private void colocarPartes() {
		Tablero tablero = Tablero.getTablero();
		for (int i = 0; i < tamanio; i++) {
			Vector posParte = new Vector(posicionCabeza.sumar(orientacion
					.porEscalar(i)));
			partesDelBarco.get(i).cambiarPosicion(posParte);
			tablero.colocarElemento(posParte, partesDelBarco.get(i));
		}
	}
}