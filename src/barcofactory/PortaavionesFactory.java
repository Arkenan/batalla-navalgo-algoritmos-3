package barcofactory;

import barcos.Barco;
import barcos.Portaaviones;
import barcos.Vector;
import barcos.strategies.MovimientoStrategy;

public class PortaavionesFactory implements AbstractBarcoFactory {
	@Override
	public Barco crearBarco(Vector orientacion, MovimientoStrategy movimiento) {
		return new Portaaviones(orientacion, movimiento);
	}

}
