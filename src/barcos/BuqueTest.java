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

// TAM = 4

public class BuqueTest {

    @Test
    public void testParaComprobarQueSeConstruyeCorrectamente() {
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        Buque buque = new Buque(orientacion, estrategia);
        estrategia.setBarco(buque);

        System.out.println(1);
        ArrayList<Parte> partes = buque.obtenerPartes();
        System.out.println(1);
        System.out.println(partes.toString());
        System.out.println(1);
        assertEquals(partes.size(), buque.obtenerTamanio());
    }

    @Test
    public void testParaComprobarQueCuandoSeCreaNoEsteDaniado() {
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        Buque buque = new Buque(orientacion, estrategia);
        estrategia.setBarco(buque);

        assertFalse(buque.estaDaniado());
    }

    @Test
    public void testParaComprobarQueCuandoSeCreaNoEsteDestruido() {
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        Buque buque = new Buque(orientacion, estrategia);
        estrategia.setBarco(buque);

        assertFalse(buque.estaDestruido());
    }

    /*
     * esto ahora es de la estrategia
     * 
     * @Test public void testParaComprobarQueSeGuardaCorrectamenteElMovimiento() {
     * MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1)); Vector
     * orientacion = new Vector(1, 0); Buque buque = new Buque(orientacion, estrategia);
     * estrategia.setBarco(buque);
     * 
     * Vector unMovimientoVector = buque.obtenerDireccionMovimiento();
     * 
     * assertEquals(unMovimientoVector.x(), movimiento.x()); assertEquals(unMovimientoVector.y(),
     * movimiento.y());
     * 
     * }
     * 
     * 
     * @Test public void testParaComprobarQueSeInvierteCorrectamenteElMovimiento() {
     * MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1)); Vector
     * movimientoInvertido = new Vector(-1, -1); Vector orientacion = new Vector(1, 0); Buque buque
     * = new Buque(orientacion, estrategia); estrategia.setBarco(buque);
     * 
     * buque.invertirDireccionMovimiento(); Vector nuevoMovimiento =
     * buque.obtenerDireccionMovimiento();
     * 
     * assertEquals(nuevoMovimiento.x(), movimientoInvertido.x()); assertEquals(nuevoMovimiento.y(),
     * movimientoInvertido.y());
     * 
     * }
     */
    @Test
    public void testParaComprobarQueCuandoSeLeDisparaSeDania() {
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Buque buque = new Buque(orientacion, estrategia);
        estrategia.setBarco(buque);

        ArrayList<Parte> lasPartes = buque.obtenerPartes();
        Parte unaParte = lasPartes.get(1);
        unaParte.explosion(disparo);

        assertEquals(buque.estaDaniado(), true);

    }

    @Test
    public void testParaComprobarQueCuandoSeLeDisparaUnaVezSeDestruye() {
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Buque buque = new Buque(orientacion, estrategia);
        estrategia.setBarco(buque);

        ArrayList<Parte> lasPartes = buque.obtenerPartes();
        Parte unaParte = lasPartes.get(1);
        unaParte.explosion(disparo);

        assertEquals(buque.estaDestruido(), true);

    }

    @Test
    public void testParaComprobarQueCuandoSeLeDisparaEnTodasLasPartesSeDestruye() {
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();

        Buque buque = new Buque(orientacion, estrategia);
        estrategia.setBarco(buque);

        ArrayList<Parte> lasPartes = buque.obtenerPartes();
        for (int i = 0; i < buque.obtenerTamanio(); i++) {
            Parte unaParte = lasPartes.get(i);
            unaParte.explosion(disparo);
        }
        assertEquals(buque.estaDestruido(), true);
    }

    @Test
    public void testParaComprobarQueCuandoExplotaUnaMinaSeDania() {
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        Mina mina = new MinaContacto();

        Buque buque = new Buque(orientacion, estrategia);
        estrategia.setBarco(buque);

        ArrayList<Parte> lasPartes = buque.obtenerPartes();

        Parte unaParte = lasPartes.get(1);

        unaParte.explosion(mina);

        assertEquals(buque.estaDaniado(), true);
    }

    @Test
    public void testParaComprobarQueCuandoSeExplotaUnaMinaEnTodasLasPartesSeDestruye() {
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        Mina mina = new MinaContacto();

        Buque buque = new Buque(orientacion, estrategia);
        estrategia.setBarco(buque);

        ArrayList<Parte> lasPartes = buque.obtenerPartes();
        for (int i = 0; i < buque.obtenerTamanio(); i++) {
            Parte unaParte = lasPartes.get(i);
            unaParte.explosion(mina);
        }
        assertEquals(buque.estaDestruido(), true);
    }

