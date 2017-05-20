package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.*;
import be.leerstad.eindwerk.utils.MySqlUtil;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InteractionDAOImpl extends BaseDAO implements InteractionDAO<Interaction> {

    private static final Logger LOG = Logger.getLogger(InteractionDAOImpl.class.getName());
    private static final String GET_ALL_SESSIONS = "SELECT * FROM sessions, users, sites " +
            "WHERE sessions.UserID = users.UserID AND sessions.SiteID = sites.SiteId";
    private static final String GET_ALL_VISITS = "SELECT * FROM visits, siteapplications, schools " +
            "WHERE visits.SiteApplicationID = siteapplications.ApplicationID AND visits.IPSchool = schools.IPAddress";
    private static final String DELETE_SESSION = "DELETE FROM sessions WHERE LogFile = ?";
    private static final String DELETE_VISIT = "DELETE FROM visits WHERE LogFile = ?";

    private static InteractionDAOImpl instance;

    private InteractionDAOImpl() {
    }

    public synchronized static InteractionDAOImpl getInstance() {
        if (instance == null) {
            instance = new InteractionDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Interaction> getInteractions() {
        List<Interaction> interactions = new ArrayList<>();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatementVisits = connection.prepareStatement(GET_ALL_VISITS);
                ResultSet resultSetVisits = preparedStatementVisits.executeQuery();
                PreparedStatement preparedStatementSessions = connection.prepareStatement(GET_ALL_SESSIONS);
                ResultSet resultSetSessions = preparedStatementSessions.executeQuery()
        ) {

            while (resultSetVisits.next()) {
                interactions.add(MySqlUtil.getVisitResult(resultSetVisits));
            }

            LOG.log(Level.DEBUG, "Successfully retrieved all visits");

            while (resultSetSessions.next()) {
                interactions.add(MySqlUtil.getSessionResult(resultSetSessions));
            }

            LOG.log(Level.DEBUG, "Successfully retrieved all sessions");

        } catch (SQLException sqle) {
            LOG.log(Level.ERROR, "Unable to execute statement ", sqle);
        } catch (DAOException daoe) {
            LOG.log(Level.ERROR, "Unable to get connection ", daoe);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to get interactions ", e);
        }

        return interactions;
    }

    public void insertInteractions(List<Interaction> interactions) {
        SessionDAOImpl.getInstance().insertSessions(filterSessions(interactions));
        VisitDAOImpl.getInstance().insertVisits(filterVisits(interactions));
    }

    @Override
    public void deleteInteraction(Logfile logfile) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatementSessions = connection.prepareStatement(DELETE_SESSION);
                PreparedStatement preparedStatementVisits = connection.prepareStatement(DELETE_VISIT)
        ) {

            preparedStatementSessions.setString(1, logfile.getLogFile());
            preparedStatementSessions.executeUpdate();
            preparedStatementVisits.setString(1, logfile.getLogFile());
            preparedStatementVisits.executeUpdate();

            LOG.log(Level.DEBUG, "Successfully deleted all interactions in " + logfile);

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement(s) ", e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to delete all interactions in " + logfile, e);
        }
    }

    public void deleteInteraction(String fileName) {
        deleteInteraction(new Logfile(fileName));
    }

    @Override
    public void deleteInteractions(List<Logfile> logfiles) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatementSessions = connection.prepareStatement(DELETE_SESSION);
                PreparedStatement preparedStatementVisits = connection.prepareStatement(DELETE_VISIT)
        ) {
            for (Logfile logfile : logfiles) {
                preparedStatementSessions.setString(1, logfile.getLogFile());
                preparedStatementSessions.executeUpdate();
                preparedStatementVisits.setString(1, logfile.getLogFile());
                preparedStatementVisits.executeUpdate();
            }

            LOG.log(Level.DEBUG, "Successfully deleted interactions from " + logfiles.size() + " logfiles.");

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement(s) ", e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to delete interactions " , e);
        }
    }

    private List<Session> filterSessions(List<Interaction> interactions) {
        return interactions.stream()
                .filter(Session.class::isInstance)
                .map(Session.class::cast)
                .collect(Collectors.toList());
    }

    private List<Visit> filterVisits(List<Interaction> interactions) {
        return interactions.stream()
                .filter(Visit.class::isInstance)
                .map(Visit.class::cast)
                .collect(Collectors.toList());
    }
}
