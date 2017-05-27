package be.leerstad.eindwerk.business.cache;

import be.leerstad.eindwerk.model.User;
import be.leerstad.eindwerk.service.UserDAOImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class UserCacheTest {

    private User user1;
    private User user2;
    private User user3;
    private User testUser;
    private String userId1;
    private String userId2;

    @Before
    public void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
        Field instanceField = UserCache.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
    }

    @Before
    public void setUp() {
        userId1 = "HKJ";
        userId2 = "XXX";            // New userId
        testUser = null;
        user1 = new User(userId1);
        user2 = new User(userId2);  // New user
        user3 = new User();         // New user
    }

    @After
    public void tearDown() {
        if (UserCache.getInstance().containsKey(userId2)) {
            UserDAOImpl.getInstance().deleteUser(userId2);
        }
    }

    private void resetCache(UserCache userCache, Map<String,User> map) throws NoSuchFieldException, IllegalAccessException {
        Field cacheField = UserCache.class.getDeclaredField("cache");
        cacheField.setAccessible(true);
        cacheField.set(userCache, map);
    }

    @Test
    public void testGetInstance() {
        UserCache userCache = UserCache.getInstance();
        assertNotNull(userCache);
    }

    @Test
    public void testGetUser() throws NoSuchFieldException, IllegalAccessException {
        UserCache userCache = UserCache.getInstance();

        // Existing user
        testUser = userCache.getUser(userId1);
        assertNotNull(testUser);
        assertTrue(userCache.containsKey(userId1));
        assertTrue(userCache.containsValue(user1));
        assertEquals(testUser, user1);

        // New user
        testUser = userCache.getUser(userId2);
        assertNotNull(testUser);
        assertTrue(userCache.containsKey(userId2));
        assertTrue(userCache.containsValue(testUser));
        assertTrue(userCache.containsValue(user2));
        assertEquals(testUser, user2);

        // Corrupt cache gets restored
        resetCache(userCache, null);
        testUser = userCache.getUser(userId1);
        assertEquals(testUser, user1);
    }

    @Test
    public void testContainsKey() {
        UserCache userCache = UserCache.getInstance();
        assertFalse(userCache.containsKey(null));
        assertFalse(userCache.containsKey(userId2));

        userCache.put(null, null);
        userCache.put(userId2, user2);
        assertTrue(userCache.containsKey(null));
        assertTrue(userCache.containsKey(userId1));
    }

    @Test
    public void testContainsValue() {
        UserCache userCache = UserCache.getInstance();
        assertFalse(userCache.containsValue(null));
        assertFalse(userCache.containsValue(user2));

        userCache.put(null, null);
        userCache.put(userId2, user2);
        assertTrue(userCache.containsValue(null));
        assertTrue(userCache.containsValue(user2));
    }

    @Test
    public void testGet() {
        UserCache userCache = UserCache.getInstance();
        testUser = userCache.get(userId2);
        assertNull(testUser);

        userCache.put(userId2, user2);
        testUser = userCache.get(userId2);
        assertNotNull(testUser);
        assertEquals(testUser, user2);
    }

    @Test
    public void testValues() {
        UserCache userCache = UserCache.getInstance();
        Collection<User> users = userCache.values();
        assertNotNull(users);
        assertEquals(users.size(), 207);

        userCache.put(userId1, user1);
        userCache.put(userId2, user2);
        userCache.put("", user3);

        users = userCache.values();
        assertNotNull(users);
        assertEquals(users.size(), 209);
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
        assertTrue(users.contains(user3));
        assertFalse(users.contains(testUser));
    }

    @Test
    public void testPut() throws NoSuchFieldException, IllegalAccessException {
        UserCache userCache = UserCache.getInstance();
        resetCache(userCache, new HashMap<>());
        userCache.put(null, null);
        assertTrue(userCache.containsKey(null));
        assertTrue(userCache.containsValue(null));

        userCache.put("", user3);
        assertTrue(userCache.containsKey(""));
        assertTrue(userCache.containsValue(user3));

        userCache.put(userId1, user1);
        assertTrue(userCache.containsKey(userId1));
        assertTrue(userCache.containsValue(user1));

        // The put method overwrites the previous value associated with the given key.
        userCache.put(userId1, user2);
        assertTrue(userCache.containsKey(userId1));
        assertTrue(userCache.containsValue(user2));
    }

    @Test
    public void testSize() throws NoSuchFieldException, IllegalAccessException {
        UserCache userCache = UserCache.getInstance();
        resetCache(userCache, new HashMap<>());
        assertEquals(userCache.size(), 0);

        userCache.put(null, null);
        assertEquals(userCache.size(), 1);

        userCache.put("", user3);
        assertEquals(userCache.size(), 2);

        userCache.put(userId1, user1);
        assertEquals(userCache.size(), 3);

        // The put method overwrites the previous value associated with the given key.
        userCache.put(userId1, user2);
        assertEquals(userCache.size(), 3);
    }

    @Test
    public void testIsEmpty() throws NoSuchFieldException, IllegalAccessException {
        UserCache userCache = UserCache.getInstance();
        resetCache(userCache, new HashMap<>());
        assertTrue(userCache.isEmpty());

        userCache.put(userId1, user1);
        assertFalse(userCache.isEmpty());
    }

    @Test
    public void testFill() throws NoSuchFieldException, IllegalAccessException {
        UserCache userCache = UserCache.getInstance();
        resetCache(userCache, new HashMap<>());
        assertEquals(userCache.size(), 0);

        userCache.fill();
        assertEquals(userCache.size(), 207);

        // Insert a user in database
        UserDAOImpl.getInstance().insertUser(user2);
        userCache.fill();
        assertEquals(userCache.size(), 208);
        assertTrue(userCache.containsValue(user1));
        assertTrue(userCache.containsValue(user2));
        assertFalse(userCache.containsValue(user3));

        // Filling the cache again with the same data, gives the same result
        userCache.fill();
        assertEquals(userCache.size(), 208);
        assertTrue(userCache.containsValue(user1));
        assertTrue(userCache.containsValue(user2));
        assertFalse(userCache.containsValue(user3));
    }

    @Test
    public void testIterator() throws NoSuchFieldException, IllegalAccessException {
        // Test the 3 methods 'hasNext', 'next' and 'remove' from the implemented Iterator interface
        UserCache userCache = UserCache.getInstance();
        resetCache(userCache, new HashMap<>());

        Iterator<User> iterator0 = userCache.iterator();
        assertFalse(iterator0.hasNext());
        try {
            testUser = iterator0.next();
            fail("Expected NoSuchElementException to be thrown");
        } catch (NoSuchElementException e) {
            // This exception is expected.
        }

        userCache.put(userId1, user1);
        Iterator<User> iterator1 = userCache.iterator();
        assertTrue(iterator1.hasNext());
        testUser = iterator1.next();
        assertEquals(testUser, user1);
        assertFalse(iterator1.hasNext());
        try {
            testUser = iterator1.next();
            fail("Expected NoSuchElementException to be thrown");
        } catch (NoSuchElementException e) {
            // This exception is expected.
        }
        iterator1.remove();
        assertEquals(userCache.size(), 0);
        assertFalse(iterator1.hasNext());
        try {
            testUser = iterator1.next();
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