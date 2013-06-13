package escenario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import partes.ParteDanioTotal;
import barcos.Vector;

public class TableroTest {

    @Test
    public void testSoloHayUnTablero() {
        Tablero tablero1 = Tablero.getTablero();
        Tablero tablero2 = Tablero.getTablero();
        assertEquals(tablero1, tablero2);
    }

    @Test
    public void testFueraDeRango() {
        assertTrue(Tablero.getTablero().fueraDeRango(new Vector(0, 5)));
        assertTrue(Tablero.getTablero().fueraDeRango(new Vector(5, 0)));
        assertTrue(Tablero.getTablero().fueraDeRango(new Vector(11, 5)));
        assertTrue(Tablero.getTablero().fueraDeRango(new Vector(5, 11)));
    }

    @Test
    public void testElElementoQuedaCorrectmenteColocado() {
        Tablero tablero = Tablero.getTablero();
        ParteDanioTotal parte = new ParteDanioTotal(2);
        Vector posicion = new Vector(5, 5);
        tablero.colocarElemento(posicion, parte);
        assertTrue(tablero.elementoPerteneceAlCasillero(posicion, parte));
    }

    @Test
    public void testSacarElemento() {
        Tablero tablero = Tablero.getTablero();
        ParteDanioTotal parte = new ParteDanioTotal(2);
        Vector posicion = new Vector(5, 5);
        tablero.colocarElemento(posicion, parte);
        tablero.sacarElemento(posicion, parte);
        assertFalse(tablero.elementoPerteneceAlCasillero(posicion, parte));

    }
}
