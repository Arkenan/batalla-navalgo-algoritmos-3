package barcos.strategies;

import observador.Observable;

import org.dom4j.Element;

import barcos.Barco;
import barcos.Vector;

public interface MovimientoStrategy extends Observable {

    public abstract Vector ejecutar();

    public abstract void setBarco(Barco barco);

    // esto lo hago para mantener algunos tests, no se si es bueno.
    public abstract Vector obtenerDireccionMovimiento();

    public abstract Element generarNodo();
}
