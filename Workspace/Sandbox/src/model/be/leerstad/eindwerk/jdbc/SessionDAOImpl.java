// Generated by DB Solo 5.2.1 on Feb 14, 2017 at 9:31:39 PM
package be.leerstad.eindwerk.jdbc;

import be.leerstad.eindwerk.Session;
import be.leerstad.eindwerk.dao.DAOException;
import be.leerstad.eindwerk.dao.SessionDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class SessionDAOImpl implements SessionDAO {
    //
    // static data
    //
    protected static List pkColumns = new ArrayList();
    protected static List stdColumns = new ArrayList();
    protected static List allColumns = new ArrayList();
    protected static String tableName = "sessions";

    static {
        pkColumns.add("SessionID");
        stdColumns.add("LogFile");
        stdColumns.add("IPAddress");
        stdColumns.add("SessionTime");
        stdColumns.add("TotalTime");
        stdColumns.add("TransferredBytes");
        stdColumns.add("NumberOfRequests");
        stdColumns.add("UserID");
        stdColumns.add("SiteID");
        allColumns.addAll(pkColumns);
        allColumns.addAll(stdColumns);
    }

    //
    // data
    //
    protected Connection conn = null;

    //
    // construction
    //
    public SessionDAOImpl() {
        this(null);
    }

    public SessionDAOImpl(Connection conn) {
        this.conn = conn;
    }

    //
    // CRUD methods
    //
    public Session getByPrimaryKey(String sessionid) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            int pos = 1;
            ps = getConn().prepareStatement(DBUtil.select(tableName, allColumns, pkColumns));
            DBUtil.bind(ps, pos++, sessionid);
            rs = ps.executeQuery();

            if (rs.next()) {
                return fromResultSet(rs);
            }
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            DBUtil.close(ps, rs);
        }

        return null;
    }

    public long selectCount() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = getConn().prepareStatement("select count(*) from " + tableName);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getLong(1);
            }
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            DBUtil.close(ps, rs);
        }

        return 0;
    }

    public long selectCount(String whereStatement, Object[] bindVariables)
        throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        if (!whereStatement.trim().toUpperCase().startsWith("WHERE")) {
            whereStatement = " WHERE " + whereStatement;
        }
        else if (whereStatement.startsWith(" ") == false) {
            whereStatement = " " + whereStatement;
        }

        try {
            ps = getConn().prepareStatement("select count(*) from " + tableName + whereStatement);

            for (int i = 0; i < bindVariables.length; i++)
                DBUtil.bind(ps, i + 1, bindVariables[i]);

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getLong(1);
            }
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            DBUtil.close(ps, rs);
        }

        return 0;
    }

    public List selectAll() throws DAOException {
        List ret = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = getConn().prepareStatement(DBUtil.select(tableName, allColumns));
            rs = ps.executeQuery();

            while (rs.next())
                ret.add(fromResultSet(rs));
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            DBUtil.close(ps, rs);
        }

        return ret;
    }

    public List select(String whereStatement, Object[] bindVariables)
        throws DAOException {
        List ret = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;

        if (!whereStatement.trim().toUpperCase().startsWith("WHERE")) {
            whereStatement = " WHERE " + whereStatement;
        }
        else if (whereStatement.startsWith(" ") == false) {
            whereStatement = " " + whereStatement;
        }

        try {
            ps = getConn().prepareStatement(DBUtil.select(tableName, allColumns) + whereStatement);

            for (int i = 0; i < bindVariables.length; i++)
                DBUtil.bind(ps, i + 1, bindVariables[i]);

            rs = ps.executeQuery();

            while (rs.next())
                ret.add(fromResultSet(rs));
        }
        catch (SQLException e) {
            throw new DAOException("Error in select(), table = " + tableName, e);
        }
        finally {
            DBUtil.close(ps, rs);
        }

        return ret;
    }

    public int update(Session obj) throws DAOException {
        PreparedStatement ps = null;
        int pos = 1;

        try {
            ps = getConn().prepareStatement(DBUtil.update(tableName, stdColumns, pkColumns));
            pos = bindStdColumns(ps, obj, pos);
            bindPrimaryKey(ps, obj, pos);

            int rowCount = ps.executeUpdate();

            if (rowCount != 1) {
                throw new DAOException("Error updating " + obj.getClass() + " in " + tableName +
                    ", affected rows = " + rowCount);
            }

            return rowCount;
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            DBUtil.close(ps, null);
        }
    }

    public int insert(Session obj) throws DAOException {
        PreparedStatement ps = null;
        int pos = 1;

        try {
            ps = getConn().prepareStatement(DBUtil.insert(tableName, pkColumns, stdColumns));
            pos = bindPrimaryKey(ps, obj, pos);
            bindStdColumns(ps, obj, pos);

            int rowCount = ps.executeUpdate();

            if (rowCount != 1) {
                throw new DAOException("Error inserting " + obj.getClass() + " in " + tableName +
                    ", affected rows = " + rowCount);
            }

            return rowCount;
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            DBUtil.close(ps, null);
        }
    }

    public int delete(Session obj) throws DAOException {
        PreparedStatement ps = null;

        try {
            ps = getConn().prepareStatement(DBUtil.delete(tableName, pkColumns));
            bindPrimaryKey(ps, obj, 1);

            int rowCount = ps.executeUpdate();

            if (rowCount != 1) {
                throw new DAOException("Error deleting " + obj.getClass() + " in " + tableName +
                    ", affected rows = " + rowCount);
            }

            return rowCount;
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            DBUtil.close(ps, null);
        }
    }

    //
    // finders
    //
    public List getByLogfile(String logfile) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List ret = new ArrayList();

        try {
            if (null == logfile) {
                ps = getConn()
                         .prepareStatement(DBUtil.selectNull(tableName, allColumns,
                            Arrays.asList(new String[]{ "LogFile" })));
            }
            else {
                ps = getConn()
                         .prepareStatement(DBUtil.select(tableName, allColumns,
                            Arrays.asList(new String[]{ "LogFile" })));
                DBUtil.bind(ps, 1, logfile);
            }

            rs = ps.executeQuery();

            while (rs.next())
                ret.add(fromResultSet(rs));
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            DBUtil.close(ps, rs);
        }

        return ret;
    }

    public List getByIpaddress(String ipaddress) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List ret = new ArrayList();

        try {
            if (null == ipaddress) {
                ps = getConn()
                         .prepareStatement(DBUtil.selectNull(tableName, allColumns,
                            Arrays.asList(new String[]{ "IPAddress" })));
            }
            else {
                ps = getConn()
                         .prepareStatement(DBUtil.select(tableName, allColumns,
                            Arrays.asList(new String[]{ "IPAddress" })));
                DBUtil.bind(ps, 1, ipaddress);
            }

            rs = ps.executeQuery();

            while (rs.next())
                ret.add(fromResultSet(rs));
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            DBUtil.close(ps, rs);
        }

        return ret;
    }

    public List getBySessiontime(Date sessiontime) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List ret = new ArrayList();

        try {
            if (null == sessiontime) {
                ps = getConn()
                         .prepareStatement(DBUtil.selectNull(tableName, allColumns,
                            Arrays.asList(new String[]{ "SessionTime" })));
            }
            else {
                ps = getConn()
                         .prepareStatement(DBUtil.select(tableName, allColumns,
                            Arrays.asList(new String[]{ "SessionTime" })));
                DBUtil.bind(ps, 1, sessiontime);
            }

            rs = ps.executeQuery();

            while (rs.next())
                ret.add(fromResultSet(rs));
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            DBUtil.close(ps, rs);
        }

        return ret;
    }

    public List getByTotaltime(Integer totaltime) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List ret = new ArrayList();

        try {
            if (null == totaltime) {
                ps = getConn()
                         .prepareStatement(DBUtil.selectNull(tableName, allColumns,
                            Arrays.asList(new String[]{ "TotalTime" })));
            }
            else {
                ps = getConn()
                         .prepareStatement(DBUtil.select(tableName, allColumns,
                            Arrays.asList(new String[]{ "TotalTime" })));
                DBUtil.bind(ps, 1, totaltime);
            }

            rs = ps.executeQuery();

            while (rs.next())
                ret.add(fromResultSet(rs));
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            DBUtil.close(ps, rs);
        }

        return ret;
    }

    public List getByTransferredbytes(Integer transferredbytes)
        throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List ret = new ArrayList();

        try {
            if (null == transferredbytes) {
                ps = getConn()
                         .prepareStatement(DBUtil.selectNull(tableName, allColumns,
                            Arrays.asList(new String[]{ "TransferredBytes" })));
            }
            else {
                ps = getConn()
                         .prepareStatement(DBUtil.select(tableName, allColumns,
                            Arrays.asList(new String[]{ "TransferredBytes" })));
                DBUtil.bind(ps, 1, transferredbytes);
            }

            rs = ps.executeQuery();

            while (rs.next())
                ret.add(fromResultSet(rs));
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            DBUtil.close(ps, rs);
        }

        return ret;
    }

    public List getByNumberofrequests(Integer numberofrequests)
        throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List ret = new ArrayList();

        try {
            if (null == numberofrequests) {
                ps = getConn()
                         .prepareStatement(DBUtil.selectNull(tableName, allColumns,
                            Arrays.asList(new String[]{ "NumberOfRequests" })));
            }
            else {
                ps = getConn()
                         .prepareStatement(DBUtil.select(tableName, allColumns,
                            Arrays.asList(new String[]{ "NumberOfRequests" })));
                DBUtil.bind(ps, 1, numberofrequests);
            }

            rs = ps.executeQuery();

            while (rs.next())
                ret.add(fromResultSet(rs));
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            DBUtil.close(ps, rs);
        }

        return ret;
    }

    public List getByUserid(String userid) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List ret = new ArrayList();

        try {
            if (null == userid) {
                ps = getConn()
                         .prepareStatement(DBUtil.selectNull(tableName, allColumns,
                            Arrays.asList(new String[]{ "UserID" })));
            }
            else {
                ps = getConn()
                         .prepareStatement(DBUtil.select(tableName, allColumns,
                            Arrays.asList(new String[]{ "UserID" })));
                DBUtil.bind(ps, 1, userid);
            }

            rs = ps.executeQuery();

            while (rs.next())
                ret.add(fromResultSet(rs));
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            DBUtil.close(ps, rs);
        }

        return ret;
    }

    public List getBySiteid(Integer siteid) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List ret = new ArrayList();

        try {
            if (null == siteid) {
                ps = getConn()
                         .prepareStatement(DBUtil.selectNull(tableName, allColumns,
                            Arrays.asList(new String[]{ "SiteID" })));
            }
            else {
                ps = getConn()
                         .prepareStatement(DBUtil.select(tableName, allColumns,
                            Arrays.asList(new String[]{ "SiteID" })));
                DBUtil.bind(ps, 1, siteid);
            }

            rs = ps.executeQuery();

            while (rs.next())
                ret.add(fromResultSet(rs));
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            DBUtil.close(ps, rs);
        }

        return ret;
    }

    //
    // helpers
    //
    protected int bindPrimaryKey(PreparedStatement ps, Session obj, int pos)
        throws SQLException {
        DBUtil.bind(ps, pos++, obj.getSessionid());

        return pos;
    }

    protected int bindStdColumns(PreparedStatement ps, Session obj, int pos)
        throws SQLException {
        DBUtil.bind(ps, pos++, obj.getLogfile());
        DBUtil.bind(ps, pos++, obj.getIpaddress());
        DBUtil.bind(ps, pos++, obj.getSessiontime());
        DBUtil.bind(ps, pos++, obj.getTotaltime());
        DBUtil.bind(ps, pos++, obj.getTransferredbytes());
        DBUtil.bind(ps, pos++, obj.getNumberofrequests());
        DBUtil.bind(ps, pos++, obj.getUserid());
        DBUtil.bind(ps, pos++, obj.getSiteid());

        return pos;
    }

    protected Session fromResultSet(ResultSet rs) throws SQLException {
        Session obj = new Session();

        obj.setSessionid(DBUtil.getString(rs, "SessionID"));
        obj.setLogfile(DBUtil.getString(rs, "LogFile"));
        obj.setIpaddress(DBUtil.getString(rs, "IPAddress"));
        obj.setSessiontime(DBUtil.getDate(rs, "SessionTime"));
        obj.setTotaltime(DBUtil.getInteger(rs, "TotalTime"));
        obj.setTransferredbytes(DBUtil.getInteger(rs, "TransferredBytes"));
        obj.setNumberofrequests(DBUtil.getInteger(rs, "NumberOfRequests"));
        obj.setUserid(DBUtil.getString(rs, "UserID"));
        obj.setSiteid(DBUtil.getInteger(rs, "SiteID"));

        return obj;
    }

    protected Connection getConn() {
        return (conn == null) ? DBUtil.getConnection() : conn;
    }
}
