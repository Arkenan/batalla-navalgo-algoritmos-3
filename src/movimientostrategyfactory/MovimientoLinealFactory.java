package movimientostrategyfactory;

import barcos.Vector;
import barcos.strategies.MovimientoLinealStrategy;
import barcos.strategies.MovimientoStrategy;

public class MovimientoLinealFactory implements AbstractMovimientoFactory {
	public MovimientoStrategy crearMovimientoStrategy(Vector direccion) {
		return new MovimientoLinealStrategy(direccion);
	}
}
