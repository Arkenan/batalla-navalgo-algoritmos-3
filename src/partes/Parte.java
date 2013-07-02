package partes;

import observador.ObjetoObservable;

import org.dom4j.Element;

import barcos.Vector;
import disparos.Disparo;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

public abstract class Parte extends ObjetoObservable implements
		ObjetoPosicionable {
	protected int vida;
	protected Vector posicion;

	public Parte(int vidaIni) {
		super();
		vida = vidaIni;
	}

	@Override
	public int getX() {
		return posicion.x();
	}

	@Override
	public int getY() {
		return posicion.y();
	}

	public boolean estaDestruida() {
		return (vida <= 0);
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
		notificar();
	}

	public abstract Element generarNodo();

	public int getVida() {
		return vida;
	}
}
