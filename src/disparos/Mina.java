package disparos;

import java.util.ArrayList;
import java.util.LinkedList;

import partes.Parte;
import partes.ParteDanioDisparo;
import partes.ParteDanioTotal;
import barcos.Vector;
import casillero.Casillero;
import escenario.Tablero;
import excepciones.PosicionInvalida;

public abstract class Mina extends Disparo {
	protected int radio;
	protected LinkedList<Casillero> casillerosAfectados;

	public int radio() {
		return radio;
	}

	@Override
	public void cambiarCasillerosAfectados(Vector posicion)
			throws PosicionInvalida {
		Tablero tablero = Tablero.getTablero();
		casillerosAfectados = tablero.casillasAfectadas(posicion, this.radio());
	}

	@Override
	public void daniar() {
		for (int j = 0; j < casillerosAfectados.size(); j++) {
			Casillero casilleroAfectado = casillerosAfectados.get(j);
			ArrayList<Parte> partesAfectadas = casilleroAfectado
					.obtenerPartes();
			for (int i = 0; i < partesAfectadas.size(); i++) {
				(partesAfectadas.get(i)).explosion(this);
			}
		}
	}

	@Override
	public void afectar(ParteDanioTotal parte) {
		parte.recibirDanio(1);
	}

	@Override
	public void afectar(ParteDanioDisparo parte) {
	}

}