    @Test
    public void testParaComprobarQueCuandoExplotaUnaMinaSeDestruye() {
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        Mina mina = new MinaContacto();
        Buque buque = new Buque(orientacion, estrategia);
        estrategia.setBarco(buque);

        ArrayList<Parte> lasPartes = buque.obtenerPartes();

        Parte unaParte = lasPartes.get(1);

        unaParte.explosion(mina);

        assertEquals(buque.estaDestruido(), true);
    }

    @Test
    public void testParaComprobarQueSeColocaCorrectamente() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 4);

        Vector posicion1 = new Vector(5, 4);
        Vector posicion2 = new Vector(5, 5);
        Vector posicion3 = new Vector(5, 6);
        Vector posicion4 = new Vector(5, 7);

        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(0, 1);
        Buque buque = new Buque(orientacion, estrategia);
        estrategia.setBarco(buque);
        buque.colocarEnTablero(posicion);

        ArrayList<Parte> partes = buque.obtenerPartes();

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)), true);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)), true);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion3, partes.get(2)), true);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion4, partes.get(3)), true);
    }

    @Test
    public void testParaComprobarQueLanzaUnaExcepcionCuandoNoSePuedeColocarEnUnaPosicion() throws PosicionInvalida {
        Vector posicion = new Vector(10, 10);
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        boolean excepcionLanzada = false;
        Buque buque = new Buque(orientacion, estrategia);
        estrategia.setBarco(buque);
        try {
            buque.colocarEnTablero(posicion);
        } catch (PosicionInvalida e) {
            excepcionLanzada = true;
        }

        assertTrue(excepcionLanzada);
    }

    @Test
    public void testParaComprobarQueCambiaDeDireccionAlLlegarAlBorde() {
        Vector posicion = new Vector(10, 7);
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(0, 1);
        Buque buque = new Buque(orientacion, estrategia);
        estrategia.setBarco(buque);
        buque.colocarEnTablero(posicion);
        buque.moverse();
        Vector nuevoMovimiento = buque.obtenerDireccionMovimiento();
        assertEquals(nuevoMovimiento.x(), -1);
        assertEquals(nuevoMovimiento.y(), -1);
    }

    @Test
    public void testParaComprobarQueLasPartesSeEncuentranEnLasNuevasPosiciones() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 5);
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(0, 1);
        Buque buque = new Buque(orientacion, estrategia);
        estrategia.setBarco(buque);
        buque.colocarEnTablero(posicion);

        Vector posicion1 = new Vector(6, 6);
        Vector posicion2 = new Vector(6, 7);
        Vector posicion3 = new Vector(6, 8);
        Vector posicion4 = new Vector(6, 9);

        buque.moverse();

        ArrayList<Parte> partes = buque.obtenerPartes();

        assertTrue(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)));
        assertTrue(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)));
        assertTrue(tablero.elementoPerteneceAlCasillero(posicion3, partes.get(2)));
        assertTrue(tablero.elementoPerteneceAlCasillero(posicion4, partes.get(3)));
    }

    @Test
    public void testParaComprobarQueLasPartesYaNoSeEncuentranEnSuPosicionAnterior() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 5);
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(0, 1);
        Buque buque = new Buque(orientacion, estrategia);
        estrategia.setBarco(buque);
        buque.colocarEnTablero(posicion);

        Vector posicion1 = new Vector(5, 5);
        Vector posicion2 = new Vector(5, 6);
        Vector posicion3 = new Vector(5, 7);
        Vector posicion4 = new Vector(5, 8);
        buque.moverse();

        ArrayList<Parte> partes = buque.obtenerPartes();

        assertFalse(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)));
        assertFalse(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)));
        assertFalse(tablero.elementoPerteneceAlCasillero(posicion3, partes.get(2)));
        assertFalse(tablero.elementoPerteneceAlCasillero(posicion4, partes.get(3)));

    }

    @Test
    public void testParaComprobarCuandoSeColocaSoloSeEncuentraUnaParteEnCadaPosicion() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 5);
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        Buque buque = new Buque(orientacion, estrategia);
        estrategia.setBarco(buque);
        buque.colocarEnTablero(posicion);

        Vector posicion0 = new Vector(5, 5);
        Vector posicion1 = new Vector(5, 6);
        Vector posicion2 = new Vector(5, 7);
        Vector posicion3 = new Vector(5, 8);

        ArrayList<Parte> partes = buque.obtenerPartes();

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion0, partes.get(1)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion0, partes.get(2)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion0, partes.get(3)), false);

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(2)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(3)), false);

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(0)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(3)), false);

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion3, partes.get(0)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion3, partes.get(1)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion3, partes.get(2)), false);

    }

}
