package partida;

import java.util.ArrayList;

import movimientostrategyfactory.AbstractMovimientoFactory;
import movimientostrategyfactory.MovimientoLinealFactory;
import barcofactory.AbstractBarcoFactory;
import barcofactory.BuqueFactory;
import barcofactory.DestructorFactory;
import barcofactory.LanchaFactory;
import barcofactory.PortaavionesFactory;
import barcofactory.RompehieloFactory;
import barcos.Barco;
import barcos.Destructible;
import barcos.Movible;
import barcos.Vector;
import barcos.strategies.MovimientoStrategy;

public class ManejadorDeBarcos {

	protected ArrayList<Movible> elementosAMoverPorTurno;
	protected ArrayList<Destructible> elementosADestruirParaGanar;
	final ArrayList<AbstractMovimientoFactory> constructoresDeMovimientosPosibles;

	public ManejadorDeBarcos() {
		elementosAMoverPorTurno = new ArrayList<Movible>();
		elementosADestruirParaGanar = new ArrayList<Destructible>();
		constructoresDeMovimientosPosibles = new ArrayList<AbstractMovimientoFactory>();
		constructoresDeMovimientosPosibles.add(new MovimientoLinealFactory());
	}

	public void crearBarcosPorDefault() {
		ArrayList<AbstractBarcoFactory> factorysDeBarco = new ArrayList<AbstractBarcoFactory>();
		for (int i = 0; i < 2; i++) {
			factorysDeBarco.add(new LanchaFactory());
			factorysDeBarco.add(new DestructorFactory());
		}
		factorysDeBarco.add(new BuqueFactory());
		factorysDeBarco.add(new PortaavionesFactory());
		factorysDeBarco.add(new RompehieloFactory());
		crearBarcosConParametrosAleatoriosDeLosConstructores(factorysDeBarco);
	}

	public void crearBarcosConParametrosAleatoriosDeLosConstructores(
			ArrayList<AbstractBarcoFactory> factorysDeBarco) {
		AbstractBarcoFactory factoryDeBarcoAuxiliar;
		Barco barcoAuxiliar;
		for (int i = 0; i < factorysDeBarco.size(); i++) {
			factoryDeBarcoAuxiliar = factorysDeBarco.get(i);
			barcoAuxiliar = factoryDeBarcoAuxiliar.crearBarco(
					this.crearOrientacionAleatoria(),
					this.crearMovimientoAleatorio());
			elementosAMoverPorTurno.add(barcoAuxiliar);
			elementosADestruirParaGanar.add(barcoAuxiliar);
			barcoAuxiliar.colocarEnTablero(this.crearPosicionAleatoria());
		}
	}

	public int cantidadDeElementosADestruirParaGanar() {
		return elementosADestruirParaGanar.size();
	}

	public int cantidadDeElementosDestruidos() {
		int contador = 0;
		for (int x = 0; x < elementosADestruirParaGanar.size(); x++) {
			if ((elementosADestruirParaGanar.get(x)).estaDestruido()) {
				contador++;
			}
		}
		return contador;

	}

	public void moverElementos() {
		for (int x = 0; x < elementosAMoverPorTurno.size(); x++) {
			(elementosAMoverPorTurno.get(x)).moverse();
		}

	}

	public ArrayList<Destructible> obtenerDestructibles() {
		return elementosADestruirParaGanar;
	}

	public boolean todosLosDestructiblesEstanDestruidos() {
		return (this.cantidadDeElementosDestruidos()) == (this
				.cantidadDeElementosADestruirParaGanar());
	}

	// ----------------- Metodos Privados --------------------------------

	private Vector crearPosicionAleatoria() {
		return new Vector((int) (Math.random() * 4) + 1,
				(int) (Math.random() * 4) + 1);
	}

	private Vector crearOrientacionAleatoria() {
		int x = (int) (Math.random() * 2);
		return new Vector(x, 1 - x);
	}

	private MovimientoStrategy crearMovimientoAleatorio() {
		int x = (int) (Math.random() * 2);
		Vector direccion;
		if (x == 0)
			direccion = new Vector(x, 1);
		else {
			direccion = new Vector(x, (int) (Math.random() * 2));
		}
		int posicionAleatoria = (int) (Math.random() * (constructoresDeMovimientosPosibles
				.size()));
		AbstractMovimientoFactory constructorAleatorio = constructoresDeMovimientosPosibles
				.get(posicionAleatoria);
		return constructorAleatorio.crearMovimientoStrategy(direccion);
	}
}
