package partes;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import disparos.Disparo;

public class ParteDanioTotal extends Parte {

    public ParteDanioTotal(int vidaIni) {
        super(vidaIni);
    }

    @Override
    public void explosion(Disparo disparo) {
        disparo.afectar(this);
    }

    @Override
    public Element generarNodo() {
        Element nodoADevolver = DocumentHelper.createElement("ParteDanioTotal");
        nodoADevolver.addAttribute("vida", Integer.toString(vida));
        return nodoADevolver;
    }

}
