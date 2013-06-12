package partes;

import barcos.Vector;

public class Parte {
	protected int vidaInicial;
	protected int vida;
	protected Vector posicion;

	public Parte(int vidaIni) {
		vidaInicial = vidaIni;
		vida = vidaIni;
		posicion = new Vector(3, 3);
	}

	public boolean estaDestruida() {
		return (vida <= 0);
	}

	public boolean estaDaniada() {
		return (!(vida == vidaInicial));
	}

	public void disminuirVidaEn(int vidaADisminuir) {
		vida -= vidaADisminuir;
	}

	public void cambiarPosicion(Vector nuevaPosicion) {
		posicion.setX(nuevaPosicion.x());
		posicion.setY(nuevaPosicion.y());
	}

	public Vector obtenerPosicion() {
		return posicion;
	}
}
