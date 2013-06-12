package disparos;

import java.util.ArrayList;

import partes.Parte;
import barcos.Vector;
import casillero.Casillero;
import escenario.Tablero;

public class DisparoConvencional extends Disparo {
	protected Casillero casilleroAfectado;

	public DisparoConvencional() {
		costo = 200;
		nombre = "disparoconvencional";
	}

	@Override
	public void cambiarCasillerosAfectados(Vector posicion) {
		Tablero tablero = Tablero.getTablero();
		casilleroAfectado = tablero.obtenerCasillero(posicion);
	}

	@Override
	public boolean debeExplotar() {
		return true;
	}

	@Override
	public void explotar() {
		ArrayList<Parte> partesAfectadas = casilleroAfectado.obtenerPartes();
		for (int i = 0; i < partesAfectadas.size(); i++) {
			(partesAfectadas.get(i)).explosion(this);
		}
	}

}
