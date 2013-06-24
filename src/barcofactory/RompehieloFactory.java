package barcofactory;

import barcos.Barco;
import barcos.Rompehielo;
import barcos.Vector;
import barcos.strategies.MovimientoStrategy;

public class RompehieloFactory implements AbstractBarcoFactory {
	@Override
	public Barco crearBarco(Vector orientacion, MovimientoStrategy movimiento) {
		return new Rompehielo(orientacion, movimiento);
	}
}
