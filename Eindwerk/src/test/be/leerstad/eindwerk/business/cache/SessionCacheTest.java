package be.leerstad.eindwerk.business.cache;

import be.leerstad.eindwerk.model.Logfile;
import be.leerstad.eindwerk.model.Session;
import be.leerstad.eindwerk.model.Site;
import be.leerstad.eindwerk.model.User;
import be.leerstad.eindwerk.service.LogAnalyserDAOImpl;
import be.leerstad.eindwerk.service.SessionDAOImpl;
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
@PrepareForTest(SessionCache.class)
public class SessionCacheTest {

    private Session session1;
    private Session session2;
    private Session testSession;
    private String sessionId1;
    private String mockId;

    @Before
    public void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
        Field instanceField = SessionCache.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
    }

    @Before
    public void resetDatabase() {
        LogAnalyserDAOImpl.getInstance().clearDatabase();
    }

    @Before
    public void setUp() {
        sessionId1 = "12345678-1234-1234-1234-1234567890ab";
        mockId = "66d4f844-f4f4-4d90-bd4b-dcdcf16827be";
        UUID sessionUuid1 = UUID.fromString("12345678-1234-1234-1234-1234567890ab");
        UUID mockUuid = UUID.fromString("66d4f844-f4f4-4d90-bd4b-dcdcf16827be");

        mockStatic(UUID.class);
        when(UUID.randomUUID()).thenReturn(mockUuid);
        when(UUID.fromString(anyString())).thenReturn(sessionUuid1);
        when(UUID.fromString(mockId)).thenReturn(mockUuid);

        testSession = null;
        session1 = new Session(sessionId1, new Logfile("ProxyLog_2016-11-04.log"), "192.168.1.1",
                LocalTime.of(12,34,56), 60, 123456, 42,
                new User("HKJ"), new Site(1, "google.com"));
        session2 = new Session();
    }

    @Test
    public void testGetInstance() {
        SessionCache sessionCache = SessionCache.getInstance();
        assertNotNull(sessionCache);
    }

    @Test
    public void testGetSession() throws NoSuchFieldException, IllegalAccessException {
        SessionCache sessionCache = SessionCache.getInstance();

        // New session: cache shouldn't make new Session, this is up to the parser who has all the data
        testSession = sessionCache.getSession(sessionId1);
        assertNull(testSession);

        // Existing session
        sessionCache.put(sessionId1, session1);
        testSession = sessionCache.getSession(sessionId1);
        assertNotNull(testSession);
        assertTrue(sessionCache.containsKey(sessionId1));
        assertTrue(sessionCache.containsValue(testSession));
        assertTrue(sessionCache.containsValue(session1));

        // Corrupt cache gets restored
        Field cacheField = SessionCache.class.getDeclaredField("cache");
        cacheField.setAccessible(true);
        cacheField.set(sessionCache, null);
        testSession = sessionCache.getSession(sessionId1);
    }

    @Test
    public void testContainsKey() {
        SessionCache sessionCache = SessionCache.getInstance();
        assertFalse(sessionCache.containsKey(null));
        assertFalse(sessionCache.containsKey(sessionId1));
        assertFalse(sessionCache.containsKey(mockId));

        sessionCache.put(null, null);
        sessionCache.put(sessionId1, session1);
        sessionCache.put(mockId, session2);
        assertTrue(sessionCache.containsKey(null));
        assertTrue(sessionCache.containsKey(sessionId1));
        assertTrue(sessionCache.containsKey(mockId));
    }

    @Test
    public void testContainsValue() {
        SessionCache sessionCache = SessionCache.getInstance();
        assertFalse(sessionCache.containsValue(null));
        assertFalse(sessionCache.containsValue(session1));
        assertFalse(sessionCache.containsValue(session2));

        sessionCache.put(null, null);
        sessionCache.put(sessionId1, session1);
        sessionCache.put(mockId, session2);
        assertTrue(sessionCache.containsValue(null));
        assertTrue(sessionCache.containsValue(session1));
        assertTrue(sessionCache.containsValue(session2));
    }

    @Test
    public void testGet() {
        SessionCache sessionCache = SessionCache.getInstance();
        testSession = sessionCache.get(sessionId1);
        assertNull(testSession);

        sessionCache.put(sessionId1, session1);
        testSession = sessionCache.get(sessionId1);
        assertNotNull(testSession);
        assertEquals(testSession, session1);
    }

    @Test
    public void testValues() {
        SessionCache sessionCache = SessionCache.getInstance();
        Collection<Session> sessions = sessionCache.values();
        assertNotNull(sessions);
        assertTrue(sessions.isEmpty());

        sessionCache.put(sessionId1, session1);
        sessionCache.put(mockId, session2);

        sessions = sessionCache.values();
        assertNotNull(sessions);
        assertEquals(sessions.size(), 2);
        assertTrue(sessions.contains(session1));
        assertTrue(sessions.contains(session2));
        assertFalse(sessions.contains(testSession));
    }

    @Test
    public void testPut() {
        SessionCache sessionCache = SessionCache.getInstance();
        sessionCache.put(null, null);
        assertTrue(sessionCache.containsKey(null));
        assertTrue(sessionCache.containsValue(null));

        sessionCache.put(sessionId1, session1);
        assertTrue(sessionCache.containsKey(sessionId1));
        assertTrue(sessionCache.containsValue(session1));

        sessionCache.put(mockId, session2);
        assertTrue(sessionCache.containsKey(mockId));
        assertTrue(sessionCache.containsValue(session2));

        // The put method overwrites the previous value associated with the given key.
        sessionCache.put(mockId, testSession);
        assertTrue(sessionCache.containsKey(mockId));
        assertTrue(sessionCache.containsValue(testSession));
    }

    @Test
    public void testSize() {
        SessionCache sessionCache = SessionCache.getInstance();
        assertEquals(sessionCache.size(), 0);

        sessionCache.put(null, null);
        assertEquals(sessionCache.size(), 1);

        sessionCache.put(sessionId1, session1);
        assertEquals(sessionCache.size(), 2);

        sessionCache.put(mockId, session2);
        assertEquals(sessionCache.size(), 3);

        // The put method overwrites the previous value associated with the given key.
        sessionCache.put(mockId, testSession);
        assertEquals(sessionCache.size(), 3);
    }

    @Test
    public void testIsEmpty() {
        SessionCache sessionCache = SessionCache.getInstance();
        assertTrue(sessionCache.isEmpty());

        sessionCache.put(sessionId1, session1);
        assertFalse(sessionCache.isEmpty());
    }

    @Test
    public void testFill() throws Exception {
        SessionCache sessionCache = SessionCache.getInstance();

        // Database is empty
        sessionCache.fill();
        assertEquals(sessionCache.size(), 0);

        // Insert 1 sessions in database
        SessionDAOImpl.getInstance().insertSession(session1);
        sessionCache.fill();
        assertEquals(sessionCache.size(), 1);
        assertTrue(sessionCache.containsValue(session1));
        assertFalse(sessionCache.containsValue(session2));

        // Filling the cache again with the same data, gives the same result
        sessionCache.fill();
        assertEquals(sessionCache.size(), 1);
        assertTrue(sessionCache.containsValue(session1));
        assertFalse(sessionCache.containsValue(session2));
    }

    @Test
    public void testIterator() {
        // Test the 3 methods 'hasNext', 'next' and 'remove' from the implemented Iterator interface
        SessionCache sessionCache = SessionCache.getInstance();

        Iterator<Session> iterator0 = sessionCache.iterator();
        assertFalse(iterator0.hasNext());
        try {
            testSession = iterator0.next();
            fail("Expected NoSuchElementException to be thrown");
        } catch (NoSuchElementException e) {
            // This exception is expected.
        }

        sessionCache.put(sessionId1, session1);
        Iterator<Session> iterator1 = sessionCache.iterator();
        assertTrue(iterator1.hasNext());
        testSession = iterator1.next();
        assertEquals(testSession, session1);
        assertFalse(iterator1.hasNext());
        try {
            testSession = iterator1.next();
            fail("Expected NoSuchElementException to be thrown");
        } catch (NoSuchElementException e) {
            // This exception is expected.
        }
        iterator1.remove();
        assertEquals(sessionCache.size(), 0);
        assertFalse(iterator1.hasNext());
        try {
            testSession = iterator1.next();
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