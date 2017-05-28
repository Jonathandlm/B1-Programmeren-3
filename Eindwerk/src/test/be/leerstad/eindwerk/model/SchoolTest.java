package be.leerstad.eindwerk.model;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class SchoolTest {

    private String ipAddressEmpty;
    private String ipAddress;
    private String siteEmpty;
    private String site;
    private String street;
    private String zip;
    private String city;
    private School schoolEmptyConstructor;
    private School schoolBasicConstructor;
    private School schoolCompleteConstructor;

    @Before
    public void testSetUp() throws Exception {
        ipAddressEmpty = "10.110.c.d";
        ipAddress = "10.120.c.d";
        siteEmpty = "UNKNOWN";
        site = "CVO Leerstad";
        street = "Brouwerijstraat 5";
        zip = "9160";
        city = "Lokeren";
        schoolEmptyConstructor = new School();
        schoolBasicConstructor = new School(ipAddress);
        schoolCompleteConstructor = new School(ipAddress, site, street, zip, city);
    }

    @Test
    public void testEmptyConstructor() {
        assertEquals(schoolEmptyConstructor.getIpAddress(), ipAddressEmpty);
        assertEquals(schoolEmptyConstructor.getSite(), schoolBasicConstructor.getSite());
        assertEquals(schoolEmptyConstructor.getStreet(), schoolBasicConstructor.getStreet());
        assertEquals(schoolEmptyConstructor.getZip(), schoolBasicConstructor.getZip());
        assertEquals(schoolEmptyConstructor.getCity(), schoolBasicConstructor.getCity());
    }

    @Test
    public void testBasicConstructor() {
        assertEquals(schoolBasicConstructor.getIpAddress(), ipAddress);
        assertEquals(schoolBasicConstructor.getSite(), siteEmpty);
        assertEquals(schoolBasicConstructor.getStreet(), null);
        assertEquals(schoolBasicConstructor.getZip(), null);
        assertEquals(schoolBasicConstructor.getCity(), null);
    }

    @Test
    public void testCompleteConstructor() {
        assertEquals(schoolCompleteConstructor.getIpAddress(), ipAddress);
        assertEquals(schoolCompleteConstructor.getSite(), site);
        assertEquals(schoolCompleteConstructor.getStreet(), street);
        assertEquals(schoolCompleteConstructor.getZip(), zip);
        assertEquals(schoolCompleteConstructor.getCity(), city);
    }

    @Test
    public void testGetIpAddress() throws NoSuchFieldException, IllegalAccessException {
        final Field field = schoolEmptyConstructor.getClass().getDeclaredField("ipAddress");
        field.setAccessible(true);
        field.set(schoolEmptyConstructor, ipAddress);

        final String result = schoolEmptyConstructor.getIpAddress();

        assertEquals("field wasn't retrieved properly", result, ipAddress);
    }

    @Test
    public void testSetIpAddress() throws NoSuchFieldException, IllegalAccessException {
        schoolEmptyConstructor.setIpAddress(ipAddress);

        final Field field = schoolEmptyConstructor.getClass().getDeclaredField("ipAddress");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(schoolEmptyConstructor), ipAddress);
    }

    @Test
    public void testGetSite() throws NoSuchFieldException, IllegalAccessException {
        final Field field = schoolEmptyConstructor.getClass().getDeclaredField("site");
        field.setAccessible(true);
        field.set(schoolEmptyConstructor, site);

        final String result = schoolEmptyConstructor.getSite();

        assertEquals("field wasn't retrieved properly", result, site);
    }

    @Test
    public void testSetSite() throws NoSuchFieldException, IllegalAccessException {
        schoolEmptyConstructor.setSite(site);

        final Field field = schoolEmptyConstructor.getClass().getDeclaredField("site");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(schoolEmptyConstructor), site);
    }

    @Test
    public void testGetStreet() throws NoSuchFieldException, IllegalAccessException {
        final Field field = schoolEmptyConstructor.getClass().getDeclaredField("street");
        field.setAccessible(true);
        field.set(schoolEmptyConstructor, street);

        final String result = schoolEmptyConstructor.getStreet();

        assertEquals("field wasn't retrieved properly", result, street);
    }

    @Test
    public void testSetStreet() throws NoSuchFieldException, IllegalAccessException {
        schoolEmptyConstructor.setStreet(street);

        final Field field = schoolEmptyConstructor.getClass().getDeclaredField("street");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(schoolEmptyConstructor), street);
    }

    @Test
    public void testGetZip() throws NoSuchFieldException, IllegalAccessException {
        final Field field = schoolEmptyConstructor.getClass().getDeclaredField("zip");
        field.setAccessible(true);
        field.set(schoolEmptyConstructor, zip);

        final String result = schoolEmptyConstructor.getZip();

        assertEquals("field wasn't retrieved properly", result, zip);
    }

    @Test
    public void testSetZip() throws NoSuchFieldException, IllegalAccessException {
        schoolEmptyConstructor.setZip(zip);

        final Field field = schoolEmptyConstructor.getClass().getDeclaredField("zip");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(schoolEmptyConstructor), zip);
    }

    @Test
    public void testGetCity() throws NoSuchFieldException, IllegalAccessException {
        final Field field = schoolEmptyConstructor.getClass().getDeclaredField("city");
        field.setAccessible(true);
        field.set(schoolEmptyConstructor, city);

        final String result = schoolEmptyConstructor.getCity();

        assertEquals("field wasn't retrieved properly", result, city);
    }

    @Test
    public void testSetCity() throws NoSuchFieldException, IllegalAccessException {
        schoolEmptyConstructor.setCity(city);

        final Field field = schoolEmptyConstructor.getClass().getDeclaredField("city");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(schoolEmptyConstructor), city);
    }

    @Test
    public void testEquals() {
        School schoolEmptyConstructor2 = new School();
        School schoolEmptyConstructor3 = new School();
        School schoolBasicConstructor2 = new School(ipAddress);
        School schoolBasicConstructor3 = new School(ipAddress);
        School schoolCompleteConstructor2 = new School(ipAddress, site, street, zip, city);
        School schoolCompleteConstructor3 = new School(ipAddress, site, street, zip, city);

        // Rule 1: reflexive
        assertTrue(schoolEmptyConstructor.equals(schoolEmptyConstructor));
        assertTrue(schoolBasicConstructor.equals(schoolBasicConstructor));
        assertTrue(schoolCompleteConstructor.equals(schoolCompleteConstructor));

        // Rule 2: transitive
        assertTrue(schoolEmptyConstructor.equals(schoolEmptyConstructor2));
        assertTrue(schoolEmptyConstructor2.equals(schoolEmptyConstructor3));
        assertTrue(schoolEmptyConstructor.equals(schoolEmptyConstructor3));
        assertTrue(schoolBasicConstructor.equals(schoolBasicConstructor2));
        assertTrue(schoolBasicConstructor2.equals(schoolBasicConstructor3));
        assertTrue(schoolBasicConstructor.equals(schoolBasicConstructor3));
        assertTrue(schoolCompleteConstructor.equals(schoolCompleteConstructor2));
        assertTrue(schoolCompleteConstructor2.equals(schoolCompleteConstructor3));
        assertTrue(schoolCompleteConstructor.equals(schoolCompleteConstructor3));

        // Rule 3: symmetric
        assertTrue(schoolEmptyConstructor2.equals(schoolEmptyConstructor));
        assertTrue(schoolBasicConstructor2.equals(schoolBasicConstructor));
        assertTrue(schoolCompleteConstructor2.equals(schoolCompleteConstructor));

        // Rule 4: null
        assertFalse(schoolEmptyConstructor.equals(null));
        assertFalse(schoolBasicConstructor.equals(null));
        assertFalse(schoolCompleteConstructor.equals(null));

        // Rule 5: hashcode
        assertEquals(schoolEmptyConstructor.hashCode(), schoolEmptyConstructor2.hashCode());
        assertEquals(schoolBasicConstructor.hashCode(), schoolBasicConstructor2.hashCode());
        assertEquals(schoolCompleteConstructor.hashCode(), schoolCompleteConstructor2.hashCode());

        // Inconvertible types
        assertFalse(schoolEmptyConstructor.equals("a string"));
        assertFalse(schoolBasicConstructor.equals("a string"));
        assertFalse(schoolCompleteConstructor.equals("a string"));

        // Changing ipAddress should change equals
        schoolEmptyConstructor2.setIpAddress(ipAddress);
        assertFalse(schoolEmptyConstructor.equals(schoolEmptyConstructor2));
    }

    @Test
    public void testHashCode() {
        School schoolEmptyConstructor2 = new School();
        School schoolEmptyConstructor3 = new School();

        assertEquals(schoolEmptyConstructor.hashCode(), schoolEmptyConstructor.hashCode());
        assertTrue(schoolEmptyConstructor.equals(schoolEmptyConstructor2));
        assertEquals(schoolEmptyConstructor.hashCode(), schoolEmptyConstructor2.hashCode());
        assertEquals(schoolEmptyConstructor.hashCode(), schoolEmptyConstructor3.hashCode());
        assertNotEquals(schoolEmptyConstructor.hashCode(), schoolBasicConstructor.hashCode());

        // Changing Site shouldn't change hashcode
        schoolEmptyConstructor2.setSite(site);
        assertEquals(schoolEmptyConstructor.hashCode(), schoolEmptyConstructor2.hashCode());
        assertNotEquals(schoolEmptyConstructor2.hashCode(), schoolBasicConstructor.hashCode());

        // Changing ipAddress should change hashcode
        schoolEmptyConstructor3.setIpAddress(ipAddress);
        assertNotEquals(schoolEmptyConstructor.hashCode(), schoolEmptyConstructor3.hashCode());
        assertEquals(schoolEmptyConstructor3.hashCode(), schoolBasicConstructor.hashCode());
    }

    @Test
    public void testToString() {
        String schoolEmptyConstructorToString = "School{" + siteEmpty + " (" + ipAddressEmpty + ")}";
        String schoolBasicConstructorToString = "School{" + siteEmpty + " (" + ipAddress + ")}";
        String schoolCompleteConstructorToString = "School{" + site + " (" + ipAddress + ")}";
        assertEquals(schoolEmptyConstructor.toString(), schoolEmptyConstructorToString);
        assertEquals(schoolBasicConstructor.toString(), schoolBasicConstructorToString);
        assertEquals(schoolCompleteConstructor.toString(), schoolCompleteConstructorToString);
    }

}