package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.Logfile;
import be.leerstad.eindwerk.model.Session;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionDAOImpl extends BaseDAO implements InteractionDAO<Session> {

    private static final Logger LOG = Logger.getLogger(SessionDAOImpl.class.getName());
    private static final String GET_ALL_SESSIONS = "SELECT * FROM sessions, sites WHERE sessions.SiteID = sites.SiteId";
    private static final String GET_SESSION = "SELECT * FROM sessions, sites " +
            "WHERE sessions.SiteID = sites.SiteId AND SessionID = ?";
    private static final String INSERT_SESSION = "INSERT INTO sessions (SessionID, LogFile, IPAddress, SessionTime, " +
            "TotalTime, TransferredBytes, NumberOfRequests, UserID, SiteID) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_SESSION = "DELETE FROM sessions WHERE SessionID = ?";
    private static final String UPDATE_SESSION = "UPDATE sessions SET LogFile = ?, IPAddress = ?, SessionTime = ?, " +
            "TotalTime = ?, TransferredBytes = ?, NumberOfRequests = ?, UserID = ?, SiteID = ? WHERE SessionID = ?";

    private static SessionDAOImpl instance;

    private SessionDAOImpl() {
    }

    public static SessionDAOImpl getInstance() {
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
                Session session = new Session();
                session.setId(resultSet.getString("SessionID"));
                session.setLogfile(new Logfile(resultSet.getString("LogFile")));
                session.setIpAddress(resultSet.getString("IPAddress"));
                session.setTime(resultSet.getTime("SessionTime").toLocalTime());
                session.setTotalTimeInSec(resultSet.getInt("TotalTime"));
                session.setTransferredBytes(resultSet.getInt("TransferredBytes"));
                session.setNumberOfRequests(resultSet.getInt("NumberOfRequests"));
                session.setUserId(resultSet.getString("UserID"));
                session.setSite(resultSet.getString("Site"));
                sessions.add(session);
            }

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement ", e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to get sessions ", e);
        }

        return sessions;
    }

    @Override
    public Session getInteraction(String id) {
        Session session = new Session();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_SESSION);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            session.setId(resultSet.getString("SessionID"));
            session.setLogfile(new Logfile(resultSet.getString("LogFile")));
            session.setIpAddress(resultSet.getString("IPAddress"));
            session.setTime(resultSet.getTime("SessionTime").toLocalTime());
            session.setTotalTimeInSec(resultSet.getInt("TotalTime"));
            session.setTransferredBytes(resultSet.getInt("TransferredBytes"));
            session.setNumberOfRequests(resultSet.getInt("NumberOfRequests"));
            session.setUserId(resultSet.getString("UserID"));
            session.setSite(resultSet.getString("Site"));

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement ", e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to get session ", e);
        }
        return session;
    }

    @Override
    public void insertInteraction(Session session) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_SESSION)
        ) {

            preparedStatement.setString(1, session.getId());
            // TODO: complete preparedStatement

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement ", e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to insert session ", e);
        }
    }

    @Override
    public void deleteInteraction(Session session) {

    }

    @Override
    public void updateInteraction(Session session) {

    }

}
