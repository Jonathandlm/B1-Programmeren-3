package be.leerstad.evaluatieoefeningen.parking.test;

import be.leerstad.evaluatieoefeningen.parking.*;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VehicleTest {

    private Vehicle bike;
    private Vehicle motorcycle;
    private Vehicle car;
    private Person passenger1;
    private Person passenger2;
    private Person passenger3;
    private Person passenger4;
    private Collection passengers1;
    private Collection passengers4;
    private Collection mixedCollection;

    @Before
    public void init() {
        bike = new Bike("Cannondale", "Synapse Carbon Ultegra 3", "green");
        motorcycle = new Motorcycle("Kawasaki", "Ninja ZX-14R", "BLACK", 208);
        car = new Car("Tesla", "Model S", "ReD", 320);
        passenger1 = new Person("12345", "John", "Doe");
        passenger2 = new Person("24680", "Jane", "Doe");
        passenger3 = new Person("13579", "Elon", "Musk");
        passenger4 = new Person("98765", "Shōzō", "Kawasaki");
        passengers1 = new ArrayList<Person>();
        passengers1.add(passenger1);
        passengers4 = new ArrayList<Person>();
        passengers4.add(passenger1);
        passengers4.add(passenger2);
        passengers4.add(passenger3);
        passengers4.add(passenger4);
        mixedCollection = new ArrayList();
        mixedCollection.add(passenger1);
        mixedCollection.add("aString");
    }

    @Test
    public void testConstructors() {
        assertEquals("Should return 'Cannondale'", "Cannondale", bike.getModel());
        assertEquals("Should return 'Ninja ZX-14R'", "Ninja ZX-14R", motorcycle.getType());
        assertEquals("Should return 'Color.red'", Color.red, car.getColor());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorColorExceptions(){
        bike = new Bike("Model", "Type", "pink");
        motorcycle = new Motorcycle("Model", "Type", "yellow", 1);
        car = new Car("Model", "Type", "brown", 1);
    }

    @Test
    public void testGetModels() {
        assertEquals("Should return 'Cannondale'", "Cannondale", bike.getModel());
        assertEquals("Should return 'Kawasaki'", "Kawasaki", motorcycle.getModel());
        assertEquals("Should return 'Tesla'", "Tesla", car.getModel());
    }

    @Test
    public void testGetTypes() {
        assertEquals("Should return 'Synapse Carbon Ultegra 3'", "Synapse Carbon Ultegra 3", bike.getType());
        assertEquals("Should return 'Ninja ZX-14R'", "Ninja ZX-14R", motorcycle.getType());
        assertEquals("Should return 'Model S'", "Model S", car.getType());
    }

    @Test
    public void testGetColors() {
        assertEquals("Should return 'Color.green'", Color.green, bike.getColor());
        assertEquals("Should return 'Color.black'", Color.black, motorcycle.getColor());
        assertEquals("Should return 'Color.red'", Color.red, car.getColor());
    }

    @Test
    public void testIsMotorized() {
        assertFalse("Should not be motorized", bike.isMotorized());
        assertTrue("Should be motorized", motorcycle.isMotorized());
        assertTrue("Should be motorized", car.isMotorized());
    }

    @Test
    public void testGetPowers() {
        assertEquals("Should return '0'", 0, bike.getPower());
        assertEquals("Should return '208'", 208, motorcycle.getPower());
        assertEquals("Should return '320'", 320, car.getPower());
    }

    @Test
    public void testGetNbrPassengerSeats() {
        assertEquals("Should return '0'", 0, bike.getNbrPassengerSeats());
        assertEquals("Should return '1'", 1, motorcycle.getNbrPassengerSeats());
        assertEquals("Should return '4'", 4, car.getNbrPassengerSeats());
    }

    @Test
    public void testGetPassengerLists() {
        assertEquals("Should return '0'", 0, bike.getPassengerList().size());
        assertEquals("Should return '0'", 0, motorcycle.getPassengerList().size());
        assertEquals("Should return '0'", 0, car.getPassengerList().size());
    }

    @Test
    public void testTakePassengers() {
        assertFalse("Should not take passengers", bike.takePassengers(passengers1));
        assertTrue("Should take passengers", motorcycle.takePassengers(passengers1));
        assertFalse("Should not take passengers", motorcycle.takePassengers(passengers4));
        assertTrue("Should take passengers", car.takePassengers(passengers1));
        assertFalse("Should not take passengers", car.takePassengers(passengers1));
    }

    @Test
    public void testEquals() {
        Bike bike1 = new Bike("Cannondale", "Synapse Carbon Ultegra 3", "green");
        Bike bike2 = new Bike("Cannondale", "Synapse Carbon Ultegra 3", "green");

        // Rule 1: reflexive
        assertTrue(bike.equals(bike));

        // Rule 2: transitive
        assertTrue(bike.equals(bike1));
        assertTrue(bike1.equals(bike2));
        assertTrue(bike2.equals(bike));

        // Rule 3: symmetric
        assertTrue(bike1.equals(bike));

        // Rule 4: other object
        assertFalse(bike.equals("a string"));

        // Rule 5: null
        assertFalse(bike.equals(null));

        // Rule 6: hashcode
        assertEquals(bike.hashCode(), bike1.hashCode());
    }

    @Test
    public void testHashCode() {
        Bike bike1 = new Bike("Cannondale", "Synapse Carbon Ultegra 3", "green");
        Bike bike2 = new Bike("Cannondale", "Synapse Carbon Ultegra 3", "green");

        assertEquals(bike1.hashCode(), bike1.hashCode());
        assertTrue(bike1.equals(bike2));
        assertEquals(bike1.hashCode(), bike2.hashCode());
    }

    @Test
    public void testCompareTo(){
        Bike bike1 = new Bike("Cannondale", "Synapse Carbon Ultegra 3", "black");
        Bike bike2 = new Bike("Cannondale", "Synapse Carbon Ultegra 4", "green");
        Bike bike3 = new Bike("Trek", "Madone 9.9", "green");

        assertEquals("Should return '0'", 0, bike.compareTo(bike1));
        assertTrue("Should return larger", bike2.compareTo(bike1) >= 1);
        assertTrue("Should return larger", bike3.compareTo(bike1) >= 1);
        assertTrue("Should return larger", car.compareTo(bike) >= 1);
    }

}
