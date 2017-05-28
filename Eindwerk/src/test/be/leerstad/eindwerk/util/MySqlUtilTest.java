package be.leerstad.eindwerk.util;

import be.leerstad.eindwerk.model.*;
import be.leerstad.eindwerk.service.BaseDAO;
import be.leerstad.eindwerk.service.DAOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalTime;

import static be.leerstad.eindwerk.util.MySqlUtil.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MySqlUtilTest {

    private String sqlQuery;
    private Connection connection;
    private PreparedStatement preparedStatement;

    @Before
    public void setUp() throws DAOException {
        connection = new BaseDAO().getConnection();
    }

    @After
    public void tearDown() throws SQLException {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    private String getStatement(String preparedStatement) {
        return preparedStatement.substring(preparedStatement.indexOf(':') + 2);
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor constructor = MySqlUtil.class.getDeclaredConstructor();
        assertTrue("Constructor is not private", Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testSetPreparedLogfileStatement() throws SQLException {
        sqlQuery = "INSERT INTO logfiles (LogFile, LogFileDate) VALUES (?,?)";
        Logfile logfile = new Logfile("ProxyLog_2016-12-06.log");
        preparedStatement = connection.prepareStatement(sqlQuery);

        setPreparedLogfileStatement(logfile, preparedStatement);

        assertEquals(getStatement(preparedStatement.toString()),
                "INSERT INTO logfiles (LogFile, LogFileDate) VALUES ('ProxyLog_2016-12-06.log','2016-12-06')");
    }

    @Test
    public void testSetPreparedSchoolStatement() throws SQLException {
        sqlQuery = "INSERT INTO schools (IPAddress, Site, Street, Zip, City) VALUES (?,?,?,?,?)";
        School school = new School("10.120.c.d", "CVO Leerstad", "Brouwerijstraat 5", "9160", "Lokeren");
        preparedStatement = connection.prepareStatement(sqlQuery);

        setPreparedSchoolStatement(school, preparedStatement);

        assertEquals(getStatement(preparedStatement.toString()),
                "INSERT INTO schools (IPAddress, Site, Street, Zip, City) VALUES ('10.120.c.d','CVO Leerstad','Brouwerijstraat 5','9160','Lokeren')");
    }

    @Test
    public void testSetPreparedSessionStatement() throws SQLException {
        sqlQuery = "INSERT INTO sessions (SessionID, LogFile, IPAddress, SessionTime, " +
                "TotalTime, TransferredBytes, NumberOfRequests, UserID, SiteID) VALUES (?,?,?,?,?,?,?,?,?)";
        Session session = new Session("12345678-1234-1234-1234-1234567890ab", new Logfile("ProxyLog_2016-11-04.log"),
                "192.168.1.1", LocalTime.of(12,34), 60, 123456,
                42, new User("HKJ"), new Site(1, "google.com"));
        preparedStatement = connection.prepareStatement(sqlQuery);

        setPreparedSessionStatement(session, preparedStatement);

        assertEquals(getStatement(preparedStatement.toString()),
                "INSERT INTO sessions (SessionID, LogFile, IPAddress, SessionTime, TotalTime, TransferredBytes," +
                        " NumberOfRequests, UserID, SiteID) VALUES ('12345678-1234-1234-1234-1234567890ab'," +
                        "'ProxyLog_2016-11-04.log','192.168.1.1','12:34:00',60,123456,42,'HKJ',1)");
    }

    @Test
    public void testSetPreparedSiteStatement() throws SQLException {
        sqlQuery = "INSERT INTO sites (SiteID, Site) VALUES (?,?)";
        Site site = new Site(1, "google.com");
        preparedStatement = connection.prepareStatement(sqlQuery);

        setPreparedSiteStatement(site, preparedStatement);

        assertEquals(getStatement(preparedStatement.toString()),
                "INSERT INTO sites (SiteID, Site) VALUES (1,'google.com')");
    }

    @Test
    public void testSetPreparedSiteApplicationStatement() throws SQLException {
        sqlQuery = "INSERT INTO siteapplications (ApplicationID, Application) VALUES (?,?)";
        SiteApplication siteApplication = new SiteApplication(1, "ELO");
        preparedStatement = connection.prepareStatement(sqlQuery);

        setPreparedSiteApplicationStatement(siteApplication, preparedStatement);

        assertEquals(getStatement(preparedStatement.toString()),
                "INSERT INTO siteapplications (ApplicationID, Application) VALUES (1,'ELO')");
    }

    @Test
    public void testSetPreparedUserStatement() throws SQLException {
        sqlQuery = "INSERT INTO users (UserID, Name, FirstName, Cat) VALUES (?,?,?,?)";
        User user = new User("HKJ", "Faillard", "Bruno", "Lector");
        preparedStatement = connection.prepareStatement(sqlQuery);

        setPreparedUserStatement(user, preparedStatement);

        assertEquals(getStatement(preparedStatement.toString()),
                "INSERT INTO users (UserID, Name, FirstName, Cat) VALUES ('HKJ','Faillard','Bruno','Lector')");
    }

    @Test
    public void testSetPreparedVisitStatement() throws SQLException {
        sqlQuery = "INSERT INTO visits (VisitID, LogFile, IPAddress, VisitTime, TotalTime, " +
                "TransferredBytes, NumberOfRequests, User, SiteApplicationID, IPSchool) VALUES (?,?,?,?,?,?,?,?,?,?)";
        Visit visit = new Visit("12345678-1234-1234-1234-1234567890ab", new Logfile("localhost_access_log.2016-12-06.txt"),
                "192.168.1.1", LocalTime.of(12, 34), 60, 123456, 42, "-",
                new SiteApplication(1, "ELO"), new School("10.120.c.d"));
        preparedStatement = connection.prepareStatement(sqlQuery);

        setPreparedVisitStatement(visit, preparedStatement);

        assertEquals(getStatement(preparedStatement.toString()),
                "INSERT INTO visits (VisitID, LogFile, IPAddress, VisitTime, TotalTime, TransferredBytes, " +
                        "NumberOfRequests, User, SiteApplicationID, IPSchool) VALUES ('12345678-1234-1234-1234-1234567890ab'," +
                        "'localhost_access_log.2016-12-06.txt','192.168.1.1','12:34:00',60,123456,42,'-',1,'10.120.c.d')");
    }

    @Test
    public void testGetLogfileResult() throws SQLException {
    }

    @Test
    public void testGetSchoolResult() throws SQLException {
    }

    @Test
    public void testGetSessionResult() throws SQLException {
    }

    @Test
    public void testGetSiteResult() throws SQLException {
    }

    @Test
    public void testGetSiteApplicationResult() throws SQLException {
    }

    @Test
    public void testGetUserResult() throws SQLException {
    }

    @Test
    public void testGetVisitResult() throws SQLException {
    }

}