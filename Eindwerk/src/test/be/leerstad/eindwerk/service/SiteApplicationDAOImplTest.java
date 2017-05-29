package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.SiteApplication;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SiteApplicationDAOImplTest {

    private SiteApplication siteApplication1;
    private SiteApplication siteApplication2;
    private List<SiteApplication> insertList;
    private List<SiteApplication> siteApplications;

    @Before
    public void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
        Field instanceField = SiteApplicationDAOImpl.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
    }

    @Before
    public void resetDatabase() {
        LogAnalyserDAOImpl.getInstance().clearDatabase();
    }

    @Before
    public void setUp() throws Exception {
        siteApplication1 = new SiteApplication(1, "ELO");
        siteApplication2 = new SiteApplication(2, "HOI");
        insertList = new ArrayList<>();
        insertList.add(siteApplication1);
        insertList.add(siteApplication2);
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor constructor = SiteApplicationDAOImpl.class.getDeclaredConstructor();
        assertTrue("Constructor is not private", Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testSiteApplication() throws Exception {
        SiteApplication siteApplication;
        SiteApplicationDAOImpl siteApplicationDAO = SiteApplicationDAOImpl.getInstance();
        siteApplications = siteApplicationDAO.getSiteApplications();
        assertEquals(siteApplications.size(), 0);

        // Test getSiteApplication
        siteApplication = siteApplicationDAO.getSiteApplication(siteApplication1.getApplicationId());
        assertNull(siteApplication);

        // Test getSiteApplication, insertSiteApplication
        siteApplicationDAO.insertSiteApplication(siteApplication1);
        siteApplications = siteApplicationDAO.getSiteApplications();
        assertEquals(siteApplications.size(), 1);

        siteApplicationDAO.insertSiteApplication(siteApplication2);
        siteApplications = siteApplicationDAO.getSiteApplications();
        assertEquals(siteApplications.size(), 2);

        siteApplication = siteApplicationDAO.getSiteApplication(siteApplication1.getApplicationId());
        assertEquals(siteApplication, siteApplication1);
        siteApplication = siteApplicationDAO.getSiteApplication(siteApplication2.getApplicationId());
        assertEquals(siteApplication, siteApplication2);
    }

    @Test
    public void testSiteApplications() throws Exception {
        SiteApplicationDAOImpl siteApplicationDAO = SiteApplicationDAOImpl.getInstance();
        Map<Integer, String> siteApplicationCache = siteApplicationDAO.fillCache();

        // Test getSiteApplications
        siteApplications = siteApplicationDAO.getSiteApplications();
        assertTrue(siteApplications.isEmpty());
        assertTrue(siteApplicationCache.isEmpty());

        // Test getSiteApplications and insertSiteApplications
        siteApplicationDAO.insertSiteApplications(insertList);
        siteApplications = siteApplicationDAO.getSiteApplications();
        assertEquals(siteApplications.size(), 2);
        siteApplicationCache = siteApplicationDAO.fillCache();
        assertEquals(siteApplicationCache.size(), 2);
    }

    @Test
    public void testGetConnection() throws DAOException {
        Connection connection = SiteApplicationDAOImpl.getInstance().getConnection();
        assertNotNull(connection);
    }

}