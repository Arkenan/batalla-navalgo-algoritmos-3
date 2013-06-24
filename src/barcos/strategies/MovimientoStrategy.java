package barcos.strategies;

import barcos.Barco;
import barcos.Vector;

public interface MovimientoStrategy {

    public abstract Vector ejecutar();

    public abstract void setBarco(Barco barco);
}
