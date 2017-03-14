package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.Session;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionDAOImpl implements SessionDAO {

    private static final Logger LOG = Logger.getLogger(SessionDAOImpl.class.getName());
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
    public void saveInteraction(Session session) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO sessions (LogFile,IPAddress,SessionTime,TotalTime,TransferredBytes," +
                    "UserID,SiteID) VALUES (?,?,?,?,?,?,(SELECT SiteID FROM sites WHERE Site=?))";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, session.getLogFile().getLogFile());
        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to create statement ", e);
        }
    }

    @Override
    public Session loadInteraction(Integer id) {
        return null;
    }
}
