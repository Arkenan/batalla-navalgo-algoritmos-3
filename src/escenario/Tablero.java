package escenario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import partes.Parte;
import barcos.Barco;
import barcos.Vector;
import casillero.Casillero;
import excepciones.PosicionInvalida;

/* patron de diseño singleton */
public final class Tablero {

	private final Map<String, Casillero> casilleros;
	private final int ancho;
	private final int alto;
	protected ArrayList<Barco> barcos;

	private Tablero(int ancho, int alto) {
		casilleros = new HashMap<String, Casillero>();
		this.ancho = ancho;
		this.alto = alto;
		barcos = new ArrayList<Barco>();
	}

	/* constructor privado para evitar copias extra. */
	private static Tablero tableroUnico = new Tablero(10, 10);

	/* obtencion de la unica instancia */
	public static Tablero getTablero() {
		return tableroUnico;
	}

	/* Utilizado para generar una clave del diccionario a partir de dos numeros */
	private String obtenerClave(int x, int y) {
		String clave = (Integer.toString(x) + Integer.toString(y));
		return clave;
	}

	/*
	 * Utilizado para encontrar un casillero a partir de dos enteros, que pueden
	 * venir de un vector en otro obtenerCasillero(Vector)
	 */
	public Casillero obtenerCasillero(int x, int y) {
		String clave = obtenerClave(x, y);
		if (!casilleros.containsKey(clave)) {
			casilleros.put(clave, new Casillero());
		}
		return casilleros.get(clave);
	}

	public Casillero obtenerCasillero(Vector posicion) {
		return (obtenerCasillero(posicion.x(), posicion.y()));
	}

	/*
	 * Retorna verdadero si una posicion descrita por un vector no pertenece al
	 * tablero.
	 */
	public boolean fueraDeRango(Vector posicion) {
		boolean fuera = false;
		if (posicion.x() > ancho || posicion.x() < 1 || posicion.y() < 1
				|| posicion.y() > alto) {
			fuera = true;
		}
		return fuera;
	}

	/* Coloca una parte de un barco. */
	public void colocarElemento(Vector posicion, Parte elemento) {
		obtenerCasillero(posicion).colocarElemento(elemento);
	}

	/* Quita una parte de un barco. */
	public void sacarElemento(Vector posicion, Parte elemento) {
		obtenerCasillero(posicion).quitarElemento(elemento);
	}

	/*
	 * Metodo utilizado por las minas para obtener las casillas que seran
	 * afectadas por su explosion
	 */
	public LinkedList<Casillero> casillasAfectadas(Vector posicion, int radio)
			throws PosicionInvalida {
		if (this.fueraDeRango(posicion))
			throw new PosicionInvalida("Hubo una posicion invalida");
		LinkedList<Casillero> lista = new LinkedList<Casillero>();
		for (int columna = posicion.x() - radio; columna <= posicion.x()
				+ radio; columna++) {
			for (int fila = posicion.y() - radio; fila <= posicion.y() + radio; fila++) {
				lista.add(obtenerCasillero(columna, fila));
			}
		}
		return lista;
	}

	public boolean elementoPerteneceAlCasillero(Vector posicion, Parte elemento) {
		return obtenerCasillero(posicion).contiene(elemento);
	}

}