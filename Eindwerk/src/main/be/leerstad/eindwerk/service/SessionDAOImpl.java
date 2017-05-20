package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.Logfile;
import be.leerstad.eindwerk.model.Session;
import be.leerstad.eindwerk.utils.MySqlUtil;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionDAOImpl extends BaseDAO implements SessionDAO {

    private static final Logger LOG = Logger.getLogger(SessionDAOImpl.class.getName());
    private static final String GET_ALL_SESSIONS = "SELECT * FROM sessions, users, sites " +
            "WHERE sessions.UserID = users.UserID AND sessions.SiteID = sites.SiteId";
    private static final String INSERT_LOGFILE = "INSERT INTO logfiles (LogFile, LogFileDate) VALUES (?,?) " +
            "ON DUPLICATE KEY UPDATE LogFileDate=LogFileDate";
    private static final String INSERT_USER = "INSERT INTO users (UserID, Name, FirstName, Cat) VALUES (?,?,?,?) " +
            "ON DUPLICATE KEY UPDATE Name=Name";
    private static final String INSERT_SITE = "INSERT INTO sites (SiteID, Site) VALUES (?,?) " +
            "ON DUPLICATE KEY UPDATE Site=Site";
    private static final String INSERT_SESSION = "INSERT INTO sessions (SessionID, LogFile, IPAddress, SessionTime, " +
            "TotalTime, TransferredBytes, NumberOfRequests, UserID, SiteID) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_SESSION = "DELETE FROM sessions WHERE LogFile = ?";

    private static SessionDAOImpl instance;

    private SessionDAOImpl() {
    }

    public synchronized static SessionDAOImpl getInstance() {
        if (instance == null) {
            instance = new SessionDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Session> getInteractions() {
        List<Session> sessions = new ArrayList<>();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SESSIONS);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while (resultSet.next()) {
                sessions.add(MySqlUtil.getSessionResult(resultSet));
            }

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement ", e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to get sessions ", e);
        }

        return sessions;
    }

    @Override
    public void insertSession(Session session) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedLogfileStatement = connection.prepareStatement(INSERT_LOGFILE);
                PreparedStatement preparedUserStatement = connection.prepareStatement(INSERT_USER);
                PreparedStatement preparedSiteStatement = connection.prepareStatement(INSERT_SITE);
                PreparedStatement preparedSessionStatement = connection.prepareStatement(INSERT_SESSION)
        ) {

            MySqlUtil.setPreparedLogfileStatement(session.getLogfile(), preparedLogfileStatement);
            MySqlUtil.setPreparedUserStatement(session.getUser(), preparedUserStatement);
            MySqlUtil.setPreparedSiteStatement(session.getSite(), preparedSiteStatement);
            MySqlUtil.setPreparedSessionStatement(session, preparedSessionStatement);

            preparedLogfileStatement.executeUpdate();
            preparedUserStatement.executeUpdate();
            preparedSiteStatement.executeUpdate();
            preparedSessionStatement.executeUpdate();

            LOG.log(Level.DEBUG, "Successfully inserted " + session);

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statements ", e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to insert " + session, e);
        }
    }

    @Override
    public void insertSessions(List<Session> sessions) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedLogfileStatement = connection.prepareStatement(INSERT_LOGFILE);
                PreparedStatement preparedUserStatement = connection.prepareStatement(INSERT_USER);
                PreparedStatement preparedSiteStatement = connection.prepareStatement(INSERT_SITE);
                PreparedStatement preparedSessionStatement = connection.prepareStatement(INSERT_SESSION)
        ) {
            for (Session session : sessions) {
                MySqlUtil.setPreparedLogfileStatement(session.getLogfile(), preparedLogfileStatement);
                MySqlUtil.setPreparedUserStatement(session.getUser(), preparedUserStatement);
                MySqlUtil.setPreparedSiteStatement(session.getSite(), preparedSiteStatement);
                MySqlUtil.setPreparedSessionStatement(session, preparedSessionStatement);

                preparedLogfileStatement.executeUpdate();
                preparedUserStatement.executeUpdate();
                preparedSiteStatement.executeUpdate();
                preparedSessionStatement.executeUpdate();
            }

            LOG.log(Level.DEBUG, "Successfully inserted " + sessions.size() + " sessions.");

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement(s) ", e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to insert sessions " , e);
        }
    }

    @Override
    public void deleteInteraction(Logfile logfile) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SESSION)
        ) {

            MySqlUtil.setPreparedLogfileStatement(logfile, preparedStatement);

            preparedStatement.executeUpdate();

            LOG.log(Level.DEBUG, "Successfully deleted sessions in " + logfile);

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement ", e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to delete sessions in " + logfile, e);
        }
    }

    @Override
    public void deleteInteractions(List<Logfile> logfiles) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SESSION)
        ) {
            for (Logfile logfile : logfiles) {
                MySqlUtil.setPreparedLogfileStatement(logfile, preparedStatement);

                preparedStatement.executeUpdate();
            }

            LOG.log(Level.DEBUG, "Successfully deleted sessions from " + logfiles.size() + " logfiles.");

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement ", e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to delete sessions " , e);
        }
    }

}
