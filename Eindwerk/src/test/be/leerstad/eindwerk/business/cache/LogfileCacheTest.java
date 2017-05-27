package be.leerstad.eindwerk.business.cache;

import be.leerstad.eindwerk.model.Logfile;
import be.leerstad.eindwerk.service.LogAnalyserDAOImpl;
import be.leerstad.eindwerk.service.LogfileDAOImpl;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.Assert.*;

public class LogfileCacheTest {

    private Logfile logfile1;
    private Logfile logfile2;
    private Logfile logfile3;
    private Logfile testLogfile;
    private String logfileName1;
    private String logfileName2;

    @Before
    public void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
        Field instanceField = LogfileCache.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
    }

    @Before
    public void resetDatabase() {
        LogAnalyserDAOImpl.getInstance().clearDatabase();
    }

    @Before
    public void setUp() {
        logfileName1 = "ProxyLog_2016-12-06.log";
        logfileName2 = "localhost_access_log.2016-12-06.txt";
        testLogfile = null;
        logfile1 = new Logfile(logfileName1);
        logfile2 = new Logfile(logfileName2);
        logfile3 = new Logfile();
    }

    @Test
    public void testGetInstance() {
        LogfileCache logfileCache = LogfileCache.getInstance();
        assertNotNull(logfileCache);
    }

    @Test
    public void testGetLogfile() throws NoSuchFieldException, IllegalAccessException {
        LogfileCache logfileCache = LogfileCache.getInstance();

        // New logfile
        testLogfile = logfileCache.getLogfile(logfileName1);
        assertNotNull(testLogfile);
        assertTrue(logfileCache.containsKey(logfileName1));
        assertTrue(logfileCache.containsValue(testLogfile));
        assertTrue(logfileCache.containsValue(logfile1));
        assertEquals(testLogfile, logfile1);

        // Existing logfile
        testLogfile = logfileCache.getLogfile(logfileName1);
        assertNotNull(testLogfile);
        assertTrue(logfileCache.containsKey(logfileName1));
        assertTrue(logfileCache.containsValue(testLogfile));
        assertTrue(logfileCache.containsValue(logfile1));
        assertEquals(testLogfile, logfile1);

        // Corrupt cache gets restored
        Field cacheField = LogfileCache.class.getDeclaredField("cache");
        cacheField.setAccessible(true);
        cacheField.set(logfileCache, null);
        testLogfile = logfileCache.getLogfile(logfileName1);
        assertEquals(testLogfile, logfile1);
    }

    @Test
    public void testContainsKey() {
        LogfileCache logfileCache = LogfileCache.getInstance();
        assertFalse(logfileCache.containsKey(null));
        assertFalse(logfileCache.containsKey(""));
        assertFalse(logfileCache.containsKey(logfileName1));

        logfileCache.put(null, null);
        logfileCache.put("", logfile3);
        logfileCache.put(logfileName1, logfile1);
        assertTrue(logfileCache.containsKey(null));
        assertTrue(logfileCache.containsKey(""));
        assertTrue(logfileCache.containsKey(logfileName1));
    }

    @Test
    public void testContainsValue() {
        LogfileCache logfileCache = LogfileCache.getInstance();
        assertFalse(logfileCache.containsValue(null));
        assertFalse(logfileCache.containsValue(logfile3));
        assertFalse(logfileCache.containsValue(logfile1));

        logfileCache.put(null, null);
        logfileCache.put("", logfile3);
        logfileCache.put(logfileName1, logfile1);
        assertTrue(logfileCache.containsValue(null));
        assertTrue(logfileCache.containsValue(logfile3));
        assertTrue(logfileCache.containsValue(logfile1));
    }

    @Test
    public void testGet() {
        LogfileCache logfileCache = LogfileCache.getInstance();
        testLogfile = logfileCache.get(logfileName1);
        assertNull(testLogfile);

        logfileCache.put(logfileName1, logfile1);
        testLogfile = logfileCache.get(logfileName1);
        assertNotNull(testLogfile);
        assertEquals(testLogfile, logfile1);
    }

    @Test
    public void testValues() {
        LogfileCache logfileCache = LogfileCache.getInstance();
        Collection<Logfile> logfiles = logfileCache.values();
        assertNotNull(logfiles);
        assertTrue(logfiles.isEmpty());

        logfileCache.put(logfileName1, logfile1);
        logfileCache.put(logfileName2, logfile2);
        logfileCache.put("", logfile3);

        logfiles = logfileCache.values();
        assertNotNull(logfiles);
        assertEquals(logfiles.size(), 3);
        assertTrue(logfiles.contains(logfile1));
        assertTrue(logfiles.contains(logfile2));
        assertTrue(logfiles.contains(logfile3));
        assertFalse(logfiles.contains(testLogfile));
    }

    @Test
    public void testPut() {
        LogfileCache logfileCache = LogfileCache.getInstance();
        logfileCache.put(null, null);
        assertTrue(logfileCache.containsKey(null));
        assertTrue(logfileCache.containsValue(null));

        logfileCache.put("", logfile3);
        assertTrue(logfileCache.containsKey(""));
        assertTrue(logfileCache.containsValue(logfile3));

        logfileCache.put(logfileName1, logfile1);
        assertTrue(logfileCache.containsKey(logfileName1));
        assertTrue(logfileCache.containsValue(logfile1));

        // The put method overwrites the previous value associated with the given key.
        logfileCache.put(logfileName1, logfile2);
        assertTrue(logfileCache.containsKey(logfileName1));
        assertTrue(logfileCache.containsValue(logfile2));
    }

    @Test
    public void testSize() {
        LogfileCache logfileCache = LogfileCache.getInstance();
        assertEquals(logfileCache.size(), 0);

        logfileCache.put(null, null);
        assertEquals(logfileCache.size(), 1);

        logfileCache.put("", logfile3);
        assertEquals(logfileCache.size(), 2);

        logfileCache.put(logfileName1, logfile1);
        assertEquals(logfileCache.size(), 3);

        // The put method overwrites the previous value associated with the given key.
        logfileCache.put(logfileName1, logfile2);
        assertEquals(logfileCache.size(), 3);
    }

    @Test
    public void testIsEmpty() {
        LogfileCache logfileCache = LogfileCache.getInstance();
        assertTrue(logfileCache.isEmpty());

        logfileCache.put(logfileName1, logfile1);
        assertFalse(logfileCache.isEmpty());
    }

    @Test
    public void testFill() {
        LogfileCache logfileCache = LogfileCache.getInstance();

        // Database is empty
        logfileCache.fill();
        assertEquals(logfileCache.size(), 0);

        // Insert 3 logfiles in database
        List<Logfile> logfiles = Arrays.asList(logfile1, logfile2, logfile3);
        LogfileDAOImpl.getInstance().insertLogfiles(logfiles);

        logfileCache.fill();
        assertEquals(logfileCache.size(), 3);
        assertTrue(logfileCache.containsValue(logfile1));
        assertTrue(logfileCache.containsValue(logfile2));
        assertTrue(logfileCache.containsValue(logfile3));

        // Filling the cache again with the same data, gives the same result
        logfileCache.fill();
        assertEquals(logfileCache.size(), 3);
        assertTrue(logfileCache.containsValue(logfile1));
        assertTrue(logfileCache.containsValue(logfile2));
        assertTrue(logfileCache.containsValue(logfile3));
    }

    @Test
    public void testIterator() {
        // Test the 3 methods 'hasNext', 'next' and 'remove' from the implemented Iterator interface
        LogfileCache logfileCache = LogfileCache.getInstance();

        Iterator<Logfile> iterator0 = logfileCache.iterator();
        assertFalse(iterator0.hasNext());
        try {
            testLogfile = iterator0.next();
            fail("Expected NoSuchElementException to be thrown");
        } catch (NoSuchElementException e) {
            // This exception is expected.
        }

        logfileCache.put(logfileName1, logfile1);
        Iterator<Logfile> iterator1 = logfileCache.iterator();
        assertTrue(iterator1.hasNext());
        testLogfile = iterator1.next();
        assertEquals(testLogfile, logfile1);
        assertFalse(iterator1.hasNext());
        try {
            testLogfile = iterator1.next();
            fail("Expected NoSuchElementException to be thrown");
        } catch (NoSuchElementException e) {
            // This exception is expected.
        }
        iterator1.remove();
        assertEquals(logfileCache.size(), 0);
        assertFalse(iterator1.hasNext());
        try {
            testLogfile = iterator1.next();
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