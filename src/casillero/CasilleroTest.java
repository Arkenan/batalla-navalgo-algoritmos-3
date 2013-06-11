package casillero;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import partes.Parte;
import partes.ParteDanioDisparo;
import disparos.Mina;

public class CasilleroTest {

    @Test
    public void testColocoUnaParteEnUnCasilleroYPrueboQueSeIntrodujoCorrectamente() {
        Casillero casillero = new Casillero();
        Parte parte = new Parte(1);
        casillero.colocarParte(parte, "barco1");
        Parte parteAuxiliar = casillero.sacarParte("barco1");
        assertEquals(parte, parteAuxiliar);
    }

    @Test
    public void testParaComprobarQueAlCrearUnCasilleroEstaVacio() {
        Casillero casillero = new Casillero();
        assertEquals(casillero.contieneMinas(), false);
        assertEquals(casillero.contienePartes(), false);
    }

    @Test
    public void testParaComprobarElEstadoDeLasPartesLuegoDeRecibirUnDisparoElCasillero() {
        Casillero casillero = new Casillero();
        ParteDanioDisparo parte = new ParteDanioDisparo(1);
        casillero.colocarParte(parte, "barco1");
        casillero.transmitirDisparoPartes();
        ParteDanioDisparo parteAux = (ParteDanioDisparo) casillero.sacarParte("barco1");
        assertEquals(parteAux.estaDaniada(), true);
    }

    @Test
    public void testParaComprobarQueGuardaCorrectamenteUnaMina() {
        Casillero casillero = new Casillero();
        Mina mina = new Mina();
        casillero.colocarMina(mina, "mina1");
        assertEquals(casillero.sacarMina("mina1"), mina);
    }

    @Test
    public void testParaComprobarQueAlGuardarUnaMinaContieneMinas() {
        Casillero casillero = new Casillero();
        Mina mina = new Mina();
        casillero.colocarMina(mina, "mina");
        assertEquals(casillero.contieneMinas(), true);
    }

    @Test
    public void testParaComprobarQueAlGuardarUnaParteContienPartes() {
        Casillero casillero = new Casillero();
        Parte parte = new Parte(1);
        casillero.colocarParte(parte, "parte");
        assertEquals(casillero.contienePartes(), true);
    }

    @Test
    public void testParaComprobarQueSeObtieneLasPartesCorrectamente() {
        Casillero casillero = new Casillero();
        Parte parte = new Parte(1);
        Parte parte1 = new Parte(1);
        casillero.colocarParte(parte, "parte");
        casillero.colocarParte(parte1, "parte1");
        ArrayList lista = new ArrayList();
        lista.add(parte);
        lista.add(parte1);
        ArrayList lista2 = casillero.obtenerPartes();
        for (int i = 0; i <= 1; i++) {
            assertEquals(lista.get(i), lista2.get(i));
        }

    }
}
