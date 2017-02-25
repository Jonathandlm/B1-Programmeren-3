package be.leerstad.collections.parking.test;

import be.leerstad.collections.parking.Car;
import be.leerstad.collections.parking.Parking;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by peterhardeel on 13/12/2016.
 */
public class ParkingTest {
    private Parking parking;
    private Car ca1, ca2, ca3, ca4, ca5,ca6;

    @Before
    public void setUp() throws Exception {
        parking = new Parking();

        ca1 = new Car("Mercedes", "SL", "23456");
        ca2 = new Car("Alpha Romeo", "chacha", "23457");
        ca3 = new Car("Honda", "Civic", "86754");
        ca4 = new Car("Alpha Romeo", "blabla", "67843");
        ca5 = new Car("Mercedes", "SLE", "53213");
        ca6 = new Car("Mercedes", "SL", "23456");

        parking.addVehicle(ca1);
        parking.addVehicle(ca2);
        parking.addVehicle(ca3);
        parking.addVehicle(ca4);
        parking.addVehicle(ca5);


    }

    @Test
    public void getVehicles() throws Exception {

        Assert.assertEquals("Expected size 5", 5, parking.getVehicles().size());

    }

    @Test
    public void addVehicle() throws Exception {

        Assert.assertFalse("can't add a car with the same VIN number", parking.addVehicle(ca6));
        Assert.assertTrue("Can add this car to the parking", parking.addVehicle(new Car("Mercedes", "SL", "12345")));
    }

}