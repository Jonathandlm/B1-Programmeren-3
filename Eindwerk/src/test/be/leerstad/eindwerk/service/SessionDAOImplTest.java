package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.business.cache.SiteCache;
import be.leerstad.eindwerk.business.cache.UserCache;
import be.leerstad.eindwerk.model.Logfile;
import be.leerstad.eindwerk.model.Session;
import be.leerstad.eindwerk.model.Site;
import be.leerstad.eindwerk.model.User;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SessionDAOImplTest {

    @Before
    public void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
        Field instanceField = SessionDAOImpl.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
    }

    @Before
    public void resetDatabase() {
        LogAnalyserDAOImpl.getInstance().clearDatabase();
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor constructor = SessionDAOImpl.class.getDeclaredConstructor();
        assertTrue("Constructor is not private", Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testAllInteractions() {
        SessionDAOImpl sessionDAO = SessionDAOImpl.getInstance();
        Map<String, Session> sessionCache = sessionDAO.fillCache();

        // Setup
        User user = UserCache.getInstance().getUser("HKJ");
        Site site = SiteCache.getInstance().getSite("google.com");
        Logfile sessionLogfile1 = new Logfile("ProxyLog_2016-11-04.log");
        Logfile sessionLogfile2 = new Logfile("ProxyLog_2016-12-06.log");
        List<Logfile> logfiles = Arrays.asList(sessionLogfile1, sessionLogfile2);
        Session session1 = new Session(sessionLogfile1, "10.120.230.78",
                LocalTime.parse("01:23:45"),123456, user, site);
        Session session2 = new Session(sessionLogfile2, "10.120.230.78",
                LocalTime.parse("12:34:56"),123456, user, site);
        List<Session> insertList = new ArrayList<>();
        insertList.add(session1);
        insertList.add(session2);

        // Test getInteractions
        List<Session> sessions = sessionDAO.getInteractions();
        assertTrue(sessions.isEmpty());
        assertTrue(sessionCache.isEmpty());

        // Test getInteractions and insertInteractions
        sessionDAO.insertSessions(insertList);
        sessions = sessionDAO.getInteractions();
        assertEquals(sessions.size(), 2);
        sessionCache = sessionDAO.fillCache();
        assertEquals(sessionCache.size(), 2);

        // Test getInteractions and deleteInteractions
        sessionDAO.deleteInteractions(logfiles);
        sessions = sessionDAO.getInteractions();
        assertTrue(sessions.isEmpty());
        sessionCache = sessionDAO.fillCache();
        assertTrue(sessionCache.isEmpty());

        // Test getInteractions, insertInteractions and deleteInteraction
        sessionDAO.insertSession(session1);
        sessionDAO.insertSession(session2);
        sessions = sessionDAO.getInteractions();
        assertEquals(sessions.size(), 2);
        sessionCache = sessionDAO.fillCache();
        assertEquals(sessionCache.size(), 2);

        sessionDAO.deleteInteraction(sessionLogfile1);
        sessions = sessionDAO.getInteractions();
        assertEquals(sessions.size(), 1);
        sessionCache = sessionDAO.fillCache();
        assertEquals(sessionCache.size(), 1);

        sessionDAO.deleteInteraction(sessionLogfile2);
        sessions = sessionDAO.getInteractions();
        assertTrue(sessions.isEmpty());
        sessionCache = sessionDAO.fillCache();
        assertTrue(sessionCache.isEmpty());
    }

    @Test
    public void testGetConnection() throws DAOException {
        Connection connection = SessionDAOImpl.getInstance().getConnection();
        assertNotNull(connection);
    }

}