package disparos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import partes.Parte;
import barcos.Vector;
import escenario.Tablero;

public class MinaDobleRadioTest {

    @Test
    public void testCreoUnaMinaRadio() {
        MinaDobleRadio mina = new MinaDobleRadio();
        Vector posicion = new Vector(3, 3);
        mina.cambiarCasillerosAfectados(posicion);
        Vector posicionPertenecienteAlRadio = new Vector(3, 1);
        Parte parte = new Parte(1);
        Tablero tablero = Tablero.getTablero();
        tablero.colocarElemento(posicionPertenecienteAlRadio, parte);
        mina.explotar();
        assertTrue(parte.estaDaniada());
    }

    @Test
    public void testCreoUnaMinaDobleRadio() {
        MinaDobleRadio mina = new MinaDobleRadio();
        Vector posicion = new Vector(3, 3);
        mina.cambiarCasillerosAfectados(posicion);
        Vector posicionNoPertenecienteAlRadio = new Vector(9, 9);
        Parte parte = new Parte(1);
        Tablero tablero = Tablero.getTablero();
        tablero.colocarElemento(posicionNoPertenecienteAlRadio, parte);
        mina.explotar();
        assertFalse(parte.estaDaniada());
    }

}
