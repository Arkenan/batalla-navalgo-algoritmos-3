package barcos;

import partes.ParteDanioTotal;
import barcos.strategies.MovimientoStrategy;
import constructoresdevistas.AbstractVistasBarcoFactory;

public abstract class NaveConDanioTotal extends Barco {

	public NaveConDanioTotal(Vector orient, int tam, int vida,
			MovimientoStrategy estrategia) {
		super(orient, tam, vida, estrategia);
	}

	@Override
	public void construirPartes() {
		for (int i = 0; i < tamanio; i++) {
			partesDelBarco.add(new ParteDanioTotal(vida));
		}

	}

	@Override
	public abstract AbstractVistasBarcoFactory getVistasFactory();

}
