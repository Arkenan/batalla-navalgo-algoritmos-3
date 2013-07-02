package constructoresdevistas;

import vistasbarcos.VistaBarco;
import barcos.Barco;

public interface AbstractVistasBarcoFactory {
	public abstract VistaBarco crearVista();

	public abstract void setBarco(Barco barco);
}
