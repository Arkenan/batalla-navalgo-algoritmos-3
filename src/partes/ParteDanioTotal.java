package partes;

import disparos.Disparo;

public class ParteDanioTotal extends Parte {

    public ParteDanioTotal(int vidaIni) {
        super(vidaIni);
    }

    @Override
    public void explosion(Disparo disparo) {
        disparo.afectar(this);
    }

}
