package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.Logfile;
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

public class LogfileDAOImplTest {

    private Logfile logfile1;
    private Logfile logfile2;
    private List<Logfile> insertList;
    private List<Logfile> logfiles;

    @Before
    public void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
        Field instanceField = LogfileDAOImpl.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
    }

    @Before
    public void resetDatabase() {
        LogAnalyserDAOImpl.getInstance().clearDatabase();
    }

    @Before
    public void setUp() throws Exception {
        logfile1 = new Logfile("ProxyLog_2016-11-04.log");
        logfile2 = new Logfile("localhost_access_log.2016-12-06.txt");
        insertList = new ArrayList<>();
        insertList.add(logfile1);
        insertList.add(logfile2);
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor constructor = LogfileDAOImpl.class.getDeclaredConstructor();
        assertTrue("Constructor is not private", Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testLogfile() throws Exception {
        LogfileDAOImpl logfileDAO = LogfileDAOImpl.getInstance();
        logfiles = logfileDAO.getLogfiles();
        assertEquals(logfiles.size(), 0);

        // Test getLogfile, insertLogfile
        logfileDAO.insertLogfile(logfile1);
        logfiles = logfileDAO.getLogfiles();
        assertEquals(logfiles.size(), 1);

        logfileDAO.insertLogfile(logfile2);
        logfiles = logfileDAO.getLogfiles();
        assertEquals(logfiles.size(), 2);
    }

    @Test
    public void testLogfiles() throws Exception {
        LogfileDAOImpl logfileDAO = LogfileDAOImpl.getInstance();
        Map<String, Logfile> logfileCache = logfileDAO.fillCache();

        // Test getLogfiles
        logfiles = logfileDAO.getLogfiles();
        assertTrue(logfiles.isEmpty());
        assertTrue(logfileCache.isEmpty());

        // Test getLogfiles and insertLogfiles
        logfileDAO.insertLogfiles(insertList);
        logfiles = logfileDAO.getLogfiles();
        assertEquals(logfiles.size(), 2);
        logfileCache = logfileDAO.fillCache();
        assertEquals(logfileCache.size(), 2);
    }

    @Test
    public void testGetConnection() throws DAOException {
        Connection connection = LogfileDAOImpl.getInstance().getConnection();
        assertNotNull(connection);
    }

}