package barcos.strategies;

import barcos.Barco;
import barcos.Vector;

public class MovimientoLinealStrategy implements MovimientoStrategy {

    private Barco barco;
    private Vector direccionActual;

    public MovimientoLinealStrategy(Vector direccionInicial) {
        direccionActual.asignar(direccionInicial);
    }

    @Override
    public Vector ejecutar() {
        return barco.obtenerPosicion().sumar(direccionActual);
    }

    @Override
    public void setBarco(Barco barco) {
        this.barco = barco;

    }

}
