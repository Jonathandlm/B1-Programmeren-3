package be.leerstad.eindwerk.business;

import be.leerstad.eindwerk.model.Interaction;
import be.leerstad.eindwerk.model.Session;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SessionParserTest {

    private SessionParser sessionParser;
    private Path path = Paths.get(System.getProperty("user.dir"),"input","ProxyLog_2017-01-17.log");

    @Before
    public void init() {
        sessionParser = new SessionParser();
    }

    @Test
    public void testParseLogFile() {
        sessionParser.parseLogFile(path.toFile());
        List<Interaction> sessions = sessionParser.getLogfile().getInteractions();
        assertEquals(6, sessions.size());
        Session firstSession = new Session(sessionParser.getLogfile(),"10.120.230.78",
                LocalTime.parse("08:29:38"),46437,"HKJ","vacature.com");
        assertEquals(firstSession, sessions.get(0));
    }

    //TODO: complete test
    @Test
    public void testParseLogLine() {

    }
}
