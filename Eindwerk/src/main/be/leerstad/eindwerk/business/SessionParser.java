package main.be.leerstad.eindwerk.business;

import main.be.leerstad.eindwerk.model.Session;
import main.be.leerstad.eindwerk.model.Interaction;
import main.be.leerstad.eindwerk.model.LogFile;
import main.be.leerstad.eindwerk.utils.regex;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SessionParser extends Parser{
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
                "(?:0|\\S{4,11})\\t" +                                          // No group: Object Source
                "(?:\\d{1,5})";
        super.PATTERN = Pattern.compile(REGEX);
    }

    @Override
    public void parseLogFile(File file) {
        String fileName = file.getName();
        if(isValidLogFile(fileName)) {
            // Ignore Sessions without ip-address and without user (both "-")
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String logLine;
                Session session;
                List<Interaction> sessionList = getLogFile().getInteractions();
                while ((logLine = br.readLine()) != null) {
                    session = parseLogLine(logLine);
                    if (session == null) continue;
                    if ((session.getUserId().equals("-")) && (session.getIpAddress().equals("-"))) continue;
                    boolean isNewSession = true;
                    for (Interaction i : sessionList) {
                        if (i.equals(session)) {
                            Session s = (Session) i;
                            s.add(session);
                            isNewSession = false;
                        }
                    }
                    if (isNewSession) sessionList.add(session);
                }
                getLogFile().setInteractions(sessionList);
            } catch (IOException e) {
                LOG.log(Level.ERROR, "Unable to read " + fileName, e);
            }
        }
        else {
            LOG.log(Level.ERROR, "Unable to parse " + fileName);
        }
    }

    @Override
    public boolean isValidLogFile(String fileName) {
        int positionUnderscore = fileName.lastIndexOf('_');
        int positionDot = fileName.lastIndexOf('.');
        if ((positionUnderscore < 0) || (positionDot < 0)) {
            LOG.log(Level.ERROR, fileName + " is not a valid Session Logfile filename.");
            return false;
        }

        String fileType = fileName.substring(0, positionUnderscore);
        String fileDate = fileName.substring(positionUnderscore + 1, positionDot);
        String fileExtension = fileName.substring(positionDot + 1);

        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);      // Checks if month and day are in valid ranges
        try {
            sdf.parse(fileDate);
        } catch (ParseException e) {
            LOG.log(Level.ERROR, fileName + " - Filename does not contain a valid date (" + dateFormat + ").");
            return false;
        }

        if (fileType.equals("ProxyLog") && fileExtension.equals("log")) {
            setLogFile(new LogFile(fileName, Date.valueOf(fileDate)));
            return true;
        }
        LOG.log(Level.ERROR, fileName + " is not a valid Session Logfile filename.");
        return false;
    }

    @Override
    public Session parseLogLine(String logline) {
        Matcher m = PATTERN.matcher(logline);

        if (!m.find()) {
            LOG.log(Level.ERROR, "Cannot parse logline: " + logline);
            throw new RuntimeException("Error parsing logline");
        }

        Session session;
        try {
            session = new Session(getLogFile(), m.group(1), Time.valueOf(m.group(3)),
                    new Integer(m.group(5)) + new Integer(m.group(6)), m.group(2),
                    regex.getDomainName(m.group(7)));
        } catch (URISyntaxException | NullPointerException e) {
            LOG.log(Level.ERROR, "Cannot parse URL: " + m.group(7));
            session = null;
        }
        return session;
    }

}
