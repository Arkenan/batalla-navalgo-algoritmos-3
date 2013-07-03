package vistadaniadores;

import observador.Observador;
import fiuba.algo3.titiritero.modelo.ObjetoDibujable;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;

public interface VistaDaniador extends Observador, ObjetoDibujable {
	public abstract void dibujar(SuperficieDeDibujo superficieDeDibujo);

	public abstract String obtenerEstado();
}
