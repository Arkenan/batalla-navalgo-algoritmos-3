package constructoresdevistas;

import java.util.ArrayList;

import partes.Parte;
import barcos.Vector;

public class VistasBuqueFactory extends VistasBarcoFactory implements
		AbstractVistasBarcoFactory {
	final static int TAM_BUQUE = 4;

	public VistasBuqueFactory(Vector orientacionBarco,
			ArrayList<Parte> partesAUtilizar) {
		super(TAM_BUQUE, orientacionBarco, "buque", partesAUtilizar);
	}
}
