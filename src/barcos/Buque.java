package barcos;

public class Buque extends NaveConDanioTotal {

    public Buque(Vector mov, Vector orient) {
        super(mov, orient, 4, 1);
    }

    @Override
    public boolean estaDestruido() {
        return this.estaDaniado();
    }

}
