package vista;

import java.awt.Color;

import fiuba.algo3.titiritero.dibujables.Circulo;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

public class VistaPrueba extends Circulo {

	public VistaPrueba(int arg0, ObjetoPosicionable arg1) {
		super(arg0, arg1);
	}

	@Override
	public Color getColor() {
		return Color.RED;
	}

}
