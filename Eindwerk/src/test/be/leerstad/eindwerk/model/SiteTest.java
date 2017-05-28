package be.leerstad.eindwerk.model;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class SiteTest {
    
    private int siteIdEmpty;
    private int siteId;
    private String siteEmpty;
    private String site;
    private Site siteEmptyConstructor;
    private Site siteCompleteConstructor;

    @Before
    public void testSetUp() throws Exception {
        siteIdEmpty = 0;
        siteId = 1;
        siteEmpty = "";
        site = "google.com";
        siteEmptyConstructor = new Site();
        siteCompleteConstructor = new Site(siteId, site);
    }

    @Test
    public void testEmptyConstructor() {
        assertEquals(siteEmptyConstructor.getSiteId(), siteIdEmpty);
        assertEquals(siteEmptyConstructor.getSite(), siteEmpty);
    }

    @Test
    public void testCompleteConstructor() {
        assertEquals(siteCompleteConstructor.getSiteId(), siteId);
        assertEquals(siteCompleteConstructor.getSite(), site);
    }

    @Test
    public void testGetSiteId() throws NoSuchFieldException, IllegalAccessException {
        final Field field = siteEmptyConstructor.getClass().getDeclaredField("siteId");
        field.setAccessible(true);
        field.set(siteEmptyConstructor, siteId);

        final int result = siteEmptyConstructor.getSiteId();

        assertEquals("field wasn't retrieved properly", result, siteId);
    }

    @Test
    public void testSetSiteId() throws NoSuchFieldException, IllegalAccessException {
        siteEmptyConstructor.setSiteId(siteId);

        final Field field = siteEmptyConstructor.getClass().getDeclaredField("siteId");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(siteEmptyConstructor), siteId);
    }

    @Test
    public void testGetSite() throws NoSuchFieldException, IllegalAccessException {
        final Field field = siteEmptyConstructor.getClass().getDeclaredField("site");
        field.setAccessible(true);
        field.set(siteEmptyConstructor, site);

        final String result = siteEmptyConstructor.getSite();

        assertEquals("field wasn't retrieved properly", result, site);
    }

    @Test
    public void testSetSite() throws NoSuchFieldException, IllegalAccessException {
        siteEmptyConstructor.setSite(site);

        final Field field = siteEmptyConstructor.getClass().getDeclaredField("site");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(siteEmptyConstructor), site);
    }

    @Test
    public void testEquals() {
        Site siteEmptyConstructor2 = new Site();
        Site siteEmptyConstructor3 = new Site();
        Site siteCompleteConstructor2 = new Site(siteId, site);
        Site siteCompleteConstructor3 = new Site(siteId, site);

        // Rule 1: reflexive
        assertTrue(siteEmptyConstructor.equals(siteEmptyConstructor));
        assertTrue(siteCompleteConstructor.equals(siteCompleteConstructor));

        // Rule 2: transitive
        assertTrue(siteEmptyConstructor.equals(siteEmptyConstructor2));
        assertTrue(siteEmptyConstructor2.equals(siteEmptyConstructor3));
        assertTrue(siteEmptyConstructor.equals(siteEmptyConstructor3));
        assertTrue(siteCompleteConstructor.equals(siteCompleteConstructor2));
        assertTrue(siteCompleteConstructor2.equals(siteCompleteConstructor3));
        assertTrue(siteCompleteConstructor.equals(siteCompleteConstructor3));

        // Rule 3: symmetric
        assertTrue(siteEmptyConstructor2.equals(siteEmptyConstructor));
        assertTrue(siteCompleteConstructor2.equals(siteCompleteConstructor));

        // Rule 4: null
        assertFalse(siteEmptyConstructor.equals(null));
        assertFalse(siteCompleteConstructor.equals(null));

        // Rule 5: hashcode
        assertEquals(siteEmptyConstructor.hashCode(), siteEmptyConstructor2.hashCode());
        assertEquals(siteCompleteConstructor.hashCode(), siteCompleteConstructor2.hashCode());

        // Inconvertible types
        assertFalse(siteEmptyConstructor.equals("a string"));
        assertFalse(siteCompleteConstructor.equals("a string"));

        // Changing siteId or site should change equals
        siteEmptyConstructor2.setSiteId(siteId);
        assertFalse(siteEmptyConstructor.equals(siteEmptyConstructor2));

        siteEmptyConstructor3.setSite("bing.com");
        assertFalse(siteEmptyConstructor.equals(siteEmptyConstructor3));
    }

    @Test
    public void testHashCode() {
        Site siteEmptyConstructor2 = new Site();
        Site siteEmptyConstructor3 = new Site();

        assertEquals(siteEmptyConstructor.hashCode(), siteEmptyConstructor.hashCode());
        assertTrue(siteEmptyConstructor.equals(siteEmptyConstructor2));
        assertEquals(siteEmptyConstructor.hashCode(), siteEmptyConstructor2.hashCode());
        assertEquals(siteEmptyConstructor.hashCode(), siteEmptyConstructor3.hashCode());
        assertNotEquals(siteEmptyConstructor.hashCode(), siteCompleteConstructor.hashCode());

        // Changing siteId or site should change hashcode
        siteEmptyConstructor2.setSiteId(siteId);
        assertNotEquals(siteEmptyConstructor.hashCode(), siteEmptyConstructor2.hashCode());
        assertNotEquals(siteEmptyConstructor2.hashCode(), siteCompleteConstructor.hashCode());
        siteEmptyConstructor3.setSite("bing.com");
        assertNotEquals(siteEmptyConstructor.hashCode(), siteEmptyConstructor3.hashCode());
        assertNotEquals(siteEmptyConstructor3.hashCode(), siteCompleteConstructor.hashCode());
    }

    @Test
    public void testToString() {
        String siteEmptyConstructorToString = "Site{" + siteEmpty + '}';
        String siteCompleteConstructorToString = "Site{" + site + '}';
        assertEquals(siteEmptyConstructor.toString(), siteEmptyConstructorToString);
        assertEquals(siteCompleteConstructor.toString(), siteCompleteConstructorToString);
    }

}