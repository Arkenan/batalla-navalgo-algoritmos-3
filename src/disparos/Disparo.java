package disparos;

import observador.ObjetoObservable;
import partes.ParteDanioDisparo;
import partes.ParteDanioTotal;
import barcos.Vector;
import excepciones.PosicionInvalida;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

public abstract class Disparo extends ObjetoObservable implements Daniador, ObjetoPosicionable {
    protected int costo;
    protected String nombre;
    protected Vector posicion;

    @Override
    public int costo() {
        return costo;
    }

    @Override
    public String obtenerNombre() {
        return nombre;
    }

    public abstract void cambiarCasillerosAfectados(Vector posicion) throws PosicionInvalida;

    @Override
    public void pasarTurno() {

    }

    public abstract void afectar(ParteDanioTotal parte);

    public abstract void afectar(ParteDanioDisparo parte);

}