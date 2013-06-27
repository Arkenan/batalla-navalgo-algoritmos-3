package vista;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import partes.Parte;
import fiuba.algo3.titiritero.dibujables.SuperficiePanel;
import fiuba.algo3.titiritero.modelo.ObjetoDibujable;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;

public class VistaParte implements ObjetoDibujable {

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
	}

	@Override
	public void dibujar(SuperficieDeDibujo superficieDeDibujo) {
		Graphics grafico = ((SuperficiePanel) superficieDeDibujo).getBuffer();
		if (parte.estaDestruida()) {
			grafico.drawImage(this.imagenDestruida,
					this.objetoPosicionable.getX(),
					this.objetoPosicionable.getY(), 40, 40, null);
		} else {
			grafico.drawImage(this.imagenSana, this.objetoPosicionable.getX(),
					this.objetoPosicionable.getY(), 40, 40, null);
		}
	}
}