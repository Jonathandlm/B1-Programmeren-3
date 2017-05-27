package be.leerstad.eindwerk.util;

import be.leerstad.eindwerk.service.DAOException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PropertyUtil {

    private static final Logger LOG = Logger.getLogger(PropertyUtil.class.getName());

    public static Connection getConnectionProperties() throws DAOException {

        Properties properties = new Properties();

        Connection connection;

        try {
            properties.load(PropertyUtil.class.getResourceAsStream("/config.properties"));

            Class.forName(properties.getProperty("DB_DRIVER_CLASS"));

            connection = DriverManager.getConnection(properties.getProperty("DB_URL"),
                    properties.getProperty("DB_USERNAME"),
                    properties.getProperty("DB_PASSWORD"));

        } catch (FileNotFoundException e) {
            LOG.log(Level.ERROR, "Unable to find properties file: " + e);
            throw new DAOException("Unable to find properties file.");
        } catch (IOException e) {
            LOG.log(Level.ERROR, "Unable to load properties file: " + e);
            throw new DAOException("Unable to load properties file.");
        } catch (ClassNotFoundException e) {
            LOG.log(Level.ERROR, "Unable to load driver class: " + e);
            throw new DAOException("Unable to load driver class.");
        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to configure connection: " + e);
            throw new DAOException("Unable to configure connection.");
        }

        return connection;
    }

    public static File getFileLocation(String propertyKey) {

        Properties properties = new Properties();

        File file;

        try {
            properties.load(PropertyUtil.class.getResourceAsStream("/config.properties"));
            file = new File(properties.getProperty(propertyKey));

        } catch (IOException e) {
            file = new File(System.getProperty("user.home"));
            LOG.log(Level.ERROR, "Unable to load properties file: " + e);
        }

        return file;

    }

    public static Properties getProperties() {

        Properties properties = new Properties();

        try {
            properties.load(PropertyUtil.class.getResourceAsStream("/config.properties"));

        } catch (IOException e) {
            LOG.log(Level.ERROR, "Unable to find properties file: " + e);
        }

        return properties;
    }
}
