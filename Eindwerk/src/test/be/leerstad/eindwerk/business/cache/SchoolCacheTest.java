package be.leerstad.eindwerk.business.cache;

import be.leerstad.eindwerk.model.School;
import be.leerstad.eindwerk.service.SchoolDAOImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.Assert.*;

public class SchoolCacheTest {

    private School school1;
    private School school2;
    private School school3;
    private School testSchool;
    private String ipAddress1;
    private String ipAddress2;

    @Before
    public void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
        Field instanceField = SchoolCache.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
    }

    @Before
    public void setUp() {
        ipAddress1 = "10.120.c.d";
        ipAddress2 = "10.2.c.d";    // New school
        testSchool = null;
        school1 = new School(ipAddress1);
        school2 = new School(ipAddress2);
        school3 = new School();
    }

    @After
    public void tearDown() {
        if (SchoolCache.getInstance().containsKey(ipAddress2)) {
            SchoolDAOImpl.getInstance().deleteSchool(ipAddress2);
        }
    }

    private void resetCache(SchoolCache schoolCache, Map<String,School> map) throws NoSuchFieldException, IllegalAccessException {
        Field cacheField = SchoolCache.class.getDeclaredField("cache");
        cacheField.setAccessible(true);
        cacheField.set(schoolCache, map);
    }

    @Test
    public void testGetInstance() {
        SchoolCache schoolCache = SchoolCache.getInstance();
        assertNotNull(schoolCache);
    }

    @Test
    public void testGetSchool() throws NoSuchFieldException, IllegalAccessException {
        SchoolCache schoolCache = SchoolCache.getInstance();

        // Existing school
        testSchool = schoolCache.getSchool(ipAddress1);
        assertNotNull(testSchool);
        assertTrue(schoolCache.containsKey(ipAddress1));
        assertTrue(schoolCache.containsValue(school1));
        assertEquals(testSchool, school1);

        // New school
        testSchool = schoolCache.getSchool(ipAddress2);
        assertNotNull(testSchool);
        assertTrue(schoolCache.containsKey(ipAddress2));
        assertTrue(schoolCache.containsValue(testSchool));
        assertTrue(schoolCache.containsValue(school2));
        assertEquals(testSchool, school2);

        // Corrupt cache gets restored
        resetCache(schoolCache, null);
        testSchool = schoolCache.getSchool(ipAddress1);
        assertEquals(testSchool, school1);
    }

    @Test
    public void testContainsKey() {
        SchoolCache schoolCache = SchoolCache.getInstance();
        assertFalse(schoolCache.containsKey(null));
        assertFalse(schoolCache.containsKey(ipAddress2));

        schoolCache.put(null, null);
        schoolCache.put(ipAddress2, school2);
        assertTrue(schoolCache.containsKey(null));
        assertTrue(schoolCache.containsKey(ipAddress1));
    }

    @Test
    public void testContainsValue() {
        SchoolCache schoolCache = SchoolCache.getInstance();
        assertFalse(schoolCache.containsValue(null));
        assertFalse(schoolCache.containsValue(school2));

        schoolCache.put(null, null);
        schoolCache.put(ipAddress2, school2);
        assertTrue(schoolCache.containsValue(null));
        assertTrue(schoolCache.containsValue(school2));
    }

    @Test
    public void testGet() {
        SchoolCache schoolCache = SchoolCache.getInstance();
        testSchool = schoolCache.get(ipAddress2);
        assertNull(testSchool);

        schoolCache.put(ipAddress2, school2);
        testSchool = schoolCache.get(ipAddress2);
        assertNotNull(testSchool);
        assertEquals(testSchool, school2);
    }

    @Test
    public void testValues() {
        SchoolCache schoolCache = SchoolCache.getInstance();
        Collection<School> schools = schoolCache.values();
        assertNotNull(schools);
        assertEquals(schools.size(), 41);

        schoolCache.put(ipAddress1, school1);
        schoolCache.put(ipAddress2, school2);
        schoolCache.put("10.110.c.d", school3);

        schools = schoolCache.values();
        assertNotNull(schools);
        assertEquals(schools.size(), 42);
        assertTrue(schools.contains(school1));
        assertTrue(schools.contains(school2));
        assertTrue(schools.contains(school3));
        assertFalse(schools.contains(testSchool));
    }

    @Test
    public void testPut() throws NoSuchFieldException, IllegalAccessException {
        SchoolCache schoolCache = SchoolCache.getInstance();
        resetCache(schoolCache, new HashMap<>());
        schoolCache.put(null, null);
        assertTrue(schoolCache.containsKey(null));
        assertTrue(schoolCache.containsValue(null));

        schoolCache.put("10.110.c.d", school3);
        assertTrue(schoolCache.containsKey("10.110.c.d"));
        assertTrue(schoolCache.containsValue(school3));

        schoolCache.put(ipAddress1, school1);
        assertTrue(schoolCache.containsKey(ipAddress1));
        assertTrue(schoolCache.containsValue(school1));

        // The put method overwrites the previous value associated with the given key.
        schoolCache.put(ipAddress1, school2);
        assertTrue(schoolCache.containsKey(ipAddress1));
        assertTrue(schoolCache.containsValue(school2));
    }

    @Test
    public void testSize() throws NoSuchFieldException, IllegalAccessException {
        SchoolCache schoolCache = SchoolCache.getInstance();
        resetCache(schoolCache, new HashMap<>());
        assertEquals(schoolCache.size(), 0);

        schoolCache.put(null, null);
        assertEquals(schoolCache.size(), 1);

        schoolCache.put("10.110.c.d", school3);
        assertEquals(schoolCache.size(), 2);

        schoolCache.put(ipAddress1, school1);
        assertEquals(schoolCache.size(), 3);

        // The put method overwrites the previous value associated with the given key.
        schoolCache.put(ipAddress1, school2);
        assertEquals(schoolCache.size(), 3);
    }

    @Test
    public void testIsEmpty() throws NoSuchFieldException, IllegalAccessException {
        SchoolCache schoolCache = SchoolCache.getInstance();
        resetCache(schoolCache, new HashMap<>());
        assertTrue(schoolCache.isEmpty());

        schoolCache.put(ipAddress1, school1);
        assertFalse(schoolCache.isEmpty());
    }

    @Test
    public void testFill() throws NoSuchFieldException, IllegalAccessException {
        SchoolCache schoolCache = SchoolCache.getInstance();
        resetCache(schoolCache, new HashMap<>());
        assertEquals(schoolCache.size(), 0);

        schoolCache.fill();
        assertEquals(schoolCache.size(), 41);

        // Insert a school in database
        SchoolDAOImpl.getInstance().insertSchool(school2);
        schoolCache.fill();
        assertEquals(schoolCache.size(), 42);
        assertTrue(schoolCache.containsValue(school1));
        assertTrue(schoolCache.containsValue(school2));
        assertTrue(schoolCache.containsValue(school3));

        // Filling the cache again with the same data, gives the same result
        schoolCache.fill();
        assertEquals(schoolCache.size(), 42);
        assertTrue(schoolCache.containsValue(school1));
        assertTrue(schoolCache.containsValue(school2));
        assertTrue(schoolCache.containsValue(school3));
    }

    @Test
    public void testIterator() throws NoSuchFieldException, IllegalAccessException {
        // Test the 3 methods 'hasNext', 'next' and 'remove' from the implemented Iterator interface
        SchoolCache schoolCache = SchoolCache.getInstance();
        resetCache(schoolCache, new HashMap<>());

        Iterator<School> iterator0 = schoolCache.iterator();
        assertFalse(iterator0.hasNext());
        try {
            testSchool = iterator0.next();
            fail("Expected NoSuchElementException to be thrown");
        } catch (NoSuchElementException e) {
            // This exception is expected.
        }

        schoolCache.put(ipAddress1, school1);
        Iterator<School> iterator1 = schoolCache.iterator();
        assertTrue(iterator1.hasNext());
        testSchool = iterator1.next();
        assertEquals(testSchool, school1);
        assertFalse(iterator1.hasNext());
        try {
            testSchool = iterator1.next();
            fail("Expected NoSuchElementException to be thrown");
        } catch (NoSuchElementException e) {
            // This exception is expected.
        }
        iterator1.remove();
        assertEquals(schoolCache.size(), 0);
        assertFalse(iterator1.hasNext());
        try {
            testSchool = iterator1.next();
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