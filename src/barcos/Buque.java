package barcos;

import barcos.strategies.MovimientoStrategy;

public class Buque extends NaveConDanioTotal {

    public Buque(Vector orient, MovimientoStrategy estrategia) {
        super(orient, 4, 1, estrategia);
    }

    @Override
    public boolean estaDestruido() {
        return this.estaDaniado();
    }

}
