package barcos;

import barcos.strategies.MovimientoStrategy;
import constructoresdevistas.AbstractVistasBarcoFactory;
import constructoresdevistas.VistasPortaavionesFactory;

public class Portaaviones extends NaveConDanioTotal {
	public Portaaviones(Vector orient, MovimientoStrategy estrategia) {
		super(orient, 5, 1, estrategia);
	}

	public AbstractVistasBarcoFactory getVistasFactory() {
		return new VistasPortaavionesFactory(this.obtenerOrientacion(),
				this.obtenerPartes());
	}
}
