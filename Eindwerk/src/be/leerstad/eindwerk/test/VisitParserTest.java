package be.leerstad.eindwerk.test;

import be.leerstad.eindwerk.main.business.VisitParser;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VisitParserTest {
    private VisitParser visitParser;
    private Path path = Paths.get(System.getProperty("user.dir"),"input","localhost_access_log.2017-01-07.txt");

    @Before
    public void init() {
        visitParser = new VisitParser();
    }

    @Test
    public void testIsValidLogFile() {
        assertTrue(visitParser.isValidLogFile(path.getFileName().toString()));
        assertTrue(visitParser.isValidLogFile("localhost_access_log.2017-01-07.txt"));
        assertFalse(visitParser.isValidLogFile("Localhost_access_log.2017-01-07.txt"));
        assertFalse(visitParser.isValidLogFile("localhost_access_log.2017-01-32.txt"));
        assertFalse(visitParser.isValidLogFile("localhost_access_log.2017 01 07.txt"));
        assertFalse(visitParser.isValidLogFile("localhost_access_log.2017-01-07.log"));
        assertFalse(visitParser.isValidLogFile("localhost_access_log.log.2017-01-07.log"));
    }

    @Test
    public void testParseLogFile() {
        /*visitParser.parseLogFile(path.toFile());
        List<Interaction> sessions = sessionParser.getLogFile().getInteractions();
        assertEquals(6, sessions.size());
        Session firstSession = new Session(sessionParser.getLogFile(),"10.120.230.78", new Time(30578000),
                46437,"HKJ","vacature.com");
        assertEquals(firstSession, sessions.get(0));*/
    }

}
