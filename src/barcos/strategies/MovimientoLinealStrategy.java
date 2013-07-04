package barcos.strategies;

import observador.ObjetoObservable;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import barcos.Barco;
import barcos.Vector;

public class MovimientoLinealStrategy extends ObjetoObservable implements MovimientoStrategy {

    private Barco barco;
    private final Vector direccionActual;

    public MovimientoLinealStrategy(Vector direccionInicial) {
        super();
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
            if (!(this.obtenerDireccionMovimiento().sonOrtogonales(barco.obtenerOrientacion()))) {
                this.barco.invertirPartes();
            }
            notificar();
            return this.ejecutar();
        }

    }

    @Override
    public Vector obtenerDireccionMovimiento() {
        return direccionActual;
    }

    @Override
    public Element generarNodo() {
        Element nodoADevolver = DocumentHelper.createElement("Estrategia");
        nodoADevolver.addAttribute("tipo", "MovimientoLinealStrategy");
        nodoADevolver.addAttribute("direccionActual", this.direccionActual.toString());
        return nodoADevolver;
    }

}
