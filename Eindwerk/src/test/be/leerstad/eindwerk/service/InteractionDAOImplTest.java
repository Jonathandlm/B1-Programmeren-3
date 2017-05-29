package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.business.cache.SchoolCache;
import be.leerstad.eindwerk.business.cache.SiteApplicationCache;
import be.leerstad.eindwerk.business.cache.SiteCache;
import be.leerstad.eindwerk.business.cache.UserCache;
import be.leerstad.eindwerk.model.*;
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

import static org.junit.Assert.*;

public class InteractionDAOImplTest {

    @Before
    public void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
        Field instanceField = InteractionDAOImpl.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
    }

    @Before
    public void resetDatabase() {
        LogAnalyserDAOImpl.getInstance().clearDatabase();
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor constructor = InteractionDAOImpl.class.getDeclaredConstructor();
        assertTrue("Constructor is not private", Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testAllInteractions() throws Exception {
        InteractionDAOImpl interactionDAO = InteractionDAOImpl.getInstance();

        // Setup
        User user = UserCache.getInstance().getUser("HKJ");
        Site site = SiteCache.getInstance().getSite("google.com");
        SiteApplication siteApplication = SiteApplicationCache.getInstance().getSiteApplication("ELO");
        School school = SchoolCache.getInstance().getSchool("10.120.c.d");
        Logfile sessionLogfile = new Logfile("ProxyLog_2016-11-04.log");
        Logfile visitLogfile = new Logfile("localhost_access_log.2016-12-06.txt");
        List<Logfile> logfiles = Arrays.asList(sessionLogfile, visitLogfile);
        Session session = new Session(sessionLogfile, "10.120.230.78",
                LocalTime.now(),123456, user, site);
        Visit visit = new Visit(visitLogfile, "10.120.230.78",
                LocalTime.now(),123456,  "-", siteApplication, school);
        List<Interaction> insertList = new ArrayList<>();
        insertList.add(session);
        insertList.add(visit);

        // Test getInteractions
        List<Interaction> interactions = interactionDAO.getInteractions();
        assertTrue(interactions.isEmpty());

        // Test getInteractions and insertInteractions
        interactionDAO.insertInteractions(insertList);
        interactions = interactionDAO.getInteractions();
        assertEquals(interactions.size(), 2);

        // Test getInteractions and deleteInteractions
        interactionDAO.deleteInteractions(logfiles);
        interactions = interactionDAO.getInteractions();
        assertTrue(interactions.isEmpty());

        // Test getInteractions, insertInteractions and deleteInteraction
        interactionDAO.insertInteractions(insertList);
        interactions = interactionDAO.getInteractions();
        assertEquals(interactions.size(), 2);
        interactionDAO.deleteInteraction(sessionLogfile.getLogfile());
        interactions = interactionDAO.getInteractions();
        assertEquals(interactions.size(), 1);
        interactionDAO.deleteInteraction(visitLogfile);
        interactions = interactionDAO.getInteractions();
        assertEquals(interactions.size(), 0);
    }

    @Test
    public void testGetConnection() throws Exception {
        Connection connection = InteractionDAOImpl.getInstance().getConnection();
        assertNotNull(connection);
    }

}