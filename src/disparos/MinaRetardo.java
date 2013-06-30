package disparos;

import org.dom4j.Element;

public abstract class MinaRetardo extends Mina {
	protected int cantidadDeTurnosAEsperar;

	MinaRetardo() {
		super();
		nombre = "minaretardo";
		cantidadDeTurnosAEsperar = 3;
	}

	@Override
	public void pasarTurno() {
		cantidadDeTurnosAEsperar -= 1;
	}

	@Override
	public boolean debeDaniarEnEsteTurno() {
		if (cantidadDeTurnosAEsperar == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public abstract Element generarNodo();
}
