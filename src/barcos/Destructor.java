package barcos;

import partes.ParteDanioDisparo;
import barcos.strategies.MovimientoStrategy;
import constructoresdevistas.AbstractVistasBarcoFactory;
import constructoresdevistas.VistasDestructorFactory;

public class Destructor extends Barco {

	public Destructor(Vector orient, MovimientoStrategy estrategia) {
		super(orient, 3, 1, estrategia);

	}

	@Override
	public void construirPartes() {
		for (int i = 0; i < tamanio; i++) {
			partesDelBarco.add(new ParteDanioDisparo(vida));
		}
	}

	@Override
	public boolean estaDaniado() {
		for (int i = 0; i < tamanio; i++) {
			ParteDanioDisparo unaParte = (ParteDanioDisparo) partesDelBarco
					.get(i);
			if (unaParte.estaDestruida()) {
				return true;
			}
		}
		return false;
	}

	public AbstractVistasBarcoFactory getVistasFactory() {
		return new VistasDestructorFactory(this.obtenerOrientacion(),
				this.obtenerPartes());
	}

}
