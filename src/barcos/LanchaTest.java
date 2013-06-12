package barcos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import partes.Parte;
import disparos.DisparoConvencional;
import disparos.Mina;
import disparos.MinaContacto;
import escenario.Tablero;
import excepciones.PosicionInvalida;

public class LanchaTest {

    // TAM = 2
    @Test
    public void testParaComprobarQueSeGuardaCorrectamenteElMovimiento() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Lancha lancha = new Lancha(movimiento, posicion, orientacion);

        Vector unMovimientoVector = lancha.obtenerDireccionMovimiento();

        assertEquals(unMovimientoVector.x(), movimiento.x());
        assertEquals(unMovimientoVector.y(), movimiento.y());

    }

    @Test
    public void testParaComprobarQueSeInvierteCorrectamenteElMovimiento() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector movimientoInvertido = new Vector(-1, -1);
        Vector orientacion = new Vector(1, 0);
        Lancha lancha = new Lancha(movimiento, posicion, orientacion);

        lancha.invertirDireccionMovimiento();
        Vector nuevoMovimiento = lancha.obtenerDireccionMovimiento();

        assertEquals(nuevoMovimiento.x(), movimientoInvertido.x());
        assertEquals(nuevoMovimiento.y(), movimientoInvertido.y());

    }

    @Test
    public void testParaComprobarQueCuandoSeLeDisparaSeDania() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Lancha lancha = new Lancha(movimiento, posicion, orientacion);

        ArrayList<Parte> lasPartes = lancha.obtenerPartes();
        Parte unaParte = lasPartes.get(1);
        unaParte.explosion(disparo);

        assertEquals(lancha.estaDaniado(), true);

    }

    @Test
    public void testParaComprobarQueCuandoSeCreaNoEsteDaniado() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Lancha lancha = new Lancha(movimiento, posicion, orientacion);

        assertEquals(lancha.estaDaniado(), false);
    }

    @Test
    public void testParaComprobarQueCuandoSeCreaNoEsteDestruido() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Lancha lancha = new Lancha(movimiento, posicion, orientacion);

        assertEquals(lancha.estaDestruido(), false);
    }

    @Test
    public void testParaComprobarQueCuandoSeLeDisparaUnaVezSoloDeDania() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Lancha lancha = new Lancha(movimiento, posicion, orientacion);

        ArrayList<Parte> lasPartes = lancha.obtenerPartes();
        Parte unaParte = lasPartes.get(1);
        unaParte.explosion(disparo);
        assertEquals(lancha.estaDestruido(), false);

    }

    @Test
    public void testParaComprobarQueCuandoSeLeDisparaEnTodasLasPartesSeDestruye() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Lancha lancha = new Lancha(movimiento, posicion, orientacion);

        ArrayList<Parte> lasPartes = lancha.obtenerPartes();
        for (int i = 0; i < lancha.obtenerTamanio(); i++) {
            Parte unaParte = lasPartes.get(i);
            unaParte.explosion(disparo);
        }
        assertEquals(lancha.estaDestruido(), true);
    }

    @Test
    public void testParaComprobarQueCuandoExplotaUnaMinaSeDania() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Mina mina = new MinaContacto();
        Lancha lancha = new Lancha(movimiento, posicion, orientacion);

        ArrayList<Parte> lasPartes = lancha.obtenerPartes();

        Parte unaParte = lasPartes.get(1);

        unaParte.explosion(mina);

        assertEquals(lancha.estaDaniado(), true);
    }

    @Test
    public void testParaComprobarQueCuandoSeExplotaUnaMinaEnTodasLasPartesSeDestruye() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Mina mina = new MinaContacto();
        Lancha lancha = new Lancha(movimiento, posicion, orientacion);

        ArrayList<Parte> lasPartes = lancha.obtenerPartes();
        for (int i = 0; i < lancha.obtenerTamanio(); i++) {
            Parte unaParte = lasPartes.get(i);
            unaParte.explosion(mina);
        }
        assertEquals(lancha.estaDestruido(), true);
    }

    @Test
    public void testParaComprobarQueSeCambiaCorrectamenteLaPosicion() {
        Vector posicion = new Vector(5, 4);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Lancha lancha = new Lancha(movimiento, posicion, orientacion);
        lancha.cambiarPosicion();
        Vector unaPosicion = lancha.obtenerPosicion();
        assertEquals(unaPosicion.x(), 6);
        assertEquals(unaPosicion.y(), 5);

    }

    @Test
    public void testParaComprobarQueSeColocaCorrectamente() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 4);

        Vector posicion1 = new Vector(5, 4);
        Vector posicion2 = new Vector(5, 5);

        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(0, 1);
        Lancha nave = new Lancha(movimiento, posicion, orientacion);
        ArrayList<Parte> partes = nave.obtenerPartes();

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)), true);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)), true);

    }

    @Test
    public void testParaComprobarQueLanzaUnaExcepcionCuandoNoSePuedeColocarEnUnaPosicion() throws PosicionInvalida {
        Vector posicion = new Vector(10, 10);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        boolean valor = false;
        try {
            Lancha lancha = new Lancha(movimiento, posicion, orientacion);
        } catch (PosicionInvalida error) {
            valor = true;
        }
        assertTrue(valor);
    }

    @Test
    public void testParaComprobarQueSuPosicionCambioDeManeraCorrecta() {
        Vector posicion = new Vector(5, 4);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Lancha lancha = new Lancha(movimiento, posicion, orientacion);
        lancha.moverse();
        Vector posActual = lancha.obtenerPosicion();
        assertEquals(posActual.x(), 6);
        assertEquals(posActual.y(), 5);
    }

    @Test
    public void testParaComprobarQueCambiaDeDireccionAlLlegarAlBorde() {
        Vector posicion = new Vector(10, 8);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(0, 1);
        Lancha lancha = new Lancha(movimiento, posicion, orientacion);

        lancha.moverse();
        Vector nuevoMovimiento = lancha.obtenerDireccionMovimiento();

        assertEquals(nuevoMovimiento.x(), -1);
        assertEquals(nuevoMovimiento.y(), -1);

    }

    @Test
    public void testParaComprobarQueLasPartesSeEncuentranEnLasNuevasPosiciones() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(0, 1);
        Lancha lancha = new Lancha(movimiento, posicion, orientacion);

        Vector posicion1 = new Vector(6, 6);
        Vector posicion2 = new Vector(6, 7);

        lancha.moverse();

        ArrayList<Parte> partes = lancha.obtenerPartes();

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)), true);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)), true);

    }

    @Test
    public void testParaComprobarQueLasPartesYaNoSeEncuentranEnSuPosicionAnterior() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Lancha lancha = new Lancha(movimiento, posicion, orientacion);

        Vector posicion1 = new Vector(5, 5);
        Vector posicion2 = new Vector(5, 6);

        lancha.moverse();

        ArrayList<Parte> partes = lancha.obtenerPartes();

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)), false);
    }

    @Test
    public void testParaComprobarCuandoSeColocaSoloSeEncuentraUnaParteEnCadaPosicion() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Lancha lancha = new Lancha(movimiento, posicion, orientacion);

        Vector posicion1 = new Vector(5, 5);
        Vector posicion2 = new Vector(5, 6);

        ArrayList<Parte> partes = lancha.obtenerPartes();

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(1)), false);

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(0)), false);

    }

    // -----------------------------------------------------------------------
    // Cambie esto de un test a un metodo interno que es usado por otro test.
    // Aunque no se si estara bien que dependa el test de este metodo.
    // -----------------------------------------------------------------------

}
