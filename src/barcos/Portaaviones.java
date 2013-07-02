package barcos;

import org.dom4j.Element;

import barcos.strategies.MovimientoStrategy;

public class Portaaviones extends NaveConDanioTotal {
	public Portaaviones(Vector orient, MovimientoStrategy estrategia) {
		super(orient, 5, 1, estrategia);
	}

	public Portaaviones(Element nodoBarco, MovimientoStrategy estrategia) {
		super(5, 1, estrategia, nodoBarco);
	}

	@Override
	public Element generarNodo() {
		return super.generarNodo("Portaaviones");

	}
}
