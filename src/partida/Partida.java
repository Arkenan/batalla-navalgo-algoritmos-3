package partida;

import java.util.ArrayList;

import barcos.Destructible;
import barcos.Vector;
import disparos.Disparo;
import excepciones.DisparoInvalido;

public class Partida {
	private int puntos;
	final int decrecimientoDePuntosPorPasoDeTurno = 10;
	ArrayList<Disparo> disparos;
	ManejadorDeBarcos manejadorDeBarcos;

	public Partida() {
		puntos = 10000;
		disparos = new ArrayList<Disparo>();
		manejadorDeBarcos = new ManejadorDeBarcos();
		manejadorDeBarcos.crearBarcosPorDefault();
	}

	public int getPuntos() {
		return puntos;
	}

	// Paso de turno sin realizar acciones.
	public void pasarTurno() {
		this.reducirPuntosEn(decrecimientoDePuntosPorPasoDeTurno);
		this.realizarCambiosPasoTurno();
	}

	// Nos devuelve un booleano que informa si los puntos son suficientes o no
	// para seguir jugando.
	public boolean faltanPuntosParaSeguir() {
		return (puntos < decrecimientoDePuntosPorPasoDeTurno);
	}

	// Este metodo se ocupa del movimiento de barcos y explosion de minas.

	public boolean gano() {
		return manejadorDeBarcos.todosLosDestructiblesEstanDestruidos();
	}

	public boolean perdio() {
		if (!gano()) {
			return this.faltanPuntosParaSeguir();
		}
		return false;
	}

	public void colocarDisparo(String nombre, Vector posicion)
			throws DisparoInvalido {
		ConstructorDeDisparo constructorDeDisparo = new ConstructorDeDisparo();
		Disparo unDisparo = constructorDeDisparo.construirDisparo(nombre,
				posicion);
		disparos.add(unDisparo);
		this.reducirPuntosEn(unDisparo.costo());
		this.realizarCambiosPasoTurno();
	}

	public ArrayList<Destructible> obtenerDestructibles() {
		return manejadorDeBarcos.obtenerDestructibles();
	}

	// ------------------- Metodos Privados -----------------------------

	private void reducirPuntosEn(int puntosADisminuir) {
		puntos -= puntosADisminuir;
	}

	private void realizarCambiosPasoTurno() {
		this.pasoTurnoDisparos();
		manejadorDeBarcos.moverElementos();
	}

	private void pasoTurnoDisparos() {
		Disparo disparoAuxiliar;
		int i = 0;
		while (i < disparos.size()) {
			if ((disparos.get(i)).debeExplotar()) {
				disparoAuxiliar = disparos.remove(i);
				disparoAuxiliar.explotar();
			} else {

				disparoAuxiliar = disparos.get(i);
				disparoAuxiliar.pasarTurno();
				i++;
			}
		}

	}

}
