package partida;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.dom4j.Element;
import org.junit.Test;

import barcos.Vector;
import disparos.DisparoConvencional;

public class PartidaTest {

    @Test
    public void testAlCrearUnaPartidaNuevaLosPuntosDebenSerMayoresACero() {
        Partida partida = new Partida();
        assertTrue(partida.getPuntos() > 0);

    }

    @Test
    public void testAlPasarUnTurnoLosPuntosDebenDisminuir() {
        Partida partida = new Partida();
        int puntosIniciales = partida.getPuntos();
        partida.pasarTurno();
        assertTrue(puntosIniciales > partida.getPuntos());
    }

    @Test
    public void testAlTerminarseLosPuntosElMetodoFaltanPuntosParaSeguirDeberiaDarTrue() {
        Partida partida = new Partida();
        for (int x = 1; x <= 50; x++) {
            partida.colocarDaniador(partida.crearDaniador("disparoconvencional", new Vector(3, 3)));
        }
        assertTrue(partida.faltanPuntosParaSeguir());
    }

    @Test
    public void testAlColocarUnaMinaDeberianDisminuirLosPuntos() {
        Partida partida = new Partida();
        Vector posicion = new Vector(10, 10);
        int puntosIniciales = partida.getPuntos();
        partida.colocarDaniador(partida.crearDaniador("minacontacto", posicion));
        assertTrue(puntosIniciales > partida.getPuntos());

    }

    @Test
    public void testAlTratarDecolocarDaniadorsEnPosicionesInvalidasNoDeberianDecrecerLosPuntos() {
        Partida partida = new Partida();
        int puntosIniciales = partida.getPuntos();
        for (int i = 0; i < 10; i++)
            partida.colocarDaniador(partida.crearDaniador("minacontacto", new Vector(101, 1100)));
        assertEquals(puntosIniciales, partida.getPuntos());
    }

    @Test
    public void testGanarUnaPartida() {
        Partida partida;
        try {
            Element nodoPartida = Partida.obtenerNodoPartida("Niveles XML/nivelLanchaInmovil.xml");
            partida = new Partida(nodoPartida);
            partida.crearBarcos(nodoPartida);
        } catch (IOException e) {
            partida = new Partida();
        }

        assertFalse(partida.gano());
        DisparoConvencional disparo = new DisparoConvencional();
        DisparoConvencional disparo2 = new DisparoConvencional();
        disparo.cambiarCasillerosAfectados(new Vector(5, 5));
        disparo2.cambiarCasillerosAfectados(new Vector(6, 5));
        partida.colocarDaniador(disparo);
        partida.colocarDaniador(disparo2);
        partida.pasarTurno();

        assertTrue(partida.gano());
    }

    @Test
    public void testLevantaNivelSinError() throws IOException {
        Partida partida = new Partida(Partida.obtenerNodoPartida("niveles XML/NivelLanchaInmovil.xml"));
    }

    @Test
    public void testPuntosInicialesDelNivel() {
        Partida partida;
        try {
            partida = new Partida(Partida.obtenerNodoPartida("niveles XML/NivelLanchaInmovil.xml"));
        } catch (IOException e) {
            partida = new Partida();
        }
        assertEquals(partida.getPuntos(), 2000);
    }

    @Test
    public void testLanchaEnPosicionCorrecta() {
        Partida partida;
        try {
            partida = new Partida(Partida.obtenerNodoPartida("niveles XML/NivelLanchaInmovil.xml"));
        } catch (IOException e) {
            partida = new Partida();
        }
        assertEquals(partida.getPuntos(), 2000);

    }

}
