package observador;

public interface Observable {

	public abstract void agregarObservador(Observador observador);

	public abstract void eliminarObservador(Observador observador);

	public abstract void notificar();

}
