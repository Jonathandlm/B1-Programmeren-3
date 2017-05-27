package be.leerstad.eindwerk.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;

public class BaseDAOTest {
    private BaseDAO baseDAO;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        baseDAO = new BaseDAO();
        connection = baseDAO.getConnection();
    }

    @After
    public void tearDown() throws Exception {
        connection.close();
    }

    @Test
    public void testConstructor() {
        assertNotNull(baseDAO);
    }

    @Test
    public void testGetConnection() {
        assertNotNull(connection);
    }

}