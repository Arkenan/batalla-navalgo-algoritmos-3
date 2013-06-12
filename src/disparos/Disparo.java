package disparos;

import partes.Parte;
import partes.ParteDanioDisparo;
import barcos.Vector;

public abstract class Disparo {
	protected int costo;
	protected String nombre;
	protected int potencia;

	public Disparo() {
		potencia = 1;
	}

	public int costo() {
		return costo;
	}

	public String obtenerNombre() {
		return nombre;
	}

	public abstract void cambiarCasillerosAfectados(Vector posicion);

	public abstract boolean debeExplotar();

	public abstract void explotar();

	public void pasarTurno() {

	}

	public abstract void afectar(Parte parte);

	public abstract void afectar(ParteDanioDisparo parte);

	public void afectarNormalmente(Parte parte) {
		parte.disminuirVidaEn(potencia);
	}
}