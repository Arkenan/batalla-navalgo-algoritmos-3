package vista;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import observador.Observador;
import partes.Parte;
import barcos.Vector;
import fiuba.algo3.titiritero.dibujables.SuperficiePanel;
import fiuba.algo3.titiritero.modelo.ObjetoDibujable;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;

public abstract class VistaParte implements ObjetoDibujable, Observador {

	protected BufferedImage imagenActual;
	protected Parte parte;
	protected ObjetoPosicionable objetoPosicionable;
	protected Vector direccion;

	public VistaParte(ObjetoPosicionable posicionable, Vector unaDireccion)
			throws IOException {
		parte = (Parte) posicionable;
		objetoPosicionable = posicionable;
		direccion = unaDireccion;

	}

	private int conversionDeCoordenada(int coordenada) {
		return 40 * (coordenada - 1);
	}

	@Override
	public void dibujar(SuperficieDeDibujo superficieDeDibujo) {
		Graphics grafico = ((SuperficiePanel) superficieDeDibujo).getBuffer();

		grafico.drawImage(this.imagenActual,
				conversionDeCoordenada(this.objetoPosicionable.getX()),
				conversionDeCoordenada(this.objetoPosicionable.getY()), 40, 40,
				null);
	}

	public abstract void cambiarDireccion(Vector direccion);
}