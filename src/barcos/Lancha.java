package barcos;

import org.dom4j.Element;

import barcos.strategies.MovimientoStrategy;

public class Lancha extends NaveConDanioTotal {

	public Lancha(Vector orient, MovimientoStrategy estrategia) {
		super(orient, 2, 1, estrategia);
	}

	public Lancha(Element nodoBarco, MovimientoStrategy estrategia) {
		super(2, 1, estrategia, nodoBarco);
	}

	@Override
	public Element generarNodo() {
		return super.generarNodo("Lancha");

	}
}