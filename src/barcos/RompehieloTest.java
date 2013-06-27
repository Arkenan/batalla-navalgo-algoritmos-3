package barcos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import partes.Parte;
import barcos.strategies.MovimientoLinealStrategy;
import disparos.DisparoConvencional;
import disparos.Mina;
import disparos.MinaContacto;
import escenario.Tablero;
import excepciones.PosicionInvalida;

public class RompehieloTest {

	@Test
	public void testParaComprobarQueCuandoSeLeDisparaUnaVezoNoSeDestruye() {
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(1, 0);
		DisparoConvencional disparo = new DisparoConvencional();
		Rompehielo rompehielo = new Rompehielo(orientacion, estrategia);
		estrategia.setBarco(rompehielo);

		ArrayList<Parte> lasPartes = rompehielo.obtenerPartes();
		Parte unaParte = lasPartes.get(1);
		unaParte.explosion(disparo);

		assertFalse(unaParte.estaDestruida());

	}

	@Test
	public void testParaComprobarQueCuandoSeCreaNoEsteDestruido() {
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(1, 0);
		Rompehielo rompehielo = new Rompehielo(orientacion, estrategia);
		estrategia.setBarco(rompehielo);

		assertEquals(rompehielo.estaDestruido(), false);
	}

	@Test
	public void testParaComprobarQueCuandoSeLeDisparaUnaVezNoSeDestruye() {
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(1, 0);
		DisparoConvencional disparo = new DisparoConvencional();
		Rompehielo rompehielo = new Rompehielo(orientacion, estrategia);
		estrategia.setBarco(rompehielo);

		ArrayList<Parte> lasPartes = rompehielo.obtenerPartes();
		Parte unaParte = lasPartes.get(1);
		unaParte.explosion(disparo);
		assertEquals(rompehielo.estaDestruido(), false);

	}

	@Test
	public void testParaComprobarQueCuandoSeLeDisparaEnTodasLasPartesNoSeDestruye() {
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(1, 0);
		DisparoConvencional disparo = new DisparoConvencional();
		Rompehielo rompehielo = new Rompehielo(orientacion, estrategia);
		estrategia.setBarco(rompehielo);

		ArrayList<Parte> lasPartes = rompehielo.obtenerPartes();
		for (int i = 0; i < rompehielo.obtenerTamanio(); i++) {
			Parte unaParte = lasPartes.get(i);
			unaParte.explosion(disparo);
		}
		assertFalse(rompehielo.estaDestruido());
	}

