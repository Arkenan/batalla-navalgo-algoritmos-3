package barcos;

import barcos.strategies.MovimientoStrategy;

public class Lancha extends NaveConDanioTotal {

    public Lancha(Vector orient, MovimientoStrategy estrategia) {
        super(orient, 2, 1, estrategia);
    }
}