package constructoresdevistas;

import java.util.ArrayList;

import partes.Parte;
import barcos.Vector;

public class VistasDestructorFactory extends VistasBarcoFactory implements
		AbstractVistasBarcoFactory {
	final static int TAM_DESTRUCTOR = 3;

	public VistasDestructorFactory(Vector orientacionBarco,
			ArrayList<Parte> partesAUtilizar) {
		super(TAM_DESTRUCTOR, orientacionBarco, "destructor", partesAUtilizar);
	}
}
