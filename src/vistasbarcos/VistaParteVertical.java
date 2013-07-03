package vistasbarcos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import barcos.Vector;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

public class VistaParteVertical extends VistaParte {
	protected BufferedImage imagenDestruidaArriba;
	protected BufferedImage imagenDestruidaAbajo;
	protected BufferedImage imagenSanaArriba;
	protected BufferedImage imagenSanaAbajo;

	public VistaParteVertical(String dirSana, String dirDestruida,
			ObjetoPosicionable posicionable, Vector direccion, String nombre)
			throws IOException {
		super(posicionable, direccion);
		imagenDestruidaArriba = ImageIO.read(new File(dirDestruida + "arriba"
				+ "/" + nombre));
		imagenDestruidaAbajo = ImageIO.read(new File(dirDestruida + "abajo"
				+ "/" + nombre));
		imagenSanaArriba = ImageIO.read(new File(dirSana + "arriba" + "/"
				+ nombre));
		imagenSanaAbajo = ImageIO.read(new File(dirSana + "abajo" + "/"
				+ nombre));
		imagenActual = this.obtenerImagenCorrespondiente(direccion);
	}

	public BufferedImage obtenerImagenCorrespondiente(Vector direccion) {
		if (parte.estaDestruida()) {
			if (direccion.y() == -1)
				return imagenDestruidaArriba;
			else
				return imagenDestruidaAbajo;
		} else {
			if (direccion.y() == -1)
				return imagenSanaArriba;
			else
				return imagenSanaAbajo;
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
