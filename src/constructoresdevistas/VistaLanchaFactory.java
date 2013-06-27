package constructoresdevistas;

import java.util.ArrayList;

import partes.Parte;
import barcos.Vector;

public class VistaLanchaFactory extends VistasBarcoFactory implements
		AbstractVistasBarcoFactory {
	final static int TAM_LANCHA = 2;

	public VistaLanchaFactory(Vector orientacionBarco,
			ArrayList<Parte> partesAUtilizar) {
		super(TAM_LANCHA, orientacionBarco, "lancha", partesAUtilizar);
	}
}
