package disparos;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class MinaRadio extends MinaRetardo {
    public MinaRadio() {
        super();
        radio = 1;
        costo = 50;
        nombre = "minaradio";
    }

    @Override
    public Element generarNodo() {
        Element nodoADevolver = DocumentHelper.createElement("MinaRadio");
        nodoADevolver.addAttribute("turnos", Integer.toString(cantidadDeTurnosAEsperar));
        return nodoADevolver;
    }

}
