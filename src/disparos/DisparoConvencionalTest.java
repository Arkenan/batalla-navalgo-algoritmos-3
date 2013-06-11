package disparos;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import partes.Parte;
import barcos.Vector;
import escenario.Tablero;

public class DisparoConvencionalTest {

    @Test
    public void testAlCrearUnDisparoConvencionalYPreguntarleSiDebeExplotarDebeDarVerdadero() {
        DisparoConvencional disparo = new DisparoConvencional();
        assertTrue(disparo.debeExplotar());
    }

    @Test
    public void testAlPosicionarUnDisparoConvencionalEnUnCasilleroQueTengaUnaParteYHacerloExplotarLaParteDebeResultarDaniada() {
        Parte unaParte = new Parte(1);
        Tablero tablero = Tablero.getTablero();
        Vector posicion = new Vector(1, 1);
        tablero.colocarElemento(posicion, unaParte);
        DisparoConvencional disparo = new DisparoConvencional();
        disparo.cambiarCasillerosAfectados(posicion);
        disparo.explotar();
        assertTrue(unaParte.estaDaniada());
    }

}
