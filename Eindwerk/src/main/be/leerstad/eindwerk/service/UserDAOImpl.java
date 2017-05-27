package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.User;
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

public class UserDAOImpl extends BaseDAO implements UserDAO {

    private static final Logger LOG = Logger.getLogger(UserDAOImpl.class.getName());
    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String GET_USER = "SELECT * FROM users WHERE UserID = ?";
    private static final String INSERT_USER = "INSERT INTO users (UserID, Name, FirstName, Cat) VALUES (?,?,?,?)";
    private static final String DELETE_USER = "DELETE FROM users WHERE UserID = ?";

    private static UserDAOImpl instance;

    private UserDAOImpl() {
    }

    public synchronized static UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while (resultSet.next()) {
                users.add(MySqlUtil.getUserResult(resultSet));
            }

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement " + GET_ALL_USERS, e);
        } catch (DAOException e) {
            LOG.log(Level.ERROR, "Unable to get connection ", e);
        }

        return users;
    }

    @Override
    public User getUser(String userId) {
        User user = new User();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_USER)
        ) {
            preparedStatement.setString(1, userId);

            try (
                    ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                while (resultSet.next()) {
                    user = MySqlUtil.getUserResult(resultSet);
                }
                LOG.log(Level.DEBUG, "Successfully retrieved user with IP Address: " + userId);

            }

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement " + GET_USER, e);
        } catch (DAOException e) {
            LOG.log(Level.ERROR, "Unable to get connection ", e);
        }

        return user;
    }

    @Override
    public void insertUser(User user) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)
        ) {
            MySqlUtil.setPreparedUserStatement(user, preparedStatement);

            preparedStatement.executeUpdate();

            LOG.log(Level.DEBUG, "Successfully inserted " + user);

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement " + INSERT_USER, e);
        } catch (DAOException e) {
            LOG.log(Level.ERROR, "Unable to get connection ", e);
        }
    }

    @Override
    public void deleteUser(String userId) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)
        ) {

            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();

            LOG.log(Level.DEBUG, "Successfully deleted user with User ID " + userId);

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement(s) ", e);
        } catch (DAOException e) {
            LOG.log(Level.ERROR, "Unable to get connection ", e);
        }
    }

    @Override
    public Map<String,User> fillCache() {
        Map<String,User> cache = new HashMap<>();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while (resultSet.next()) {
                cache.put(resultSet.getString("UserID"), MySqlUtil.getUserResult(resultSet));
            }

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement " + GET_ALL_USERS, e);
        } catch (DAOException e) {
            LOG.log(Level.ERROR, "Unable to get connection ", e);
        }

        return cache;
    }

}
