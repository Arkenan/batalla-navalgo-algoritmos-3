package barcos.strategies;

import barcos.Barco;
import barcos.Vector;

public class MovimientoLinealStrategy implements MovimientoStrategy {

    private Barco barco;
    private final Vector direccionActual;

    public MovimientoLinealStrategy(Vector direccionInicial) {
        direccionActual = direccionInicial;
    }

    @Override
    public void setBarco(Barco barco) {
        this.barco = barco;

    }

    @Override
    public Vector ejecutar() {
        Vector nuevaPosicion = barco.obtenerPosicion().sumar(direccionActual);
        if (barco.verificarPosicion(nuevaPosicion)) {
            return nuevaPosicion;
        } else {
            direccionActual.asignar(direccionActual.porEscalar(-1));
            return this.ejecutar();
        }

    }

    @Override
    public Vector obtenerDireccionMovimiento() {
        return direccionActual;
    }

}
