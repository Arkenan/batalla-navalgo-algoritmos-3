package disparos;

import casillero.Casillero;

public class MinaContacto extends Mina {
    public MinaContacto() {
        super();
        radio = 0;
        costo = 150;
        nombre = "minacontacto";
    }

    @Override
    public boolean debeExplotar() {
        Casillero casilleroAfectado = casillerosAfectados.get(0);
        return (casilleroAfectado.contienePartes());
    }

}
