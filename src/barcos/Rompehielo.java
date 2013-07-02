package barcos;

import org.dom4j.Element;

import barcos.strategies.MovimientoStrategy;

public class Rompehielo extends NaveConDanioTotal {

	public Rompehielo(Vector orient, MovimientoStrategy estrategia) {
		super(orient, 3, 2, estrategia);
	}

	public Rompehielo(Element nodoBarco, MovimientoStrategy estrategia) {
		super(3, 2, estrategia, nodoBarco);
	}

	@Override
	public Element generarNodo() {
		return super.generarNodo("Rompehielo");

	}
}
