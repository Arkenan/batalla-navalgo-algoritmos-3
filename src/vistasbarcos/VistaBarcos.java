package vistasbarcos;

import java.awt.Color;

import partes.Parte;
import fiuba.algo3.titiritero.dibujables.Circulo;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

public class VistaBarcos extends Circulo {
	Parte parteBarco;

	public VistaBarcos(int arg0, ObjetoPosicionable arg1) {
		super(arg0, arg1);
		parteBarco = (Parte) arg1;
	}

	@Override
	public Color getColor() {
		if (parteBarco.estaDestruida())
			return Color.RED;
		else
			return super.getColor();
	}
}
