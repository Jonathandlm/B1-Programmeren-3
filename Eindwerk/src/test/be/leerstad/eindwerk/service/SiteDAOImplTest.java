package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.Site;
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

public class SiteDAOImplTest {

    private Site site1;
    private Site site2;
    private List<Site> insertList;
    private List<Site> sites;

    @Before
    public void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
        Field instanceField = SiteDAOImpl.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
    }

    @Before
    public void resetDatabase() {
        LogAnalyserDAOImpl.getInstance().clearDatabase();
    }

    @Before
    public void setUp() throws Exception {
        site1 = new Site(1, "ELO");
        site2 = new Site(2, "HOI");
        insertList = new ArrayList<>();
        insertList.add(site1);
        insertList.add(site2);
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor constructor = SiteDAOImpl.class.getDeclaredConstructor();
        assertTrue("Constructor is not private", Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testSite() throws Exception {
        Site site;
        SiteDAOImpl siteDAO = SiteDAOImpl.getInstance();
        sites = siteDAO.getSites();
        assertEquals(sites.size(), 0);

        // Test getSite
        site = siteDAO.getSite(site1.getSiteId());
        assertNull(site);

        // Test getSite, insertSite
        siteDAO.insertSite(site1);
        sites = siteDAO.getSites();
        assertEquals(sites.size(), 1);

        siteDAO.insertSite(site2);
        sites = siteDAO.getSites();
        assertEquals(sites.size(), 2);

        site = siteDAO.getSite(site1.getSiteId());
        assertEquals(site, site1);
        site = siteDAO.getSite(site2.getSiteId());
        assertEquals(site, site2);
    }

    @Test
    public void testSites() throws Exception {
        SiteDAOImpl siteDAO = SiteDAOImpl.getInstance();
        Map<Integer, String> siteCache = siteDAO.fillCache();

        // Test getSites
        sites = siteDAO.getSites();
        assertTrue(sites.isEmpty());
        assertTrue(siteCache.isEmpty());

        // Test getSites and insertSites
        siteDAO.insertSites(insertList);
        sites = siteDAO.getSites();
        assertEquals(sites.size(), 2);
        siteCache = siteDAO.fillCache();
        assertEquals(siteCache.size(), 2);
    }

    @Test
    public void testGetConnection() throws DAOException {
        Connection connection = SiteDAOImpl.getInstance().getConnection();
        assertNotNull(connection);
    }

}