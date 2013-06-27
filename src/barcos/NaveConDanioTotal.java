package barcos;

import org.dom4j.Element;

import partes.ParteDanioTotal;
import barcos.strategies.MovimientoStrategy;
import constructoresdevistas.AbstractVistasBarcoFactory;

public abstract class NaveConDanioTotal extends Barco {

    public NaveConDanioTotal(Vector orient, int tam, int vida, MovimientoStrategy estrategia) {
        super(orient, tam, vida, estrategia);
    }

    public NaveConDanioTotal(int tam, int vida, MovimientoStrategy estrategia, Element nodoBarco) {
        super(tam, vida, estrategia, nodoBarco);
    }

    @Override
    public void construirPartes() {
        for (int i = 0; i < tamanio; i++) {
            partesDelBarco.add(new ParteDanioTotal(vida));
        }

    }

    @Override
    public void construirParte(Element nodoParte) {
        partesDelBarco.add(new ParteDanioTotal(Integer.parseInt(nodoParte.attributeValue("vida"))));
    }

    @Override
    public abstract AbstractVistasBarcoFactory getVistasFactory();

    @Override
    public Element generarNodo(String nombreBarco) {
        return super.generarNodo(nombreBarco);

    }

}
