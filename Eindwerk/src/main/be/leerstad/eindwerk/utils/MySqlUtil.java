package be.leerstad.eindwerk.utils;

import be.leerstad.eindwerk.model.*;

import java.sql.*;

public class MySqlUtil {

    public static void setPreparedLogfileStatement(Logfile logfile, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, logfile.getLogFile());
        preparedStatement.setDate(2, Date.valueOf(logfile.getLogFileDate()));
    }

    public static void setPreparedSchoolStatement(School school, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, school.getIpAddress());
        preparedStatement.setString(2, school.getSite());
        preparedStatement.setString(3, school.getStreet());
        preparedStatement.setString(4, school.getZip());
        preparedStatement.setString(5, school.getCity());
    }

    public static void setPreparedSessionStatement(Session session, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, session.getId());
        preparedStatement.setString(2, session.getLogfile().getLogFile());
        preparedStatement.setString(3, session.getIpAddress());
        preparedStatement.setTime(4, Time.valueOf(session.getTime()));
        preparedStatement.setInt(5, session.getTotalTimeInSec());
        preparedStatement.setInt(6, session.getTransferredBytes());
        preparedStatement.setInt(7, session.getNumberOfRequests());
        preparedStatement.setString(8, session.getUser().getUserId());
        preparedStatement.setInt(9, session.getSite().getSiteId());
    }

    public static void setPreparedSiteStatement(Site site, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, site.getSiteId());
        preparedStatement.setString(2, site.getSite());
    }

    public static void setPreparedSiteApplicationStatement(SiteApplication siteApplication, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, siteApplication.getApplicationId());
        preparedStatement.setString(2, siteApplication.getApplication());
    }

    public static void setPreparedUserStatement(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getUserId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getFirstName());
        preparedStatement.setString(4, user.getCat());
    }

    public static void setPreparedVisitStatement(Visit visit, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, visit.getId());
        preparedStatement.setString(2, visit.getLogfile().getLogFile());
        preparedStatement.setString(3, visit.getIpAddress());
        preparedStatement.setTime(4, Time.valueOf(visit.getTime()));
        preparedStatement.setInt(5, visit.getTotalTimeInSec());
        preparedStatement.setInt(6, visit.getTransferredBytes());
        preparedStatement.setInt(7, visit.getNumberOfRequests());
        preparedStatement.setString(8, visit.getUser());
        preparedStatement.setInt(9, visit.getSiteApplication().getApplicationId());
        preparedStatement.setString(10, visit.getSchool().getIpAddress());
    }


    public static Logfile getLogfileResult(ResultSet resultSet) throws SQLException {
        return new Logfile(resultSet.getString("LogFile"),
                resultSet.getDate("LogFileDate").toLocalDate());
    }

    public static School getSchoolResult(ResultSet resultSet) throws SQLException {
        return new School(resultSet.getString("IPAddress"), resultSet.getString("Site"),
                resultSet.getString("Street"), resultSet.getString("ZIP"),
                resultSet.getString("City"));
    }

    public static Session getSessionResult(ResultSet resultSet) throws SQLException {
        return new Session(resultSet.getString("SessionID"),
                new Logfile(resultSet.getString("LogFile")),
                resultSet.getString("IPAddress"),
                resultSet.getTime("SessionTime").toLocalTime(),
                resultSet.getInt("TotalTime"),
                resultSet.getInt("TransferredBytes"),
                resultSet.getInt("NumberOfRequests"),
                new User(resultSet.getString("UserID"), resultSet.getString("Name"),
                        resultSet.getString("FirstName"), resultSet.getString("Cat")),
                new Site(resultSet.getInt("SiteID"), resultSet.getString("Site")));
    }

    public static Site getSiteResult(ResultSet resultSet) throws SQLException {
        return new Site(resultSet.getInt("SiteID"), resultSet.getString("Site"));
    }

    public static SiteApplication getSiteApplicationResult(ResultSet resultSet) throws SQLException {
        return new SiteApplication(resultSet.getInt("ApplicationID"),
                resultSet.getString("Application"));
    }

    public static User getUserResult(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getString("UserID"), resultSet.getString("Name"),
                resultSet.getString("FirstName"), resultSet.getString("Cat"));
    }

    public static Visit getVisitResult(ResultSet resultSet) throws SQLException {
        return new Visit(resultSet.getString("VisitID"),
                new Logfile(resultSet.getString("LogFile")),
                resultSet.getString("IPAddress"),
                resultSet.getTime("VisitTime").toLocalTime(),
                resultSet.getInt("TotalTime"),
                resultSet.getInt("TransferredBytes"),
                resultSet.getInt("NumberOfRequests"),
                resultSet.getString("User"),
                new SiteApplication(resultSet.getInt("ApplicationID"),
                        resultSet.getString("Application")),
                new School(resultSet.getString("IPAddress"), resultSet.getString("Site"),
                        resultSet.getString("Street"), resultSet.getString("Zip"),
                        resultSet.getString("City")));
    }

}
