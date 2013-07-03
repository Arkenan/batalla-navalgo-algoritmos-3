package vistasbarcos;

import java.util.ArrayList;

import observador.ObjetoObservable;
import observador.Observador;
import barcos.Barco;

public class VistaBarco extends ObjetoObservable implements Observador {
	ArrayList<VistaParte> vistasPartes;
	Barco barco;

	public VistaBarco(ArrayList<VistaParte> partes, Barco unBarco) {
		vistasPartes = partes;
		barco = unBarco;
		barco.agregarObservador(this);
		barco.obtenerEstrategiaDeMovimiento().agregarObservador(this);
	}

	public ArrayList<VistaParte> obtenerVistasPartes() {
		return vistasPartes;

	}

	public boolean estaDestruido() {
		return barco.estaDestruido();
	}

	@Override
	public void actualizar() {
		if (barco.estaDestruido()) {
			this.notificar();
		} else {
			for (int i = 0; i < vistasPartes.size(); i++) {
				vistasPartes.get(i).cambiarDireccion(
						barco.obtenerDireccionMovimiento());
			}
		}

	}

}
