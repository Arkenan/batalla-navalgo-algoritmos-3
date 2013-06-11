package disparos;

public class MinaRetardo extends Mina {
    protected int cantidadDeTurnosAEsperar;

    MinaRetardo() {
        super();
        nombre = "minaretardo";
        cantidadDeTurnosAEsperar = 3;
    }

    @Override
    public void pasarTurno() {
        cantidadDeTurnosAEsperar -= 1;
    }

    @Override
    public boolean debeExplotar() {
        if (cantidadDeTurnosAEsperar == 0) {
            return true;
        } else {
            return false;
        }
    }
}
