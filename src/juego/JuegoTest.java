package juego;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import partes.Parte;
import barcos.Barco;
import barcos.Vector;

public class JuegoTest {

    @Test
    public void testAlCrearUnJuegoNuevoLosPuntosDebenSerMayoresACero() {
        Juego juego = new Juego();
        assertTrue(juego.getPuntos() > 0);
    }

    @Test
    public void testAlPasarUnTurnoLosPuntosDebenDisminuir() {
        Juego juego = new Juego();
        int puntosIniciales = juego.getPuntos();
        juego.pasarTurno();
        assertTrue(puntosIniciales > juego.getPuntos());
    }

    @Test
    public void testAlTerminarseLosPuntosElMetodoFaltanPuntosParaSeguirDeberiaDarTrue() {
        Juego juego = new Juego();
        for (int x = 1; x <= 50; x++) {
            juego.colocarDisparo("disparoconvencional", new Vector(0, 0));
        }
        assertTrue(juego.faltanPuntosParaSeguir());
    }

    @Test
    public void testAlColocarUnaMinaDeberianDisminuirLosPuntos() {
        Juego juego = new Juego();
        Vector posicion = new Vector(10, 10);
        int puntosIniciales = juego.getPuntos();
        try {
            juego.colocarDisparo("minacontacto", posicion);
            assertTrue(puntosIniciales > juego.getPuntos());
        } catch (DisparoInvalido error) {
        }
    }

    @Test
    public void testGanarUnJuego() {
        Juego juego = new Juego();
        ArrayList<Barco> barcos = juego.obtenerBarcos();
        ArrayList<Parte> partes;
        Vector posicionDisparo;
        for (int h = 1; h < 2; h++) {
            System.out.println((int) Math.random() * 11);
            for (int i = 0; i < barcos.size(); i++) {
                partes = (barcos.get(i)).obtenerPartes();
                for (int j = 0; j < partes.size(); j++) {
                    posicionDisparo = (partes.get(j)).obtenerPosicion();
                    juego.colocarDisparo("disparoconvencional", posicionDisparo);
                }
            }
        }
        assertTrue(juego.gano());

    }
}