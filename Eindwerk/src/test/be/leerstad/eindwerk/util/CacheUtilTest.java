package be.leerstad.eindwerk.util;

import be.leerstad.eindwerk.business.cache.SessionCache;
import be.leerstad.eindwerk.model.Logfile;
import be.leerstad.eindwerk.model.Session;
import be.leerstad.eindwerk.model.Site;
import be.leerstad.eindwerk.model.User;
import be.leerstad.eindwerk.service.LogAnalyserDAOImpl;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.Year;
import java.time.YearMonth;
import java.util.Set;
import java.util.TreeSet;

import static java.time.LocalTime.now;
import static org.junit.Assert.*;

public class CacheUtilTest {

    private Session session1;
    private Session session2;
    private Session session3;
    private Session session4;
    private SessionCache sessionCache;
    private Set<YearMonth> months;
    private Set<Year> years;

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
        session1 = new Session(new Logfile("ProxyLog_2016-12-01.log"), "192.168.1.1", now(),
                123456, new User("AAA"), new Site(1, "google.com"));
        session2 = new Session(new Logfile("ProxyLog_2017-01-01.log"), "192.168.1.2", now(),
                234567, new User("BBB"), new Site(2, "google.be"));
        session3 = new Session(new Logfile("ProxyLog_2017-02-01.log"), "192.168.1.3", now(),
                345678, new User("CCC"), new Site(3, "yahoo.com"));
        session4 = new Session(new Logfile("ProxyLog_2016-12-01.log"), "192.168.1.4", now(),
                456789, new User("DDD"), new Site(4, "bing.com"));
        sessionCache = SessionCache.getInstance();
        months = new TreeSet<>();
        years = new TreeSet<>();
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor constructor = CacheUtil.class.getDeclaredConstructor();
        assertTrue("Constructor is not private", Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testGetMonthsFromCache() {
        months = CacheUtil.getMonthsFromCache(sessionCache);
        assertNotNull(months);
        assertEquals(months.size(), 0);

        sessionCache.put(session1.getId(), session1);
        sessionCache.put(session2.getId(), session2);
        sessionCache.put(session3.getId(), session3);
        sessionCache.put(session4.getId(), session4);

        months = CacheUtil.getMonthsFromCache(sessionCache);
        assertNotNull(months);
        assertEquals(months.size(), 3);
        assertTrue(months.contains(YearMonth.from(session1.getLogfile().getLogfileDate())));
        assertTrue(months.contains(YearMonth.from(session2.getLogfile().getLogfileDate())));
        assertTrue(months.contains(YearMonth.from(session3.getLogfile().getLogfileDate())));
        assertTrue(months.contains(YearMonth.from(session4.getLogfile().getLogfileDate())));
    }

    @Test
    public void testGetYearsFromCache() {
        years = CacheUtil.getYearsFromCache(sessionCache);
        assertNotNull(years);
        assertEquals(years.size(), 0);

        sessionCache.put(session1.getId(), session1);
        sessionCache.put(session2.getId(), session2);
        sessionCache.put(session3.getId(), session3);
        sessionCache.put(session4.getId(), session4);

        years = CacheUtil.getYearsFromCache(sessionCache);
        assertNotNull(years);
        assertEquals(years.size(), 2);
        assertTrue(years.contains(Year.from(session1.getLogfile().getLogfileDate())));
        assertTrue(years.contains(Year.from(session2.getLogfile().getLogfileDate())));
        assertTrue(years.contains(Year.from(session3.getLogfile().getLogfileDate())));
        assertTrue(years.contains(Year.from(session4.getLogfile().getLogfileDate())));
    }

}