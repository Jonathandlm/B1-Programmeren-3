package be.leerstad.eindwerk.business.parser;

import be.leerstad.eindwerk.business.cache.SchoolCache;
import be.leerstad.eindwerk.business.cache.SiteApplicationCache;
import be.leerstad.eindwerk.model.Logfile;
import be.leerstad.eindwerk.model.School;
import be.leerstad.eindwerk.model.SiteApplication;
import be.leerstad.eindwerk.model.Visit;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class VisitParserTest {

    private VisitParser visitParser;
    private Path path = Paths.get(System.getProperty("user.dir"),"input","localhost_access_log.2016-12-06.txt");
    private File file = new File("localhost_access_log.2016-12-06.txt");
    private School school;
    private SiteApplication siteApplication;

    @Before
    public void init() {
        visitParser = new VisitParser();
        school = SchoolCache.getInstance().getSchool("10.120.c.d");
        siteApplication = SiteApplicationCache.getInstance().getSiteApplication("ELO");
    }

    @Test
    public void testParseLogfile() {
        List<Visit> visits = visitParser.parseLogfile(path.toFile());
        assertEquals(117, visits.size());
        Visit firstVisit = new Visit(visitParser.getLogfile(), "10.120.230.53", LocalTime.parse("07:22:29"), 24222,
                "-", siteApplication, school);
        assertEquals(visits.get(0), firstVisit);
        assertEquals(visits.get(0).getLogfile(), firstVisit.getLogfile());
        assertEquals(visits.get(0).getIpAddress(), firstVisit.getIpAddress());
        assertEquals(visits.get(0).getTime(), firstVisit.getTime());
        assertEquals(visits.get(0).getTransferredBytes(), firstVisit.getTransferredBytes());
        assertEquals(visits.get(0).getTotalTimeInSec(), new Integer(3));
        assertEquals(visits.get(0).getNumberOfRequests(), new Integer(2));
        assertEquals(visits.get(0).getUser(), firstVisit.getUser());
        assertEquals(visits.get(0).getSiteApplication(), firstVisit.getSiteApplication());
        assertEquals(visits.get(0).getSchool(), firstVisit.getSchool());

        // Wrong path
        visits = visitParser.parseLogfile(file);
        assertTrue(visits.isEmpty());
    }

    @Test
    public void testParseLogLine() {
        Logfile logfile = new Logfile("localhost_access_log.2016-12-06.txt");
        visitParser.setLogfile(logfile);
        Visit visit = visitParser.parseLogLine("10.120.230.53 - - [06/Dec/2016:07:22:29 1000] \"GET /ELO/tiles-config_1_1.dtd HTTP/1.1\" 200 12111");
        assertEquals(visit.getLogfile(), logfile);
        assertEquals(visit.getIpAddress(),"10.120.230.53");
        assertEquals(visit.getTime(), LocalTime.parse("07:22:29"));
        assertEquals(visit.getTotalTimeInSec(), Integer.valueOf(1));
        assertEquals(visit.getTransferredBytes(), Integer.valueOf(12111));
        assertEquals(visit.getNumberOfRequests(), Integer.valueOf(1));
        assertEquals(visit.getUser(), "-");
        assertEquals(visit.getSiteApplication(), siteApplication);
        assertEquals(visit.getSchool(), school);

        assertNull(visitParser.parseLogLine(""));
        assertNull(visitParser.parseLogLine("120.230.53 - - [06/Dec/2016:07:22:29 1000] \"GET /ELO/ HTTP/1.1\" 200 12111"));
        assertNull(visitParser.parseLogLine("999.120.230.53 - - [06/Dec/2016:07:22:29 1000] \"GET /ELO/ HTTP/1.1\" 200 12111"));
        assertNull(visitParser.parseLogLine("10.120.230.53 - - [06/Dec/2016:99:22:29 1000] \"GET /ELO/ HTTP/1.1\" 200 12111"));
        assertNull(visitParser.parseLogLine("10.120.230.53 - - [06/Dec/2016:07:22:29 1000] \"\" 200 12111"));
        assertNull(visitParser.parseLogLine("10.120.230.53 - - [06/Dec/2016:07:22:29 1000] \"GET /ELO/ HTTP/1.1\" 9000 12111"));
    }

}
