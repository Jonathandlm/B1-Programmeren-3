package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.util.PropertyUtil;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class BaseDAO implements DAO {

    private static final Logger LOG = Logger.getLogger(BaseDAO.class.getName());

    public Connection getConnection() throws DAOException {
        Connection connection;

        try {
            connection = PropertyUtil.getConnectionProperties();
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to create connection: " + e);
            throw new DAOException(e.getMessage());
        }

        return connection;
    }
}