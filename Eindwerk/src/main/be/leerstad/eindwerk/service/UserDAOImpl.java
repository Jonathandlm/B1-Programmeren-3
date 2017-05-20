package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.User;
import be.leerstad.eindwerk.utils.MySqlUtil;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends BaseDAO implements UserDAO {

    private static final Logger LOG = Logger.getLogger(UserDAOImpl.class.getName());
    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String GET_USER = "SELECT * FROM users WHERE UserID = ?";
    private static final String INSERT_USER = "INSERT INTO users (UserID, Name, FirstName, Cat) VALUES (?,?,?,?)";

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
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to get users ", e);
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
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to get user with IP Address: " + userId, e);
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
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to insert " + user, e);
        }
    }

    @Override
    public void insertUsers(List<User> users) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)
        ) {
            for (User user : users) {
                MySqlUtil.setPreparedUserStatement(user, preparedStatement);

                preparedStatement.executeUpdate();
            }

            LOG.log(Level.DEBUG, "Successfully inserted " + users.size() + " users.");

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement " + INSERT_USER, e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to insert users ", e);
        }
    }
}
