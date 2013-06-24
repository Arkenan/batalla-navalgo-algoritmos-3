package barcos;

import partes.ParteDanioTotal;
import barcos.strategies.MovimientoStrategy;

public class NaveConDanioTotal extends Barco {

    public NaveConDanioTotal(Vector orient, int tam, int vida, MovimientoStrategy estrategia) {
        super(orient, tam, vida, estrategia);
    }

    @Override
    public void construirPartes() {
        for (int i = 0; i < tamanio; i++) {
            partesDelBarco.add(new ParteDanioTotal(vida));
        }

    }

    @Override
    public boolean estaDaniado() {
        for (int i = 0; i < tamanio; i++) {
            ParteDanioTotal unaParte = (ParteDanioTotal) partesDelBarco.get(i);
            if (unaParte.estaDaniada()) {
                return true;
            }
        }
        return false;
    }

}
