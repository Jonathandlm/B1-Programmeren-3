package be.leerstad.eindwerk.business.parser;

import be.leerstad.eindwerk.model.Session;
import be.leerstad.eindwerk.model.Site;
import be.leerstad.eindwerk.model.User;
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
        List<Session> sessions = sessionParser.parseLogfile(path.toFile());
        assertEquals(6, sessions.size());
        Session firstSession = new Session(sessionParser.getLogfile(),"10.120.230.78",
                LocalTime.parse("08:29:38"),46437,new User("HKJ"),
                new Site(1,"vacature.com"));
        assertEquals(firstSession, sessions.get(0));
    }

    //TODO: complete test
    @Test
    public void testParseLogLine() {

    }
}
