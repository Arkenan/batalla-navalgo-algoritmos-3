package barcofactory;

import barcos.Barco;
import barcos.Destructor;
import barcos.Vector;
import barcos.strategies.MovimientoStrategy;

public class DestructorFactory implements AbstractBarcoFactory {
	@Override
	public Barco crearBarco(Vector orientacion, MovimientoStrategy movimiento) {
		return new Destructor(orientacion, movimiento);
	}
}
