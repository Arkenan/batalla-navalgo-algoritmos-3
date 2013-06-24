package partida;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

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

	/*
	 * Para
	 * 
	 * @Test public void
	 * testAlPedirleQueMuevaLosBarcosDeberiaCambiarSuPosicion() {
	 * ManejadorDeBarcos manejador = new ManejadorDeBarcos(); ArrayList<Barco>
	 * barcos = manejador.obtenerBarcos(); Barco barcoAuxiliar = barcos.get(0);
	 * Vector posicionFinal = barcoAuxiliar.obtenerPosicion(); Vector
	 * posicionInicial = new Vector(posicionFinal.x(), posicionFinal.y());
	 * manejador.moverElementos();
	 * 
	 * assertFalse(posicionInicial.sonIguales(posicionFinal)); }
	 */
}
