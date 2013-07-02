package vista;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import observador.Observador;
import partes.Parte;
import barcos.Vector;
import fiuba.algo3.titiritero.dibujables.SuperficiePanel;
import fiuba.algo3.titiritero.modelo.ObjetoDibujable;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;

public class VistaParte implements ObjetoDibujable, Observador {

	protected BufferedImage imagenActual;
	protected final BufferedImage imagenSana;
	protected final BufferedImage imagenDestruida;
	protected Parte parte;
	protected ObjetoPosicionable objetoPosicionable;

	public VistaParte(String dirSana, String dirDestruida,
			ObjetoPosicionable posicionable) throws IOException {
		parte = (Parte) posicionable;
		imagenDestruida = ImageIO.read(new File(dirDestruida));
		imagenSana = ImageIO.read(new File(dirSana));
		objetoPosicionable = posicionable;
		imagenActual = imagenSana;
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

	@Override
	public void actualizar() {
		if (parte.getVida() == 0)
			imagenActual = imagenDestruida;
	}

	public void cambiarDireccion(Vector direccion) {
	}
}