package be.leerstad;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ParkingTest {
    private Parking parking;
    private Auto blauweBMW;
    private Auto rodeVolvo;
    private Auto valseLada;

    @Before
    public void setUp() {
        parking = new Parking();
        Auto blauweBMW = new Auto("BMW", "blauw", "123abc");
        Auto rodeVolvo = new Auto("Volvo", "rood", "456def");
        Auto valseLada = new Auto("Lada", "zwart", "123abc");
    }

    @Test
    public void testSize() {
        int desiredResult = 0;
        assertEquals("Not correct size", desiredResult, parking.aantalAutos());
    }

    @Test
    public void testFirstAdd() {
        parking.addAuto(blauweBMW);
        String desiredResult = "Parking met " + 1 + " auto(s):\n" +
                "parkingSet=" + parking.getParkingSet();
        assertEquals("Not correct set", desiredResult, parking.toString());
    }

    @Test
    public void testSizeAfterFirstAdd() {
        parking.addAuto(blauweBMW);
        int desiredResult = 1;
        assertEquals("Not correct size", desiredResult, parking.aantalAutos());
    }

    @Test
    public void testSecondAdd() {
        parking.addAuto(blauweBMW);
        parking.addAuto(rodeVolvo);
        String desiredResult = "Parking met " + 2 + " auto(s):\n" +
                "parkingSet=" + parking.getParkingSet();
        assertEquals("Not correct set", desiredResult, parking.toString());
    }

    @Test
    public void testSizeAfterSecondAdd() {
        parking.addAuto(blauweBMW);
        parking.addAuto(rodeVolvo);
        int desiredResult = 2;
        assertEquals("Not correct size", desiredResult, parking.aantalAutos());
    }

    @Test
    public void testThirdAdd() {
        parking.addAuto(valseLada);
        String desiredResult = "Parking met " + 2 + " auto(s):\n" +
                "parkingSet=" + parking.getParkingSet();
        assertEquals("Not correct set", desiredResult, parking.toString());
    }

    @Test
    public void testSizeAfterThirdAdd() {
        int desiredResult = 2;
        assertEquals("Not correct size", desiredResult, parking.aantalAutos());
    }
}
