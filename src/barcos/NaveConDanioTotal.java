package barcos;

import partes.ParteDanioTotal;
import excepciones.PosicionInvalida;

public class NaveConDanioTotal extends Barco {

    public NaveConDanioTotal(Vector mov, Vector pos, Vector orient, int tam) throws PosicionInvalida {
        super(mov, pos, orient, tam);
        System.out.println("NaveConDanioTotal");
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
            if (unaParte.estaDestruida()) {
                return true;
            }
        }
        return false;
    }

}
