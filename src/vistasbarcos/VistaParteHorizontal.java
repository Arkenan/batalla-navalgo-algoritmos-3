package vistasbarcos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import partes.Parte;
import barcos.Vector;

public class VistaParteHorizontal extends VistaParte {
	protected BufferedImage imagenDestruidaDerecha;
	protected BufferedImage imagenDestruidaIzquierda;
	protected BufferedImage imagenSanaDerecha;
	protected BufferedImage imagenSanaIzquierda;

	public VistaParteHorizontal(String dirSana, String dirDestruida,
			Parte parte, Vector direccion, String nombre) throws IOException {
		super(parte, direccion);
		imagenDestruidaIzquierda = ImageIO.read(new File(dirDestruida
				+ "izquierda" + "/" + nombre));
		imagenDestruidaDerecha = ImageIO.read(new File(dirDestruida + "derecha"
				+ "/" + nombre));
		imagenSanaDerecha = ImageIO.read(new File(dirSana + "derecha" + "/"
				+ nombre));
		imagenSanaIzquierda = ImageIO.read(new File(dirSana + "izquierda" + "/"
				+ nombre));
		imagenActual = this.obtenerImagenCorrespondiente(direccion);
	}

	@Override
	public BufferedImage obtenerImagenCorrespondiente(Vector direccion) {
		if (parte.estaDestruida()) {
			if (direccion.x() == -1)
				return imagenDestruidaIzquierda;
			else
				return imagenDestruidaDerecha;
		} else {
			if (direccion.x() == -1)
				return imagenSanaIzquierda;
			else
				return imagenSanaDerecha;
		}

	}

	@Override
	public void cambiarDireccion(Vector unaDireccion) {
		direccion = unaDireccion;
		imagenActual = this.obtenerImagenCorrespondiente(unaDireccion);

	}
}
