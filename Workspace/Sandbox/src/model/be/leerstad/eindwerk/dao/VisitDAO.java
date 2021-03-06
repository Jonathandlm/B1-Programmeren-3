// Generated by DB Solo 5.2.1 on Feb 14, 2017 at 9:31:39 PM
package be.leerstad.eindwerk.dao;

import be.leerstad.eindwerk.Visit;

import java.util.Date;
import java.util.List;


public interface VisitDAO {
    // CRUD methods
    public Visit getByPrimaryKey(String visitid) throws DAOException;

    public List selectAll() throws DAOException;

    public List select(String whereStatement, Object[] bindVariables)
        throws DAOException;

    public long selectCount() throws DAOException;

    public long selectCount(String whereStatement, Object[] bindVariables)
        throws DAOException;

    public int update(Visit obj) throws DAOException;

    public int insert(Visit obj) throws DAOException;

    public int delete(Visit obj) throws DAOException;

    // Finders
    public List getByLogfile(String logfile) throws DAOException;

    public List getByIpaddress(String ipaddress) throws DAOException;

    public List getByVisittime(Date visittime) throws DAOException;

    public List getByTotaltime(Integer totaltime) throws DAOException;

    public List getByTransferredbytes(Integer transferredbytes)
        throws DAOException;

    public List getByNumberofrequests(Integer numberofrequests)
        throws DAOException;

    public List getByUser(String user) throws DAOException;

    public List getBySiteapplicationid(Integer siteapplicationid)
        throws DAOException;

    public List getByIpschool(String ipschool) throws DAOException;
}
