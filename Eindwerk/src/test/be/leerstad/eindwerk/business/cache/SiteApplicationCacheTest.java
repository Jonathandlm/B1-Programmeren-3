package be.leerstad.eindwerk.business.cache;

import be.leerstad.eindwerk.model.SiteApplication;
import be.leerstad.eindwerk.service.LogAnalyserDAOImpl;
import be.leerstad.eindwerk.service.SiteApplicationDAOImpl;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class SiteApplicationCacheTest {

    private SiteApplication siteApplication1;
    private SiteApplication siteApplication2;
    private SiteApplication testSiteApplication;
    private String applicationName1;
    private String testName;

    @Before
    public void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
        Field instanceField = SiteApplicationCache.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
    }

    @Before
    public void resetDatabase() {
        LogAnalyserDAOImpl.getInstance().clearDatabase();
    }

    @Before
    public void setUp() {
        applicationName1 = "ELO";
        testName = null;
        testSiteApplication = null;
        siteApplication1 = new SiteApplication(1, applicationName1);
        siteApplication2 = new SiteApplication();
    }

    @Test
    public void testGetInstance() {
        SiteApplicationCache siteApplicationCache = SiteApplicationCache.getInstance();
        assertNotNull(siteApplicationCache);
    }

    @Test
    public void testGetSiteApplication() throws NoSuchFieldException, IllegalAccessException {
        SiteApplicationCache siteApplicationCache = SiteApplicationCache.getInstance();

        // New siteApplication
        testSiteApplication = siteApplicationCache.getSiteApplication(applicationName1);
        assertNotNull(testSiteApplication);
        assertTrue(siteApplicationCache.containsKey(1));
        assertTrue(siteApplicationCache.containsValue(applicationName1));
        assertEquals(testSiteApplication, siteApplication1);

        // Existing siteApplication
        testSiteApplication = siteApplicationCache.getSiteApplication(applicationName1);
        assertNotNull(testSiteApplication);
        assertTrue(siteApplicationCache.containsKey(1));
        assertTrue(siteApplicationCache.containsValue(applicationName1));
        assertTrue(siteApplicationCache.containsValue(testSiteApplication.getApplication()));
        assertEquals(testSiteApplication, siteApplication1);

        // Corrupt cache gets restored
        Field cacheField = SiteApplicationCache.class.getDeclaredField("cache");
        cacheField.setAccessible(true);
        cacheField.set(siteApplicationCache, null);
        testSiteApplication = siteApplicationCache.getSiteApplication(applicationName1);
        assertEquals(testSiteApplication, siteApplication1);
    }

    @Test
    public void testContainsKey() {
        SiteApplicationCache siteApplicationCache = SiteApplicationCache.getInstance();
        assertFalse(siteApplicationCache.containsKey(null));
        assertFalse(siteApplicationCache.containsKey(1));
        assertFalse(siteApplicationCache.containsKey(2));

        siteApplicationCache.put(null, null);
        siteApplicationCache.put(1, applicationName1);
        siteApplicationCache.put(2, "");
        assertTrue(siteApplicationCache.containsKey(null));
        assertTrue(siteApplicationCache.containsKey(1));
        assertTrue(siteApplicationCache.containsKey(2));
    }

    @Test
    public void testContainsValue() {
        SiteApplicationCache siteApplicationCache = SiteApplicationCache.getInstance();
        assertFalse(siteApplicationCache.containsValue(null));
        assertFalse(siteApplicationCache.containsValue(applicationName1));
        assertFalse(siteApplicationCache.containsValue(""));

        siteApplicationCache.put(null, null);
        siteApplicationCache.put(1, applicationName1);
        siteApplicationCache.put(2, "");
        assertTrue(siteApplicationCache.containsValue(null));
        assertTrue(siteApplicationCache.containsValue(applicationName1));
        assertTrue(siteApplicationCache.containsValue(""));
    }

    @Test
    public void testGet() {
        SiteApplicationCache siteApplicationCache = SiteApplicationCache.getInstance();
        testName = siteApplicationCache.get(1);
        assertNull(testName);

        siteApplicationCache.put(1, applicationName1);
        testName = siteApplicationCache.get(1);
        assertNotNull(testName);
        assertEquals(applicationName1, testName);
    }

    @Test
    public void testValues() {
        SiteApplicationCache siteApplicationCache = SiteApplicationCache.getInstance();
        Collection<String> siteApplicationNames = siteApplicationCache.values();
        assertNotNull(siteApplicationNames);
        assertTrue(siteApplicationNames.isEmpty());

        siteApplicationCache.put(1, applicationName1);
        siteApplicationCache.put(2, "");

        siteApplicationNames = siteApplicationCache.values();
        assertNotNull(siteApplicationNames);
        assertEquals(siteApplicationNames.size(), 2);
        assertTrue(siteApplicationNames.contains(applicationName1));
        assertTrue(siteApplicationNames.contains(""));
        assertFalse(siteApplicationNames.contains(testName));
    }

    @Test
    public void testPut() {
        SiteApplicationCache siteApplicationCache = SiteApplicationCache.getInstance();
        siteApplicationCache.put(null, null);
        assertTrue(siteApplicationCache.containsKey(null));
        assertTrue(siteApplicationCache.containsValue(null));

        siteApplicationCache.put(1, applicationName1);
        assertTrue(siteApplicationCache.containsKey(1));
        assertTrue(siteApplicationCache.containsValue(applicationName1));

        siteApplicationCache.put(2, "");
        assertTrue(siteApplicationCache.containsKey(2));
        assertTrue(siteApplicationCache.containsValue(""));

        // The put method overwrites the previous value associated with the given key.
        siteApplicationCache.put(2, testName);
        assertTrue(siteApplicationCache.containsKey(2));
        assertTrue(siteApplicationCache.containsValue(testName));
    }

    @Test
    public void testSize() {
        SiteApplicationCache siteApplicationCache = SiteApplicationCache.getInstance();
        assertEquals(siteApplicationCache.size(), 0);

        siteApplicationCache.put(null, null);
        assertEquals(siteApplicationCache.size(), 1);

        siteApplicationCache.put(1, applicationName1);
        assertEquals(siteApplicationCache.size(), 2);

        siteApplicationCache.put(2, "");
        assertEquals(siteApplicationCache.size(), 3);

        // The put method overwrites the previous value associated with the given key.
        siteApplicationCache.put(2, testName);
        assertEquals(siteApplicationCache.size(), 3);
    }

    @Test
    public void testIsEmpty() {
        SiteApplicationCache siteApplicationCache = SiteApplicationCache.getInstance();
        assertTrue(siteApplicationCache.isEmpty());

        siteApplicationCache.put(1, applicationName1);
        assertFalse(siteApplicationCache.isEmpty());
    }

    @Test
    public void testFill() {
        SiteApplicationCache siteApplicationCache = SiteApplicationCache.getInstance();

        // Database is empty
        siteApplicationCache.fill();
        assertEquals(siteApplicationCache.size(), 0);

        // Insert 2 siteApplications in database
        SiteApplicationDAOImpl.getInstance().insertSiteApplication(siteApplication1);
        SiteApplicationDAOImpl.getInstance().insertSiteApplication(siteApplication2);
        siteApplicationCache.fill();
        assertEquals(siteApplicationCache.size(), 2);
        assertTrue(siteApplicationCache.containsValue(applicationName1));
        assertTrue(siteApplicationCache.containsValue(""));
        assertFalse(siteApplicationCache.containsValue(testName));

        // Filling the cache again with the same data, gives the same result
        siteApplicationCache.fill();
        assertEquals(siteApplicationCache.size(), 2);
        assertTrue(siteApplicationCache.containsValue(applicationName1));
        assertTrue(siteApplicationCache.containsValue(""));
        assertFalse(siteApplicationCache.containsValue(testName));
    }

    @Test
    public void testIterator() {
        // Test the 3 methods 'hasNext', 'next' and 'remove' from the implemented Iterator interface
        SiteApplicationCache siteApplicationCache = SiteApplicationCache.getInstance();

        Iterator<String> iterator0 = siteApplicationCache.iterator();
        assertFalse(iterator0.hasNext());
        try {
            testName = iterator0.next();
            fail("Expected NoSuchElementException to be thrown");
        } catch (NoSuchElementException e) {
            // This exception is expected.
        }

        siteApplicationCache.put(1, applicationName1);
        Iterator<String> iterator1 = siteApplicationCache.iterator();
        assertTrue(iterator1.hasNext());
        testName = iterator1.next();
        assertEquals(testName, applicationName1);
        assertFalse(iterator1.hasNext());
        try {
            testName = iterator1.next();
            fail("Expected NoSuchElementException to be thrown");
        } catch (NoSuchElementException e) {
            // This exception is expected.
        }
        iterator1.remove();
        assertEquals(siteApplicationCache.size(), 0);
        assertFalse(iterator1.hasNext());
        try {
            testName = iterator1.next();
            fail("Expected NoSuchElementException to be thrown");
        } catch (NoSuchElementException e) {
            // This exception is expected.
        }
        try {
            iterator1.remove();
            fail("Expected IllegalStateException to be thrown");
        } catch (IllegalStateException e) {
            // This exception is expected.
        }
    }

}