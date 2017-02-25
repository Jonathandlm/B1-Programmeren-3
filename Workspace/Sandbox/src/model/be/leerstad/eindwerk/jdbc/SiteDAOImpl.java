// Generated by DB Solo 5.2.1 on Feb 14, 2017 at 9:31:39 PM
package be.leerstad.eindwerk.jdbc;

import be.leerstad.eindwerk.Site;
import be.leerstad.eindwerk.dao.DAOException;
import be.leerstad.eindwerk.dao.SiteDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SiteDAOImpl implements SiteDAO {
    //
    // static data
    //
    protected static List pkColumns = new ArrayList();
    protected static List stdColumns = new ArrayList();
    protected static List allColumns = new ArrayList();
    protected static String tableName = "sites";

    static {
        pkColumns.add("SiteId");
        stdColumns.add("Site");
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
    public SiteDAOImpl() {
        this(null);
    }

    public SiteDAOImpl(Connection conn) {
        this.conn = conn;
    }

    //
    // CRUD methods
    //
    public Site getByPrimaryKey(int siteid) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            int pos = 1;
            ps = getConn().prepareStatement(DBUtil.select(tableName, allColumns, pkColumns));
            DBUtil.bind(ps, pos++, siteid);
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

    public int update(Site obj) throws DAOException {
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

    public int insert(Site obj) throws DAOException {
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

    public int delete(Site obj) throws DAOException {
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
    public List getBySite(String site) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List ret = new ArrayList();

        try {
            if (null == site) {
                ps = getConn()
                         .prepareStatement(DBUtil.selectNull(tableName, allColumns,
                            Arrays.asList(new String[]{ "Site" })));
            }
            else {
                ps = getConn()
                         .prepareStatement(DBUtil.select(tableName, allColumns,
                            Arrays.asList(new String[]{ "Site" })));
                DBUtil.bind(ps, 1, site);
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
    protected int bindPrimaryKey(PreparedStatement ps, Site obj, int pos)
        throws SQLException {
        DBUtil.bind(ps, pos++, obj.getSiteid());

        return pos;
    }

    protected int bindStdColumns(PreparedStatement ps, Site obj, int pos)
        throws SQLException {
        DBUtil.bind(ps, pos++, obj.getSite());

        return pos;
    }

    protected Site fromResultSet(ResultSet rs) throws SQLException {
        Site obj = new Site();

        obj.setSiteid(DBUtil.getInt(rs, "SiteId"));
        obj.setSite(DBUtil.getString(rs, "Site"));

        return obj;
    }

    protected Connection getConn() {
        return (conn == null) ? DBUtil.getConnection() : conn;
    }
}
