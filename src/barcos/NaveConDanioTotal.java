package barcos;

import partes.ParteDanioTotal;
import excepciones.PosicionInvalida;

public class NaveConDanioTotal extends Barco {

    public NaveConDanioTotal(Vector mov, Vector orient, int tam, int vida) throws PosicionInvalida {
        super(mov, orient, tam, vida);
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
