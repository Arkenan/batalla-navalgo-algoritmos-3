package vistadaniadores;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import observador.ObjetoObservable;
import disparos.DisparoConvencional;
import fiuba.algo3.titiritero.dibujables.SuperficiePanel;
import fiuba.algo3.titiritero.modelo.ObjetoDibujable;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;

public class VistaDisparoConvencional extends ObjetoObservable implements
		VistaDaniador, ObjetoDibujable {
	protected BufferedImage imagenActual;
	protected DisparoConvencional disparo;
	final static String DIRECTORIO_IMAGEN_DISPARO = "imagenes/disparo.png";
	protected String estado;

	public VistaDisparoConvencional(DisparoConvencional unDisparo) {
		disparo = unDisparo;
		try {
			imagenActual = ImageIO.read(new File(DIRECTORIO_IMAGEN_DISPARO));
		} catch (IOException e) {
			e.printStackTrace();
		}
		estado = "disparo";
	}

	private int conversionDeCoordenada(int coordenada) {
		return 40 * (coordenada - 1);
	}

	@Override
	public void dibujar(SuperficieDeDibujo superficieDeDibujo) {
		Graphics grafico = ((SuperficiePanel) superficieDeDibujo).getBuffer();
		grafico.drawImage(this.imagenActual,
				conversionDeCoordenada(disparo.getX()),
				conversionDeCoordenada(disparo.getY()), 40, 40, null);
	}

	@Override
	public void actualizar() {
		estado = "gastado";
		System.out.println("fue notificado");
		notificar();
	}

	@Override
	public String obtenerEstado() {
		return estado;
	}
}
