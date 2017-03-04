package be.leerstad.eindwerk.business;

import main.be.leerstad.eindwerk.business.VisitParser;
import main.be.leerstad.eindwerk.model.Interaction;
import main.be.leerstad.eindwerk.model.Visit;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VisitParserTest {
    private VisitParser visitParser;
    private Path path = Paths.get(System.getProperty("user.dir"),"input","localhost_access_log.2016-12-06.txt");

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
        assertFalse(visitParser.isValidLogFile("localhost_access_log.log.2017-01-07.txt"));
    }

    @Test
    public void testParseLogLine() {
        Visit visit = visitParser.parseLogLine("10.120.230.53 - - [06/Dec/2016:07:22:29 1000] \"GET /ELO/tiles-config_1_1.dtd HTTP/1.1\" 200 12111");
        System.out.println(visit);
    }

    @Test
    public void testParseLogFile() {
        visitParser.parseLogFile(path.toFile());
        List<Interaction> visits = visitParser.getLogFile().getInteractions();
        visits.forEach(System.out::println);
        //assertEquals(6, sessions.size());
        //Session firstSession = new Session(sessionParser.getLogFile(),"10.120.230.78", new Time(30578000), 46437,"HKJ","vacature.com");
        //assertEquals(firstSession, sessions.get(0));
    }

}
