package be.leerstad.eindwerk.service;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class LogAnalyserDAOImpl extends BaseDAO implements LogAnalyserDAO {

    private static final Logger LOG = Logger.getLogger(LogAnalyserDAOImpl.class.getName());
    private static final String TRUNCATE_TABLES = "TRUNCATE TABLE ";

    private final List<String> TABLES = Arrays.asList("logfiles", "sessions", "visits", "sites", "siteapplications");

    private static LogAnalyserDAOImpl instance;

    private LogAnalyserDAOImpl() {
    }

    public static LogAnalyserDAOImpl getInstance() {
        if (instance == null) {
            instance = new LogAnalyserDAOImpl();
        }
        return instance;
    }


    @Override
    public void clearDatabase() {
        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement()
        ) {
            for (String s : TABLES) {
                statement.execute(TRUNCATE_TABLES + s);
                LOG.log(Level.DEBUG, "Database table " + s + " cleared");
            }
            LOG.log(Level.DEBUG, "Database tables cleared");

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement ", e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to truncate tables ", e);
        }
    }
}
