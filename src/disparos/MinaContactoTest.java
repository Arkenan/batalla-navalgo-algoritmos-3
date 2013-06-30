package disparos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import partes.ParteDanioTotal;
import barcos.Vector;
import escenario.Tablero;
import excepciones.PosicionInvalida;

public class MinaContactoTest {

	@Test
	public void testAlCrearUnaMinaContactoYPreguntarleSiDebeDaniarSiendoQueNoHayBarcosDebeSerFalso() {

		MinaContacto mina = new MinaContacto();

		mina.cambiarCasillerosAfectados(new Vector(10, 10));

		assertFalse(mina.debeDaniarEnEsteTurno());

	}

	@Test
	public void testAlCrearUnaMInaDeContactoEnElLugarQueHayUnaParteYPreguntarleSiDebedaniarDebeDarTrue() {

		MinaContacto mina = new MinaContacto();

		ParteDanioTotal parte = new ParteDanioTotal(1);

		Tablero tablero = Tablero.getTablero();

		Vector posicion = new Vector(1, 1);

		tablero.colocarElemento(posicion, parte);

		mina.cambiarCasillerosAfectados(posicion);

		assertTrue(mina.debeDaniarEnEsteTurno());
	}

	@Test
	public void testAlCrearlaEnPosicionInvalidaDeberiaDarError() {
		MinaContacto unaMina = new MinaContacto();
		boolean valor = false;
		try {
			unaMina.cambiarCasillerosAfectados(new Vector(111, 231));
		} catch (PosicionInvalida error) {
			valor = true;
		}
		assertTrue(valor);
	}

}
