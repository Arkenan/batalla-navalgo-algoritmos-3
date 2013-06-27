package partida;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.Test;

import barcos.Barco;
import barcos.Vector;

public class ManejadorDeBarcosTest {

    @Test
    public void testAlCrearUnManejadorDeBarcosPedirleLaCantidadDebeSerLaPredeterminada() {
        ManejadorDeBarcos manejador = new ManejadorDeBarcos();
        manejador.crearBarcosPorDefault();
        assertEquals(manejador.cantidadDeElementosADestruirParaGanar(), 7);
    }

    @Test
    public void testAlCrearElManejadorDeBarcosConDosBarcosNoDeberianHaberBarcosDestruidos() {
        ManejadorDeBarcos manejador = new ManejadorDeBarcos();
        manejador.crearBarcosPorDefault();
        assertEquals(manejador.cantidadDeElementosDestruidos(), 0);
    }

    @Test
    public void testAlPedirleQueMuevaLosBarcosDeberiaCambiarSuPosicion() {
        ManejadorDeBarcos manejador = new ManejadorDeBarcos();
        ArrayList<Barco> barcos = manejador.crearBarcosPorDefault();
        Barco barcoAuxiliar = barcos.get(0);
        Vector posicionFinal = barcoAuxiliar.obtenerPosicion();
        Vector posicionInicial = new Vector(posicionFinal.x(), posicionFinal.y());
        manejador.moverElementos();
        assertFalse(posicionInicial.sonIguales(posicionFinal));
    }

}