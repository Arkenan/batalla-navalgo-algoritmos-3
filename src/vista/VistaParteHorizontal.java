package vista;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import barcos.Vector;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

public class VistaParteHorizontal extends VistaParte {
	protected BufferedImage imagenDestruidaDerecha;
	protected BufferedImage imagenDestruidaIzquierda;
	protected BufferedImage imagenSanaDerecha;
	protected BufferedImage imagenSanaIzquierda;

	public VistaParteHorizontal(String dirSana, String dirDestruida,
			ObjetoPosicionable posicionable, Vector direccion)
			throws IOException {
		super(posicionable, direccion);
		// imagenDestruidaIzquierda = ImageIO.read(new File(dirDestruida
		// + "izquierda.png"));
		// imagenDestruidaDerecha = ImageIO.read(new File(dirDestruida
		// + "derecha.png"));
		imagenSanaDerecha = ImageIO.read(new File(dirSana + "derecha.png"));
		imagenSanaIzquierda = ImageIO.read(new File(dirSana + "izquierda.png"));
		imagenActual = this.obtenerImagenCorrespondiente(direccion);
	}

	public BufferedImage obtenerImagenCorrespondiente(Vector direccion) {
		if (parte.estaDestruida()) {
			if (direccion.x() == 1)
				return imagenDestruidaDerecha;
			else
				return imagenDestruidaIzquierda;
		} else {
			if (direccion.x() == 1)
				return imagenSanaDerecha;
			else
				return imagenSanaIzquierda;
		}

	}

	@Override
	public void cambiarDireccion(Vector unaDireccion) {
		direccion = unaDireccion;
		imagenActual = this.obtenerImagenCorrespondiente(unaDireccion);

	}

	@Override
	public void actualizar() {
		imagenActual = this.obtenerImagenCorrespondiente(direccion);

	}

}
