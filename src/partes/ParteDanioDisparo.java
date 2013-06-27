package partes;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import disparos.Disparo;

public class ParteDanioDisparo extends Parte {

    public ParteDanioDisparo(int vidaIni) {
        super(vidaIni);
    }

    @Override
    public void explosion(Disparo disparo) {
        disparo.afectar(this);
    }

    @Override
    public Element generarNodo() {
        Element nodoADevolver = DocumentHelper.createElement("ParteDanioDisparo");
        nodoADevolver.addAttribute("vida", Integer.toString(vida));
        return nodoADevolver;
    }
}
