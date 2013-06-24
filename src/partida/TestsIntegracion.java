package partida;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import barcos.Vector;
import excepciones.DisparoInvalido;

public class TestsIntegracion {
	@Test
	public void testNoGanaAlComienzo() {
		Partida partida = new Partida();
		assertFalse(partida.gano());
	}

	@Test
	public void testPierdeAlGastarMuchosPuntos() {
		Partida partida = new Partida();
		Vector posicion = new Vector(1, 1);
		for (int i = 0; i < 50; i++) {
			try {
				partida.colocarDisparo("disparoconvencional", posicion);
			} catch (DisparoInvalido e) {
				System.out.println("\nDisparo Invalido\n");
			}
		}
		assertTrue(partida.perdio());
	}

	@Test
	public void testNoPierdeConPocosDisparos() {
		Partida partida = new Partida();
		Vector posicion = new Vector(1, 1);
		for (int i = 0; i < 20; i++) {
			try {
				partida.colocarDisparo("disparoconvencional", posicion);
			} catch (DisparoInvalido e) {
				System.out.println("\nDisparo Invalido\n");
			}
		}
		assertFalse(partida.perdio());
	}

	@Test
	public void test() {
		Partida partida = new Partida();
		Vector posicion = new Vector(1, 1);
		for (int i = 0; i < 20; i++) {
			try {
				partida.colocarDisparo("disparoconvencional", posicion);
			} catch (DisparoInvalido e) {
				System.out.println("\nDisparo Invalido\n");
			}
		}
		assertFalse(partida.perdio());
	}

}
