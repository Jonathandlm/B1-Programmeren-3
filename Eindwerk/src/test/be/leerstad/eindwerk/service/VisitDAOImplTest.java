package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.business.cache.SchoolCache;
import be.leerstad.eindwerk.business.cache.SiteApplicationCache;
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
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class VisitDAOImplTest {

    @Before
    public void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
        Field instanceField = VisitDAOImpl.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
    }

    @Before
    public void resetDatabase() {
        LogAnalyserDAOImpl.getInstance().clearDatabase();
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor constructor = VisitDAOImpl.class.getDeclaredConstructor();
        assertTrue("Constructor is not private", Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testAllVisits() throws Exception {
        VisitDAOImpl visitDAO = VisitDAOImpl.getInstance();
        Map<String, Visit> visitCache = visitDAO.fillCache();

        // Setup
        SiteApplication siteApplication = SiteApplicationCache.getInstance().getSiteApplication("ELO");
        School school = SchoolCache.getInstance().getSchool("10.120.c.d");
        Logfile visitLogfile1 = new Logfile("localhost_access_log.2016-12-05.txt");
        Logfile visitLogfile2 = new Logfile("localhost_access_log.2016-12-06.txt");
        List<Logfile> logfiles = Arrays.asList(visitLogfile1, visitLogfile2);
        Visit visit1 = new Visit(visitLogfile1, "10.120.230.78",
                LocalTime.parse("01:23:45"),123456,  "-", siteApplication, school);
        Visit visit2 = new Visit(visitLogfile2, "10.120.230.78",
                LocalTime.parse("12:34:56"),123456,  "-", siteApplication, school);
        List<Visit> insertList = new ArrayList<>();
        insertList.add(visit1);
        insertList.add(visit2);

        // Test getInteractions
        List<Visit> visits = visitDAO.getInteractions();
        assertTrue(visits.isEmpty());
        assertTrue(visitCache.isEmpty());

        // Test getInteractions and insertVisits
        visitDAO.insertVisits(insertList);
        visits = visitDAO.getInteractions();
        assertEquals(visits.size(), 2);
        visitCache = visitDAO.fillCache();
        assertEquals(visitCache.size(), 2);

        // Test getInteractions and deleteInteractions
        visitDAO.deleteInteractions(logfiles);
        visits = visitDAO.getInteractions();
        assertTrue(visits.isEmpty());
        visitCache = visitDAO.fillCache();
        assertTrue(visitCache.isEmpty());

        // Test getInteractions, insertVisit and deleteInteraction
        visitDAO.insertVisit(visit1);
        visitDAO.insertVisit(visit2);
        visits = visitDAO.getInteractions();
        assertEquals(visits.size(), 2);
        visitCache = visitDAO.fillCache();
        assertEquals(visitCache.size(), 2);

        visitDAO.deleteInteraction(visit1.getLogfile());
        visits = visitDAO.getInteractions();
        assertEquals(visits.size(), 1);
        visitCache = visitDAO.fillCache();
        assertEquals(visitCache.size(), 1);

        visitDAO.deleteInteraction(visitLogfile2);
        visits = visitDAO.getInteractions();
        assertEquals(visits.size(), 0);
        visitCache = visitDAO.fillCache();
        assertTrue(visitCache.isEmpty());
    }

    @Test
    public void testGetConnection() throws DAOException {
        Connection connection = VisitDAOImpl.getInstance().getConnection();
        assertNotNull(connection);
    }

}