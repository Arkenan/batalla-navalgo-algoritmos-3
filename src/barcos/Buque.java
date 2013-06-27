package barcos;

import barcos.strategies.MovimientoStrategy;
import constructoresdevistas.AbstractVistasBarcoFactory;
import constructoresdevistas.VistasBuqueFactory;

public class Buque extends NaveConDanioTotal {

	public Buque(Vector orient, MovimientoStrategy estrategia) {
		super(orient, 4, 1, estrategia);
	}

	@Override
	public boolean estaDestruido() {
		return this.obtenerPartes().get(0).estaDestruida();
	}

	@Override
	public AbstractVistasBarcoFactory getVistasFactory() {
		return new VistasBuqueFactory(this.obtenerOrientacion(),
				this.obtenerPartes());
	}

}
