package constructoresdevistas;

import java.io.IOException;
import java.util.ArrayList;

import partes.Parte;
import vista.VistaParte;
import vistasbarcos.VistaBarco;
import barcos.Barco;
import barcos.Vector;

public abstract class VistasBarcoFactory implements AbstractVistasBarcoFactory {
	ArrayList<String> directorioImagenesPartesSanas;
	ArrayList<String> directorioImagenesPartesDestruidas;
	ArrayList<Parte> partes;
	String nombreBarco;
	Barco barco;

	public VistasBarcoFactory(String nombreDeUnBarco) {
		nombreBarco = nombreDeUnBarco;
	}

	@Override
	public void setBarco(Barco unBarco) {
		barco = unBarco;
		this.setParametros(barco.obtenerTamanio(), barco.obtenerOrientacion(),
				nombreBarco, barco.obtenerPartes());

	}

	public void setParametros(int tamanio, Vector orientacionBarco,
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
	public VistaBarco crearVista() {
		ArrayList<VistaParte> vistasDePartes = new ArrayList<VistaParte>();
		Parte parteAuxiliar;
		for (int i = 0; i < partes.size(); i++) {
			try {
				parteAuxiliar = partes.get(i);
				VistaParte vistaParteAuxiliar = new VistaParte(
						directorioImagenesPartesSanas.get(i),
						directorioImagenesPartesDestruidas.get(i),
						parteAuxiliar);

				vistasDePartes.add(vistaParteAuxiliar);
				parteAuxiliar.agregarObservador(vistaParteAuxiliar);

			} catch (IOException error) {
				System.out.println("error en la creacion de las vistas");
			}
		}
		VistaBarco vistaBarco = new VistaBarco(vistasDePartes, barco);
		return vistaBarco;

	}
}
