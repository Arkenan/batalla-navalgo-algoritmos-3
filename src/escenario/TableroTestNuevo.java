package escenario;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import barcos.Vector;
import casillero.Casillero;

public class TableroTestNuevo {

    @Test
    public void testQuedaUnElementoColocadoSinError() {
        // caso trivial.
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 5);
        tablero.colocarElemento(posicion, 1);
    }

    @Test
    public void testSeGuardaCorrectamente() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 5);
        tablero.colocarElemento(posicion, 1);
        assertTrue(tablero.elementoPerteneceAlCasillero(posicion, 1));
    }

    @Test
    public void testDevuelveListaDeTamanioCorrecto() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 5);
        LinkedList<Casillero> lista = tablero.casillasAfectadas(posicion, 0);
        assertTrue(lista.size() == 1);
        lista = tablero.casillasAfectadas(posicion, 1);
        assertTrue(lista.size() == 9);
        lista = tablero.casillasAfectadas(posicion, 2);
        assertTrue(lista.size() == 25);

    }

}
