package partida;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import barcos.Vector;

public class PartidaTest {

	@Test
	public void testAlCrearUnaPartidaNuevaLosPuntosDebenSerMayoresACero() {
		Partida partida = new Partida();
		assertTrue(partida.getPuntos() > 0);
	}

	@Test
	public void testAlPasarUnTurnoLosPuntosDebenDisminuir() {
		Partida partida = new Partida();
		int puntosIniciales = partida.getPuntos();
		partida.pasarTurno();
		assertTrue(puntosIniciales > partida.getPuntos());
	}

	@Test
	public void testAlTerminarseLosPuntosElMetodoFaltanPuntosParaSeguirDeberiaDarTrue() {
		Partida partida = new Partida();
		for (int x = 1; x <= 50; x++) {
			partida.colocarDisparo("disparoconvencional", new Vector(3, 3));
		}
		assertTrue(partida.faltanPuntosParaSeguir());
	}

	@Test
	public void testAlColocarUnaMinaDeberianDisminuirLosPuntos() {
		Partida partida = new Partida();
		Vector posicion = new Vector(10, 10);
		int puntosIniciales = partida.getPuntos();
		partida.colocarDisparo("minacontacto", posicion);
		assertTrue(puntosIniciales > partida.getPuntos());

	}

	@Test
	public void testAlTratarDeColocarDisparosEnPosicionesInvalidasNoDeberianDecrecerLosPuntos() {
		Partida partida = new Partida();
		int puntosIniciales = partida.getPuntos();
		for (int i = 0; i < 10; i++)
			partida.colocarDisparo("minacontacto", new Vector(101, 1100));
		assertEquals(puntosIniciales, partida.getPuntos());
	}
	/*
	 * En este tambien deberia agregarle posicion a movible
	 * 
	 * @Test public void testGanarUnaPartida() { Partida partida = new
	 * Partida(); ArrayList<Barco> barcos = partida.obtener(); ArrayList<Parte>
	 * partes; Vector posicionDisparo; for (int h = 1; h <= 2; h++) { for (int i
	 * = 0; i < barcos.size(); i++) { partes = (barcos.get(i)).obtenerPartes();
	 * for (int j = 0; j < partes.size(); j++) { posicionDisparo =
	 * (partes.get(j)).obtenerPosicion();
	 * partida.colocarDisparo("disparoconvencional", posicionDisparo); } } }
	 * assertTrue(partida.gano()); }
	 */

}
