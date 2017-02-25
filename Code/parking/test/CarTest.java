package be.leerstad.collections.parking.test;


import be.leerstad.collections.parking.Car;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by peterhardeel on 13/12/2016.
 */
public class CarTest {

    private Car car;

    @Before
    public void init() {
        car = new Car("SL", "Mercedes", "23456");
    }

    @Test
    public void testConstructor() {
        assertEquals("Mercedes", car.getMake());
    }


    @Test
    public void getMake() throws Exception {
        Assert.assertEquals("Should return Mercedes","Mercedes",car.getMake());

    }

    @Test
    public void getModel() throws Exception {
        Assert.assertEquals("Should return SL","SL",car.getModel());

    }

    @Test
    public void getVIN() throws Exception {
        Assert.assertEquals("Should return 23456","23456",car.getVIN());

    }

    @Test
    public void equals() throws Exception {
        Car ca1 = new Car("SL", "Mercedes", "23456");
        Car ca2 = new Car("SL", "Mercedes", "23456");


        // Rule 1: reflexive
        assertTrue(car.equals(car));

        // Rule 2: transitive
        assertTrue(car.equals(ca1));
        assertTrue(ca1.equals(ca2));
        assertTrue(car.equals(ca2));

        // Rule 3: symmetric
        assertTrue(ca1.equals(car));

        // Rule 4: null
        assertFalse(car.equals(null));

        // Rule 5: hashcode
        assertEquals(car.hashCode(), ca1.hashCode());

       
        assertFalse(car.equals("a string"));

    }

    @Test
    public void testHashCode() throws Exception {
        Car ca1 = new Car("SL", "Mercedes", "23456");
        Car ca2 = new Car("SL", "Mercedes", "23456");


        assertEquals(ca1.hashCode(), ca1.hashCode());
        assertTrue(ca1.equals(ca2));
        assertEquals(ca1.hashCode(), ca2.hashCode());
    }

}