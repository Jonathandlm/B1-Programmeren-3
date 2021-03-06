// Generated by DB Solo 5.2.1 on Feb 14, 2017 at 9:31:39 PM
package be.leerstad.eindwerk.test;

import be.leerstad.eindwerk.User;
import be.leerstad.eindwerk.jdbc.UserDAOImpl;

import junit.framework.TestCase;

import java.sql.Connection;


public class UserDAOImplTest extends TestCase {
    private Connection conn = null;

    protected void setUp() throws Exception {
        conn = TestUtil.getConnection();
    }

    protected void tearDown() throws Exception {
        TestUtil.closeConnection(conn);
        conn = null;
    }

    public void testSelect() {
        UserDAOImpl obj = new UserDAOImpl(getConn());

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
            UserDAOImpl dao = new UserDAOImpl(getConn());

            User obj = insert(getConn());
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
            UserDAOImpl dao = new UserDAOImpl(getConn());
            User obj = insert(getConn());

            assertTrue(dao.getByName(obj.getName()).size() > 0);
            assertTrue(dao.getByFirstname(obj.getFirstname()).size() > 0);
            assertTrue(dao.getByCat(obj.getCat()).size() > 0);
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
            UserDAOImpl dao = new UserDAOImpl(getConn());

            int before = dao.selectAll().size();
            User obj = insert(getConn());
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
            UserDAOImpl dao = new UserDAOImpl(getConn());
            User obj = insert(getConn());

            // update
            String tempname = TestUtil.random_String();
            String tempfirstname = TestUtil.random_String();
            String tempcat = TestUtil.random_String();

            obj.setName(tempname);
            obj.setFirstname(tempfirstname);
            obj.setCat(tempcat);
            dao.update(obj);

            // verify results
            obj = dao.getByPrimaryKey(obj.getUserid());

            assertEquals(tempname, obj.getName());
            assertEquals(tempfirstname, obj.getFirstname());
            assertEquals(tempcat, obj.getCat());
            getConn().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    protected static User insert(Connection conn) throws Exception {
        User obj = new User();
        UserDAOImpl dao = new UserDAOImpl(conn);

        obj.setUserid(TestUtil.random_String());
        obj.setName(TestUtil.random_String());
        obj.setFirstname(TestUtil.random_String());
        obj.setCat(TestUtil.random_String());

        dao.insert(obj);

        return obj;
    }

    private Connection getConn() {
        return conn;
    }
}
