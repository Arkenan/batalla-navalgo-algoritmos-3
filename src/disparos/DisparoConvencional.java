package disparos;

import java.util.ArrayList;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import partes.Parte;
import partes.ParteDanioDisparo;
import partes.ParteDanioTotal;
import barcos.Vector;
import casillero.Casillero;
import escenario.Tablero;
import excepciones.PosicionInvalida;

public class DisparoConvencional extends Disparo {
	protected Casillero casilleroAfectado;

	public DisparoConvencional() {
		super();
		costo = 200;
		nombre = "disparoconvencional";
	}

	@Override
	public void cambiarCasillerosAfectados(Vector unaPosicion)
			throws PosicionInvalida {
		Tablero tablero = Tablero.getTablero();
		if (tablero.fueraDeRango(unaPosicion)) {
			throw new PosicionInvalida(
					"El disparo queda fuera de rango. Imposible colocar.");
		}
		casilleroAfectado = tablero.obtenerCasillero(unaPosicion);
		posicion = unaPosicion;
	}

	@Override
	public boolean debeDaniarEnEsteTurno() {
		return true;
	}

	@Override
	public void daniar() {
		ArrayList<Parte> partesAfectadas = casilleroAfectado.obtenerPartes();
		for (int i = 0; i < partesAfectadas.size(); i++) {
			(partesAfectadas.get(i)).explosion(this);
		}
		notificar();
	}

	@Override
	public void afectar(ParteDanioTotal parte) {
		parte.recibirDanio(1);
	}

	@Override
	public void afectar(ParteDanioDisparo parte) {
		parte.recibirDanio(1);
	}

	@Override
	public Element generarNodo() {
		Element nodoADevolver = DocumentHelper
				.createElement("DisparoConvencional");
		return nodoADevolver;
	}

	@Override
	public int getX() {
		return posicion.x();
	}

	@Override
	public int getY() {
		return posicion.y();
	}

}
