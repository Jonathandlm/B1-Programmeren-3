package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.Logfile;
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

public class LogfileDAOImpl extends BaseDAO implements LogfileDAO {
    private static final Logger LOG = Logger.getLogger(LogfileDAOImpl.class.getName());
    private static final String GET_ALL_LOGFILES = "SELECT * FROM logfiles ORDER BY LogfileDate DESC";
    private static final String INSERT_LOGFILE = "INSERT INTO logfiles (LogFile, LogFileDate) VALUES (?,?)";

    private static LogfileDAOImpl instance;

    private LogfileDAOImpl() {
    }

    public synchronized static LogfileDAOImpl getInstance() {
        if (instance == null) {
            instance = new LogfileDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Logfile> getLogfiles() {
        List<Logfile> logfiles = new ArrayList<>();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_LOGFILES);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while (resultSet.next()) {
                logfiles.add(MySqlUtil.getLogfileResult(resultSet));
            }

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement ", e);
        } catch (DAOException e) {
            LOG.log(Level.ERROR, "Unable to get connection ", e);
        }

        return logfiles;
    }

    @Override
    public void insertLogfile(Logfile logfile) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOGFILE)
        ) {
            MySqlUtil.setPreparedLogfileStatement(logfile, preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement " + INSERT_LOGFILE, e);
        } catch (DAOException e) {
            LOG.log(Level.ERROR, "Unable to get connection ", e);
        }
    }

    @Override
    public void insertLogfiles(List<Logfile> logfiles) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOGFILE)
        ) {
            for (Logfile logfile : logfiles) {
                MySqlUtil.setPreparedLogfileStatement(logfile, preparedStatement);
                preparedStatement.executeUpdate();
            }

            LOG.log(Level.DEBUG, "Successfully inserted " + logfiles.size() + " logfiles.");

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement ", e);
        } catch (DAOException e) {
            LOG.log(Level.ERROR, "Unable to get connection ", e);
        }
    }

    @Override
    public Map<String, Logfile> fillCache() {
        Map<String,Logfile> cache = new HashMap<>();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_LOGFILES);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while (resultSet.next()) {
                cache.put(resultSet.getString("Logfile"), MySqlUtil.getLogfileResult(resultSet));
            }

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement " + GET_ALL_LOGFILES, e);
        } catch (DAOException e) {
            LOG.log(Level.ERROR, "Unable to get connection ", e);
        }

        return cache;
    }

}
