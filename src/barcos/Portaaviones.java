package barcos;

import barcos.strategies.MovimientoStrategy;

public class Portaaviones extends NaveConDanioTotal {
    public Portaaviones(Vector orient, MovimientoStrategy estrategia) {
        super(orient, 5, 1, estrategia);
    }
}
