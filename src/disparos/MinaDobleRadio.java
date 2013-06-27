package disparos;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class MinaDobleRadio extends MinaRetardo {
    public MinaDobleRadio() {
        super();
        radio = 2;
        costo = 100;
        nombre = "minadobleradio";
    }

    @Override
    public Element generarNodo() {
        Element nodoADevolver = DocumentHelper.createElement("MinaDobleRadio");
        nodoADevolver.addAttribute("turnos", Integer.toString(cantidadDeTurnosAEsperar));
        return nodoADevolver;
    }

}
