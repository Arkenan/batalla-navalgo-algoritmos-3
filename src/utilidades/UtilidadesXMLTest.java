package utilidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class UtilidadesXMLTest {

    @Test
    public void testObtieneTodosLosXML() {
        ArrayList<String> nombresObtenidos = UtilidadesXML.obtenerNombres("Pruebas/CarpetaPrueba1");
        assertTrue(nombresObtenidos.get(0).equals("1"));
        assertTrue(nombresObtenidos.get(1).equals("2"));
        assertTrue(nombresObtenidos.get(2).equals("3"));
    }

    @Test
    public void testObtieneSoloLosXML() {
        ArrayList<String> nombresObtenidos = UtilidadesXML.obtenerNombres("Pruebas/CarpetaPrueba2");
        assertTrue(nombresObtenidos.get(0).equals("1"));
        assertTrue(nombresObtenidos.get(1).equals("2"));
        assertTrue(nombresObtenidos.get(2).equals("4"));
        assertEquals(nombresObtenidos.size(), 3);

    }
}
