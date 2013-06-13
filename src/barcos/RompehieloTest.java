package barcos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import partes.Parte;
import disparos.DisparoConvencional;
import disparos.Mina;
import disparos.MinaContacto;
import escenario.Tablero;
import excepciones.PosicionInvalida;

public class RompehieloTest {

    @Test
    public void testParaComprobarQueSeGuardaCorrectamenteElMovimiento() {
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Rompehielo nave = new Rompehielo(movimiento, orientacion);

        Vector unMovimientoVector = nave.obtenerDireccionMovimiento();

        assertEquals(unMovimientoVector.x(), movimiento.x());
        assertEquals(unMovimientoVector.y(), movimiento.y());

    }

    @Test
    public void testParaComprobarQueSeInvierteCorrectamenteElMovimiento() {
        Vector movimiento = new Vector(1, 1);
        Vector movimientoInvertido = new Vector(-1, -1);
        Vector orientacion = new Vector(1, 0);
        Rompehielo nave = new Rompehielo(movimiento, orientacion);

        nave.invertirDireccionMovimiento();
        Vector nuevoMovimiento = nave.obtenerDireccionMovimiento();

        assertEquals(nuevoMovimiento.x(), movimientoInvertido.x());
        assertEquals(nuevoMovimiento.y(), movimientoInvertido.y());

    }

