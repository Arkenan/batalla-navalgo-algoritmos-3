package partes;

import barcos.Vector;
import disparos.Disparo;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

public abstract class Parte implements ObjetoPosicionable {
	protected int vidaInicial;
	protected int vida;
	protected Vector posicion;

	public Parte(int vidaIni) {
		vidaInicial = vidaIni;
		vida = vidaIni;
	}

	@Override
	public int getX() {
		return 20 * (posicion.x());
	}

	@Override
	public int getY() {
		return 20 * (posicion.y());
	}

	public boolean estaDestruida() {
		return (vida <= 0);
	}

	public boolean estaDaniada() {
		return (!(vida == vidaInicial));
	}

	public abstract void explosion(Disparo disparo);

	public void cambiarPosicion(Vector nuevaPosicion) {
		if (posicion == null) {
			posicion = new Vector(nuevaPosicion);
		} else {
			posicion.setX(nuevaPosicion.x());
			posicion.setY(nuevaPosicion.y());
		}
	}

	public Vector obtenerPosicion() {
		return posicion;
	}

	public void recibirDanio(int danio) {
		vida -= danio;
	}
}