	@Test
	public void testParaComprobarQueCuandoSeLeDisparaEnTodasLasPartesDosVecesSeDestruye() {
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(1, 0);
		DisparoConvencional disparo = new DisparoConvencional();
		Rompehielo rompehielo = new Rompehielo(orientacion, estrategia);
		estrategia.setBarco(rompehielo);

		ArrayList<Parte> lasPartes = rompehielo.obtenerPartes();
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < rompehielo.obtenerTamanio(); i++) {
				Parte unaParte = lasPartes.get(i);
				unaParte.explosion(disparo);
			}
		}

		assertEquals(rompehielo.estaDestruido(), true);
	}

	@Test
	public void testParaComprobarQueCuandoExplotaUnaMinaDosVecesSeDestruyeLaParte() {
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(1, 0);
		Mina mina = new MinaContacto();
		Rompehielo rompehielo = new Rompehielo(orientacion, estrategia);
		estrategia.setBarco(rompehielo);

		ArrayList<Parte> lasPartes = rompehielo.obtenerPartes();

		Parte unaParte = lasPartes.get(1);

		unaParte.explosion(mina);
		unaParte.explosion(mina);

		assertTrue(unaParte.estaDestruida());
	}

	@Test
	public void testParaComprobarQueCuandoSeExplotaUnaMinaEnTodasLasPartesNoSeDestruye() {
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(1, 0);
		Mina mina = new MinaContacto();
		Rompehielo rompehielo = new Rompehielo(orientacion, estrategia);
		estrategia.setBarco(rompehielo);

		ArrayList<Parte> lasPartes = rompehielo.obtenerPartes();
		for (int i = 0; i < rompehielo.obtenerTamanio(); i++) {
			Parte unaParte = lasPartes.get(i);
			unaParte.explosion(mina);
		}
		assertFalse(rompehielo.estaDestruido());
	}

	@Test
	public void testParaComprobarQueCuandoSeExplotaUnaMinaEnTodasLasPartesDosVecesSeDestruye() {
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(1, 0);
		Mina mina = new MinaContacto();
		Rompehielo rompehielo = new Rompehielo(orientacion, estrategia);
		estrategia.setBarco(rompehielo);

		ArrayList<Parte> lasPartes = rompehielo.obtenerPartes();
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < rompehielo.obtenerTamanio(); i++) {
				Parte unaParte = lasPartes.get(i);
				unaParte.explosion(mina);
			}
		}

		assertEquals(rompehielo.estaDestruido(), true);
	}

	@Test
	public void testParaComprobarQueCuandoExplotaUnaMinaNoSeDestruye() {
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(1, 0);
		Mina mina = new MinaContacto();
		Rompehielo rompehielo = new Rompehielo(orientacion, estrategia);
		estrategia.setBarco(rompehielo);

		ArrayList<Parte> lasPartes = rompehielo.obtenerPartes();

		Parte unaParte = lasPartes.get(1);

		unaParte.explosion(mina);

		assertEquals(rompehielo.estaDestruido(), false);
	}

	@Test
	public void testParaComprobarQueSeColocaCorrectamente() {
		Tablero tablero = Tablero.getTablero();
		Vector posicion = new Vector(5, 4);

		Vector posicion1 = new Vector(5, 4);
		Vector posicion2 = new Vector(5, 5);
		Vector posicion3 = new Vector(5, 6);

		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(0, 1);
		Rompehielo rompehielo = new Rompehielo(orientacion, estrategia);
		estrategia.setBarco(rompehielo);
		rompehielo.colocarEnTablero(posicion);
		ArrayList<Parte> partes = rompehielo.obtenerPartes();
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)),
				true);
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)),
				true);
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion3, partes.get(2)),
				true);
	}

	@Test
	public void testParaComprobarQueLanzaUnaExcepcionCuandoNoSePuedeColocarEnUnaPosicion()
			throws PosicionInvalida {
		Vector posicion = new Vector(10, 10);
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(1, 0);
		boolean excepcionLanzada = false;
		Rompehielo rompehielo = new Rompehielo(orientacion, estrategia);
		estrategia.setBarco(rompehielo);
		try {
			rompehielo.colocarEnTablero(posicion);
		} catch (PosicionInvalida error) {
			excepcionLanzada = true;
		}
		assertTrue(excepcionLanzada);
	}

	@Test
	public void testParaComprobarQueSePosicionCambioDeManeraCorrecta() {
		Vector posicion = new Vector(5, 4);
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(1, 0);
		Rompehielo rompehielo = new Rompehielo(orientacion, estrategia);
		estrategia.setBarco(rompehielo);
		rompehielo.colocarEnTablero(posicion);
		rompehielo.moverse();
		Vector posActual = rompehielo.obtenerPosicion();
		assertEquals(posActual.x(), 6);
		assertEquals(posActual.y(), 5);
	}

	@Test
	public void testParaComprobarQueCambiaDeDireccionAlLlegarAlBorde() {
		Vector posicion = new Vector(10, 8);
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(0, 1);
		Rompehielo rompehielo = new Rompehielo(orientacion, estrategia);
		estrategia.setBarco(rompehielo);
		rompehielo.colocarEnTablero(posicion);
		rompehielo.moverse();
		Vector nuevoMovimiento = rompehielo.obtenerDireccionMovimiento();

		assertEquals(nuevoMovimiento.x(), -1);
		assertEquals(nuevoMovimiento.y(), -1);

	}

	@Test
	public void testParaComprobarQueLasPartesSeEncuentranEnLasNuevasPosiciones() {
		Tablero tablero = Tablero.getTablero();
		Vector posicion = new Vector(5, 5);
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(0, 1);
		Rompehielo rompehielo = new Rompehielo(orientacion, estrategia);
		estrategia.setBarco(rompehielo);

		Vector posicion1 = new Vector(6, 6);
		Vector posicion2 = new Vector(6, 7);
		Vector posicion3 = new Vector(6, 8);
		rompehielo.colocarEnTablero(posicion);
		rompehielo.moverse();

		ArrayList<Parte> partes = rompehielo.obtenerPartes();

		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)),
				true);
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)),
				true);
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion3, partes.get(2)),
				true);

	}

	@Test
	public void testParaComprobarQueLasPartesYaNoSeEncuentranEnSuPosicionAnterior() {
		Tablero tablero = Tablero.getTablero();
		Vector posicion = new Vector(5, 5);
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(0, 1);
		Rompehielo rompehielo = new Rompehielo(orientacion, estrategia);
		estrategia.setBarco(rompehielo);

		Vector posicion1 = new Vector(5, 5);
		Vector posicion2 = new Vector(5, 6);
		Vector posicion3 = new Vector(5, 7);
		rompehielo.colocarEnTablero(posicion);
		rompehielo.moverse();

		ArrayList<Parte> partes = rompehielo.obtenerPartes();

		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)),
				false);
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)),
				false);
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion3, partes.get(2)),
				false);
	}

	@Test
	public void testParaComprobarCuandoSeColocaSoloSeEncuentraUnaParteEnCadaPosicion() {
		Tablero tablero = Tablero.getTablero();
		Vector posicion = new Vector(5, 5);
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(1, 0);
		Rompehielo rompehielo = new Rompehielo(orientacion, estrategia);
		estrategia.setBarco(rompehielo);
		rompehielo.colocarEnTablero(posicion);
		Vector posicion0 = new Vector(5, 5);
		Vector posicion1 = new Vector(5, 6);
		Vector posicion2 = new Vector(5, 7);

		ArrayList<Parte> partes = rompehielo.obtenerPartes();

		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion0, partes.get(1)),
				false);
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion0, partes.get(2)),
				false);

		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)),
				false);
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion1, partes.get(2)),
				false);

		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion2, partes.get(0)),
				false);
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)),
				false);

	}

}
