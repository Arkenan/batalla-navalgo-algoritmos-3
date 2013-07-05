package vistadaniadores;

import observador.Observable;
import observador.Observador;
import fiuba.algo3.titiritero.modelo.ObjetoDibujable;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;

public interface VistaDaniador extends Observador, Observable, ObjetoDibujable {
    @Override
    public abstract void dibujar(SuperficieDeDibujo superficieDeDibujo);

    public abstract String obtenerEstado();
}
