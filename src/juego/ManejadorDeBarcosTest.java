package juego;

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
		assertEquals(manejador.cantidadDeBarcos(), 7);
	}

	@Test
	public void testAlCrearElManejadorDeBarcosConDosBarcosNoDeberianHaberBarcosDestruidos() {
		ManejadorDeBarcos manejador = new ManejadorDeBarcos();
		assertEquals(manejador.cantidadDeBarcosDestruidos(), 0);
	}

	@Test
	public void testAlPedirleQueMuevaLosBarcosDeberiaCambiarSuPosicion() {
		ManejadorDeBarcos manejador = new ManejadorDeBarcos();
		ArrayList<Barco> barcos = manejador.obtenerBarcos();
		Barco barcoAuxiliar = barcos.get(0);
		Vector posicionFinal = barcoAuxiliar.obtenerPosicion();
		Vector posicionInicial = new Vector(posicionFinal.x(),
				posicionFinal.y());
		manejador.moverBarcos();

		assertFalse(posicionInicial.sonIguales(posicionFinal));
	}
}
