package barcos.strategies;

import observador.ObjetoObservable;
import barcos.Barco;
import barcos.Vector;

public class MovimientoLinealStrategy extends ObjetoObservable implements
		MovimientoStrategy {

	private Barco barco;
	private final Vector direccionActual;

	public MovimientoLinealStrategy(Vector direccionInicial) {
		super();
		direccionActual = direccionInicial;
	}

	@Override
	public void setBarco(Barco barco) {
		this.barco = barco;

	}

	@Override
	public Vector ejecutar() {
		Vector nuevaPosicion = barco.obtenerPosicion().sumar(direccionActual);
		if (barco.verificarPosicion(nuevaPosicion)) {
			return nuevaPosicion;
		} else {
			direccionActual.asignar(direccionActual.porEscalar(-1));
			notificar();
			return this.ejecutar();
		}

	}

	@Override
	public Vector obtenerDireccionMovimiento() {
		return direccionActual;
	}

}
