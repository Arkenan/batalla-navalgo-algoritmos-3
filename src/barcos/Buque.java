package barcos;

import java.util.Iterator;

import org.dom4j.Element;

import partes.Parte;
import barcos.strategies.MovimientoStrategy;
import constructoresdevistas.AbstractVistasBarcoFactory;
import constructoresdevistas.VistasBuqueFactory;

public class Buque extends NaveConDanioTotal {

    public Buque(Vector orient, MovimientoStrategy estrategia) {
        super(orient, 4, 1, estrategia);
    }

    public Buque(Element nodoBarco, MovimientoStrategy estrategia) {
        super(4, 1, estrategia, nodoBarco);
    }

    @Override
    public boolean estaDestruido() {
        Iterator<Parte> it = this.obtenerPartes().iterator();
        while (it.hasNext()) {
            Parte parteActual = it.next();
            if (parteActual.estaDestruida()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public AbstractVistasBarcoFactory getVistasFactory() {
        return new VistasBuqueFactory(this.obtenerOrientacion(), this.obtenerPartes());
    }

    @Override
    public Element generarNodo() {
        return super.generarNodo("Buque");

    }
}
