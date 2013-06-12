package partes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import barcos.Vector;
import disparos.DisparoConvencional;
import disparos.MinaContacto;

public class ParteTest {
	int vida = 2;

	@Test
	public void testAlDisPararSobreLaParteEstaPasaAEstarDaniada() {
		Parte parte = new Parte(vida);
		DisparoConvencional disparo = new DisparoConvencional();
		disparo.afectar(parte);
		assertEquals(parte.estaDaniada(), true);
	}

	@Test
	public void testAlExplotarUnaMinaSobreLaParteEstaPasaAEstarDaniada() {
		Parte parte = new Parte(vida);
		MinaContacto mina = new MinaContacto();
		mina.afectar(parte);
		assertEquals(parte.estaDaniada(), true);
	}

	@Test
	public void testAlRecibirUnDisparoYUnaExplosionDeMinaDeberiaEstarDestruida() {
		Parte parte = new Parte(vida);
		DisparoConvencional disparo = new DisparoConvencional();
		disparo.afectar(parte);
		MinaContacto mina = new MinaContacto();
		mina.afectar(parte);
		assertEquals(parte.estaDestruida(), true);
	}

	@Test
	public void testAlAsignarleUnaPosicionALaParteYLuegoPedirselaDeberianSerIguales() {
		Parte parte = new Parte(vida);
		Vector posicion = new Vector(1, 1);
		parte.cambiarPosicion(posicion);
		assertTrue(posicion.sonIguales(parte.obtenerPosicion()));
	}

}
