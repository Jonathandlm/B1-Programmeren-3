package be.leerstad.eindwerk.business.cache;

import be.leerstad.eindwerk.model.Site;
import be.leerstad.eindwerk.service.LogAnalyserDAOImpl;
import be.leerstad.eindwerk.service.SiteDAOImpl;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class SiteCacheTest {

    private Site site1;
    private Site site2;
    private Site testSite;
    private String siteName1;
    private String testName;

    @Before
    public void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
        Field instanceField = SiteCache.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
    }

    @Before
    public void resetDatabase() {
        LogAnalyserDAOImpl.getInstance().clearDatabase();
    }

    @Before
    public void setUp() {
        siteName1 = "google.com";
        testName = null;
        testSite = null;
        site1 = new Site(1, siteName1);
        site2 = new Site();
    }

    @Test
    public void testGetInstance() {
        SiteCache siteCache = SiteCache.getInstance();
        assertNotNull(siteCache);
    }

    @Test
    public void testGetSite() throws NoSuchFieldException, IllegalAccessException {
        SiteCache siteCache = SiteCache.getInstance();

        // New site
        testSite = siteCache.getSite(siteName1);
        assertNotNull(testSite);
        assertTrue(siteCache.containsKey(1));
        assertTrue(siteCache.containsValue(siteName1));
        assertEquals(testSite, site1);

        // Existing site
        testSite = siteCache.getSite(siteName1);
        assertNotNull(testSite);
        assertTrue(siteCache.containsKey(1));
        assertTrue(siteCache.containsValue(siteName1));
        assertTrue(siteCache.containsValue(testSite.getSite()));
        assertEquals(testSite, site1);

        // Corrupt cache gets restored
        Field cacheField = SiteCache.class.getDeclaredField("cache");
        cacheField.setAccessible(true);
        cacheField.set(siteCache, null);
        testSite = siteCache.getSite(siteName1);
        assertEquals(testSite, site1);
    }

    @Test
    public void testContainsKey() {
        SiteCache siteCache = SiteCache.getInstance();
        assertFalse(siteCache.containsKey(null));
        assertFalse(siteCache.containsKey(1));
        assertFalse(siteCache.containsKey(2));

        siteCache.put(null, null);
        siteCache.put(1, siteName1);
        siteCache.put(2, "");
        assertTrue(siteCache.containsKey(null));
        assertTrue(siteCache.containsKey(1));
        assertTrue(siteCache.containsKey(2));
    }

    @Test
    public void testContainsValue() {
        SiteCache siteCache = SiteCache.getInstance();
        assertFalse(siteCache.containsValue(null));
        assertFalse(siteCache.containsValue(siteName1));
        assertFalse(siteCache.containsValue(""));

        siteCache.put(null, null);
        siteCache.put(1, siteName1);
        siteCache.put(2, "");
        assertTrue(siteCache.containsValue(null));
        assertTrue(siteCache.containsValue(siteName1));
        assertTrue(siteCache.containsValue(""));
    }

    @Test
    public void testGet() {
        SiteCache siteCache = SiteCache.getInstance();
        testName = siteCache.get(1);
        assertNull(testName);

        siteCache.put(1, siteName1);
        testName = siteCache.get(1);
        assertNotNull(testName);
        assertEquals(siteName1, testName);
    }

    @Test
    public void testValues() {
        SiteCache siteCache = SiteCache.getInstance();
        Collection<String> siteNames = siteCache.values();
        assertNotNull(siteNames);
        assertTrue(siteNames.isEmpty());

        siteCache.put(1, siteName1);
        siteCache.put(2, "");

        siteNames = siteCache.values();
        assertNotNull(siteNames);
        assertEquals(siteNames.size(), 2);
        assertTrue(siteNames.contains(siteName1));
        assertTrue(siteNames.contains(""));
        assertFalse(siteNames.contains(testName));
    }

    @Test
    public void testPut() {
        SiteCache siteCache = SiteCache.getInstance();
        siteCache.put(null, null);
        assertTrue(siteCache.containsKey(null));
        assertTrue(siteCache.containsValue(null));

        siteCache.put(1, siteName1);
        assertTrue(siteCache.containsKey(1));
        assertTrue(siteCache.containsValue(siteName1));

        siteCache.put(2, "");
        assertTrue(siteCache.containsKey(2));
        assertTrue(siteCache.containsValue(""));

        // The put method overwrites the previous value associated with the given key.
        siteCache.put(2, testName);
        assertTrue(siteCache.containsKey(2));
        assertTrue(siteCache.containsValue(testName));
    }

    @Test
    public void testSize() {
        SiteCache siteCache = SiteCache.getInstance();
        assertEquals(siteCache.size(), 0);

        siteCache.put(null, null);
        assertEquals(siteCache.size(), 1);

        siteCache.put(1, siteName1);
        assertEquals(siteCache.size(), 2);

        siteCache.put(2, "");
        assertEquals(siteCache.size(), 3);

        // The put method overwrites the previous value associated with the given key.
        siteCache.put(2, testName);
        assertEquals(siteCache.size(), 3);
    }

    @Test
    public void testIsEmpty() {
        SiteCache siteCache = SiteCache.getInstance();
        assertTrue(siteCache.isEmpty());

        siteCache.put(1, siteName1);
        assertFalse(siteCache.isEmpty());
    }

    @Test
    public void testFill() {
        SiteCache siteCache = SiteCache.getInstance();

        // Database is empty
        siteCache.fill();
        assertEquals(siteCache.size(), 0);

        // Insert 2 sites in database
        SiteDAOImpl.getInstance().insertSite(site1);
        SiteDAOImpl.getInstance().insertSite(site2);
        siteCache.fill();
        assertEquals(siteCache.size(), 2);
        assertTrue(siteCache.containsValue(siteName1));
        assertTrue(siteCache.containsValue(""));
        assertFalse(siteCache.containsValue(testName));

        // Filling the cache again with the same data, gives the same result
        siteCache.fill();
        assertEquals(siteCache.size(), 2);
        assertTrue(siteCache.containsValue(siteName1));
        assertTrue(siteCache.containsValue(""));
        assertFalse(siteCache.containsValue(testName));
    }

    @Test
    public void testIterator() {
        // Test the 3 methods 'hasNext', 'next' and 'remove' from the implemented Iterator interface
        SiteCache siteCache = SiteCache.getInstance();

        Iterator<String> iterator0 = siteCache.iterator();
        assertFalse(iterator0.hasNext());
        try {
            testName = iterator0.next();
            fail("Expected NoSuchElementException to be thrown");
        } catch (NoSuchElementException e) {
            // This exception is expected.
        }

        siteCache.put(1, siteName1);
        Iterator<String> iterator1 = siteCache.iterator();
        assertTrue(iterator1.hasNext());
        testName = iterator1.next();
        assertEquals(testName, siteName1);
        assertFalse(iterator1.hasNext());
        try {
            testName = iterator1.next();
            fail("Expected NoSuchElementException to be thrown");
        } catch (NoSuchElementException e) {
            // This exception is expected.
        }
        iterator1.remove();
        assertEquals(siteCache.size(), 0);
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