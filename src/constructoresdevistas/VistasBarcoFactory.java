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

	public VistasBarcoFactory(int tamanio, Vector orientacionBarco,
			String nombreBarco, ArrayList<Parte> partesAUtilizar) {
		partes = partesAUtilizar;
		String orientacion;
		if (orientacionBarco.x() == 0) {
			orientacion = "vertical/";
		} else {
			orientacion = "horizontal/";
		}
		directorioImagenesPartesSanas = new ArrayList<String>();
		for (int i = 1; i <= tamanio; i++) {
			directorioImagenesPartesSanas.add("imagenes/" + nombreBarco
					+ "/sano/" + orientacion + nombreBarco
					+ Integer.toString(i) + ".png");
		}

		directorioImagenesPartesDestruidas = new ArrayList<String>();
		for (int i = 1; i <= tamanio; i++) {
			directorioImagenesPartesDestruidas.add("imagenes/" + nombreBarco
					+ "/destruido/" + orientacion + nombreBarco
					+ Integer.toString(i) + ".png");
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
				System.out.println(error);
			}
		}
		return vistasADevolver;

	}

}
