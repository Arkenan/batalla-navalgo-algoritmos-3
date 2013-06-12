package barcos;

import excepciones.PosicionInvalida;

public class Lancha extends NaveConDanioTotal {

    public Lancha(Vector mov, Vector pos, Vector orient) throws PosicionInvalida {
        super(mov, pos, orient, 2, 1);
    }
}