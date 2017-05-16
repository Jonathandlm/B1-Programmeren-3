package be.leerstad.eindwerk.service;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class BaseDAO implements DAO {

    private static final Logger LOG = Logger.getLogger(BaseDAO.class.getName());

    public Connection getConnection() throws DAOException {
        Properties properties = new Properties();
        Connection connection = null;
        try {
            properties.load(getClass().getResourceAsStream("/config.properties"));

            Class.forName(properties.getProperty("DB_DRIVER_CLASS"));

            connection = DriverManager.getConnection(properties.getProperty("DB_URL"),
                    properties.getProperty("DB_USERNAME"),
                    properties.getProperty("DB_PASSWORD"));
        } catch (FileNotFoundException e) {
            LOG.log(Level.ERROR, "Unable to find properties file: " + e);
        } catch (IOException e) {
            LOG.log(Level.ERROR, "Unable to load properties file: " + e);
        } catch (ClassNotFoundException e) {
            LOG.log(Level.ERROR, "Unable to load driver class: " + e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to create connection " + e);
            throw new DAOException();
        }
        return connection;
    }
}