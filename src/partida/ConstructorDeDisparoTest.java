package partida;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import partes.ParteDanioDisparo;
import partes.ParteDanioTotal;
import barcos.Vector;
import disparos.Disparo;
import escenario.Tablero;
import excepciones.PosicionInvalida;

public class ConstructorDeDisparoTest {

	@Test
	public void testAlCrearUnaMinaDeContactoConElConstructor() {
		ConstructorDeDisparo constructor = new ConstructorDeDisparo();
		Vector posicion = new Vector(1, 2);
		Disparo disparo = constructor
				.construirDisparo("minacontacto", posicion);
		assertEquals(disparo.costo(), 150);

	}

	@Test
	public void testCreoUnDisparoConElConstructorHacerloExplotarYVeoSiLaParteQuedoDaniada() {
		Tablero tablero = Tablero.getTablero();
		ConstructorDeDisparo constructor = new ConstructorDeDisparo();
		Vector posicion = new Vector(1, 2);
		ParteDanioTotal parte = new ParteDanioTotal(1);
		tablero.colocarElemento(posicion, parte);
		Disparo disparo = constructor.construirDisparo("disparoconvencional",
				posicion);
		disparo.explotar();

		assertTrue(parte.estaDaniada());
	}

	@Test
	public void testAlConstruirUnDisparoEnUnaPosicionFueraDelTableroDeberiaDarError() {
		boolean valor = false;
		try {
			ConstructorDeDisparo constructor = new ConstructorDeDisparo();
			Disparo disparo = constructor.construirDisparo(
					"disparoconvencional", new Vector(11, 123));
		} catch (PosicionInvalida error) {
			valor = true;
		}
		assertTrue(valor);
	}

	@Test
	public void testAlCrearUnaMinaConElConstructorHacerlaExplotarYVerSiLaParteDanioDisparQuedoDaniadaDeberiaSerFalso() {
		Tablero tablero = Tablero.getTablero();
		ConstructorDeDisparo constructor = new ConstructorDeDisparo();
		Vector posicion = new Vector(1, 2);
		ParteDanioDisparo parte = new ParteDanioDisparo(1);
		tablero.colocarElemento(posicion, parte);
		Disparo disparo = constructor.construirDisparo("minadobleradio",
				posicion);
		disparo.explotar();

		assertFalse(parte.estaDaniada());
	}
}