    @Test
    public void testParaComprobarQueCuandoSeLeDisparaUnaVezSeDaniaPeroNoSeDestruye() {
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Rompehielo nave = new Rompehielo(movimiento, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();
        Parte unaParte = lasPartes.get(1);
        unaParte.explosion(disparo);

        assertTrue(unaParte.estaDaniada());
        assertFalse(unaParte.estaDestruida());

    }

    @Test
    public void testParaComprobarQueCuandoSeLeDisparaDosVezSeDania() {
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Rompehielo nave = new Rompehielo(movimiento, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();
        Parte unaParte = lasPartes.get(1);
        unaParte.explosion(disparo);
        unaParte.explosion(disparo);

        assertTrue(nave.estaDaniado());

    }

    @Test
    public void testParaComprobarQueCuandoSeCreaNoEsteDaniado() {
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Rompehielo nave = new Rompehielo(movimiento, orientacion);

        assertEquals(nave.estaDaniado(), false);
    }

    @Test
    public void testParaComprobarQueCuandoSeCreaNoEsteDestruido() {
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Rompehielo nave = new Rompehielo(movimiento, orientacion);

        assertEquals(nave.estaDestruido(), false);
    }

    @Test
    public void testParaComprobarQueCuandoSeLeDisparaUnaVezNoSeDestruye() {
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Rompehielo nave = new Rompehielo(movimiento, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();
        Parte unaParte = lasPartes.get(1);
        unaParte.explosion(disparo);
        assertEquals(nave.estaDestruido(), false);

    }

    @Test
    public void testParaComprobarQueCuandoSeLeDisparaEnTodasLasPartesNoSeDestruye() {
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Rompehielo nave = new Rompehielo(movimiento, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();
        for (int i = 0; i < nave.obtenerTamanio(); i++) {
            Parte unaParte = lasPartes.get(i);
            unaParte.explosion(disparo);
        }
        assertFalse(nave.estaDestruido());
    }

    @Test
    public void testParaComprobarQueCuandoSeLeDisparaEnTodasLasPartesDosVecesSeDestruye() {
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Rompehielo nave = new Rompehielo(movimiento, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < nave.obtenerTamanio(); i++) {
                Parte unaParte = lasPartes.get(i);
                unaParte.explosion(disparo);
            }
        }

        assertEquals(nave.estaDestruido(), true);
    }

    @Test
    public void testParaComprobarQueCuandoExplotaUnaMinaSeDania() {
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Mina mina = new MinaContacto();
        Rompehielo nave = new Rompehielo(movimiento, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();

        Parte unaParte = lasPartes.get(1);

        unaParte.explosion(mina);

        assertTrue(nave.estaDaniado());
        assertFalse(unaParte.estaDestruida());
    }

    @Test
    public void testParaComprobarQueCuandoExplotaUnaMinaDosVecesSeDestruyeLaParte() {
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Mina mina = new MinaContacto();
        Rompehielo nave = new Rompehielo(movimiento, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();

        Parte unaParte = lasPartes.get(1);

        unaParte.explosion(mina);
        unaParte.explosion(mina);

        assertTrue(unaParte.estaDestruida());
    }

    @Test
    public void testParaComprobarQueCuandoSeExplotaUnaMinaEnTodasLasPartesNoSeDestruye() {
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Mina mina = new MinaContacto();
        Rompehielo nave = new Rompehielo(movimiento, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();
        for (int i = 0; i < nave.obtenerTamanio(); i++) {
            Parte unaParte = lasPartes.get(i);
            unaParte.explosion(mina);
        }
        assertFalse(nave.estaDestruido());
    }

    @Test
    public void testParaComprobarQueCuandoSeExplotaUnaMinaEnTodasLasPartesDosVecesSeDestruye() {
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Mina mina = new MinaContacto();
        Rompehielo nave = new Rompehielo(movimiento, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < nave.obtenerTamanio(); i++) {
                Parte unaParte = lasPartes.get(i);
                unaParte.explosion(mina);
            }
        }

        assertEquals(nave.estaDestruido(), true);
    }

    @Test
    public void testParaComprobarQueCuandoExplotaUnaMinaNoSeDestruye() {
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Mina mina = new MinaContacto();
        Rompehielo nave = new Rompehielo(movimiento, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();

        Parte unaParte = lasPartes.get(1);

        unaParte.explosion(mina);

        assertEquals(nave.estaDestruido(), false);
    }

    @Test
    public void testParaComprobarQueSeColocaCorrectamente() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 4);

        Vector posicion1 = new Vector(5, 4);
        Vector posicion2 = new Vector(5, 5);
        Vector posicion3 = new Vector(5, 6);

        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(0, 1);
        Rompehielo nave = new Rompehielo(movimiento, orientacion);
        nave.colocarEnTablero(posicion);
        ArrayList<Parte> partes = nave.obtenerPartes();
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)), true);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)), true);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion3, partes.get(2)), true);
    }

    @Test
    public void testParaComprobarQueLanzaUnaExcepcionCuandoNoSePuedeColocarEnUnaPosicion() throws PosicionInvalida {
        Vector posicion = new Vector(10, 10);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        boolean excepcionLanzada = false;
        Rompehielo nave = new Rompehielo(movimiento, orientacion);
        try {
            nave.colocarEnTablero(posicion);
        } catch (PosicionInvalida error) {
            excepcionLanzada = true;
        }
        assertTrue(excepcionLanzada);
    }

    @Test
    public void testParaComprobarQueSePosicionCambioDeManeraCorrecta() {
        Vector posicion = new Vector(5, 4);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Rompehielo nave = new Rompehielo(movimiento, orientacion);
        nave.colocarEnTablero(posicion);
        nave.moverse();
        Vector posActual = nave.obtenerPosicion();
        assertEquals(posActual.x(), 6);
        assertEquals(posActual.y(), 5);
    }

    @Test
    public void testParaComprobarQueCambiaDeDireccionAlLlegarAlBorde() {
        Vector posicion = new Vector(10, 8);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(0, 1);
        Rompehielo nave = new Rompehielo(movimiento, orientacion);
        nave.colocarEnTablero(posicion);
        nave.moverse();
        Vector nuevoMovimiento = nave.obtenerDireccionMovimiento();

        assertEquals(nuevoMovimiento.x(), -1);
        assertEquals(nuevoMovimiento.y(), -1);

    }

    @Test
    public void testParaComprobarQueLasPartesSeEncuentranEnLasNuevasPosiciones() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(0, 1);
        Rompehielo nave = new Rompehielo(movimiento, orientacion);

        Vector posicion1 = new Vector(6, 6);
        Vector posicion2 = new Vector(6, 7);
        Vector posicion3 = new Vector(6, 8);
        nave.colocarEnTablero(posicion);
        nave.moverse();

        ArrayList<Parte> partes = nave.obtenerPartes();

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)), true);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)), true);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion3, partes.get(2)), true);

    }

    @Test
    public void testParaComprobarQueLasPartesYaNoSeEncuentranEnSuPosicionAnterior() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(0, 1);
        Rompehielo nave = new Rompehielo(movimiento, orientacion);

        Vector posicion1 = new Vector(5, 5);
        Vector posicion2 = new Vector(5, 6);
        Vector posicion3 = new Vector(5, 7);
        nave.colocarEnTablero(posicion);
        nave.moverse();

        ArrayList<Parte> partes = nave.obtenerPartes();

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion3, partes.get(2)), false);
    }

    @Test
    public void testParaComprobarCuandoSeColocaSoloSeEncuentraUnaParteEnCadaPosicion() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Rompehielo nave = new Rompehielo(movimiento, orientacion);
        nave.colocarEnTablero(posicion);
        Vector posicion0 = new Vector(5, 5);
        Vector posicion1 = new Vector(5, 6);
        Vector posicion2 = new Vector(5, 7);

        ArrayList<Parte> partes = nave.obtenerPartes();

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion0, partes.get(1)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion0, partes.get(2)), false);

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(2)), false);

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(0)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)), false);

    }

}
