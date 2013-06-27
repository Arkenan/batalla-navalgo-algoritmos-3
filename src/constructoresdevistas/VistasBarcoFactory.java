package constructoresdevistas;

import java.io.IOException;
import java.util.ArrayList;

import partes.Parte;
import vista.VistaParte;
import barcos.Vector;

public abstract class VistasBarcoFactory implements AbstractVistasBarcoFactory {
	ArrayList<String> directorioImagenesPartesSanas;
	ArrayList<String> directorioImagenesPartesDestruidas;
	ArrayList<Parte> partes;

	protected VistasBarcoFactory(int tamanio, Vector orientacionBarco,
			String nombreBarco, ArrayList<Parte> partesAUtilizar) {
		partes = partesAUtilizar;
		String orientacion;
		directorioImagenesPartesSanas = new ArrayList<String>();
		if (orientacionBarco.x() == 0) {
			orientacion = "Vertical";
		} else {
			orientacion = "Horizontal";
		}
		for (int i = 1; i <= tamanio; i++) {
			directorioImagenesPartesSanas.add("imagenes/" + nombreBarco
					+ "/sano/" + orientacion + Integer.toString(i)
					+ nombreBarco + ".png");
		}
		directorioImagenesPartesDestruidas = new ArrayList<String>();
		for (int i = 1; i <= tamanio; i++) {
			directorioImagenesPartesSanas.add("imagenes/" + nombreBarco
					+ "/destruido/" + orientacion + Integer.toString(i)
					+ nombreBarco + ".png");
		}
	}

	@Override
	public ArrayList<VistaParte> crearVistas() {
		ArrayList<VistaParte> vistasADevolver = new ArrayList<VistaParte>();
		for (int i = 0; i < partes.size(); i++) {
			try {
				vistasADevolver.add(new VistaParte(
						directorioImagenesPartesSanas.get(i),
						directorioImagenesPartesDestruidas.get(i), partes
								.get(i)));
			} catch (IOException error) {

			}
		}
		return vistasADevolver;

	}

}
