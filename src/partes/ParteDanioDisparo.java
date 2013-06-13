package partes;

import disparos.Disparo;

public class ParteDanioDisparo extends Parte {

    public ParteDanioDisparo(int vidaIni) {
        super(vidaIni);
    }

    @Override
    public void explosion(Disparo disparo) {
        disparo.afectar(this);
    }
}
