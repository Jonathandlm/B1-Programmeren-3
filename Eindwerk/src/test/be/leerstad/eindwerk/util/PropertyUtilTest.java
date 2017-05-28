package be.leerstad.eindwerk.util;

import be.leerstad.eindwerk.service.DAOException;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class PropertyUtilTest {

    private Connection connection;

    @After
    public void tearDown() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor constructor = PropertyUtil.class.getDeclaredConstructor();
        assertTrue("Constructor is not private", Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testGetConnectionProperties() throws DAOException, SQLException {
        connection = PropertyUtil.getConnectionProperties();
        assertNotNull(connection);
        assertFalse(connection.isClosed());
        assertEquals(connection.getMetaData().getDriverName(), "MySQL Connector Java");
        assertEquals(connection.getMetaData().getUserName(), "cvo@localhost");
    }

    @Test
    public void testGetFileLocation() {
        File file = PropertyUtil.getFileLocation("");
        assertEquals(file, new File(System.getProperty("user.home")));

        file = PropertyUtil.getFileLocation("INPUT_FILE_LOCATION");
        assertEquals(file.getName(), "input");
    }

}