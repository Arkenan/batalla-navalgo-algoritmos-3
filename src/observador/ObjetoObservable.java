package observador;

import java.util.ArrayList;

public abstract class ObjetoObservable implements Observable {
	protected ArrayList<Observador> observadores;

	public ObjetoObservable() {
		observadores = new ArrayList<Observador>();
	}

	@Override
	public void agregarObservador(Observador observador) {
		observadores.add(observador);
	}

	@Override
	public void eliminarObservador(Observador observador) {
		observadores.remove(observador);
	}

	@Override
	public void notificar() {
		for (int i = 0; i < observadores.size(); i++) {
			observadores.get(i).actualizar();
		}
	}

}
