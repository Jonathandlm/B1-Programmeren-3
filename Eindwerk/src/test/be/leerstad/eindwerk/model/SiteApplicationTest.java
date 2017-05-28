package be.leerstad.eindwerk.model;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class SiteApplicationTest {

    private int applicationIdEmpty;
    private int applicationId;
    private String applicationEmpty;
    private String application;
    private SiteApplication siteApplicationEmptyConstructor;
    private SiteApplication siteApplicationCompleteConstructor;

    @Before
    public void testSetUp() throws Exception {
        applicationIdEmpty = 0;
        applicationId = 1;
        applicationEmpty = "";
        application = "ELO";
        siteApplicationEmptyConstructor = new SiteApplication();
        siteApplicationCompleteConstructor = new SiteApplication(applicationId, application);
    }

    @Test
    public void testEmptyConstructor() {
        assertEquals(siteApplicationEmptyConstructor.getApplicationId(), applicationIdEmpty);
        assertEquals(siteApplicationEmptyConstructor.getApplication(), applicationEmpty);
    }

    @Test
    public void testCompleteConstructor() {
        assertEquals(siteApplicationCompleteConstructor.getApplicationId(), applicationId);
        assertEquals(siteApplicationCompleteConstructor.getApplication(), application);
    }

    @Test
    public void testGetApplicationId() throws NoSuchFieldException, IllegalAccessException {
        final Field field = siteApplicationEmptyConstructor.getClass().getDeclaredField("applicationId");
        field.setAccessible(true);
        field.set(siteApplicationEmptyConstructor, applicationId);

        final int result = siteApplicationEmptyConstructor.getApplicationId();

        assertEquals("field wasn't retrieved properly", result, applicationId);
    }

    @Test
    public void testSetApplicationId() throws NoSuchFieldException, IllegalAccessException {
        siteApplicationEmptyConstructor.setApplicationId(applicationId);

        final Field field = siteApplicationEmptyConstructor.getClass().getDeclaredField("applicationId");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(siteApplicationEmptyConstructor), applicationId);
    }

    @Test
    public void testGetApplication() throws NoSuchFieldException, IllegalAccessException {
        final Field field = siteApplicationEmptyConstructor.getClass().getDeclaredField("application");
        field.setAccessible(true);
        field.set(siteApplicationEmptyConstructor, application);

        final String result = siteApplicationEmptyConstructor.getApplication();

        assertEquals("field wasn't retrieved properly", result, application);
    }

    @Test
    public void testSetApplication() throws NoSuchFieldException, IllegalAccessException {
        siteApplicationEmptyConstructor.setApplication(application);

        final Field field = siteApplicationEmptyConstructor.getClass().getDeclaredField("application");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(siteApplicationEmptyConstructor), application);
    }

    @Test
    public void testEquals() {
        SiteApplication siteApplicationEmptyConstructor2 = new SiteApplication();
        SiteApplication siteApplicationEmptyConstructor3 = new SiteApplication();
        SiteApplication siteApplicationCompleteConstructor2 = new SiteApplication(applicationId, application);
        SiteApplication siteApplicationCompleteConstructor3 = new SiteApplication(applicationId, application);

        // Rule 1: reflexive
        assertTrue(siteApplicationEmptyConstructor.equals(siteApplicationEmptyConstructor));
        assertTrue(siteApplicationCompleteConstructor.equals(siteApplicationCompleteConstructor));

        // Rule 2: transitive
        assertTrue(siteApplicationEmptyConstructor.equals(siteApplicationEmptyConstructor2));
        assertTrue(siteApplicationEmptyConstructor2.equals(siteApplicationEmptyConstructor3));
        assertTrue(siteApplicationEmptyConstructor.equals(siteApplicationEmptyConstructor3));
        assertTrue(siteApplicationCompleteConstructor.equals(siteApplicationCompleteConstructor2));
        assertTrue(siteApplicationCompleteConstructor2.equals(siteApplicationCompleteConstructor3));
        assertTrue(siteApplicationCompleteConstructor.equals(siteApplicationCompleteConstructor3));

        // Rule 3: symmetric
        assertTrue(siteApplicationEmptyConstructor2.equals(siteApplicationEmptyConstructor));
        assertTrue(siteApplicationCompleteConstructor2.equals(siteApplicationCompleteConstructor));

        // Rule 4: null
        assertFalse(siteApplicationEmptyConstructor.equals(null));
        assertFalse(siteApplicationCompleteConstructor.equals(null));

        // Rule 5: hashcode
        assertEquals(siteApplicationEmptyConstructor.hashCode(), siteApplicationEmptyConstructor2.hashCode());
        assertEquals(siteApplicationCompleteConstructor.hashCode(), siteApplicationCompleteConstructor2.hashCode());

        // Inconvertible types
        assertFalse(siteApplicationEmptyConstructor.equals("a string"));
        assertFalse(siteApplicationCompleteConstructor.equals("a string"));

        // Changing applicationId or application should change equals
        siteApplicationEmptyConstructor2.setApplicationId(applicationId);
        assertFalse(siteApplicationEmptyConstructor.equals(siteApplicationEmptyConstructor2));

        siteApplicationEmptyConstructor3.setApplication("HOI");
        assertFalse(siteApplicationEmptyConstructor.equals(siteApplicationEmptyConstructor3));
    }

    @Test
    public void testHashCode() {
        SiteApplication siteApplicationEmptyConstructor2 = new SiteApplication();
        SiteApplication siteApplicationEmptyConstructor3 = new SiteApplication();

        assertEquals(siteApplicationEmptyConstructor.hashCode(), siteApplicationEmptyConstructor.hashCode());
        assertTrue(siteApplicationEmptyConstructor.equals(siteApplicationEmptyConstructor2));
        assertEquals(siteApplicationEmptyConstructor.hashCode(), siteApplicationEmptyConstructor2.hashCode());
        assertEquals(siteApplicationEmptyConstructor.hashCode(), siteApplicationEmptyConstructor3.hashCode());
        assertNotEquals(siteApplicationEmptyConstructor.hashCode(), siteApplicationCompleteConstructor.hashCode());

        // Changing applicationId or application should change hashcode
        siteApplicationEmptyConstructor2.setApplicationId(applicationId);
        assertNotEquals(siteApplicationEmptyConstructor.hashCode(), siteApplicationEmptyConstructor2.hashCode());
        assertNotEquals(siteApplicationEmptyConstructor2.hashCode(), siteApplicationCompleteConstructor.hashCode());
        siteApplicationEmptyConstructor3.setApplication("HOI");
        assertNotEquals(siteApplicationEmptyConstructor.hashCode(), siteApplicationEmptyConstructor3.hashCode());
        assertNotEquals(siteApplicationEmptyConstructor3.hashCode(), siteApplicationCompleteConstructor.hashCode());
    }

    @Test
    public void testToString() {
        String siteApplicationEmptyConstructorToString = "SiteApplication{" + applicationEmpty + '}';
        String siteApplicationCompleteConstructorToString = "SiteApplication{" + application + '}';
        assertEquals(siteApplicationEmptyConstructor.toString(), siteApplicationEmptyConstructorToString);
        assertEquals(siteApplicationCompleteConstructor.toString(), siteApplicationCompleteConstructorToString);
    }

}