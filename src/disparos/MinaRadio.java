package disparos;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import barcos.Vector;

public class MinaRadio extends MinaRetardo {
    public MinaRadio() {
        super();
        radio = 1;
        costo = 50;
        nombre = "minaradio";
    }

    public MinaRadio(Element nodo) {
        super();
        radio = 1;
        costo = 50;
        nombre = "minaradio";
        cambiarCasillerosAfectados(new Vector(nodo.attributeValue("posicion")));
        cantidadDeTurnosAEsperar = Integer.parseInt(nodo.attributeValue("turnos"));
    }

    @Override
    public Element generarNodo() {
        Element nodoADevolver = DocumentHelper.createElement("MinaRadio");
        nodoADevolver.addAttribute("turnos", Integer.toString(cantidadDeTurnosAEsperar));
        return nodoADevolver;
    }

}
