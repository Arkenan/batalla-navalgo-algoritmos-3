package barcos;

import org.dom4j.Element;

import barcos.strategies.MovimientoStrategy;
import constructoresdevistas.AbstractVistasBarcoFactory;
import constructoresdevistas.VistaLanchaFactory;

public class Lancha extends NaveConDanioTotal {

    public Lancha(Vector orient, MovimientoStrategy estrategia) {
        super(orient, 2, 1, estrategia);
    }

    @Override
    public AbstractVistasBarcoFactory getVistasFactory() {
        return new VistaLanchaFactory(this.obtenerOrientacion(), this.obtenerPartes());
    }

    public Lancha(Element nodoBarco, MovimientoStrategy estrategia) {
        super(2, 1, estrategia, nodoBarco);
    }

    @Override
    public Element generarNodo() {
        return super.generarNodo("Lancha");

    }
}