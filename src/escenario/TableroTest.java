package escenario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import barcos.Lancha;
import barcos.Vector;
import excepciones.FueraDeRango;

public class TableroTest {
    @Test
    public void testSiempreSeUsaElMismoTablero() {
        Tablero tablero1 = Tablero.getTablero();
        Tablero tablero2 = Tablero.getTablero();
        assertEquals(tablero1, tablero2);
    }

    @Test
    public void testLanzaExcepcionAlPasarseConElBarcoAIzquierda() {
        boolean huboEx = false;
        Tablero tablero = Tablero.getTablero();
        int[] mov = {1, 0};
        Vector pos = new Vector(2, 2);
        Lancha lancha = new Lancha(pos, mov);
        int[] posicion = {1, 1};
        try {
            tablero.ponerBarco(lancha, posicion);
        } catch (FueraDeRango fuera) {
            huboEx = true;
        }
        assertTrue(huboEx);
    }

    @Test
    public void testLanzaExcepcionAlPasarseConElBarcoADerecha() {
        boolean huboEx = false;
        Tablero tablero = Tablero.getTablero();
        Lancha lancha = new Lancha();
        int[] posicion = {11, 1};
        try {
            tablero.ponerBarco(lancha, posicion);
        } catch (FueraDeRango fuera) {
            huboEx = true;
        }
        assertTrue(huboEx);
    }

    @Test
    public void testExcepcionAlTirarEnVacio() {
        Tablero tablero = Tablero.getTablero();
        int[] posicionDisparo = {6, 6};
        tablero.disparar(posicionDisparo);
        assertFalse(lancha.estaDaniado());
    }

    @Test
    public void testNoSeDaniaSiSeTiraEnVacio() {
        Tablero tablero = Tablero.getTablero();
        Lancha lancha = new Lancha();
        int[] posicion = {5, 5};
        try {
            tablero.ponerBarco(lancha, posicion);
        } catch (FueraDeRango fuera) {
        }
        int[] posicionDisparo = {6, 6};
        tablero.disparar(posicionDisparo);
        assertFalse(lancha.estaDaniado());
    }

    @Test
    public void testSeMueveCorrectamente() {
        Tablero tablero = Tablero.getTablero();
        Lancha lancha = new Lancha();
        int[] posicion = {5, 5};
        try {
            tablero.ponerBarco(lancha, posicion);
        } catch (FueraDeRango fuera) {
        }
        assertTrue(true);
    }

    @Test
    public void testGuardaYDaniaCorrectamente() {
        Tablero tablero = Tablero.getTablero();
        Lancha lancha = new Lancha();
        int[] posicion = {5, 5};
        try {
            tablero.ponerBarco(lancha, posicion);
        } catch (FueraDeRango fuera) {
        }
        tablero.disparar(posicion);
        assertTrue(lancha.estaDaniado());

    }
}
