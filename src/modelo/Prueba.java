package modelo;

import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;
import fiuba.algo3.titiritero.modelo.ObjetoVivo;

public class Prueba implements ObjetoVivo, ObjetoPosicionable {
	private int x = 10;
	private int y = 2;

	@Override
	public void vivir() {
		x++;
		y++;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

}
