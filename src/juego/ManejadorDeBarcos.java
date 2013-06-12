package juego;

import java.util.ArrayList;

import barcos.Barco;
import barcos.Buque;
import barcos.Destructor;
import barcos.Lancha;
import barcos.Portaaviones;
import barcos.Rompehielo;
import barcos.Vector;

public class ManejadorDeBarcos {

	private final ArrayList<Barco> barcos;

	public ManejadorDeBarcos() {
		barcos = new ArrayList<Barco>();
		this.crearBarcosConDireccionYSentidoAleatorio();
	}

	public void crearBarcosConDireccionYSentidoAleatorio() {
		Lancha lancha;
		Destructor destructor;
		Buque buque;
		Portaaviones portaaviones;
		Rompehielo rompehielo;

		for (int x = 1; x < 3; x++) {
			lancha = new Lancha(this.crearpOrientacionAleatoria(),
					this.crearpPosicionAleatoria(),
					this.crearDireccionAleatoria());
			barcos.add(lancha);

			destructor = new Destructor(this.crearpOrientacionAleatoria(),
					this.crearpPosicionAleatoria(),
					this.crearDireccionAleatoria());
			barcos.add(destructor);
		}

		portaaviones = new Portaaviones(this.crearpOrientacionAleatoria(),
				this.crearpPosicionAleatoria(), this.crearDireccionAleatoria());
		barcos.add(portaaviones);

		rompehielo = new Rompehielo(this.crearpOrientacionAleatoria(),
				this.crearpPosicionAleatoria(), this.crearDireccionAleatoria());
		barcos.add(rompehielo);

		buque = new Buque(this.crearpOrientacionAleatoria(),
				this.crearpPosicionAleatoria(), this.crearDireccionAleatoria());
		barcos.add(buque);

	}

	private Vector crearpPosicionAleatoria() {
		return new Vector((int) (Math.random() * 5) + 1,
				(int) (Math.random() * 5) + 1);
	}

	private Vector crearpOrientacionAleatoria() {
		int x = (int) (Math.random() * 2);
		return new Vector(x, 1 - x);
	}

	private Vector crearDireccionAleatoria() {
		return new Vector((int) (Math.random() * 2), (int) (Math.random() * 2));
	}

	public int cantidadDeBarcos() {
		return barcos.size();
	}

	public int cantidadDeBarcosDestruidos() {
		int contador = 0;
		for (int x = 0; x < barcos.size(); x++) {
			if ((barcos.get(x)).estaDestruido()) {
				contador++;
			}
		}
		return contador;

	}

	public void colocarBarcos() {

	}

	public void moverBarcos() {
		for (int x = 0; x < barcos.size(); x++) {
			(barcos.get(x)).moverse();
		}

	}

	public ArrayList<Barco> obtenerBarcos() {
		return barcos;
	}

	public boolean todosLosBarcosEstanDestruidos() {
		return (this.cantidadDeBarcosDestruidos()) == (this.cantidadDeBarcos());
	}
}
