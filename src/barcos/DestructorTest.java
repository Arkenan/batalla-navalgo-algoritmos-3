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

public class DestructorTest {

    // TAM = 3
    @Test
    public void testParaComprobarQueCuandoSeLeDisparaSeDania() {

        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Destructor destructor = new Destructor(orientacion, estrategia);
        estrategia.setBarco(destructor);

        ArrayList<Parte> lasPartes = destructor.obtenerPartes();
        Parte unaParte = lasPartes.get(1);
        unaParte.explosion(disparo);

        assertEquals(destructor.estaDaniado(), true);

    }

    @Test
    public void testParaComprobarQueCuandoSeCreaNoEsteDaniado() {

        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        Destructor destructor = new Destructor(orientacion, estrategia);
        estrategia.setBarco(destructor);

        assertEquals(destructor.estaDaniado(), false);
    }

    @Test
    public void testParaComprobarQueCuandoSeCreaNoEsteDestruido() {

        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        Destructor destructor = new Destructor(orientacion, estrategia);
        estrategia.setBarco(destructor);

        assertEquals(destructor.estaDestruido(), false);
    }

    @Test
    public void testParaComprobarQueCuandoSeLeDisparaNoSeDestruye() {

        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Destructor destructor = new Destructor(orientacion, estrategia);
        estrategia.setBarco(destructor);

        ArrayList<Parte> lasPartes = destructor.obtenerPartes();
        Parte unaParte = lasPartes.get(1);
        unaParte.explosion(disparo);
        assertEquals(destructor.estaDestruido(), false);

    }

    @Test
    public void testParaComprobarQueCuandoSeLeDisparaEnTodasLasPartesSeDestruye() {

        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Destructor destructor = new Destructor(orientacion, estrategia);
        estrategia.setBarco(destructor);

        ArrayList<Parte> lasPartes = destructor.obtenerPartes();
        for (int i = 0; i < destructor.obtenerTamanio(); i++) {
            Parte unaParte = lasPartes.get(i);
            unaParte.explosion(disparo);
        }
        assertEquals(destructor.estaDestruido(), true);
    }

    @Test
    public void testParaComprobarQueCuandoExplotaUnaMinaNoSeDania() {

        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        Mina mina = new MinaContacto();
        Destructor destructor = new Destructor(orientacion, estrategia);
        estrategia.setBarco(destructor);

        ArrayList<Parte> lasPartes = destructor.obtenerPartes();

        Parte unaParte = lasPartes.get(1);

        unaParte.explosion(mina);

        assertFalse(destructor.estaDaniado());
    }

    @Test
    public void testParaComprobarQueCuandoSeExplotaUnaMinaEnTodasLasPartesNoSeDestruye() {

        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        Mina mina = new MinaContacto();
        Destructor destructor = new Destructor(orientacion, estrategia);
        estrategia.setBarco(destructor);

        ArrayList<Parte> lasPartes = destructor.obtenerPartes();
        for (int i = 0; i < destructor.obtenerTamanio(); i++) {
            Parte unaParte = lasPartes.get(i);
            unaParte.explosion(mina);
        }
        assertEquals(destructor.estaDestruido(), false);
    }

    @Test
    public void testParaComprobarQueCuandoExplotaUnaMinaNoSeDestruye() {

        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        Mina mina = new MinaContacto();
        Destructor destructor = new Destructor(orientacion, estrategia);
        estrategia.setBarco(destructor);

        ArrayList<Parte> lasPartes = destructor.obtenerPartes();

        Parte unaParte = lasPartes.get(1);

        unaParte.explosion(mina);

        assertFalse(destructor.estaDestruido());
    }

    @Test
    public void testParaComprobarQueSeColocaCorrectamente() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion1 = new Vector(5, 4);
        Vector posicion2 = new Vector(5, 5);
        Vector posicion3 = new Vector(5, 6);

        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(0, 1);
        Destructor destructor = new Destructor(orientacion, estrategia);
        estrategia.setBarco(destructor);
        destructor.colocarEnTablero(posicion1);

