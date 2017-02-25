package be.leerstad.evaluatieoefeningen.parking.test;

import be.leerstad.evaluatieoefeningen.parking.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ParkingTest {

    private Parking parking;
    private Vehicle bike;
    private Vehicle bike1;
    private Vehicle bike2;
    private Vehicle bike3;
    private Vehicle motorcycle;
    private Vehicle car;

    @Before
    public void init() {
        parking = new Parking();
        bike = new Bike("Cannondale", "Synapse Carbon Ultegra 3", "black");
        bike1 = new Bike("Cannondale", "Synapse Carbon Ultegra 3", "green");
        bike2 = new Bike("Cannondale", "Synapse Carbon Ultegra 4", "green");
        bike3 = new Bike("Trek", "Madone 9.9", "green");
        motorcycle = new Motorcycle("Kawasaki", "Ninja ZX-14R", "BLACK", 208);
        car = new Car("Tesla", "Model S", "ReD", 320);
    }

    private void addVehiclesToParking() {
        parking.addVehicle(bike2);
        parking.addVehicle(bike3);
        parking.addVehicle(motorcycle);
        parking.addVehicle(car);
        parking.addVehicle(bike);
        parking.addVehicle(bike1);
    }

    @Test
    public void testConstructor() {
        assertEquals("Should return '0'", 0, parking.getNbrVehicles());
    }

    @Test
    public void testGetNbrVehicles() {
        assertEquals("Should return '0'", 0, parking.getNbrVehicles());
        addVehiclesToParking();
        assertEquals("Should return '6'", 6, parking.getNbrVehicles());
    }

    @Test
    public void testGetVehicleList() {
        assertEquals("Should return '0'", 0, parking.getVehicleList().size());
    }

    @Test
    public void testAddVehicles() {
        assertEquals("Should return '0'", 0, parking.getNbrVehicles());
        assertTrue("Should be added", parking.addVehicle(bike));
        assertEquals("Should return '1'", 1, parking.getNbrVehicles());
        assertTrue("Should be added", parking.addVehicle(bike));
        assertEquals("Should return '2'", 2, parking.getNbrVehicles());
        assertTrue("Should be added", parking.addVehicle(bike1));
        assertTrue("Should be added", parking.addVehicle(bike2));
        assertTrue("Should be added", parking.addVehicle(bike3));
        assertTrue("Should be added", parking.addVehicle(motorcycle));
        assertTrue("Should be added", parking.addVehicle(car));
        assertEquals("Should return '7'", 7, parking.getNbrVehicles());
    }

    @Test
    public void testSelectVehicles() {
        addVehiclesToParking();
        assertEquals("Should return '0'", 0, parking
                .selectVehicles("","").size());
        assertEquals("Should return '1'", 1, parking
                .selectVehicles("Cannondale","Synapse Carbon Ultegra 4").size());
        assertEquals("Should return '2'", 2, parking
                .selectVehicles("Cannondale","Synapse Carbon Ultegra 3").size());
    }

    @Test
    public void testSortVehicles() {
        addVehiclesToParking();
        List list = parking.getVehicleList();
        assertEquals("Should return 'bike2'", bike2, list.get(0));
        assertEquals("Should return 'bike3'", bike3, list.get(1));
        assertEquals("Should return 'motorcycle'", motorcycle, list.get(2));
        assertEquals("Should return 'car'", car, list.get(3));
        assertEquals("Should return 'bike'", bike, list.get(4));
        assertEquals("Should return 'bike1'", bike1, list.get(5));
        parking.sortVehicles(new VehicleComparator());
        assertEquals("Should return 'bike'", bike, list.get(0));
        assertEquals("Should return 'bike1'", bike1, list.get(1));
        assertEquals("Should return 'bike2'", bike2, list.get(2));
        assertEquals("Should return 'bike3'", bike3, list.get(3));
        assertEquals("Should return 'motorcycle'", motorcycle, list.get(4));
        assertEquals("Should return 'car'", car, list.get(5));
    }
}
