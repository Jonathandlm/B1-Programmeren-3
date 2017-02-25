// Generated by DB Solo 5.2.1 on Feb 14, 2017 at 9:31:39 PM
package be.leerstad.eindwerk.test;

import org.dom4j.Element;

import org.dom4j.tree.DefaultElement;

import org.dom4j.util.NodeComparator;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;


public class TestUtil {
    public static Connection getConnection() throws Exception {
        Properties props = new Properties();

        props.put("user", "cvo");
        props.put("password", "REPLACE_WITH_REAL_PASSWORD");
        Class.forName("com.mysql.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", props);

        conn.setCatalog("loganalyser");
        conn.setAutoCommit(false);

        return conn;
    }

    public static void closeConnection(Connection conn)
        throws Exception {
        if (null != conn) {
            conn.close();
        }
    }

    //
    // Comparison
    //
    public static boolean equals(Element e1, Element e2)
        throws Exception {
        NodeComparator comp = new NodeComparator();

        return comp.compare(e1, e2) == 0;
    }

    public static boolean equals(Blob b1, Blob b2) throws Exception {
        if (b1.length() != b2.length()) {
            return false;
        }

        return Arrays.equals(b1.getBytes(1, (int) b1.length()), b2.getBytes(1, (int) b2.length()));
    }

    public static boolean equals(Clob c1, Clob c2) throws Exception {
        if (c1.length() != c2.length()) {
            return false;
        }

        return c1.getSubString(1, (int) c1.length()).equals(c2.getSubString(1, (int) c2.length()));
    }

    //
    // Methods for generating random values
    //
    public static Element random_Element() {
        return new DefaultElement(random_String());
    }

    public static String random_XMLString() {
        String elem = random_String(10);

        return "<" + elem + "/>";
    }

    public static Blob random_Blob() throws SerialException, SQLException {
        return new SerialBlob(random_byteArray(5000));
    }

    public static Clob random_Clob() throws SerialException, SQLException {
        return new SerialClob(random_String(5000).toCharArray());
    }

    public static boolean random_boolean() {
        return (random_short() % 2) == 1;
    }

    public static Boolean random_Boolean() {
        return random_boolean() ? Boolean.TRUE : Boolean.FALSE;
    }

    public static byte[] random_byteArray() {
        return random_byteArray(1);
    }

    public static byte[] random_byteArray(int len) {
        byte[] ret = new byte[len];

        for (int i = 0; i < len; i++)
            ret[i] = random_byte();

        return ret;
    }

    public static int random_int() {
        return (int) (Math.random() * Integer.MAX_VALUE);
    }

    public static Integer random_Integer() {
        return new Integer(random_int());
    }

    public static long random_long() {
        return (long) (Math.random() * Long.MAX_VALUE);
    }

    public static Long random_Long() {
        return new Long(random_long());
    }

    public static float random_float() {
        return (float) random_int();
    }

    public static double random_double() {
        return (double) random_int();
    }

    public static Double random_Double() {
        return new Double(random_double());
    }

    public static byte random_byte() {
        return (byte) random_int();
    }

    public static Byte random_Byte() {
        return new Byte(random_byte());
    }

    public static short random_short() {
        return (short) random_int();
    }

    public static Short random_Short() {
        return new Short(random_short());
    }

    public static BigInteger random_BigInteger() {
        return new BigInteger(random_long() + "");
    }

    public static BigDecimal random_BigDecimal() {
        return new BigDecimal(random_Double());
    }

    public static Date random_Date() {
        Calendar now = new GregorianCalendar();

        now.add(Calendar.DAY_OF_MONTH, random_int() % 1000);
        now.add(Calendar.HOUR, random_int() % 1000);
        now.add(Calendar.MINUTE, random_int() % 1000);
        now.add(Calendar.SECOND, random_int() % 1000);
        now.set(Calendar.MILLISECOND, 0);

        return now.getTime();
    }

    public static String random_String() {
        return random_String(1);
    }

    public static String random_String(int len) {
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < len; i++) {
            char temp = (char) ('a' + (random_int() % 26));

            buf.append(temp);
        }

        return buf.toString();
    }
}
