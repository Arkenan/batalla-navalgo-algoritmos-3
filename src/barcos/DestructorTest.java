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

public class DestructorTest {

    // TAM = 3
    @Test
    public void testParaComprobarQueSeGuardaCorrectamenteElMovimiento() {

        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Destructor nave = new Destructor(movimiento, orientacion);

        Vector unMovimientoVector = nave.obtenerDireccionMovimiento();

        assertEquals(unMovimientoVector.x, movimiento.x);
        assertEquals(unMovimientoVector.y, movimiento.y);

    }

    @Test
    public void testParaComprobarQueSeInvierteCorrectamenteElMovimiento() {

        Vector movimiento = new Vector(1, 1);
        Vector movimientoInvertido = new Vector(-1, -1);
        Vector orientacion = new Vector(1, 0);
        Destructor nave = new Destructor(movimiento, orientacion);

        nave.invertirDireccionMovimiento();
        Vector nuevoMovimiento = nave.obtenerDireccionMovimiento();

        assertEquals(nuevoMovimiento.x, movimientoInvertido.x);
        assertEquals(nuevoMovimiento.y, movimientoInvertido.y);

    }

    @Test
    public void testParaComprobarQueCuandoSeLeDisparaSeDania() {

        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Destructor nave = new Destructor(movimiento, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();
        Parte unaParte = lasPartes.get(1);
        unaParte.explosion(disparo);

        assertEquals(nave.estaDaniado(), true);

    }

    @Test
    public void testParaComprobarQueCuandoSeCreaNoEsteDaniado() {

        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Destructor nave = new Destructor(movimiento, orientacion);

        assertEquals(nave.estaDaniado(), false);
    }

    @Test
    public void testParaComprobarQueCuandoSeCreaNoEsteDestruido() {

        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Destructor nave = new Destructor(movimiento, orientacion);

        assertEquals(nave.estaDestruido(), false);
    }

    @Test
    public void testParaComprobarQueCuandoSeLeDisparaNoSeDestruye() {

        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Destructor nave = new Destructor(movimiento, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();
        Parte unaParte = lasPartes.get(1);
        unaParte.explosion(disparo);
        assertEquals(nave.estaDestruido(), false);

    }

    @Test
    public void testParaComprobarQueCuandoSeLeDisparaEnTodasLasPartesSeDestruye() {

        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Destructor nave = new Destructor(movimiento, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();
        for (int i = 0; i < nave.obtenerTamanio(); i++) {
            Parte unaParte = lasPartes.get(i);
            unaParte.explosion(disparo);
        }
        assertEquals(nave.estaDestruido(), true);
    }

    @Test
    public void testParaComprobarQueCuandoExplotaUnaMinaNoSeDania() {

        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Mina mina = new MinaContacto();
        Destructor nave = new Destructor(movimiento, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();

        Parte unaParte = lasPartes.get(1);

        unaParte.explosion(mina);

        assertFalse(nave.estaDaniado());
    }

    @Test
    public void testParaComprobarQueCuandoSeExplotaUnaMinaEnTodasLasPartesNoSeDestruye() {

        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Mina mina = new MinaContacto();
        Destructor nave = new Destructor(movimiento, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();
        for (int i = 0; i < nave.obtenerTamanio(); i++) {
            Parte unaParte = lasPartes.get(i);
            unaParte.explosion(mina);
        }
        assertEquals(nave.estaDestruido(), false);
    }

    @Test
    public void testParaComprobarQueCuandoExplotaUnaMinaNoSeDestruye() {

        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Mina mina = new MinaContacto();
        Destructor nave = new Destructor(movimiento, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();

        Parte unaParte = lasPartes.get(1);

        unaParte.explosion(mina);

        assertFalse(nave.estaDestruido());
    }

    @Test
    public void testParaComprobarQueSeColocaCorrectamente() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion1 = new Vector(5, 4);
        Vector posicion2 = new Vector(5, 5);
        Vector posicion3 = new Vector(5, 6);

        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(0, 1);
        Destructor nave = new Destructor(movimiento, orientacion);
        nave.colocarEnTablero(posicion1);

        ArrayList<Parte> partes = nave.obtenerPartes();

        assertTrue(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)));
        assertTrue(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)));
        assertTrue(tablero.elementoPerteneceAlCasillero(posicion3, partes.get(2)));
    }

    @Test
    public void testParaComprobarQueLanzaUnaExcepcionCuandoNoSePuedeColocarEnUnaPosicion() throws PosicionInvalida {
        Vector posicion = new Vector(10, 10);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        boolean excepcionLanzada = false;

        Destructor nave = new Destructor(movimiento, orientacion);
        try {
            nave.colocarEnTablero(posicion);
        } catch (PosicionInvalida e) {
            excepcionLanzada = true;
        }
        assertTrue(excepcionLanzada);
    }

    @Test
    public void testParaComprobarQueSePosicionCambioDeManeraCorrecta() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Destructor nave = new Destructor(movimiento, orientacion);
        nave.colocarEnTablero(posicion);
        nave.moverse();
        Vector posActual = nave.obtenerPosicion();
        assertEquals(posActual.x(), 6);
        assertEquals(posActual.y(), 6);
    }

    @Test
    public void testParaComprobarQueCambiaDeDireccionAlLlegarAlBorde() {
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(0, 1);
        Vector posicion = new Vector(8, 8);
        Destructor nave = new Destructor(movimiento, orientacion);
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
        Destructor nave = new Destructor(movimiento, orientacion);
        nave.colocarEnTablero(posicion);

        Vector posicion1 = new Vector(6, 6);
        Vector posicion2 = new Vector(6, 7);
        Vector posicion3 = new Vector(6, 8);

        nave.moverse();

        ArrayList<Parte> partes = nave.obtenerPartes();

        assertTrue(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)));
        assertTrue(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)));
        assertTrue(tablero.elementoPerteneceAlCasillero(posicion3, partes.get(2)));

    }

    @Test
    public void testParaComprobarQueLasPartesYaNoSeEncuentranEnSuPosicionAnterior() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(0, 1);
        Destructor nave = new Destructor(movimiento, orientacion);
        nave.colocarEnTablero(posicion);
        Vector posicion1 = new Vector(5, 5);
        Vector posicion2 = new Vector(5, 6);
        Vector posicion3 = new Vector(5, 7);

        nave.moverse();

        ArrayList<Parte> partes = nave.obtenerPartes();

        assertFalse(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)));
        assertFalse(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)));
        assertFalse(tablero.elementoPerteneceAlCasillero(posicion3, partes.get(2)));
    }

    @Test
    public void testParaComprobarCuandoSeColocaSoloSeEncuentraUnaParteEnCadaPosicion() {
        Tablero tablero = Tablero.getTablero();

        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(0, 1);
        Destructor lancha = new Destructor(movimiento, orientacion);

        Vector posicion0 = new Vector(5, 5);
        Vector posicion1 = new Vector(5, 6);
        Vector posicion2 = new Vector(5, 7);

        ArrayList<Parte> partes = lancha.obtenerPartes();

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion0, partes.get(1)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion0, partes.get(2)), false);

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(2)), false);

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(0)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)), false);

    }

}
