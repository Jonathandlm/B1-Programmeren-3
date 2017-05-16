package be.leerstad.eindwerk.business;

import be.leerstad.eindwerk.model.Interaction;
import be.leerstad.eindwerk.model.Logfile;
import be.leerstad.eindwerk.model.Session;
import be.leerstad.eindwerk.utils.RegexUtil;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SessionParser extends Parser<Session> {

    private static final Logger LOG = Logger.getLogger(SessionParser.class.getName());

    public SessionParser() {
        super.REGEX = "^((?:(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.){3}" +
                "(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9]))\\t" +         // Group 1:  Client IP
                "(?:DOM\\d\\/([A-Z]{3}))\\t" +                                  // Group 2:  Client UserName
                ".+\\t" +                                                       // No group: Client Agent
                "(?:\\d{4}-\\d{2}-\\d{2})\\t" +                                 // No group: Log Date
                "(\\d{2}:\\d{2}:\\d{2})\\t" +                                   // Group 3:  Log Time
                ".+\\t" +                                                       // No group: Server Name
                ".+\\t" +                                                       // No group: Referring Server
                "((?:(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.){3}" +
                "(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])|-)\\t" +       // Group 4:  Destination IP
                "(?:(?:(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.){3}" +
                "(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])|-)\\t" +       // No group: Destination Host Name
                "\\d{1,5}\\t" +                                                 // No group: Destination Port
                "\\d{1,}\\t" +                                                  // No group: Processing Time
                "(?:(\\d{1,})\\.0|-)\\t" +                                      // Group 5:  Bytes Received
                "(\\d{1,}|-)\\t" +                                              // Group 6:  Bytes Sent
                ".+\\t" +                                                       // No group: Protocol
                "(?:OPTIONS|GET|HEAD|POST|PUT|DELETE|TRACE|CONNECT|-)\\t" +     // No group: HTTP Method
                "(.+)\\t" +                                                     // Group 7:  URL
                "(?:0|\\S{4,11}|-)\\t" +                                        // No group: Object Source
                "(?:\\d{1,5})";
        super.PATTERN = Pattern.compile(REGEX);
    }

    @Override
    public Logfile parseLogFile(File file) {
        String fileName = file.getName();
        setLogfile(new Logfile(fileName));
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String logLine;
            Session session;
            List<Interaction> sessionList = getLogfile().getInteractions();
            while ((logLine = br.readLine()) != null) {
                session = parseLogLine(logLine);
                // Ignore invalid loglines
                if (session == null) continue;
                // Ignore Sessions without ip-address and without user (both "-")
                if ((session.getUserId().equals("-")) && (session.getIpAddress().equals("-"))) continue;
                // Equal sessions are put together, i.e. sessions within time limit
                if (sessionList.contains(session)) {
                    int index = sessionList.indexOf(session);
                    Session s = (Session) sessionList.get(index);
                    s.concatenate(session);
                } else {
                    sessionList.add(session);
                }
            }
            getLogfile().setInteractions(sessionList);
            LOG.log(Level.DEBUG, "Parsed logfile " + fileName +": " + sessionList.size() + " sessions.");
        } catch (IOException e) {
            LOG.log(Level.ERROR, "Unable to read " + fileName, e);
        }
        return getLogfile();
    }

    @Override
    public Session parseLogLine(String logline) {
        Matcher m = PATTERN.matcher(logline);
        Session session;

        if (!m.find()) {
            LOG.log(Level.ERROR, "Cannot parse logline: " + logline);
            throw new RuntimeException("Error parsing logline");
        }

        try {
            session = new Session(getLogfile(), m.group(1), LocalTime.parse(m.group(3)),
                    new Integer(m.group(5)) + new Integer(m.group(6)), m.group(2),
                    RegexUtil.getDomainName(m.group(7)));
        } catch (URISyntaxException | NullPointerException e) {
            LOG.log(Level.ERROR, "Cannot parse URL: " + m.group(7));
            session = null;
        }

        return session;
    }

}
