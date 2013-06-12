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
		DisparoConvencional disparo = new DisparoConvencional();
		disparo.afectar(parteDanioDisparo);
		assertEquals(parteDanioDisparo.estaDestruida(), true);
	}

	@Test
	public void testAlCrearLaParteNoEstaDestruida() {
		ParteDanioDisparo parteDanioDisparo = new ParteDanioDisparo(1);
		assertEquals(parteDanioDisparo.estaDestruida(), false);
	}

	@Test
	public void testAlCrearLaParteNoEstaDaniada() {
		ParteDanioDisparo parteDanioDisparo = new ParteDanioDisparo(1);
		assertEquals(parteDanioDisparo.estaDaniada(), false);
	}

	@Test
	public void testAlExplotarUnaMinaNoEsDestruida() {
		ParteDanioDisparo parteDanioDisparo = new ParteDanioDisparo(1);
		MinaContacto mina = new MinaContacto();
		mina.afectar(parteDanioDisparo);
		assertEquals(parteDanioDisparo.estaDestruida(), false);
	}

	@Test
	public void testAlExplotarUnaMinaNoEsDaniada() {
		ParteDanioDisparo parteDanioDisparo = new ParteDanioDisparo(1);
		MinaContacto mina = new MinaContacto();
		mina.afectar(parteDanioDisparo);
		assertEquals(parteDanioDisparo.estaDaniada(), false);
	}

	// Parte que tiene mas de una vida
	int vida = 4;

	@Test
	public void testAlRecibirUnDisparoNoEsDestruida() {
		ParteDanioDisparo parteDanioDisparo = new ParteDanioDisparo(vida);
		DisparoConvencional disparo = new DisparoConvencional();
		disparo.afectar(parteDanioDisparo);
		assertEquals(parteDanioDisparo.estaDestruida(), false);
	}

	@Test
	public void testAlRecibirUnDisparioEsDaniada() {
		ParteDanioDisparo parteDanioDisparo = new ParteDanioDisparo(vida);
		DisparoConvencional disparo = new DisparoConvencional();
		disparo.afectar(parteDanioDisparo);
		assertEquals(parteDanioDisparo.estaDaniada(), true);
	}

	@Test
	public void testAlRecibirNVidaCantidadDeDisparosEsDestruido() {
		ParteDanioDisparo parteDanioDisparo = new ParteDanioDisparo(vida);
		DisparoConvencional disparo = new DisparoConvencional();
		for (int i = 1; i <= vida; i++) {
			disparo.afectar(parteDanioDisparo);
		}
		assertEquals(parteDanioDisparo.estaDestruida(), true);
	}
}
