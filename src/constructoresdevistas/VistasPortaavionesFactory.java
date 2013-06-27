package constructoresdevistas;

import java.util.ArrayList;

import partes.Parte;
import barcos.Vector;

public class VistasPortaavionesFactory extends VistasBarcoFactory implements
		AbstractVistasBarcoFactory {
	final static int TAM_PORTAAVIONES = 5;

	public VistasPortaavionesFactory(Vector orientacionBarco,
			ArrayList<Parte> partesAUtilizar) {
		super(TAM_PORTAAVIONES, orientacionBarco, "portaaviones",
				partesAUtilizar);
	}
}
