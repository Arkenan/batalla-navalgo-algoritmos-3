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

public class PortaavionesTest {

	// TAM = 5

	@Test
	public void testParaComprobarQueCuandoSeCreaNoEsteDestruido() {
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(1, 0);
		Portaaviones portaaviones = new Portaaviones(orientacion, estrategia);
		estrategia.setBarco(portaaviones);

		assertFalse(portaaviones.estaDestruido());
	}

	@Test
	public void testParaComprobarQueCuandoSeLeDisparaUnaVezNoSeDestruye() {
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(1, 0);
		DisparoConvencional disparo = new DisparoConvencional();
		Portaaviones portaaviones = new Portaaviones(orientacion, estrategia);
		estrategia.setBarco(portaaviones);

		ArrayList<Parte> lasPartes = portaaviones.obtenerPartes();
		Parte unaParte = lasPartes.get(1);
		unaParte.explosion(disparo);
		assertEquals(portaaviones.estaDestruido(), false);

	}

	@Test
	public void testParaComprobarQueCuandoSeLeDisparaEnTodasLasPartesSeDestruye() {
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(1, 0);
		DisparoConvencional disparo = new DisparoConvencional();
		Portaaviones portaaviones = new Portaaviones(orientacion, estrategia);
		estrategia.setBarco(portaaviones);

		ArrayList<Parte> lasPartes = portaaviones.obtenerPartes();
		for (int i = 0; i < portaaviones.obtenerTamanio(); i++) {
			Parte unaParte = lasPartes.get(i);
			unaParte.explosion(disparo);
		}
		assertEquals(portaaviones.estaDestruido(), true);
	}

	@Test
	public void testParaComprobarQueCuandoSeExplotaUnaMinaEnTodasLasPartesSeDestruye() {
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(1, 0);
		Mina mina = new MinaContacto();
		Portaaviones portaaviones = new Portaaviones(orientacion, estrategia);
		estrategia.setBarco(portaaviones);

		ArrayList<Parte> lasPartes = portaaviones.obtenerPartes();
		for (int i = 0; i < portaaviones.obtenerTamanio(); i++) {
			Parte unaParte = lasPartes.get(i);
			unaParte.explosion(mina);
		}
		assertEquals(portaaviones.estaDestruido(), true);
	}

	@Test
	public void testParaComprobarQueCuandoExplotaUnaMinaNoSeDestruye() {
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(1, 0);
		Mina mina = new MinaContacto();
		Portaaviones portaaviones = new Portaaviones(orientacion, estrategia);
		estrategia.setBarco(portaaviones);

		ArrayList<Parte> lasPartes = portaaviones.obtenerPartes();

		Parte unaParte = lasPartes.get(1);

		unaParte.explosion(mina);

		assertFalse(portaaviones.estaDestruido());
	}

