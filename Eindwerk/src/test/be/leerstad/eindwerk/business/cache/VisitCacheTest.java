package be.leerstad.eindwerk.business.cache;

import be.leerstad.eindwerk.model.Logfile;
import be.leerstad.eindwerk.model.School;
import be.leerstad.eindwerk.model.SiteApplication;
import be.leerstad.eindwerk.model.Visit;
import be.leerstad.eindwerk.service.LogAnalyserDAOImpl;
import be.leerstad.eindwerk.service.VisitDAOImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Field;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(VisitCache.class)
public class VisitCacheTest {

    private Visit visit1;
    private Visit visit2;
    private Visit testVisit;
    private String visitId1;
    private String mockId;

    @Before
    public void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
        Field instanceField = VisitCache.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
    }

    @Before
    public void resetDatabase() {
        LogAnalyserDAOImpl.getInstance().clearDatabase();
    }

    @Before
    public void setUp() {
        visitId1 = "12345678-1234-1234-1234-1234567890ab";
        mockId = "66d4f844-f4f4-4d90-bd4b-dcdcf16827be";
        UUID visitUuid1 = UUID.fromString("12345678-1234-1234-1234-1234567890ab");
        UUID mockUuid = UUID.fromString("66d4f844-f4f4-4d90-bd4b-dcdcf16827be");

        mockStatic(UUID.class);
        when(UUID.randomUUID()).thenReturn(mockUuid);
        when(UUID.fromString(anyString())).thenReturn(visitUuid1);
        when(UUID.fromString(mockId)).thenReturn(mockUuid);

        testVisit = null;
        visit1 = new Visit(visitId1, new Logfile("localhost_access_log.2016-12-06.txt"), "10.110.1.1",
                LocalTime.of(12,34,56), 60, 123456, 42,
                "-", new SiteApplication(1, "ELO"), new School());
        visit2 = new Visit();
    }

    @Test
    public void testGetInstance() {
        VisitCache visitCache = VisitCache.getInstance();
        assertNotNull(visitCache);
    }

    @Test
    public void testGetVisit() throws NoSuchFieldException, IllegalAccessException {
        VisitCache visitCache = VisitCache.getInstance();

        // New visit: cache shouldn't make new Visit, this is up to the parser who has all the data
        testVisit = visitCache.getVisit(visitId1);
        assertNull(testVisit);

        // Existing visit
        visitCache.put(visitId1, visit1);
        testVisit = visitCache.getVisit(visitId1);
        assertNotNull(testVisit);
        assertTrue(visitCache.containsKey(visitId1));
        assertTrue(visitCache.containsValue(testVisit));
        assertTrue(visitCache.containsValue(visit1));

        // Corrupt cache gets restored
        Field cacheField = VisitCache.class.getDeclaredField("cache");
        cacheField.setAccessible(true);
        cacheField.set(visitCache, null);
        testVisit = visitCache.getVisit(visitId1);
    }

    @Test
    public void testContainsKey() {
        VisitCache visitCache = VisitCache.getInstance();
        assertFalse(visitCache.containsKey(null));
        assertFalse(visitCache.containsKey(visitId1));
        assertFalse(visitCache.containsKey(mockId));

        visitCache.put(null, null);
        visitCache.put(visitId1, visit1);
        visitCache.put(mockId, visit2);
        assertTrue(visitCache.containsKey(null));
        assertTrue(visitCache.containsKey(visitId1));
        assertTrue(visitCache.containsKey(mockId));
    }

    @Test
    public void testContainsValue() {
        VisitCache visitCache = VisitCache.getInstance();
        assertFalse(visitCache.containsValue(null));
        assertFalse(visitCache.containsValue(visit1));
        assertFalse(visitCache.containsValue(visit2));

        visitCache.put(null, null);
        visitCache.put(visitId1, visit1);
        visitCache.put(mockId, visit2);
        assertTrue(visitCache.containsValue(null));
        assertTrue(visitCache.containsValue(visit1));
        assertTrue(visitCache.containsValue(visit2));
    }

    @Test
    public void testGet() {
        VisitCache visitCache = VisitCache.getInstance();
        testVisit = visitCache.get(visitId1);
        assertNull(testVisit);

        visitCache.put(visitId1, visit1);
        testVisit = visitCache.get(visitId1);
        assertNotNull(testVisit);
        assertEquals(testVisit, visit1);
    }

    @Test
    public void testValues() {
        VisitCache visitCache = VisitCache.getInstance();
        Collection<Visit> visits = visitCache.values();
        assertNotNull(visits);
        assertTrue(visits.isEmpty());

        visitCache.put(visitId1, visit1);
        visitCache.put(mockId, visit2);

        visits = visitCache.values();
        assertNotNull(visits);
        assertEquals(visits.size(), 2);
        assertTrue(visits.contains(visit1));
        assertTrue(visits.contains(visit2));
        assertFalse(visits.contains(testVisit));
    }

    @Test
    public void testPut() {
        VisitCache visitCache = VisitCache.getInstance();
        visitCache.put(null, null);
        assertTrue(visitCache.containsKey(null));
        assertTrue(visitCache.containsValue(null));

        visitCache.put(visitId1, visit1);
        assertTrue(visitCache.containsKey(visitId1));
        assertTrue(visitCache.containsValue(visit1));

        visitCache.put(mockId, visit2);
        assertTrue(visitCache.containsKey(mockId));
        assertTrue(visitCache.containsValue(visit2));

        // The put method overwrites the previous value associated with the given key.
        visitCache.put(mockId, testVisit);
        assertTrue(visitCache.containsKey(mockId));
        assertTrue(visitCache.containsValue(testVisit));
    }

    @Test
    public void testSize() {
        VisitCache visitCache = VisitCache.getInstance();
        assertEquals(visitCache.size(), 0);

        visitCache.put(null, null);
        assertEquals(visitCache.size(), 1);

        visitCache.put(visitId1, visit1);
        assertEquals(visitCache.size(), 2);

        visitCache.put(mockId, visit2);
        assertEquals(visitCache.size(), 3);

        // The put method overwrites the previous value associated with the given key.
        visitCache.put(mockId, testVisit);
        assertEquals(visitCache.size(), 3);
    }

    @Test
    public void testIsEmpty() {
        VisitCache visitCache = VisitCache.getInstance();
        assertTrue(visitCache.isEmpty());

        visitCache.put(visitId1, visit1);
        assertFalse(visitCache.isEmpty());
    }

    @Test
    public void testFill() throws Exception {
        VisitCache visitCache = VisitCache.getInstance();

        // Database is empty
        visitCache.fill();
        assertEquals(visitCache.size(), 0);

        // Insert 1 visits in database
        VisitDAOImpl.getInstance().insertVisit(visit1);
        visitCache.fill();
        assertEquals(visitCache.size(), 1);
        assertTrue(visitCache.containsValue(visit1));
        assertFalse(visitCache.containsValue(visit2));

        // Filling the cache again with the same data, gives the same result
        visitCache.fill();
        assertEquals(visitCache.size(), 1);
        assertTrue(visitCache.containsValue(visit1));
        assertFalse(visitCache.containsValue(visit2));
    }

    @Test
    public void testIterator() {
        // Test the 3 methods 'hasNext', 'next' and 'remove' from the implemented Iterator interface
        VisitCache visitCache = VisitCache.getInstance();

        Iterator<Visit> iterator0 = visitCache.iterator();
        assertFalse(iterator0.hasNext());
        try {
            testVisit = iterator0.next();
            fail("Expected NoSuchElementException to be thrown");
        } catch (NoSuchElementException e) {
            // This exception is expected.
        }

        visitCache.put(visitId1, visit1);
        Iterator<Visit> iterator1 = visitCache.iterator();
        assertTrue(iterator1.hasNext());
        testVisit = iterator1.next();
        assertEquals(testVisit, visit1);
        assertFalse(iterator1.hasNext());
        try {
            testVisit = iterator1.next();
            fail("Expected NoSuchElementException to be thrown");
        } catch (NoSuchElementException e) {
            // This exception is expected.
        }
        iterator1.remove();
        assertEquals(visitCache.size(), 0);
        assertFalse(iterator1.hasNext());
        try {
            testVisit = iterator1.next();
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