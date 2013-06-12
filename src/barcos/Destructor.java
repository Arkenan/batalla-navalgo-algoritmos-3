package barcos;

import partes.ParteDanioDisparo;

public class Destructor extends Barco {

    public Destructor(Vector mov, Vector pos, Vector orient) {
        super(mov, pos, orient, 3, 1);

    }

    @Override
    public void construirPartes() {
        for (int i = 0; i < tamanio; i++) {
            partesDelBarco.add(new ParteDanioDisparo(vida));
        }
    }

    @Override
    public boolean estaDaniado() {
        for (int i = 0; i < tamanio; i++) {
            ParteDanioDisparo unaParte = (ParteDanioDisparo) partesDelBarco.get(i);
            if (unaParte.estaDestruida()) {
                return true;
            }
        }
        return false;
    }

}
