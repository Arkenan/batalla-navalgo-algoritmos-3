package barcofactory;

import barcos.Barco;
import barcos.Vector;
import barcos.strategies.MovimientoStrategy;

public interface AbstractBarcoFactory {
	// cambiar int por la interfaz de estrategia de movimiento cuando esta sea
	// implementada
	public abstract Barco crearBarco(Vector orientacion,
			MovimientoStrategy estrategiaDeMovimiento);
}