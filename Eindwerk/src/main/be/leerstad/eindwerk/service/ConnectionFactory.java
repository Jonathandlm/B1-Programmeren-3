package be.leerstad.eindwerk.service;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ConnectionFactory {

    private static final Logger LOG = Logger.getLogger(ConnectionFactory.class.getName());

    public static Connection getConnection (){
        Properties properties = new Properties();
        FileInputStream fileInputStream;
        Connection connection = null;
        try {
            // load properties
            fileInputStream = new FileInputStream("config.properties");
            properties.load(fileInputStream);

            // load driver class
            Class.forName(properties.getProperty("DB_DRIVER_CLASS"));

            // create connection
            connection = DriverManager.getConnection(properties.getProperty("DB_URL"),
                    properties.getProperty("DB_USERNAME"),
                    properties.getProperty("DB_PASSWORD"));
        } catch (FileNotFoundException e) {
            LOG.log(Level.ERROR, "Unable to find properties file: " + e);
        } catch (IOException e) {
            LOG.log(Level.ERROR, "Unable to load properties file: " + e);
        } catch (ClassNotFoundException e) {
            LOG.log(Level.ERROR, "Unable to load driver class: " + e);
        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to create connection " + e);
        }

        return connection;
    }

    public static void closeResultSet (ResultSet resultSet){
        try { if ( resultSet != null ) resultSet.close (); }
        catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to close result set " + e);
        }
    }

    public static void closeStatement (Statement statement){
        try { if ( statement != null ) statement.close (); }
        catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to close statement " + e);
        }
    }

    public static void closeConnection (Connection connection){
        try { if ( connection != null ) connection.close (); }
        catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to close connection " + e);
        }
    }
}