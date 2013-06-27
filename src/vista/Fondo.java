package vista;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fiuba.algo3.titiritero.dibujables.SuperficiePanel;
import fiuba.algo3.titiritero.modelo.ObjetoDibujable;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;

public class Fondo implements ObjetoDibujable {
	BufferedImage imagenDeFondo;
	int xPanel, yPanel, anchoDelPanel, altoDelPanel;

	public Fondo(int x, int y, int ancho, int alto, String directorioDeLaImagen) {
		xPanel = x;
		yPanel = y;
		anchoDelPanel = ancho;
		altoDelPanel = alto;
		try {
			imagenDeFondo = ImageIO.read(new File(directorioDeLaImagen));
		} catch (IOException error) {

		}
	}

	@Override
	public void dibujar(SuperficieDeDibujo superficieDeDibujo) {

		Graphics grafico = ((SuperficiePanel) superficieDeDibujo).getBuffer();
		grafico.drawImage(this.imagenDeFondo, xPanel, yPanel, anchoDelPanel,
				altoDelPanel, null);

	}

}
