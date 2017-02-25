// Generated by DB Solo 5.2.1 on Feb 14, 2017 at 9:31:39 PM
package be.leerstad.eindwerk.test;

import be.leerstad.eindwerk.School;
import be.leerstad.eindwerk.jdbc.SchoolDAOImpl;

import junit.framework.TestCase;

import java.sql.Connection;


public class SchoolDAOImplTest extends TestCase {
    private Connection conn = null;

    protected void setUp() throws Exception {
        conn = TestUtil.getConnection();
    }

    protected void tearDown() throws Exception {
        TestUtil.closeConnection(conn);
        conn = null;
    }

    public void testSelect() {
        SchoolDAOImpl obj = new SchoolDAOImpl(getConn());

        try {
            obj.selectAll();
        }
        catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    public void testDelete() {
        try {
            SchoolDAOImpl dao = new SchoolDAOImpl(getConn());

            School obj = insert(getConn());
            int before = dao.selectAll().size();
            int after;

            dao.delete(obj);
            after = dao.selectAll().size();
            assertTrue(after == (before - 1));
            getConn().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    public void testFinders() {
        try {
            SchoolDAOImpl dao = new SchoolDAOImpl(getConn());
            School obj = insert(getConn());

            assertTrue(dao.getBySite(obj.getSite()).size() > 0);
            assertTrue(dao.getByStreet(obj.getStreet()).size() > 0);
            assertTrue(dao.getByZip(obj.getZip()).size() > 0);
            assertTrue(dao.getByCity(obj.getCity()).size() > 0);
            dao.delete(obj);

            getConn().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    public void testInsert() {
        try {
            SchoolDAOImpl dao = new SchoolDAOImpl(getConn());

            int before = dao.selectAll().size();
            School obj = insert(getConn());
            int after = dao.selectAll().size();

            assertNotNull(obj);
            assertTrue(after == (before + 1));
            getConn().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    public void testUpdate() {
        try {
            // insert
            SchoolDAOImpl dao = new SchoolDAOImpl(getConn());
            School obj = insert(getConn());

            // update
            String tempsite = TestUtil.random_String();
            String tempstreet = TestUtil.random_String();
            String tempzip = TestUtil.random_String();
            String tempcity = TestUtil.random_String();

            obj.setSite(tempsite);
            obj.setStreet(tempstreet);
            obj.setZip(tempzip);
            obj.setCity(tempcity);
            dao.update(obj);

            // verify results
            obj = dao.getByPrimaryKey(obj.getIpaddress());

            assertEquals(tempsite, obj.getSite());
            assertEquals(tempstreet, obj.getStreet());
            assertEquals(tempzip, obj.getZip());
            assertEquals(tempcity, obj.getCity());
            getConn().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    protected static School insert(Connection conn) throws Exception {
        School obj = new School();
        SchoolDAOImpl dao = new SchoolDAOImpl(conn);

        obj.setIpaddress(TestUtil.random_String());
        obj.setSite(TestUtil.random_String());
        obj.setStreet(TestUtil.random_String());
        obj.setZip(TestUtil.random_String());
        obj.setCity(TestUtil.random_String());

        dao.insert(obj);

        return obj;
    }

    private Connection getConn() {
        return conn;
    }
}