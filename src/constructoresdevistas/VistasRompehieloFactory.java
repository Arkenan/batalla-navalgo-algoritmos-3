package constructoresdevistas;

import java.util.ArrayList;

import partes.Parte;
import barcos.Vector;

public class VistasRompehieloFactory extends VistasBarcoFactory implements
		AbstractVistasBarcoFactory {
	final static int TAM_ROMPEHIELO = 3;

	public VistasRompehieloFactory(Vector orientacionBarco,
			ArrayList<Parte> partesAUtilizar) {
		super(TAM_ROMPEHIELO, orientacionBarco, "rompehielo", partesAUtilizar);
	}
}
