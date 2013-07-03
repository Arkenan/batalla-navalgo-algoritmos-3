package vistadaniadores;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import observador.ObjetoObservable;
import observador.Observador;
import disparos.Mina;
import fiuba.algo3.titiritero.dibujables.SuperficiePanel;
import fiuba.algo3.titiritero.modelo.ObjetoDibujable;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;

public class VistaMina extends ObjetoObservable implements ObjetoDibujable,
		Observador {
	protected BufferedImage imagenActual;
	protected BufferedImage imagenMina;
	protected BufferedImage imagenExplosion;
	protected ObjetoPosicionable posicionable;
	protected Mina mina;
	final static String DIRECTORIO_IMAGEN_MINA = "imagenes/mina.png";
	final static String DIRECTORIO_IMAGEN_EXPLOSION = "imagenes/explosion.png";
	protected String estado;

	public VistaMina(Mina unaMina) {
		posicionable = unaMina;
		estado = "mina";
		mina = unaMina;
		try {
			imagenMina = ImageIO.read(new File(DIRECTORIO_IMAGEN_MINA));
			imagenExplosion = ImageIO
					.read(new File(DIRECTORIO_IMAGEN_EXPLOSION));
			imagenActual = imagenMina;
		} catch (IOException error) {
			error.printStackTrace();
		}

	}

	public int obtenerPosicionX() {
		switch (estado) {
		case "mina":
			return mina.getX();
		case "explosion":
			return mina.getX() - mina.radio();
		}
		return 0;
	}

	public int obtenerPosicionY() {
		switch (estado) {
		case "mina":
			return mina.getY();
		case "explosion":
			return mina.getY() - mina.radio();
		}
		return 0;

	}

	private int conversionDeCoordenada(int coordenada) {
		return 40 * (coordenada - 1);
	}

	public int obtenerAnchoAlto() {
		switch (estado) {
		case "mina":
			return 2;
		case "explosion":
			return 2 * (mina.radio()) + 2;
		}
		return 10;
	}

	@Override
	public void dibujar(SuperficieDeDibujo superficieDeDibujo) {
		Graphics grafico = ((SuperficiePanel) superficieDeDibujo).getBuffer();
		grafico.drawImage(this.imagenActual,
				conversionDeCoordenada(this.obtenerPosicionX()),
				conversionDeCoordenada(this.obtenerPosicionY()),
				conversionDeCoordenada(this.obtenerAnchoAlto()),
				conversionDeCoordenada(this.obtenerAnchoAlto()), null);
	}

	@Override
	public void actualizar() {
		estado = "explosion";
		imagenActual = imagenExplosion;
		this.notificar();
	}

	public String obtenerEstado() {
		return estado;
	}
}
