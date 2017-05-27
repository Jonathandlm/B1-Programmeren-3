package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.School;
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

public class SchoolDAOImpl extends BaseDAO implements SchoolDAO {

    private static final Logger LOG = Logger.getLogger(SchoolDAOImpl.class.getName());
    private static final String GET_ALL_SCHOOLS = "SELECT * FROM schools";
    private static final String GET_SCHOOL = "SELECT * FROM schools WHERE IPAddress = ?";
    private static final String INSERT_SCHOOL = "INSERT INTO schools (IPAddress, Site, Street, Zip, City)" +
            "VALUES (?,?,?,?,?)";
    private static final String DELETE_SCHOOL = "DELETE FROM schools WHERE IPAddress = ?";

    private static SchoolDAOImpl instance;

    private SchoolDAOImpl() {
    }

    public synchronized static SchoolDAOImpl getInstance() {
        if (instance == null) {
            instance = new SchoolDAOImpl();
        }
        return instance;
    }

    @Override
    public List<School> getSchools() {
        List<School> schools = new ArrayList<>();
        
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SCHOOLS);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while (resultSet.next()) {
                schools.add(MySqlUtil.getSchoolResult(resultSet));
            }

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement " + GET_ALL_SCHOOLS, e);
        } catch (DAOException e) {
            LOG.log(Level.ERROR, "Unable to get connection ", e);
        }

        return schools;
    }

    @Override
    public School getSchool(String ipAddress) {
        School school = new School();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_SCHOOL)
        ) {
            preparedStatement.setString(1, ipAddress);

            try (
                    ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                while (resultSet.next()) {
                    school = MySqlUtil.getSchoolResult(resultSet);
                }
                LOG.log(Level.DEBUG, "Successfully retrieved school with IP Address: " + ipAddress);

            }

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement " + GET_SCHOOL, e);
        } catch (DAOException e) {
            LOG.log(Level.ERROR, "Unable to get connection ", e);
        }

        return school;
    }

    @Override
    public void insertSchool(School school) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SCHOOL)
        ) {
            MySqlUtil.setPreparedSchoolStatement(school, preparedStatement);

            preparedStatement.executeUpdate();

            LOG.log(Level.DEBUG, "Successfully inserted " + school);

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement " + INSERT_SCHOOL, e);
        } catch (DAOException e) {
            LOG.log(Level.ERROR, "Unable to get connection ", e);
        }
    }

    @Override
    public void deleteSchool(String ipAddress) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SCHOOL)
        ) {

            preparedStatement.setString(1, ipAddress);
            preparedStatement.executeUpdate();

            LOG.log(Level.DEBUG, "Successfully deleted school with network address " + ipAddress);

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement(s) ", e);
        } catch (DAOException e) {
            LOG.log(Level.ERROR, "Unable to get connection ", e);
        }
    }
    
    @Override
    public Map<String, School> fillCache() {
        Map<String,School> cache = new HashMap<>();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SCHOOLS);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while (resultSet.next()) {
                cache.put(resultSet.getString("IPAddress"), MySqlUtil.getSchoolResult(resultSet));
            }

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement " + GET_ALL_SCHOOLS, e);
        } catch (DAOException e) {
            LOG.log(Level.ERROR, "Unable to get connection ", e);
        }

        return cache;
    }

}