        ArrayList<Parte> partes = destructor.obtenerPartes();

        assertTrue(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)));
        assertTrue(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)));
        assertTrue(tablero.elementoPerteneceAlCasillero(posicion3, partes.get(2)));
    }

    @Test
    public void testParaComprobarQueLanzaUnaExcepcionCuandoNoSePuedeColocarEnUnaPosicion() throws PosicionInvalida {
        Vector posicion = new Vector(10, 10);
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        boolean excepcionLanzada = false;

        Destructor destructor = new Destructor(orientacion, estrategia);
        estrategia.setBarco(destructor);
        try {
            destructor.colocarEnTablero(posicion);
        } catch (PosicionInvalida e) {
            excepcionLanzada = true;
        }
        assertTrue(excepcionLanzada);
    }

    @Test
    public void testParaComprobarQueSePosicionCambioDeManeraCorrecta() {
        Vector posicion = new Vector(5, 5);
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        Destructor destructor = new Destructor(orientacion, estrategia);
        estrategia.setBarco(destructor);
        destructor.colocarEnTablero(posicion);
        destructor.moverse();
        Vector posActual = destructor.obtenerPosicion();
        assertEquals(posActual.x(), 6);
        assertEquals(posActual.y(), 6);
    }

    @Test
    public void testParaComprobarQueCambiaDeDireccionAlLlegarAlBorde() {
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(0, 1);
        Vector posicion = new Vector(8, 8);
        Destructor destructor = new Destructor(orientacion, estrategia);
        estrategia.setBarco(destructor);
        destructor.colocarEnTablero(posicion);
        destructor.moverse();
        Vector nuevoMovimiento = destructor.obtenerDireccionMovimiento();

        assertEquals(nuevoMovimiento.x(), -1);
        assertEquals(nuevoMovimiento.y(), -1);

    }

    @Test
    public void testParaComprobarQueLasPartesSeEncuentranEnLasNuevasPosiciones() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 5);

        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(0, 1);
        Destructor destructor = new Destructor(orientacion, estrategia);
        estrategia.setBarco(destructor);
        destructor.colocarEnTablero(posicion);

        Vector posicion1 = new Vector(6, 6);
        Vector posicion2 = new Vector(6, 7);
        Vector posicion3 = new Vector(6, 8);

        destructor.moverse();

        ArrayList<Parte> partes = destructor.obtenerPartes();

        assertTrue(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)));
        assertTrue(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)));
        assertTrue(tablero.elementoPerteneceAlCasillero(posicion3, partes.get(2)));

    }

    @Test
    public void testParaComprobarQueLasPartesYaNoSeEncuentranEnSuPosicionAnterior() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 5);
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(0, 1);
        Destructor destructor = new Destructor(orientacion, estrategia);
        estrategia.setBarco(destructor);
        destructor.colocarEnTablero(posicion);
        Vector posicion1 = new Vector(5, 5);
        Vector posicion2 = new Vector(5, 6);
        Vector posicion3 = new Vector(5, 7);

        destructor.moverse();

        ArrayList<Parte> partes = destructor.obtenerPartes();

        assertFalse(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)));
        assertFalse(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)));
        assertFalse(tablero.elementoPerteneceAlCasillero(posicion3, partes.get(2)));
    }

    @Test
    public void testParaComprobarCuandoSeColocaSoloSeEncuentraUnaParteEnCadaPosicion() {
        Tablero tablero = Tablero.getTablero();

        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(0, 1);
        Destructor destructor = new Destructor(orientacion, estrategia);
        estrategia.setBarco(destructor);

        Vector posicion0 = new Vector(5, 5);
        Vector posicion1 = new Vector(5, 6);
        Vector posicion2 = new Vector(5, 7);

        ArrayList<Parte> partes = destructor.obtenerPartes();

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion0, partes.get(1)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion0, partes.get(2)), false);

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(2)), false);

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(0)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)), false);

    }

}
