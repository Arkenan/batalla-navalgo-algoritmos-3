package barcofactory;

import barcos.Barco;
import barcos.Buque;
import barcos.Vector;
import barcos.strategies.MovimientoStrategy;

public class BuqueFactory implements AbstractBarcoFactory {

	@Override
	public Barco crearBarco(Vector orientacion, MovimientoStrategy movimiento) {
		return new Buque(orientacion, movimiento);
	}
}
