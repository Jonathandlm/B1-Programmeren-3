package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.SiteApplication;
import be.leerstad.eindwerk.utils.MySqlUtil;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SiteApplicationDAOImpl extends BaseDAO implements SiteApplicationDAO {

    private static final Logger LOG = Logger.getLogger(SiteApplicationDAOImpl.class.getName());
    private static final String GET_ALL_SITEAPPLICATIONS = "SELECT * FROM siteApplications";
    private static final String GET_SITEAPPLICATION = "SELECT * FROM siteApplications WHERE ApplicationID = ?";
    private static final String INSERT_SITEAPPLICATION = "INSERT INTO siteApplications (ApplicationID, Application)" +
            "VALUES (?,?)";

    private static SiteApplicationDAOImpl instance;

    private SiteApplicationDAOImpl() {
    }

    public synchronized static SiteApplicationDAOImpl getInstance() {
        if (instance == null) {
            instance = new SiteApplicationDAOImpl();
        }
        return instance;
    }

    @Override
    public List<SiteApplication> getSiteApplications() {
        List<SiteApplication> siteApplications = new ArrayList<>();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SITEAPPLICATIONS);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while (resultSet.next()) {
                siteApplications.add(MySqlUtil.getSiteApplicationResult(resultSet));
            }

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement " + GET_ALL_SITEAPPLICATIONS, e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to get siteApplications ", e);
        }

        return siteApplications;
    }

    @Override
    public SiteApplication getSiteApplication(int applicationId) {
        SiteApplication siteApplication = new SiteApplication();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_SITEAPPLICATION)
        ) {
            preparedStatement.setInt(1, applicationId);

            try (
                    ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                while (resultSet.next()) {
                    siteApplication = MySqlUtil.getSiteApplicationResult(resultSet);
                }
                LOG.log(Level.DEBUG, "Successfully retrieved siteApplication with IP Address: " + applicationId);

            }

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement " + GET_SITEAPPLICATION, e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to get siteApplication with id: " + applicationId, e);
        }

        return siteApplication;
    }

    @Override
    public void insertSiteApplication(SiteApplication siteApplication) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SITEAPPLICATION)
        ) {
            MySqlUtil.setPreparedSiteApplicationStatement(siteApplication, preparedStatement);

            preparedStatement.executeUpdate();

            LOG.log(Level.DEBUG, "Successfully inserted " + siteApplication);

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement " + INSERT_SITEAPPLICATION, e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to insert " + siteApplication, e);
        }
    }

    @Override
    public void insertSiteApplications(List<SiteApplication> siteApplications) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SITEAPPLICATION)
        ) {
            for (SiteApplication siteApplication : siteApplications) {
                MySqlUtil.setPreparedSiteApplicationStatement(siteApplication, preparedStatement);

                preparedStatement.executeUpdate();
            }

            LOG.log(Level.DEBUG, "Successfully inserted " + siteApplications.size() + " siteApplications.");

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement " + INSERT_SITEAPPLICATION, e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to insert siteApplications ", e);
        }
    }

}
