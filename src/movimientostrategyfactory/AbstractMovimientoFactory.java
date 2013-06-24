package movimientostrategyfactory;

import barcos.Vector;
import barcos.strategies.MovimientoStrategy;

public interface AbstractMovimientoFactory {
	public abstract MovimientoStrategy crearMovimientoStrategy(Vector direccion);
}