	@Test
	public void testParaComprobarQueSeColocaCorrectamente() {
		Tablero tablero = Tablero.getTablero();
		Vector posicion = new Vector(5, 4);

		Vector posicion1 = new Vector(5, 4);
		Vector posicion2 = new Vector(5, 5);
		Vector posicion3 = new Vector(5, 6);
		Vector posicion4 = new Vector(5, 7);
		Vector posicion5 = new Vector(5, 8);

		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(0, 1);
		Portaaviones portaaviones = new Portaaviones(orientacion, estrategia);
		estrategia.setBarco(portaaviones);
		portaaviones.colocarEnTablero(posicion);
		ArrayList<Parte> partes = portaaviones.obtenerPartes();
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)),
				true);
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)),
				true);
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion3, partes.get(2)),
				true);
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion4, partes.get(3)),
				true);
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion5, partes.get(4)),
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
		Portaaviones portaaviones = new Portaaviones(orientacion, estrategia);
		estrategia.setBarco(portaaviones);
		try {
			portaaviones.colocarEnTablero(posicion);
		} catch (PosicionInvalida error) {
			excepcionLanzada = true;
		}
		assertTrue(excepcionLanzada);
	}

	@Test
	public void testParaComprobarQueCambiaDeDireccionAlLlegarAlBorde() {
		Vector posicion = new Vector(10, 6);
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(0, 1);
		Portaaviones portaaviones = new Portaaviones(orientacion, estrategia);
		estrategia.setBarco(portaaviones);
		portaaviones.colocarEnTablero(posicion);
		portaaviones.moverse();
		Vector nuevoMovimiento = portaaviones.obtenerDireccionMovimiento();

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
		Portaaviones portaaviones = new Portaaviones(orientacion, estrategia);
		estrategia.setBarco(portaaviones);

		Vector posicion1 = new Vector(6, 6);
		Vector posicion2 = new Vector(6, 7);
		Vector posicion3 = new Vector(6, 8);
		Vector posicion4 = new Vector(6, 9);
		Vector posicion5 = new Vector(6, 10);
		portaaviones.colocarEnTablero(posicion);
		portaaviones.moverse();

		ArrayList<Parte> partes = portaaviones.obtenerPartes();

		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)),
				true);
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)),
				true);
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion3, partes.get(2)),
				true);
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion4, partes.get(3)),
				true);
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion5, partes.get(4)),
				true);

	}

	@Test
	public void testParaComprobarQueLasPartesYaNoSeEncuentranEnSuPosicionAnterior() {
		Tablero tablero = Tablero.getTablero();
		Vector posicion = new Vector(5, 5);
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(1, 0);
		Portaaviones portaaviones = new Portaaviones(orientacion, estrategia);
		estrategia.setBarco(portaaviones);

		Vector posicion1 = new Vector(5, 5);
		Vector posicion2 = new Vector(5, 6);
		Vector posicion3 = new Vector(5, 7);
		Vector posicion4 = new Vector(5, 8);
		Vector posicion5 = new Vector(5, 9);
		portaaviones.colocarEnTablero(posicion);
		portaaviones.moverse();

		ArrayList<Parte> partes = portaaviones.obtenerPartes();

		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)),
				false);
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)),
				false);
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion3, partes.get(2)),
				false);
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion4, partes.get(3)),
				false);
		assertEquals(
				tablero.elementoPerteneceAlCasillero(posicion5, partes.get(4)),
				false);

	}

	@Test
	public void testParaComprobarCuandoSeColocaSoloSeEncuentraUnaParteEnCadaPosicion() {
		Tablero tablero = Tablero.getTablero();
		MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(
				new Vector(1, 1));
		Vector orientacion = new Vector(1, 0);
		Portaaviones portaaviones = new Portaaviones(orientacion, estrategia);
		estrategia.setBarco(portaaviones);

		Vector posicion0 = new Vector(5, 5);
		Vector posicion1 = new Vector(5, 6);
		Vector posicion2 = new Vector(5, 7);
		Vector posicion3 = new Vector(5, 8);
		Vector posicion4 = new Vector(5, 9);

		ArrayList<Parte> partes = portaaviones.obtenerPartes();

		assertFalse(tablero.elementoPerteneceAlCasillero(posicion0,
				partes.get(1)));
		assertFalse(tablero.elementoPerteneceAlCasillero(posicion0,
				partes.get(2)));
		assertFalse(tablero.elementoPerteneceAlCasillero(posicion0,
				partes.get(3)));
		assertFalse(tablero.elementoPerteneceAlCasillero(posicion0,
				partes.get(4)));

		assertFalse(tablero.elementoPerteneceAlCasillero(posicion1,
				partes.get(0)));
		assertFalse(tablero.elementoPerteneceAlCasillero(posicion1,
				partes.get(2)));
		assertFalse(tablero.elementoPerteneceAlCasillero(posicion1,
				partes.get(3)));
		assertFalse(tablero.elementoPerteneceAlCasillero(posicion1,
				partes.get(4)));

		assertFalse(tablero.elementoPerteneceAlCasillero(posicion2,
				partes.get(0)));
		assertFalse(tablero.elementoPerteneceAlCasillero(posicion2,
				partes.get(1)));
		assertFalse(tablero.elementoPerteneceAlCasillero(posicion2,
				partes.get(3)));
		assertFalse(tablero.elementoPerteneceAlCasillero(posicion2,
				partes.get(4)));

		assertFalse(tablero.elementoPerteneceAlCasillero(posicion3,
				partes.get(0)));
		assertFalse(tablero.elementoPerteneceAlCasillero(posicion3,
				partes.get(1)));
		assertFalse(tablero.elementoPerteneceAlCasillero(posicion3,
				partes.get(2)));
		assertFalse(tablero.elementoPerteneceAlCasillero(posicion3,
				partes.get(4)));

		assertFalse(tablero.elementoPerteneceAlCasillero(posicion4,
				partes.get(0)));
		assertFalse(tablero.elementoPerteneceAlCasillero(posicion4,
				partes.get(1)));
		assertFalse(tablero.elementoPerteneceAlCasillero(posicion4,
				partes.get(2)));
		assertFalse(tablero.elementoPerteneceAlCasillero(posicion4,
				partes.get(3)));

	}
}
