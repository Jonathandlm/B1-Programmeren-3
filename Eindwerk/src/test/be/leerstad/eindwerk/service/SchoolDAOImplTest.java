package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.School;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SchoolDAOImplTest {

    private School school1;
    private School school2;
    private Map<String, School> schoolCache;
    private int schoolCount;

    @Before
    public void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
        Field instanceField = SchoolDAOImpl.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
    }

    @Before
    public void setUp() throws Exception {
        school1 = new School("20.10.c.d");
        school2 = new School("20.20.c.d");
        schoolCache = SchoolDAOImpl.getInstance().fillCache();
        schoolCount = schoolCache.size();
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor constructor = SchoolDAOImpl.class.getDeclaredConstructor();
        assertTrue("Constructor is not private", Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testSchool() throws Exception {
        School school;
        SchoolDAOImpl schoolDAO = SchoolDAOImpl.getInstance();
        List<School> schools = schoolDAO.getSchools();
        assertEquals(schools.size(), schoolCount);

        // Test getSchool, insertSchool and deleteSchool
        schoolDAO.insertSchool(school1);
        schools = schoolDAO.getSchools();
        assertEquals(schools.size(), schoolCount + 1);
        schoolCache = schoolDAO.fillCache();
        assertEquals(schoolCache.size(), schoolCount + 1);
        school = schoolDAO.getSchool(school1.getIpAddress());
        assertEquals(school, school1);

        schoolDAO.insertSchool(school2);
        schools = schoolDAO.getSchools();
        assertEquals(schools.size(), schoolCount + 2);
        schoolCache = schoolDAO.fillCache();
        assertEquals(schoolCache.size(), schoolCount + 2);
        school = schoolDAO.getSchool(school2.getIpAddress());
        assertEquals(school, school2);

        schoolDAO.deleteSchool(school1.getIpAddress());
        schools = schoolDAO.getSchools();
        assertEquals(schools.size(), schoolCount + 1);
        schoolCache = schoolDAO.fillCache();
        assertEquals(schoolCache.size(), schoolCount + 1);

        schoolDAO.deleteSchool(school2.getIpAddress());
        schools = schoolDAO.getSchools();
        assertEquals(schools.size(), schoolCount);
        schoolCache = schoolDAO.fillCache();
        assertEquals(schoolCache.size(), schoolCount);
    }

    @Test
    public void testGetConnection() throws DAOException {
        Connection connection = SchoolDAOImpl.getInstance().getConnection();
        assertNotNull(connection);
    }

}