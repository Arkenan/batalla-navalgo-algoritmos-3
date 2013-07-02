package partida;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.Test;

import barcos.Barco;
import barcos.Vector;

public class ManejadorDeElementosDelTableroTest {

    @Test
    public void testAlCrearUnManejadorDeElementosDelTableroPedirleLaCantidadDebeSerLaPredeterminada() {
        ManejadorDeElementosDelTablero manejador = new ManejadorDeElementosDelTablero();
        manejador.crearBarcosPorDefault();
        assertEquals(manejador.cantidadDeElementosADestruirParaGanar(), 7);
    }

    @Test
    public void testAlCrearElManejadorDeElementosDelTableroConDosBarcosNoDeberianHaberBarcosDestruidos() {
        ManejadorDeElementosDelTablero manejador = new ManejadorDeElementosDelTablero();
        manejador.crearBarcosPorDefault();
        assertEquals(manejador.cantidadDeElementosDestruidos(), 0);
    }

    @Test
    public void testAlPedirleQueMuevaLosBarcosDeberiaCambiarSuPosicion() {
        ManejadorDeElementosDelTablero manejador = new ManejadorDeElementosDelTablero();
        ArrayList<Barco> barcos = manejador.crearBarcosPorDefault();
        Barco barcoAuxiliar = barcos.get(0);
        Vector posicionInicial = new Vector(barcoAuxiliar.obtenerPosicion());
        manejador.moverElementos();
        Vector posicionFinal = new Vector(barcoAuxiliar.obtenerPosicion());
        assertFalse(posicionInicial.sonIguales(posicionFinal));
    }
}