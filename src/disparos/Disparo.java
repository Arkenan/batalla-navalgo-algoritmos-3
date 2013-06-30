package disparos;

import partes.ParteDanioDisparo;
import partes.ParteDanioTotal;
import barcos.Vector;
import excepciones.PosicionInvalida;

public abstract class Disparo implements Daniador {
	protected int costo;
	protected String nombre;

	public int costo() {
		return costo;
	}

	public String obtenerNombre() {
		return nombre;
	}

	public abstract void cambiarCasillerosAfectados(Vector posicion)
			throws PosicionInvalida;

	@Override
	public void pasarTurno() {

	}

	public abstract void afectar(ParteDanioTotal parte);

	public abstract void afectar(ParteDanioDisparo parte);

}