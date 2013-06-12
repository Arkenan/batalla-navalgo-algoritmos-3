package disparos;

import java.util.ArrayList;
import java.util.LinkedList;

import partes.Parte;
import partes.ParteDanioDisparo;
import barcos.Vector;
import casillero.Casillero;
import escenario.Tablero;

public abstract class Mina extends Disparo {
	protected int radio;
	protected LinkedList<Casillero> casillerosAfectados;

	public int radio() {
		return radio;
	}

	@Override
	public void cambiarCasillerosAfectados(Vector posicion) {
		Tablero tablero = Tablero.getTablero();
		casillerosAfectados = tablero.casillasAfectadas(posicion, this.radio());
	}

	@Override
	public void explotar() {
		for (int j = 0; j < casillerosAfectados.size(); j++) {
			Casillero casilleroAfectado = casillerosAfectados.get(j);
			ArrayList<Parte> partesAfectadas = casilleroAfectado
					.obtenerPartes();
			for (int i = 0; i < partesAfectadas.size(); i++) {
				this.afectar(partesAfectadas.get(i));
			}
		}
	}

	public void afectar(Parte parte) {
		this.afectarNormalmente(parte);
	}

	public void afectar(ParteDanioDisparo parte) {

	}

}