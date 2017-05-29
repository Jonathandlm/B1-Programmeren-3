package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.User;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserDAOImplTest {

    private User user1;
    private User user2;
    private Map<String, User> userCache;
    private int userCount;

    @Before
    public void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
        Field instanceField = UserDAOImpl.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
    }

    @Before
    public void setUp() throws Exception {
        user1 = new User("AAA");
        user2 = new User("BBB");
        userCache = UserDAOImpl.getInstance().fillCache();
        userCount = userCache.size();
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor constructor = UserDAOImpl.class.getDeclaredConstructor();
        assertTrue("Constructor is not private", Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testUser() throws Exception {
        User user;
        UserDAOImpl userDAO = UserDAOImpl.getInstance();
        List<User> users = userDAO.getUsers();
        assertEquals(users.size(), userCount);

        // Test getUser, insertUser and deleteUser
        userDAO.insertUser(user1);
        users = userDAO.getUsers();
        assertEquals(users.size(), userCount + 1);
        userCache = userDAO.fillCache();
        assertEquals(userCache.size(), userCount + 1);
        user = userDAO.getUser(user1.getUserId());
        assertEquals(user, user1);

        userDAO.insertUser(user2);
        users = userDAO.getUsers();
        assertEquals(users.size(), userCount + 2);
        userCache = userDAO.fillCache();
        assertEquals(userCache.size(), userCount + 2);
        user = userDAO.getUser(user2.getUserId());
        assertEquals(user, user2);

        userDAO.deleteUser(user1.getUserId());
        users = userDAO.getUsers();
        assertEquals(users.size(), userCount + 1);
        userCache = userDAO.fillCache();
        assertEquals(userCache.size(), userCount + 1);

        userDAO.deleteUser(user2.getUserId());
        users = userDAO.getUsers();
        assertEquals(users.size(), userCount);
        userCache = userDAO.fillCache();
        assertEquals(userCache.size(), userCount);
    }

    @Test
    public void testGetConnection() throws DAOException {
        Connection connection = UserDAOImpl.getInstance().getConnection();
        assertNotNull(connection);
    }

}