package disparos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import partes.ParteDanioTotal;
import barcos.Vector;
import escenario.Tablero;

public class MinaRetardoTest {

    @Test
    public void testAlCrearlaYPreguntarleSiDebeExplotarDebeDarFalse() {
        MinaRetardo mina = new MinaRadio();
        assertFalse(mina.debeExplotar());
    }

    @Test
    public void testAlCrearUnaMinaRetardoYPasarTresTurnoYPreguntarleSiDeberiaExplotarDeberiaDarTrue() {
        MinaRetardo mina = new MinaRadio();
        for (int i = 0; i < 3; i++) {
            mina.pasarTurno();
        }
        assertTrue(mina.debeExplotar());
    }

    @Test
    public void testCreoUnaMinaRetardoEnUnaPosicionDondeHayUnaParteHagoQueExploteYLaParteDeberiaResultarDestruida() {
        MinaRetardo mina = new MinaRadio();
        Vector posicion = new Vector(1, 1);
        ParteDanioTotal parte = new ParteDanioTotal(1);
        Tablero tablero = Tablero.getTablero();
        tablero.colocarElemento(posicion, parte);
        mina.cambiarCasillerosAfectados(posicion);
        for (int i = 0; i < 3; i++) {
            mina.pasarTurno();
        }
        mina.explotar();
        assertTrue(parte.estaDestruida());
    }
}
