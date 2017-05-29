package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.util.PropertyUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DAOException.class, PropertyUtil.class, DriverManager.class})
public class DAOExceptionTest {

    private BaseDAO baseDAO;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        baseDAO = new BaseDAO();
    }

    @After
    public void tearDown() throws Exception {
        if (connection != null) connection.close();
    }

    @Test
    public void testException() throws Exception {
        mockStatic(DriverManager.class);
        when(DriverManager.getConnection(anyString(), anyString(), anyString())).thenThrow(new SQLException());
        try {
            connection = baseDAO.getConnection();
            fail("DAOException expected");
        } catch(DAOException e) {
            // This exception is expected.
            assertEquals(e.getMessage(), "Unable to configure connection.");
            e.setMessage("This is a new DAOException message!");
            assertEquals(e.toString(), "This is a new DAOException message!");
        }
    }
}