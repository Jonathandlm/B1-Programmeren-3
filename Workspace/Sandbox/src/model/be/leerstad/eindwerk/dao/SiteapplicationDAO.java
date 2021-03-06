// Generated by DB Solo 5.2.1 on Feb 14, 2017 at 9:31:39 PM
package be.leerstad.eindwerk.dao;

import be.leerstad.eindwerk.Siteapplication;

import java.util.List;


public interface SiteapplicationDAO {
    // CRUD methods
    public Siteapplication getByPrimaryKey(int applicationid)
        throws DAOException;

    public List selectAll() throws DAOException;

    public List select(String whereStatement, Object[] bindVariables)
        throws DAOException;

    public long selectCount() throws DAOException;

    public long selectCount(String whereStatement, Object[] bindVariables)
        throws DAOException;

    public int update(Siteapplication obj) throws DAOException;

    public int insert(Siteapplication obj) throws DAOException;

    public int delete(Siteapplication obj) throws DAOException;

    // Finders
    public List getByApplication(String application) throws DAOException;
}
