package be.leerstad.eindwerk.business.parser;

import be.leerstad.eindwerk.business.cache.SiteCache;
import be.leerstad.eindwerk.business.cache.UserCache;
import be.leerstad.eindwerk.model.Logfile;
import be.leerstad.eindwerk.model.Session;
import be.leerstad.eindwerk.model.Site;
import be.leerstad.eindwerk.model.User;
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

public class SessionParserTest {

    private SessionParser sessionParser;
    private Path path = Paths.get(System.getProperty("user.dir"),"input","ProxyLog_2017-01-17.log");
    private File file = new File("ProxyLog_2017-01-17.log");
    private User user;
    private Site site;

    @Before
    public void init() {
        sessionParser = new SessionParser();
    }

    @Test
    public void testParseLogfile() {
        user = UserCache.getInstance().getUser("HKJ");
        site = SiteCache.getInstance().getSite("vacature.com");
        List<Session> sessions = sessionParser.parseLogfile(path.toFile());
        assertEquals(6, sessions.size());
        Session firstSession = new Session(sessionParser.getLogfile(),"10.120.230.78",
                LocalTime.parse("08:29:38"),46437, user, site);
        assertEquals(firstSession, sessions.get(0));

        // Wrong path
        sessions = sessionParser.parseLogfile(file);
        assertTrue(sessions.isEmpty());
    }

    @Test
    public void testParseLogLine() {
        Logfile logfile = new Logfile("ProxyLog_2016-11-04.log");
        user = UserCache.getInstance().getUser("HKJ");
        site = SiteCache.getInstance().getSite("microsoft.com");
        sessionParser.setLogfile(logfile);
        assertEquals(sessionParser.parseLogLine("10.120.230.172\tDOM1/HKJ\t-\t2016-11-04\t08:24:49\t-\t-\t10.120.12.16" +
                "\t-\t80\t0\t561.0\t1266\thttp\t-\thttp://www.microsoft.com/\tUpstream\t304\t"),
                new Session(logfile, "10.120.230.172", LocalTime.of(8,24,49), 1827,
                        user, site));

        assertNull(sessionParser.parseLogLine(""));
        // IpAddress incomplete
        assertNull(sessionParser.parseLogLine(".120.230.172\tDOM1/HKJ\t-\t2016-11-04\t08:24:49\t-\t-\t10.120.12.16" +
                "\t-\t80\t0\t561.0\t1266\thttp\t-\thttp://www.microsoft.com/\tUpstream\t304\t"));
        // Incorrect username
        assertNull(sessionParser.parseLogLine("10.120.230.172\tDOM1/HAKA\t-\t2016-11-04\t08:24:49\t-\t-\t10.120.12.16" +
                "\t-\t80\t0\t561.0\t1266\thttp\t-\thttp://www.microsoft.com/\tUpstream\t304\t"));
        // Incorrect time
        assertNull(sessionParser.parseLogLine("10.120.230.172\tDOM1/HKJ\t-\t2016-11-04\t25:24:49\t-\t-\t10.120.12.16" +
                "\t-\t80\t0\t561.0\t1266\thttp\t-\thttp://www.microsoft.com/\tUpstream\t304\t"));
        // Incorrect IpAddress
        assertNull(sessionParser.parseLogLine("10.120.230.172\tDOM1/HKJ\t-\t2016-11-04\t08:24:49\t-\t-\t10.320.12.16" +
                "\t-\t80\t0\t561.0\t1266\thttp\t-\thttp://www.microsoft.com/\tUpstream\t304\t"));
        // Incorrect url
        assertNull(sessionParser.parseLogLine("10.120.230.172\tDOM1/HKJ\t-\t2016-11-04\t08:24:49\t-\t-\t10.120.12.16" +
                "\t-\t80\t0\t561.0\t1266\thttp\t-\thttp://www.microsoft;com/\tUpstream\t304\t"));
    }
}
