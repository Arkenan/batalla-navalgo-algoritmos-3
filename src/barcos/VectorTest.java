package barcos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VectorTest {

    @Test
    public void testConstructor() {
        Vector vector = new Vector(2, 3);
        assertTrue(vector.x() == 2 && vector.y() == 3);
    }

    @Test
    public void testConstructorString() {
        Vector vector = new Vector("(1,3)");
        assertEquals(vector.x(), 1);
        assertEquals(vector.y(), 3);
    }

    @Test
    public void testSonIguales() {
        Vector vector1 = new Vector(5, 5);
        Vector vector2 = new Vector(5, 5);
        assertTrue(vector1.sonIguales(vector2));
    }

    @Test
    public void testNoSonIguales() {
        Vector vector1 = new Vector(5, 5);
        Vector vector2 = new Vector(5, 3);
        assertFalse(vector1.sonIguales(vector2));
    }

    @Test
    public void testAsignar() {
        Vector vector1 = new Vector(1, 1);
        Vector vector2 = new Vector(2, 3);
        vector1.asignar(vector2);
        assertTrue(vector1.x() == 2 && vector1.y() == 3);
    }

    @Test
    public void testSuma() {
        Vector vector1 = new Vector(1, 2);
        Vector vector2 = new Vector(5, 10);
        assertTrue((vector1.sumar(vector2)).sonIguales(new Vector(6, 12)));
    }

    @Test
    public void testporEscalar0() {
        Vector vector = new Vector(5, 7);
        assertTrue(vector.porEscalar(0).sonIguales(new Vector(0, 0)));
    }

    @Test
    public void testporEscalar() {
        Vector vector = new Vector(5, 7);
        assertTrue(vector.porEscalar(3).sonIguales(new Vector(15, 21)));
    }

    @Test
    public void testToString() {
        Vector vector = new Vector(4, 7);
        assertEquals(vector.toString(), "(4,7)");
    }

}
