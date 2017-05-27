package be.leerstad.eindwerk.business.parser;

import be.leerstad.eindwerk.model.Visit;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class VisitParserTest {

    private VisitParser visitParser;
    private Path path = Paths.get(System.getProperty("user.dir"),"input","localhost_access_log.2016-12-06.txt");

    @Before
    public void init() {
        visitParser = new VisitParser();
    }

    @Test
    public void testParseLogLine() {
        Visit visit = visitParser.parseLogLine("10.120.230.53 - - [06/Dec/2016:07:22:29 1000] \"GET /ELO/tiles-config_1_1.dtd HTTP/1.1\" 200 12111");
        assertNull(visit.getLogfile());
        assertEquals(visit.getIpAddress(),"10.120.230.53");
        assertEquals(visit.getTime(), LocalTime.parse("07:22:29"));
        assertEquals(visit.getTotalTimeInSec(), Integer.valueOf(1));
        assertEquals(visit.getTransferredBytes(), Integer.valueOf(12111));
        assertEquals(visit.getNumberOfRequests(), Integer.valueOf(1));
        assertEquals(visit.getUser(), "-");
        assertEquals(visit.getSiteApplication().getApplication(), "ELO");
        assertEquals(visit.getSchool(), "10.120.c.d");
    }

    //TODO: complete test
    @Test
    public void testParseLogFile() {
        visitParser.parseLogfile(path.toFile());
        //List<Interaction> visits = visitParser.getLogfile().getInteractions();
        //visits.forEach(System.out::println);
        //assertEquals(6, sessions.size());
        //Session firstSession = new Session(sessionParser.getLogfile(),"10.120.230.78", Time.valueOf("07:22:29"), 46437,"HKJ","vacature.com");
        //assertEquals(firstSession, sessions.get(0));
    }

}
