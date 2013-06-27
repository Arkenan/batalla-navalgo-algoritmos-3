package partes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import disparos.DisparoConvencional;
import disparos.MinaContacto;

public class ParteDanioDisparoTest {

	@Test
	// Parte que contiene una sola vida
	public void testAlRecibirUnDisparoLaPartePasaAEstarDestruida() {
		ParteDanioDisparo parteDanioDisparo = new ParteDanioDisparo(1);
		parteDanioDisparo.explosion(new DisparoConvencional());
		assertEquals(parteDanioDisparo.estaDestruida(), true);
	}

	@Test
	public void testAlCrearLaParteNoEstaDestruida() {
		ParteDanioDisparo parteDanioDisparo = new ParteDanioDisparo(1);
		assertEquals(parteDanioDisparo.estaDestruida(), false);
	}

	@Test
	public void testAlExplotarUnaMinaNoEsDestruida() {
		ParteDanioDisparo parteDanioDisparo = new ParteDanioDisparo(1);
		parteDanioDisparo.explosion(new MinaContacto());
		assertEquals(parteDanioDisparo.estaDestruida(), false);
	}

	// Parte que tiene mas de una vida
	int vida = 4;

	@Test
	public void testAlRecibirUnDisparoNoEsDestruida() {
		ParteDanioDisparo parteDanioDisparo = new ParteDanioDisparo(vida);
		parteDanioDisparo.explosion(new DisparoConvencional());
		assertEquals(parteDanioDisparo.estaDestruida(), false);
	}

	@Test
	public void testAlRecibirNVidaCantidadDeDisparosEsDestruido() {
		ParteDanioDisparo parteDanioDisparo = new ParteDanioDisparo(vida);
		for (int i = 1; i <= vida; i++) {
			parteDanioDisparo.explosion(new DisparoConvencional());
		}
		assertEquals(parteDanioDisparo.estaDestruida(), true);
	}
}
