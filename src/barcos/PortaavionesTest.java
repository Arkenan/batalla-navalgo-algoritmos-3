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

public class PortaavionesTest {

    @Test
    public void testParaComprobarQueSeGuardaCorrectamenteElMovimiento() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Portaaviones buque = new Portaaviones(movimiento, posicion, orientacion);

        Vector unMovimientoVector = buque.obtenerDireccionMovimiento();

        assertEquals(unMovimientoVector.x, movimiento.x);
        assertEquals(unMovimientoVector.y, movimiento.y);

    }

    @Test
    public void testParaComprobarQueSeInvierteCorrectamenteElMovimiento() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector movimientoInvertido = new Vector(-1, -1);
        Vector orientacion = new Vector(1, 0);
        Portaaviones nave = new Portaaviones(movimiento, posicion, orientacion);

        nave.invertirDireccionMovimiento();
        Vector nuevoMovimiento = nave.obtenerDireccionMovimiento();

        assertEquals(nuevoMovimiento.x, movimientoInvertido.x);
        assertEquals(nuevoMovimiento.y, movimientoInvertido.y);

    }

    @Test
    public void testParaComprobarQueCuandoSeLeDisparaSeDania() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Portaaviones nave = new Portaaviones(movimiento, posicion, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();
        Parte unaParte = lasPartes.get(1);
        unaParte.explosion(disparo);

        assertEquals(nave.estaDaniado(), true);

    }

    @Test
    public void testParaComprobarQueCuandoSeCreaNoEsteDaniado() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Portaaviones nave = new Portaaviones(movimiento, posicion, orientacion);

        assertEquals(nave.estaDaniado(), false);
    }

    @Test
    public void testParaComprobarQueCuandoSeCreaNoEsteDestruido() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Portaaviones nave = new Portaaviones(movimiento, posicion, orientacion);

        assertEquals(nave.estaDestruido(), false);
    }

    @Test
    public void testParaComprobarQueCuandoSeLeDisparaUnaVezSeDestruye() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Portaaviones nave = new Portaaviones(movimiento, posicion, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();
        Parte unaParte = lasPartes.get(1);
        unaParte.explosion(disparo);
        assertEquals(nave.estaDestruido(), true);

    }

    @Test
    public void testParaComprobarQueCuandoSeLeDisparaEnTodasLasPartesSeDestruye() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        DisparoConvencional disparo = new DisparoConvencional();
        Portaaviones nave = new Portaaviones(movimiento, posicion, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();
        for (int i = 0; i < nave.obtenerTamanio(); i++) {
            Parte unaParte = lasPartes.get(i);
            unaParte.explosion(disparo);
        }
        assertEquals(nave.estaDestruido(), true);
    }

    @Test
    public void testParaComprobarQueCuandoExplotaUnaMinaSeDania() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Mina mina = new MinaContacto();
        Portaaviones nave = new Portaaviones(movimiento, posicion, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();

        Parte unaParte = lasPartes.get(1);

        unaParte.explosion(mina);

        assertEquals(nave.estaDaniado(), true);
    }

    @Test
    public void testParaComprobarQueCuandoSeExplotaUnaMinaEnTodasLasPartesSeDestruye() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Mina mina = new MinaContacto();
        Portaaviones nave = new Portaaviones(movimiento, posicion, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();
        for (int i = 0; i < nave.obtenerTamanio(); i++) {
            Parte unaParte = lasPartes.get(i);
            unaParte.explosion(mina);
        }
        assertEquals(nave.estaDestruido(), true);
    }

    @Test
    public void testParaComprobarQueCuandoExplotaUnaMinaSeDestruye() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Mina mina = new MinaContacto();
        Portaaviones nave = new Portaaviones(movimiento, posicion, orientacion);

        ArrayList<Parte> lasPartes = nave.obtenerPartes();

        Parte unaParte = lasPartes.get(1);

        unaParte.explosion(mina);

        assertEquals(nave.estaDestruido(), true);
    }

    @Test
    public void testParaComprobarQueSeCambiaCorrectamenteLaPosicion() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Portaaviones nave = new Portaaviones(movimiento, posicion, orientacion);

        nave.cambiarPosicion();
        Vector unaPosicion = nave.obtenerPosicion();
        assertEquals(unaPosicion.x(), 6);
        assertEquals(unaPosicion.y(), 5);

    }

    @Test
    public void testParaComprobarQueSeColocaCorrectamente() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 4);
        Vector posicion1 = new Vector(5, 4);
        Vector posicion2 = new Vector(5, 5);
        Vector posicion3 = new Vector(5, 6);
        Vector posicion4 = new Vector(5, 7);

        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Portaaviones nave = new Portaaviones(movimiento, posicion, orientacion);

        ArrayList<Parte> partes = nave.obtenerPartes();
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(1)), true);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(2)), true);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion3, partes.get(3)), true);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion4, partes.get(4)), true);
    }

    @Test
    public void testParaComprobarQueLanzaUnaExcepcionCuandoNoSePuedeColocarEnUnaPosicion() throws PosicionInvalida {
        Vector posicion = new Vector(10, 10);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        boolean valor = false;
        try {
            Portaaviones nave = new Portaaviones(movimiento, posicion, orientacion);
        } catch (PosicionInvalida error) {
            valor = true;
        }
        assertTrue(valor);
    }

    @Test
    public void testParaComprobarQueSePosicionCambioDeManeraCorrecta() {
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Portaaviones nave = new Portaaviones(movimiento, posicion, orientacion);
        nave.moverse();
        Vector posActual = nave.obtenerPosicion();
        assertEquals(posActual.x(), 6);
        assertEquals(posActual.y(), 5);
    }

    public void testParaComprobarQueCambiaDeDireccionAlLlegarAlBorde() {
        Vector posicion = new Vector(10, 8);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Portaaviones nave = new Portaaviones(movimiento, posicion, orientacion);

        nave.moverse();
        Vector nuevoMovimiento = nave.obtenerDireccionMovimiento();

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
        Vector posicion3 = new Vector(6, 8);
        Vector posicion4 = new Vector(6, 9);

        lancha.moverse();

        ArrayList<Parte> partes = lancha.obtenerPartes();

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)), true);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)), true);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion3, partes.get(2)), true);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion4, partes.get(3)), true);

    }

    @Test
    public void testParaComprobarQueLasPartesYaNoSeEncuentranEnSuPosicionAnterior() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Portaaviones nave = new Portaaviones(movimiento, posicion, orientacion);

        Vector posicion1 = new Vector(5, 5);
        Vector posicion2 = new Vector(5, 6);
        Vector posicion3 = new Vector(5, 7);
        Vector posicion4 = new Vector(5, 8);

        nave.moverse();

        ArrayList<Parte> partes = nave.obtenerPartes();

        assertEquals(tablero.elementoPerteneceAlCasillero(posicion1, partes.get(0)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion2, partes.get(1)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion3, partes.get(2)), false);
        assertEquals(tablero.elementoPerteneceAlCasillero(posicion4, partes.get(3)), false);
    }

    @Test
    public void testParaComprobarCuandoSeColocaSoloSeEncuentraUnaParteEnCadaPosicion() {
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(5, 5);
        Vector movimiento = new Vector(1, 1);
        Vector orientacion = new Vector(1, 0);
        Portaaviones lancha = new Portaaviones(movimiento, posicion, orientacion);

        Vector posicion0 = new Vector(5, 5);
        Vector posicion1 = new Vector(5, 6);
        Vector posicion2 = new Vector(5, 7);
        Vector posicion3 = new Vector(5, 8);

        ArrayList<Parte> partes = lancha.obtenerPartes();

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
