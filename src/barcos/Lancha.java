package barcos;

import barcos.strategies.MovimientoStrategy;
import constructoresdevistas.AbstractVistasBarcoFactory;
import constructoresdevistas.VistaLanchaFactory;

public class Lancha extends NaveConDanioTotal {

	public Lancha(Vector orient, MovimientoStrategy estrategia) {
		super(orient, 2, 1, estrategia);
	}

	public AbstractVistasBarcoFactory getVistasFactory() {
		return new VistaLanchaFactory(this.obtenerOrientacion(),
				this.obtenerPartes());
	}
}