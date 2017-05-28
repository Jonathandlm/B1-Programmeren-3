package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.Logfile;
import be.leerstad.eindwerk.model.Visit;
import be.leerstad.eindwerk.util.MySqlUtil;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisitDAOImpl extends BaseDAO implements VisitDAO {

    private static final Logger LOG = Logger.getLogger(VisitDAOImpl.class.getName());
    private static final String GET_ALL_VISITS = "SELECT * FROM visits, siteapplications, schools " +
            "WHERE visits.SiteApplicationID = siteapplications.ApplicationID AND visits.IPSchool = schools.IPAddress";
    private static final String INSERT_LOGFILE = "INSERT INTO logfiles (LogFile, LogFileDate) VALUES (?,?) " +
            "ON DUPLICATE KEY UPDATE LogFileDate=LogFileDate";
    private static final String INSERT_SITEAPPLICATION = "INSERT INTO siteapplications (ApplicationID, Application)" +
            "VALUES (?,?) ON DUPLICATE KEY UPDATE Application=Application";
    private static final String INSERT_SCHOOL = "INSERT INTO schools (IPAddress, Site, Street, Zip, City) " +
            "VALUES (?,?,?,?,?) ON DUPLICATE KEY UPDATE Site=Site";
    private static final String INSERT_VISIT = "INSERT INTO visits (VisitID, LogFile, IPAddress, VisitTime, TotalTime, " +
            "TransferredBytes, NumberOfRequests, User, SiteApplicationID, IPSchool) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_VISIT = "DELETE FROM visits WHERE LogFile = ?";

    private static VisitDAOImpl instance;

    private VisitDAOImpl() {
    }

    public synchronized static VisitDAOImpl getInstance() {
        if (instance == null) {
            instance = new VisitDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Visit> getInteractions() {
        List<Visit> visits = new ArrayList<>();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_VISITS);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while (resultSet.next()) {
                visits.add(MySqlUtil.getVisitResult(resultSet));
            }

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement ", e);
        } catch (DAOException e) {
            LOG.log(Level.ERROR, "Unable to get connection ", e);
        }

        return visits;
    }

    @Override
    public void insertVisit(Visit visit) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedLogfileStatement = connection.prepareStatement(INSERT_LOGFILE);
                PreparedStatement preparedSiteApplicationStatement = connection.prepareStatement(INSERT_SITEAPPLICATION);
                PreparedStatement preparedSchoolStatement = connection.prepareStatement(INSERT_SCHOOL);
                PreparedStatement preparedVisitStatement = connection.prepareStatement(INSERT_VISIT)
        ) {

            MySqlUtil.setPreparedLogfileStatement(visit.getLogfile(), preparedLogfileStatement);
            MySqlUtil.setPreparedSiteApplicationStatement(visit.getSiteApplication(), preparedSiteApplicationStatement);
            MySqlUtil.setPreparedSchoolStatement(visit.getSchool(), preparedSchoolStatement);
            MySqlUtil.setPreparedVisitStatement(visit, preparedVisitStatement);

            preparedLogfileStatement.executeUpdate();
            preparedSiteApplicationStatement.executeUpdate();
            preparedSchoolStatement.executeUpdate();
            preparedVisitStatement.executeUpdate();

            LOG.log(Level.DEBUG, "Successfully inserted " + visit);

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statements ", e);
        } catch (DAOException e) {
            LOG.log(Level.ERROR, "Unable to get connection ", e);
        }
    }

    @Override
    public void insertVisits(List<Visit> visits) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedLogfileStatement = connection.prepareStatement(INSERT_LOGFILE);
                PreparedStatement preparedSiteApplicationStatement = connection.prepareStatement(INSERT_SITEAPPLICATION);
                PreparedStatement preparedSchoolStatement = connection.prepareStatement(INSERT_SCHOOL);
                PreparedStatement preparedVisitStatement = connection.prepareStatement(INSERT_VISIT)
        ) {
            for (Visit visit : visits) {
                MySqlUtil.setPreparedLogfileStatement(visit.getLogfile(), preparedLogfileStatement);
                MySqlUtil.setPreparedSiteApplicationStatement(visit.getSiteApplication(), preparedSiteApplicationStatement);
                MySqlUtil.setPreparedSchoolStatement(visit.getSchool(), preparedSchoolStatement);
                MySqlUtil.setPreparedVisitStatement(visit, preparedVisitStatement);

                preparedLogfileStatement.executeUpdate();
                preparedSiteApplicationStatement.executeUpdate();
                preparedSchoolStatement.executeUpdate();
                preparedVisitStatement.executeUpdate();
            }

            LOG.log(Level.DEBUG, "Successfully inserted " + visits.size() + " visits.");

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement(s) ", e);
        } catch (DAOException e) {
            LOG.log(Level.ERROR, "Unable to get connection ", e);
        }
    }

    @Override
    public void deleteInteraction(Logfile logfile) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_VISIT)
        ) {

            MySqlUtil.setPreparedLogfileStatement(logfile, preparedStatement);

            preparedStatement.executeUpdate();

            LOG.log(Level.DEBUG, "Successfully deleted visits in " + logfile);

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement ", e);
        } catch (DAOException e) {
            LOG.log(Level.ERROR, "Unable to get connection ", e);
        }
    }

    @Override
    public void deleteInteractions(List<Logfile> logfiles) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_VISIT)
        ) {
            for (Logfile logfile : logfiles) {
                MySqlUtil.setPreparedLogfileStatement(logfile, preparedStatement);

                preparedStatement.executeUpdate();
            }

            LOG.log(Level.DEBUG, "Successfully deleted visits from " + logfiles.size() + " logfiles.");

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement ", e);
        } catch (DAOException e) {
            LOG.log(Level.ERROR, "Unable to get connection ", e);
        }
    }
    
    @Override
    public Map<String, Visit> fillCache() {
        Map<String,Visit> cache = new HashMap<>();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_VISITS);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while (resultSet.next()) {
                cache.put(resultSet.getString("VisitID"), MySqlUtil.getVisitResult(resultSet));
            }

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement " + GET_ALL_VISITS, e);
        } catch (DAOException e) {
            LOG.log(Level.ERROR, "Unable to get connection ", e);
        }

        return cache;
    }
}
