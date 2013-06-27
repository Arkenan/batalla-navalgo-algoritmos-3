package partes;

import org.dom4j.Element;

import barcos.Vector;
import disparos.Disparo;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

public abstract class Parte implements ObjetoPosicionable {
    protected int vida;
    protected Vector posicion;

    public Parte(int vidaIni) {
        vida = vidaIni;
    }

    @Override
    public int getX() {
        return 40 * ((posicion.x()) - 1);
    }

    @Override
    public int getY() {
        return 40 * ((posicion.y()) - 1);
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
    }

    public abstract Element generarNodo();
}
