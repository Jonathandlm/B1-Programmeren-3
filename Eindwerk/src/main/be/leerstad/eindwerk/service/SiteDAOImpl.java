package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.Site;
import be.leerstad.eindwerk.utils.MySqlUtil;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SiteDAOImpl extends BaseDAO implements SiteDAO {

    private static final Logger LOG = Logger.getLogger(SiteDAOImpl.class.getName());
    private static final String GET_ALL_SITES = "SELECT * FROM sites";
    private static final String GET_SITE = "SELECT * FROM sites WHERE SiteID = ?";
    private static final String INSERT_SITE = "INSERT INTO sites (SiteID, Site) VALUES (?,?)";

    private static SiteDAOImpl instance;

    private SiteDAOImpl() {
    }

    public synchronized static SiteDAOImpl getInstance() {
        if (instance == null) {
            instance = new SiteDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Site> getSites() {
        List<Site> sites = new ArrayList<>();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SITES);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while (resultSet.next()) {
                sites.add(MySqlUtil.getSiteResult(resultSet));
            }

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement " + GET_ALL_SITES, e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to get sites ", e);
        }

        return sites;
    }

    @Override
    public Site getSite(int applicationId) {
        Site site = new Site();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_SITE)
        ) {
            preparedStatement.setInt(1, applicationId);

            try (
                    ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                while (resultSet.next()) {
                    site = MySqlUtil.getSiteResult(resultSet);
                }
                LOG.log(Level.DEBUG, "Successfully retrieved site with IP Address: " + applicationId);

            }

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement " + GET_SITE, e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to get site with id: " + applicationId, e);
        }

        return site;
    }

    @Override
    public void insertSite(Site site) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SITE)
        ) {
            MySqlUtil.setPreparedSiteStatement(site, preparedStatement);

            preparedStatement.executeUpdate();

            LOG.log(Level.DEBUG, "Successfully inserted " + site);

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement " + INSERT_SITE, e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to insert " + site, e);
        }
    }

    @Override
    public void insertSites(List<Site> sites) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SITE)
        ) {
            for (Site site : sites) {
                MySqlUtil.setPreparedSiteStatement(site, preparedStatement);

                preparedStatement.executeUpdate();
            }

            LOG.log(Level.DEBUG, "Successfully inserted " + sites.size() + " sites.");

        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Unable to execute statement " + INSERT_SITE, e);
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Unable to insert sites ", e);
        }
    }

}
