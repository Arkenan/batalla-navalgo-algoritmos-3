package barcos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import partes.Parte;
import disparos.DisparoConvencional;
import disparos.Mina;
import disparos.MinaContacto;
import escenario.Tablero;
import excepciones.PosicionInvalida;

public class LanchaTest {

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

    /*
     * @Test public void testParaComprobarQueCuandoSeLeDisparaSeDania() { Vector posicion = new
     * Vector(5, 5); Vector movimiento = new Vector(1, 1); Vector orientacion = new Vector(1, 0);
     * Lancha lancha = new Lancha(movimiento, posicion, orientacion);
     * 
     * ArrayList<Parte> lasPartes = lancha.obtenerPartes(); Parte unaParte = lasPartes.get(1);
     * unaParte.explosionDisparo();
     * 
     * assertEquals(lancha.estaDaniado(), true);
     * 
     * }
     */

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
        Vector orientacion = new Vector(1, 0);
        Lancha nave = new Lancha(movimiento, posicion, orientacion);
        ArrayList<Parte> partes = nave.obtenerPartes();
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(1)), true);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(2)), true);

    }

    @Test
    public void testParaComprobarQueLanzaUnaExcepcionCuandoNoSePuedeColocarEnUnaPosicion() throws PosicionInvalida {
        Vector posicion = new Vector(10, 10);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        boolean valor = false;
        try {
            Lancha lancha = new Lancha(movimiento, posicion, orientacion);
            fail();
        } catch (PosicionInvalida error) {
            valor = true;
        }
        assertTrue(valor);
    }

    public void testParaComprobarQueSePosicionCambioDeManeraCorrecta() {
        Vector posicion = new Vector(5, 4);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Lancha lancha = new Lancha(movimiento, posicion, orientacion);
        lancha.moverse();
        Vector posActual = lancha.obtenerPosicion();
        assertEquals(posActual.x(), 6);
        assertEquals(posActual.y(), 5);
    }

    public void testParaComprobarQueCambiaDeDireccionAlLlegarAlBorde() {
        Vector posicion = new Vector(0, 8);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Lancha lancha = new Lancha(movimiento, posicion, orientacion);

        lancha.moverse();
        Vector nuevoMovimiento = lancha.obtenerDireccionMovimiento();

        assertEquals(nuevoMovimiento.x, -1);
        assertEquals(nuevoMovimiento.y, -1);

    }

    @Test
    public void testParaComprobarQueLasPartesSeEncuentranEnLasNuevasPosiciones() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
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
        Vector posicion3 = new Vector(5, 7);

        lancha.moverse();

        ArrayList<Parte> partes = lancha.obtenerPartes();

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
        Lancha lancha = new Lancha(movimiento, posicion, orientacion);

        Vector posicion1 = new Vector(5, 5);
        Vector posicion2 = new Vector(5, 6);
        Vector posicion3 = new Vector(5, 7);

        ArrayList<Parte> partes = lancha.obtenerPartes();

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(1)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(2)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(0)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(2)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion3, partes.get(0)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(1)), false);

    }

    // -----------------------------------------------------------------------
    // Cambie esto de un test a un metodo interno que es usado por otro test.
    // Aunque no se si estara bien que dependa el test de este metodo.
    // -----------------------------------------------------------------------

    /* TESTS PARA COMPROBAR QUE CADA PARTE SE ENCUENTRA EN UNA SOLA POSICION */
    private void paraComprobarQueLaParteSeEncuentraEnUnaSolaPosicion(int posParte) {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Lancha lancha = new Lancha(movimiento, posicion, orientacion);
        int contador = 0;

        ArrayList<Parte> partes = lancha.obtenerPartes();

        Parte parte = partes.get(posParte);

        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; i++) {
                Vector posicion1 = new Vector(i, j);
                if ((tablero.elementoPerteneceAlCasillero(posicion1, parte)) == true) {
                    contador += 1;
                }
            }
        }
        assertEquals(contador, 1);
    }

    @Test
    public void testParaComprobarQueCadaParteSeEncuentraEnUnaSolaParte() {
        this.paraComprobarQueLaParteSeEncuentraEnUnaSolaPosicion(0);
        this.paraComprobarQueLaParteSeEncuentraEnUnaSolaPosicion(1);
        this.paraComprobarQueLaParteSeEncuentraEnUnaSolaPosicion(2);
    }
}
