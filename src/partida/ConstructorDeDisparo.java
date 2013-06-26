package partida;

import java.util.ArrayList;

import barcos.Vector;
import disparos.Disparo;
import disparos.DisparoConvencional;
import disparos.MinaContacto;
import disparos.MinaDobleRadio;
import disparos.MinaRadio;
import excepciones.DisparoInvalido;
import excepciones.PosicionInvalida;

public class ConstructorDeDisparo {
	ArrayList<Disparo> disparosPosibles;

	public ConstructorDeDisparo() {
		disparosPosibles = new ArrayList<Disparo>();
		disparosPosibles.add(new MinaContacto());
		disparosPosibles.add(new MinaDobleRadio());
		disparosPosibles.add(new MinaRadio());
		disparosPosibles.add(new DisparoConvencional());
	}

	public Disparo construirDisparo(String nombre, Vector posicion)
			throws DisparoInvalido {
		String nombrePosible;
		Disparo disparoADevolver;

		for (int i = 0; i < disparosPosibles.size(); i++) {

			nombrePosible = (disparosPosibles.get(i)).obtenerNombre();

			if (nombre.equals(nombrePosible)) {

				disparoADevolver = disparosPosibles.get(i);

				try {
					disparoADevolver.cambiarCasillerosAfectados(posicion);
				} catch (PosicionInvalida error) {
					throw new DisparoInvalido();
				}

				return disparoADevolver;
			}

		}
		throw new DisparoInvalido();
	}
}
