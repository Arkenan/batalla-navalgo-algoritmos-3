package casillero;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import partes.Parte;

public class CasilleroTest {

    @Test
    public void testColocarElementoYVerQueHayAlgo() {
        Parte parte = new Parte(2);
        Casillero casillero = new Casillero();
        casillero.colocarElemento(parte);
        assertTrue(casillero.contienePartes());
    }

    @Test
    public void testColocarElementoYVerQueTieneEsaParte() {
        Parte parte = new Parte(2);
        Casillero casillero = new Casillero();
        casillero.colocarElemento(parte);
        assertTrue(casillero.contiene(parte));
    }

    @Test
    public void testDevuelveTodasSusPartes() {
        Parte parte1 = new Parte(2);
        Parte parte2 = new Parte(2);
        Casillero casillero = new Casillero();
        casillero.colocarElemento(parte1);
        casillero.colocarElemento(parte2);
        ArrayList<Parte> listaPartes = casillero.obtenerPartes();
        assertEquals(listaPartes.get(0), parte1);
        assertEquals(listaPartes.get(1), parte2);
    }

    @Test
    public void testDevuelveSoloSusPartes() {
        Parte parte1 = new Parte(2);
        Parte parte2 = new Parte(2);
        Casillero casillero = new Casillero();
        casillero.colocarElemento(parte1);
        casillero.colocarElemento(parte2);
        ArrayList<Parte> listaPartes = casillero.obtenerPartes();
        assertEquals(listaPartes.size(), 2);
    }

    @Test
    public void testSeQuitaUnElemento() {
        Parte parte1 = new Parte(2);
        Parte parte2 = new Parte(2);
        Casillero casillero = new Casillero();
        casillero.colocarElemento(parte1);
        casillero.colocarElemento(parte2);
        casillero.quitarElemento(parte2);
        assertTrue(casillero.contiene(parte1));
        assertFalse(casillero.contiene(parte2));
    }

}
