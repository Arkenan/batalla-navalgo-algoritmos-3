package disparos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import partes.ParteDanioTotal;
import barcos.Vector;
import escenario.Tablero;

public class MinaRadioTest {

    @Test
    public void testCreoUnaMinaRadio() {
        MinaRadio mina = new MinaRadio();
        Vector posicion = new Vector(3, 3);
        mina.cambiarCasillerosAfectados(posicion);
        Vector posicionPertenecienteAlRadio = new Vector(3, 2);
        ParteDanioTotal parte = new ParteDanioTotal(1);
        Tablero tablero = Tablero.getTablero();
        tablero.colocarElemento(posicionPertenecienteAlRadio, parte);
        mina.explotar();
        assertTrue(parte.estaDaniada());
    }

    @Test
    public void testCreoUnaMinaRadioYUnaParteFueraDeSuRadioYLaHagoExplotarLaParteNoDeberiaResultarDaniada() {
        MinaRadio mina = new MinaRadio();
        Vector posicion = new Vector(3, 3);
        mina.cambiarCasillerosAfectados(posicion);
        Vector posicionNoPertenecienteAlRadio = new Vector(9, 9);
        ParteDanioTotal parte = new ParteDanioTotal(1);
        Tablero tablero = Tablero.getTablero();
        tablero.colocarElemento(posicionNoPertenecienteAlRadio, parte);
        mina.explotar();
        assertFalse(parte.estaDaniada());
    }

}
