package barcos;

import barcos.strategies.MovimientoStrategy;
import constructoresdevistas.AbstractVistasBarcoFactory;
import constructoresdevistas.VistasRompehieloFactory;

public class Rompehielo extends NaveConDanioTotal {

	public Rompehielo(Vector orient, MovimientoStrategy estrategia) {
		super(orient, 3, 2, estrategia);
	}

	public AbstractVistasBarcoFactory getVistasFactory() {
		return new VistasRompehieloFactory(this.obtenerOrientacion(),
				this.obtenerPartes());
	}
}
