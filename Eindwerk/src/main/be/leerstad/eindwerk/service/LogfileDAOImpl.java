package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.Logfile;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogfileDAOImpl extends BaseDAO implements LogfileDAO {
    private static final Logger LOG = Logger.getLogger(LogfileDAOImpl.class.getName());
    private static final String GET_ALL_LOGFILES = "SELECT * from logfiles";
    private static final String INSERT_LOGFILE = "INSERT INTO logfiles (LogFile, LogFileDate) VALUES (?, ?)";
    private static final String DELETE_LOGFILE = "DELETE FROM logfiles WHERE LogFile = ?";

    private static LogfileDAOImpl instance;

    private LogfileDAOImpl() {
    }

    public static LogfileDAOImpl getInstance() {
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
                Logfile logfile = new Logfile();
                logfile.setLogFile(resultSet.getString("LogFile"));
                logfile.setLogFileDate(resultSet.getDate("LogFileDate").toLocalDate());
                logfiles.add(logfile);
            }

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement ", e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to get logfiles ", e);
        }

        return logfiles;
    }

    @Override
    public Logfile getLogfile(String id) {
        return null;
    }

    @Override
    public void insertLogfile(Logfile logfile) {

    }

    @Override
    public void insertLogfiles(List<Logfile> logfiles) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOGFILE)
        ) {
            for (Logfile logfile : logfiles) {
                preparedStatement.setString(1, logfile.getLogFile());
                preparedStatement.setDate(2, Date.valueOf(logfile.getLogFileDate()));
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement ", e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to insert logfiles ", e);
        }
    }


    @Override
    public void deleteLogfile(Logfile logfile) {

    }

    @Override
    public void updateLogfile(Logfile logfile) {

    }
}
