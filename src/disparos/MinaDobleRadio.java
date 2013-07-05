package disparos;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import barcos.Vector;

public class MinaDobleRadio extends MinaRetardo {
    public MinaDobleRadio() {
        super();
        radio = 2;
        costo = 100;
        nombre = "minadobleradio";
    }

    public MinaDobleRadio(Element nodo) {
        super();
        radio = 2;
        costo = 100;
        nombre = "minadobleradio";
        cambiarCasillerosAfectados(new Vector(nodo.attributeValue("posicion")));
        cantidadDeTurnosAEsperar = Integer.parseInt(nodo.attributeValue("turnos"));
    }

    @Override
    public Element generarNodo() {
        Element nodoADevolver = DocumentHelper.createElement("MinaDobleRadio");
        nodoADevolver.addAttribute("posicion", posicion.toString());
        nodoADevolver.addAttribute("turnos", Integer.toString(cantidadDeTurnosAEsperar));
        return nodoADevolver;
    }
}
