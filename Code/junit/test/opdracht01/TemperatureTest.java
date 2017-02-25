package be.leerstad.junit.test.opdracht01;

import be.leerstad.junit.opdracht01.Temperature;
import org.junit.Test;

import static org.junit.Assert.*;

public class TemperatureTest {
    private static final float PRECISION = 0.01F;

    @Test
    public void testConstructor() {
        Temperature t = new Temperature(5F);
        assertEquals(5F, t.getValue(), PRECISION);

    }

    @Test
    public void testValue() {
        Temperature t = new Temperature(0F);
        t.setValue(13F);
        assertEquals(13F, t.getValue(), PRECISION);
    }

    @Test
    public void testEquals() {
        Temperature t = new Temperature(20F);
        Temperature t2 = new Temperature(20F);
        Temperature t3 = new Temperature(20F);

        // Rule 1: reflexive
        assertTrue(t.equals(t));

        // Rule 2: transitive
        assertTrue(t.equals(t2));
        assertTrue(t2.equals(t3));
        assertTrue(t.equals(t3));

        // Rule 3: symmetric
        assertTrue(t2.equals(t));

        // Rule 4: null
        assertFalse(t.equals(null));

        // Rule 5: hashcode
        assertEquals(t.hashCode(), t2.hashCode());

        t2.setValue(25);
        assertFalse(t.equals(t2));
        assertFalse(t2.equals(t3));

        assertFalse(t.equals("a string"));
    }

    @Test
    public void testHashCode() {
        Temperature t = new Temperature(15);
        Temperature t2 = new Temperature(15);
        Temperature t3 = new Temperature(15);

        assertEquals(t.hashCode(), t.hashCode());
        assertTrue(t.equals(t2));
        assertEquals(t.hashCode(), t2.hashCode());
    }
}
