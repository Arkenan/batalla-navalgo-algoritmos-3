package disparos;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import casillero.Casillero;

public class MinaContacto extends Mina {
    public MinaContacto() {
        super();
        radio = 0;
        costo = 150;
        nombre = "minacontacto";
    }

    @Override
    public boolean debeExplotar() {
        Casillero casilleroAfectado = casillerosAfectados.get(0);
        return casilleroAfectado.contienePartes();
    }

    @Override
    public Element generarNodo() {
        Element nodoADevolver = DocumentHelper.createElement("MinaContacto");
        return nodoADevolver;
    }

}
