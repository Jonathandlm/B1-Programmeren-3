package be.leerstad.eindwerk.business.parser;

import be.leerstad.eindwerk.business.parser.ParseFactory;
import be.leerstad.eindwerk.business.parser.Parser;
import be.leerstad.eindwerk.business.parser.SessionParser;
import be.leerstad.eindwerk.business.parser.VisitParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class ParseFactoryTest {

    private ParseFactory factory;

    @Before
    public void init() {
        factory = new ParseFactory();
    }

    @Test
    public void testGetType() {
        Parser sessionParser = factory.getType("ProxyLog_2017-01-17.log");
        Parser visitParser = factory.getType("localhost_access_log.2016-12-06.txt");
        Parser nullParser = factory.getType("Unknown.filetype");

        assertTrue(sessionParser instanceof SessionParser);
        assertTrue(visitParser instanceof VisitParser);
        assertNull(nullParser);
    }

    @Test
    public void testIsValidSessionFile() {
        assertTrue(factory.isValidSessionFile("ProxyLog_2016-12-05.log"));
        assertFalse(factory.isValidSessionFile("Proxylog_2016-12-05.log"));
        assertFalse(factory.isValidSessionFile("ProxyLog_2016-13-05.log"));
        assertFalse(factory.isValidSessionFile("ProxyLog_2016-12-05.txt"));
    }

    @Test
    public void testIsValidVisitFile() {
        assertTrue(factory.isValidVisitFile("localhost_access_log.2017-01-07.txt"));
        assertFalse(factory.isValidVisitFile("Localhost_access_log.2017-01-07.txt"));
        assertFalse(factory.isValidVisitFile("localhost_access_log.2017-01-32.txt"));
        assertFalse(factory.isValidVisitFile("localhost_access_log.2017 01 07.txt"));
        assertFalse(factory.isValidVisitFile("localhost_access_log.2017-01-07.log"));
        assertFalse(factory.isValidVisitFile("localhost_access_log.log.2017-01-07.txt"));
    }



}
