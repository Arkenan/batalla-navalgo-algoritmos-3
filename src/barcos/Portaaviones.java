package barcos;

import org.dom4j.Element;

import barcos.strategies.MovimientoStrategy;
import constructoresdevistas.AbstractVistasBarcoFactory;
import constructoresdevistas.VistasPortaavionesFactory;

public class Portaaviones extends NaveConDanioTotal {
    public Portaaviones(Vector orient, MovimientoStrategy estrategia) {
        super(orient, 5, 1, estrategia);
    }

    @Override
    public AbstractVistasBarcoFactory getVistasFactory() {
        return new VistasPortaavionesFactory(this.obtenerOrientacion(), this.obtenerPartes());
    }

    public Portaaviones(Element nodoBarco, MovimientoStrategy estrategia) {
        super(5, 1, estrategia, nodoBarco);
    }

    @Override
    public Element generarNodo() {
        return super.generarNodo("Portaaviones");

    }
}
