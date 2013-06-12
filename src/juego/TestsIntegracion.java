package juego;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import barcos.Vector;
import excepciones.DisparoInvalido;

public class TestsIntegracion {
	@Test
	public void testNoGanaAlComienzo() {
		Juego juego = new Juego();
		assertFalse(juego.gano());
	}

	@Test
	public void testPierdeAlGastarMuchosPuntos() {
		Juego juego = new Juego();
		Vector posicion = new Vector(1, 1);
		for (int i = 0; i < 50; i++) {
			try {
				juego.colocarDisparo("disparoconvencional", posicion);
			} catch (DisparoInvalido e) {
				System.out.println("\nDisparo Invalido\n");
			}
		}
		assertTrue(juego.perdio());
	}

	@Test
	public void testNoPierdeConPocosDisparos() {
		Juego juego = new Juego();
		Vector posicion = new Vector(1, 1);
		for (int i = 0; i < 20; i++) {
			try {
				juego.colocarDisparo("disparoconvencional", posicion);
			} catch (DisparoInvalido e) {
				System.out.println("\nDisparo Invalido\n");
			}
		}
		assertFalse(juego.perdio());
	}

	@Test
	public void test() {
		Juego juego = new Juego();
		Vector posicion = new Vector(1, 1);
		for (int i = 0; i < 20; i++) {
			try {
				juego.colocarDisparo("disparoconvencional", posicion);
			} catch (DisparoInvalido e) {
				System.out.println("\nDisparo Invalido\n");
			}
		}
		assertFalse(juego.perdio());
	}

}
