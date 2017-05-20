package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.School;
import be.leerstad.eindwerk.utils.MySqlUtil;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchoolDAOImpl extends BaseDAO implements SchoolDAO {

    private static final Logger LOG = Logger.getLogger(SchoolDAOImpl.class.getName());
    private static final String GET_ALL_SCHOOLS = "SELECT * FROM schools";
    private static final String GET_SCHOOL = "SELECT * FROM schools WHERE IPAddress = ?";
    private static final String INSERT_SCHOOL = "INSERT INTO schools (IPAddress, Site, Street, Zip, City)" +
            "VALUES (?,?,?,?,?)";

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
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to get schools ", e);
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
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to get school with IP Address: " + ipAddress, e);
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
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to insert " + school, e);
        }
    }

    @Override
    public void insertSchools(List<School> schools) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SCHOOL)
        ) {
            for (School school : schools) {
                MySqlUtil.setPreparedSchoolStatement(school, preparedStatement);

                preparedStatement.executeUpdate();
            }

            LOG.log(Level.DEBUG, "Successfully inserted " + schools.size() + " schools.");

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement " + INSERT_SCHOOL, e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to insert schools ", e);
        }
    }


}
