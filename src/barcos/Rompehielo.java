package barcos;

import barcos.strategies.MovimientoStrategy;

public class Rompehielo extends NaveConDanioTotal {

    public Rompehielo(Vector orient, MovimientoStrategy estrategia) {
        super(orient, 3, 2, estrategia);
    }
}
