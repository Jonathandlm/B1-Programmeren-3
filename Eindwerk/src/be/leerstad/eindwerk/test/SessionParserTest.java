package be.leerstad.eindwerk.test;

import be.leerstad.eindwerk.main.business.SessionParser;

import be.leerstad.eindwerk.main.model.Session;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class SessionParserTest {
    private SessionParser sessionParser;
    private Path path = Paths.get(System.getProperty("user.dir"),"input","ProxyLog_2017-01-17.log");

    @Before
    public void init() {
        sessionParser = new SessionParser();
    }

    @Test
    public void testIsValidLogFile() {
        assertTrue(sessionParser.isValidLogFile(path.getFileName().toString()));
        assertTrue(sessionParser.isValidLogFile("ProxyLog_2016-12-05.log"));
        assertFalse(sessionParser.isValidLogFile("Proxylog_2016-12-05.log"));
        assertFalse(sessionParser.isValidLogFile("ProxyLog_2016-13-05.log"));
        assertFalse(sessionParser.isValidLogFile("ProxyLog_2016-12-05.txt"));
    }

    @Test
    public void testParseLogFile() {
        sessionParser.parseLogFile(path.toFile());
        // assert size interactions = 5 of zo
        List<Session> sessions = (List<Session>)sessionParser.getLogFile().getInteractions();
        sessions.stream().forEach(System.out::println);
        System.out.println(sessions.size());
    }

}
