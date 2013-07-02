package barcos;

import org.dom4j.Element;

import partes.ParteDanioDisparo;
import barcos.strategies.MovimientoStrategy;

public class Destructor extends Barco {

	public Destructor(Vector orient, MovimientoStrategy estrategia) {
		super(orient, 3, 1, estrategia);

	}

	public Destructor(Element nodoBarco, MovimientoStrategy estrategia) {
		super(3, 1, estrategia, nodoBarco);
	}

	@Override
	public void construirPartes() {
		for (int i = 0; i < tamanio; i++) {
			partesDelBarco.add(new ParteDanioDisparo(vida));
		}
	}

	@Override
	public void construirParte(Element nodoParte) {
		partesDelBarco.add(new ParteDanioDisparo(Integer.parseInt(nodoParte
				.attributeValue("vida"))));

	}

	@Override
	public Element generarNodo() {
		return super.generarNodo("Destructor");

	}
}
