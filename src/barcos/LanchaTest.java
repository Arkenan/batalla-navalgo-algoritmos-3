package barcos;

import static org.junit.Assert.assertEquals;
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

public class LanchaTest {

    // TAM = 2

    @Test
    public void testParaComprobarQueCuandoSeLeDisparaSeDania() {
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Lancha lancha = new Lancha(orientacion, estrategia);
        estrategia.setBarco(lancha);

        ArrayList<Parte> lasPartes = lancha.obtenerPartes();
        Parte unaParte = lasPartes.get(1);
        unaParte.explosion(disparo);

        assertEquals(lancha.estaDaniado(), true);

    }

    @Test
    public void testParaComprobarQueCuandoSeCreaNoEsteDaniado() {
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        Lancha lancha = new Lancha(orientacion, estrategia);
        estrategia.setBarco(lancha);

        assertEquals(lancha.estaDaniado(), false);
    }

    @Test
    public void testParaComprobarQueCuandoSeCreaNoEsteDestruido() {
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        Lancha lancha = new Lancha(orientacion, estrategia);
        estrategia.setBarco(lancha);

        assertEquals(lancha.estaDestruido(), false);
    }

    @Test
    public void testParaComprobarQueCuandoSeLeDisparaUnaVezSoloDeDania() {
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Lancha lancha = new Lancha(orientacion, estrategia);
        estrategia.setBarco(lancha);

        ArrayList<Parte> lasPartes = lancha.obtenerPartes();
        Parte unaParte = lasPartes.get(1);
        unaParte.explosion(disparo);
        assertEquals(lancha.estaDestruido(), false);

    }

    @Test
    public void testParaComprobarQueCuandoSeLeDisparaEnTodasLasPartesSeDestruye() {
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Lancha lancha = new Lancha(orientacion, estrategia);
        estrategia.setBarco(lancha);

        ArrayList<Parte> lasPartes = lancha.obtenerPartes();
        for (int i = 0; i < lancha.obtenerTamanio(); i++) {
            Parte unaParte = lasPartes.get(i);
            unaParte.explosion(disparo);
        }
        assertEquals(lancha.estaDestruido(), true);
    }

    @Test
    public void testParaComprobarQueCuandoExplotaUnaMinaSeDania() {
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        Mina mina = new MinaContacto();
        Lancha lancha = new Lancha(orientacion, estrategia);
        estrategia.setBarco(lancha);

        ArrayList<Parte> lasPartes = lancha.obtenerPartes();

        Parte unaParte = lasPartes.get(1);

        unaParte.explosion(mina);

        assertEquals(lancha.estaDaniado(), true);
    }

    @Test
    public void testParaComprobarQueCuandoSeExplotaUnaMinaEnTodasLasPartesSeDestruye() {
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        Mina mina = new MinaContacto();
        Lancha lancha = new Lancha(orientacion, estrategia);
        estrategia.setBarco(lancha);

        ArrayList<Parte> lasPartes = lancha.obtenerPartes();
        for (int i = 0; i < lancha.obtenerTamanio(); i++) {
            Parte unaParte = lasPartes.get(i);
            unaParte.explosion(mina);
        }
        assertEquals(lancha.estaDestruido(), true);
    }

    @Test
    public void testParaComprobarQueSeColocaCorrectamente() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 4);

        Vector posicion1 = new Vector(5, 4);
        Vector posicion2 = new Vector(5, 5);

        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(0, 1);
        Lancha lancha = new Lancha(orientacion, estrategia);
        estrategia.setBarco(lancha);
        lancha.colocarEnTablero(posicion);
        ArrayList<Parte> partes = lancha.obtenerPartes();

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)), true);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)), true);

    }

    @Test
    public void testParaComprobarQueLanzaUnaExcepcionCuandoNoSePuedeColocarEnUnaPosicion() throws PosicionInvalida {
        Vector posicion = new Vector(10, 10);
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        boolean excepcionLanzada = false;
        Lancha lancha = new Lancha(orientacion, estrategia);
        estrategia.setBarco(lancha);
        try {
            lancha.colocarEnTablero(posicion);
        } catch (PosicionInvalida error) {
            excepcionLanzada = true;
        }
        assertTrue(excepcionLanzada);
    }

    @Test
    public void testParaComprobarQueSuPosicionCambioDeManeraCorrecta() {
        Vector posicion = new Vector(5, 4);
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        Lancha lancha = new Lancha(orientacion, estrategia);
        estrategia.setBarco(lancha);
        lancha.colocarEnTablero(posicion);
        lancha.moverse();
        Vector posActual = lancha.obtenerPosicion();
        assertEquals(posActual.x(), 6);
        assertEquals(posActual.y(), 5);
    }

    @Test
    public void testParaComprobarQueCambiaDeDireccionAlLlegarAlBorde() {
        Vector posicion = new Vector(10, 8);
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(0, 1);
        Lancha lancha = new Lancha(orientacion, estrategia);
        estrategia.setBarco(lancha);
        lancha.colocarEnTablero(posicion);
        lancha.moverse();
        Vector nuevoMovimiento = lancha.obtenerDireccionMovimiento();

        assertEquals(nuevoMovimiento.x(), -1);
        assertEquals(nuevoMovimiento.y(), -1);

    }

    @Test
    public void testParaComprobarQueLasPartesSeEncuentranEnLasNuevasPosiciones() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 5);
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(0, 1);
        Lancha lancha = new Lancha(orientacion, estrategia);
        estrategia.setBarco(lancha);

        Vector posicion1 = new Vector(6, 6);
        Vector posicion2 = new Vector(6, 7);
        lancha.colocarEnTablero(posicion);
        lancha.moverse();

        ArrayList<Parte> partes = lancha.obtenerPartes();

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)), true);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)), true);

    }

    @Test
    public void testParaComprobarQueLasPartesYaNoSeEncuentranEnSuPosicionAnterior() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 5);
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        Lancha lancha = new Lancha(orientacion, estrategia);
        estrategia.setBarco(lancha);

        Vector posicion1 = new Vector(5, 5);
        Vector posicion2 = new Vector(5, 6);

        lancha.colocarEnTablero(posicion);
        lancha.moverse();

        ArrayList<Parte> partes = lancha.obtenerPartes();

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)), false);
    }

    @Test
    public void testParaComprobarCuandoSeColocaSoloSeEncuentraUnaParteEnCadaPosicion() {
        Tablero tablero = Tablero.getTablero();
        MovimientoLinealStrategy estrategia = new MovimientoLinealStrategy(new Vector(1, 1));
        Vector orientacion = new Vector(1, 0);
        Lancha lancha = new Lancha(orientacion, estrategia);
        estrategia.setBarco(lancha);

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
