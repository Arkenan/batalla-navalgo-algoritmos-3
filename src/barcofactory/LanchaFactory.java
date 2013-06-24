package barcofactory;

import barcos.Barco;
import barcos.Lancha;
import barcos.Vector;
import barcos.strategies.MovimientoStrategy;

public class LanchaFactory implements AbstractBarcoFactory {

	@Override
	public Barco crearBarco(Vector orientacion, MovimientoStrategy movimiento) {
		return new Lancha(orientacion, movimiento);
	}
}
