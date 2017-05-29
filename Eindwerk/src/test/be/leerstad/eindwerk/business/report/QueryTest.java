package be.leerstad.eindwerk.business.report;

import be.leerstad.eindwerk.business.cache.SessionCache;
import be.leerstad.eindwerk.business.cache.SiteApplicationCache;
import be.leerstad.eindwerk.business.cache.VisitCache;
import be.leerstad.eindwerk.business.parser.SessionParser;
import be.leerstad.eindwerk.business.parser.VisitParser;
import be.leerstad.eindwerk.model.Session;
import be.leerstad.eindwerk.model.Visit;
import be.leerstad.eindwerk.service.LogAnalyserDAOImpl;
import be.leerstad.eindwerk.service.SessionDAOImpl;
import be.leerstad.eindwerk.service.VisitDAOImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.Year;
import java.time.YearMonth;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class QueryTest {

    private Query query;
    private VisitCache visitCache = VisitCache.getInstance();
    private SessionCache sessionCache = SessionCache.getInstance();
    private SiteApplicationCache siteApplicationCache = SiteApplicationCache.getInstance();
    private int siteApplicationCount;


    @Before
    public void init() {
        query = new Query();
        if (visitCache.isEmpty()) {
            VisitParser parser = new VisitParser();
            File file = new File("./input/localhost_access_log.2016-12-06.txt");
            VisitDAOImpl.getInstance().insertVisits(parser.parseLogfile(file));
            visitCache.fill();
        }
        if (sessionCache.isEmpty()) {
            SessionParser parser = new SessionParser();
            File file = new File("./input/ProxyLog_2016-12-05.log");
            SessionDAOImpl.getInstance().insertSessions(parser.parseLogfile(file));
            sessionCache.fill();
        }
        siteApplicationCount = siteApplicationCache.size();
    }

    @After
    public void tearDown() {
        LogAnalyserDAOImpl.getInstance().clearDatabase();
    }

    @Test
    public void testGetMonthlyApplicationTotals() {
        Map<String, Map<String, Integer>> monthlyApplicationRequests = query.getMonthlyApplicationTotals(Visit::getNumberOfRequests);
        assertNotNull(monthlyApplicationRequests);
        assertEquals(monthlyApplicationRequests.size(), siteApplicationCount);

        Map<String, Map<String, Integer>> monthlyApplicationBytes = query.getMonthlyApplicationTotals(Visit::getTransferredBytes);
        assertNotNull(monthlyApplicationBytes);
        assertEquals(monthlyApplicationRequests.size(), siteApplicationCount);
    }

    @Test
    public void testGetSchoolVisitsByMonth() {
        Map<String, Integer> map = query.getSchoolVisitsByMonth(YearMonth.of(2016, 12));
        assertNotNull(map);
        assertTrue(map.size() <= 10);
    }

    @Test
    public void testGetUserTotalsByMonth() {
        Map<String, Integer> map = query.getUserTotalsByMonth(YearMonth.of(2016, 12), Session::getTotalTimeInSec);
        assertNotNull(map);
        assertTrue(map.size() <= 10);
    }

    @Test
    public void testGetMonthTotalsByYear() {
        Map<String, Integer> map = query.getMonthTotalsByYear(Year.of(2016), Session::getTransferredBytes);
        assertNotNull(map);
        assertTrue(map.size() <= 12);
    }

    @Test
    public void testGetSiteTotals() {
        Map<String, Integer> map1 = query.getSiteTotals(Session::getNumberOfRequests);
        assertNotNull(map1);
        assertTrue(map1.size() <= 10);

        Map<String, Integer> map2 = query.getSiteTotals(Session::getTotalTimeInSec);
        assertNotNull(map2);
        assertTrue(map2.size() <= 10);

        Map<String, Integer> map3 = query.getSiteTotals(Session::getTransferredBytes);
        assertNotNull(map3);
        assertTrue(map3.size() <= 10);
    }
}
