package partes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import barcos.Vector;
import disparos.DisparoConvencional;
import disparos.MinaContacto;

public class ParteTest {
    int vida = 2;

    @Test
    public void testAlDisPararSobreLaParteEstaPasaAEstarDaniada() {
        Parte parte = new Parte(vida);
        parte.explosion(new DisparoConvencional());
        assertEquals(parte.estaDaniada(), true);
    }

    @Test
    public void testAlExplotarUnaMinaSobreLaParteEstaPasaAEstarDaniada() {
        Parte parte = new Parte(vida);
        parte.explosion(new MinaContacto());
        assertEquals(parte.estaDaniada(), true);
    }

    @Test
    public void testAlRecibirUnDisparoYUnaExplosionDeMinaDeberiaEstarDestruida() {
        Parte parte = new Parte(vida);
        parte.explosion(new MinaContacto());
        parte.explosion(new DisparoConvencional());
        assertEquals(parte.estaDestruida(), true);
    }

    @Test
    public void testAlAsignarleUnaPosicionALaParteYLuegoPedirselaDeberianSerIguales() {
        Parte parte = new Parte(vida);
        Vector posicion = new Vector(1, 1);
        parte.cambiarPosicion(posicion);
        assertTrue(posicion.sonIguales(parte.obtenerPosicion()));
    }

}
