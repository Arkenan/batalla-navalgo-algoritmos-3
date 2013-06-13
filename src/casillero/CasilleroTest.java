package casillero;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import partes.Parte;
import partes.ParteDanioTotal;

public class CasilleroTest {

    @Test
    public void testColocarElementoYVerQueHayAlgo() {
        ParteDanioTotal parte = new ParteDanioTotal(2);
        Casillero casillero = new Casillero();
        casillero.colocarElemento(parte);
        assertTrue(casillero.contienePartes());
    }

    @Test
    public void testColocarElementoYVerQueTieneEsaParte() {
        ParteDanioTotal parte = new ParteDanioTotal(2);
        Casillero casillero = new Casillero();
        casillero.colocarElemento(parte);
        assertTrue(casillero.contiene(parte));
    }

    @Test
    public void testDevuelveTodasSusPartes() {
        ParteDanioTotal parte1 = new ParteDanioTotal(2);
        ParteDanioTotal parte2 = new ParteDanioTotal(2);
        Casillero casillero = new Casillero();
        casillero.colocarElemento(parte1);
        casillero.colocarElemento(parte2);
        ArrayList<Parte> listaPartes = casillero.obtenerPartes();
        assertEquals(listaPartes.get(0), parte1);
        assertEquals(listaPartes.get(1), parte2);
    }

    @Test
    public void testDevuelveSoloSusPartes() {
        ParteDanioTotal parte1 = new ParteDanioTotal(2);
        ParteDanioTotal parte2 = new ParteDanioTotal(2);
        Casillero casillero = new Casillero();
        casillero.colocarElemento(parte1);
        casillero.colocarElemento(parte2);
        ArrayList<Parte> listaPartes = casillero.obtenerPartes();
        assertEquals(listaPartes.size(), 2);
    }

    @Test
    public void testSeQuitaUnElemento() {
        ParteDanioTotal parte1 = new ParteDanioTotal(2);
        ParteDanioTotal parte2 = new ParteDanioTotal(2);
        Casillero casillero = new Casillero();
        casillero.colocarElemento(parte1);
        casillero.colocarElemento(parte2);
        casillero.quitarElemento(parte2);
        assertTrue(casillero.contiene(parte1));
        assertFalse(casillero.contiene(parte2));
    }

}
