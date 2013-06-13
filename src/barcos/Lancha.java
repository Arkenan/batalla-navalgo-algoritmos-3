package barcos;

import excepciones.PosicionInvalida;

public class Lancha extends NaveConDanioTotal {

    public Lancha(Vector mov, Vector orient) throws PosicionInvalida {
        super(mov, orient, 2, 1);
    }
}