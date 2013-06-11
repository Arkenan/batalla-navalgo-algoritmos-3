package disparos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import partes.Parte;
import barcos.Vector;
import escenario.Tablero;

public class MinaContactoTest {

    @Test
    public void testAlCrearUnaMinaContactoYPreguntarleSiDebeExplotarSiendoQueNoHayBarcosDebeSerFalso() {

        MinaContacto mina = new MinaContacto();

        mina.cambiarCasillerosAfectados(new Vector(1, 1));

        assertFalse(mina.debeExplotar());

    }

    @Test
    public void testAlCrearUnaMInaDeContactoEnElLugarQueHayUnaParteYPreguntarleSiDebeExplotarDebeDarTrue() {

        MinaContacto mina = new MinaContacto();

        Parte parte = new Parte(1);

        Tablero tablero = Tablero.getTablero();

        Vector posicion = new Vector(1, 1);

        tablero.colocarElemento(posicion, parte);

        mina.cambiarCasillerosAfectados(posicion);

        assertTrue(mina.debeExplotar());
    }

    @Test
    public void testAlCrearUnaMinaDeContactoEnUnLugarQueHayUnaParteYHacerlaExplotarLaParteDebeSerDaniada() {
        MinaContacto mina = new MinaContacto();

        Parte parte = new Parte(1);

        Tablero tablero = Tablero.getTablero();

        Vector posicion = new Vector(1, 1);

        tablero.colocarElemento(posicion, parte);

        mina.cambiarCasillerosAfectados(posicion);

        mina.explotar();

        assertTrue(parte.estaDaniada());
    }

}
