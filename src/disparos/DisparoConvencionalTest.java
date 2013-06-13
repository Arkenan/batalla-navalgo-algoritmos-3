package disparos;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import partes.ParteDanioTotal;
import barcos.Vector;
import escenario.Tablero;
import excepciones.PosicionInvalida;

public class DisparoConvencionalTest {

    @Test
    public void testAlCrearUnDisparoConvencionalYPreguntarleSiDebeExplotarDebeDarVerdadero() {
        DisparoConvencional disparo = new DisparoConvencional();
        assertTrue(disparo.debeExplotar());
    }

    @Test
    public void testAlPosicionarUnDisparoConvencionalEnUnCasilleroQueTengaUnaParteYHacerloExplotarLaParteDebeResultarDaniada() {
        ParteDanioTotal unaParte = new ParteDanioTotal(1);
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(1, 1);
        tablero.colocarElemento(posicion, unaParte);
        DisparoConvencional disparo = new DisparoConvencional();
        disparo.cambiarCasillerosAfectados(posicion);
        disparo.explotar();
        assertTrue(unaParte.estaDaniada());
    }

    @Test
    public void testAlAsignarleLosCasillerosAfectadosConUnaPosicionInvalidaDeberiaDarError() {
        DisparoConvencional disparo = new DisparoConvencional();
        boolean valor = false;
        try {
            disparo.cambiarCasillerosAfectados(new Vector(15, 123));
        } catch (PosicionInvalida error) {
            valor = true;
        }
        assertTrue(valor);

    }

}
