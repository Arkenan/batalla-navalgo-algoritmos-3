package barcos;

import org.dom4j.Element;

import barcos.strategies.MovimientoStrategy;
import constructoresdevistas.AbstractVistasBarcoFactory;
import constructoresdevistas.VistasRompehieloFactory;

public class Rompehielo extends NaveConDanioTotal {

    public Rompehielo(Vector orient, MovimientoStrategy estrategia) {
        super(orient, 3, 2, estrategia);
    }

    @Override
    public AbstractVistasBarcoFactory getVistasFactory() {
        return new VistasRompehieloFactory(this.obtenerOrientacion(), this.obtenerPartes());
    }

    public Rompehielo(Element nodoBarco, MovimientoStrategy estrategia) {
        super(3, 2, estrategia, nodoBarco);
    }

    @Override
    public Element generarNodo() {
        return super.generarNodo("Rompehielo");

    }
}
