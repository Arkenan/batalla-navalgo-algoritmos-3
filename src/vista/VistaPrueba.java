package vista;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fiuba.algo3.titiritero.dibujables.SuperficiePanel;
import fiuba.algo3.titiritero.modelo.ObjetoDibujable;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;

public class VistaPrueba implements ObjetoDibujable {

	protected final BufferedImage imagen;
	protected ObjetoPosicionable objetoPosicionable;

	public VistaPrueba(String dir, ObjetoPosicionable posicionable)
			throws IOException {
		imagen = ImageIO.read(new File(dir));
		objetoPosicionable = posicionable;

	}

	@Override
	public void dibujar(SuperficieDeDibujo superficieDeDibujo) {
		Graphics grafico = ((SuperficiePanel) superficieDeDibujo).getBuffer();
		grafico.drawImage(this.imagen, this.objetoPosicionable.getX(),
				this.objetoPosicionable.getY(), 40, 40, null);
	}
}
