package juego;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import partes.Parte;
import barcos.Vector;
import disparos.Disparo;
import escenario.Tablero;

public class ConstructorDeDisparoTest {

    @Test
    public void testAlCrearUnaMinaDeContactoConElConstructor() {
        ConstructorDeDisparo constructor = new ConstructorDeDisparo();
        Vector posicion = new Vector(1, 2);
        Disparo disparo = constructor.construirDisparo("minacontacto", posicion);
        assertEquals(disparo.costo(), 150);

    }

    @Test
    public void testAlCrearUnDisparoConElConstructorHacerloExplotarYVerSiLaParteQuedoDaniada() {
        Tablero tablero = Tablero.getTablero();
        ConstructorDeDisparo constructor = new ConstructorDeDisparo();
        Vector posicion = new Vector(1, 2);
        Parte parte = new Parte(1);
        tablero.colocarElemento(posicion, parte);
        Disparo disparo = constructor.construirDisparo("disparoconvencional", posicion);
        disparo.explotar();

        assertTrue(parte.estaDaniada());
    }
}
