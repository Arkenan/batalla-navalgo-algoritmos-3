package disparos;

import org.dom4j.Element;

public interface Daniador {
	public abstract boolean debeDaniarEnEsteTurno();

	public abstract void daniar();

	public abstract void pasarTurno();

	public abstract Element generarNodo();

	public abstract String obtenerNombre();

	public int costo();

}
