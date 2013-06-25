package partes;

import barcos.Vector;
import disparos.Disparo;

public abstract class Parte {
	protected int vidaInicial;
	protected int vida;
	protected Vector posicion;

	public Parte(int vidaIni) {
		vidaInicial = vidaIni;
		vida = vidaIni;
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
