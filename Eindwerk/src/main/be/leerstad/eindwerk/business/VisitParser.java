package be.leerstad.eindwerk.business;

import be.leerstad.eindwerk.model.Interaction;
import be.leerstad.eindwerk.model.LogFile;
import be.leerstad.eindwerk.model.Visit;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VisitParser extends Parser<Visit> {

    private static final Logger LOG = Logger.getLogger(VisitParser.class.getName());

    public VisitParser() {
        super.REGEX = "^(((?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\." +
                "(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9]))\\." +                 // Group 2: School IP
                "(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\." +
                "(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])) " +                   // Group 1: IP address
                "(\\S+) " +                                                             // Group 3: username
                "(\\S+) " +                                                             // Group 4: remote user
                "\\[[\\w/]+:(2[0-3]|[0-1][0-9]:[0-5][0-9]:[0-5][0-9]) -?\\d{4}\\] " +   // Group 5: time
                "(?:\\\"\\S+ \\/(\\w+)(?:\\/\\S*)? HTTP\\/\\d\\.\\d\\\") " +               // Group 6: site-app
                "(?:\\d{3}) " +                                                         // No group: status
                "(\\d+|-)";                                                             // Group 7: transferred bytes
        super.PATTERN = Pattern.compile(REGEX);
    }

    @Override
    public void parseLogFile(File file) {
        String fileName = file.getName();
        setLogFile(new LogFile(fileName));
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String logLine;
            Visit visit;
            List<Interaction> visitList = getLogFile().getInteractions();

            while ((logLine = br.readLine()) != null) {
                visit = parseLogLine(logLine);
                if (visit == null) continue;
                if (visitList.contains(visit)) {
                    int index = visitList.indexOf(visit);
                    Visit v = (Visit) visitList.get(index);
                    v.concatenate(visit);
                } else visitList.add(visit);
            }

            getLogFile().setInteractions(visitList);

        } catch (IOException e) {
            LOG.log(Level.ERROR, "Unable to read " + fileName, e);
        }
    }

    @Override
    public Visit parseLogLine(String logline){
        Matcher m = PATTERN.matcher(logline);
        Visit visit;

        if (!m.find()) {
            LOG.log(Level.ERROR, "Cannot parse logline: " + logline);
            throw new RuntimeException("Error parsing logline");
        }

        try {
            visit = new Visit(getLogFile(), m.group(1), LocalTime.parse(m.group(5)),
                    (m.group(7).equals("-") ? 0 : new Integer(m.group(7))),
                    m.group(3), m.group(6), m.group(2));

        } catch (NullPointerException e) {
            LOG.log(Level.ERROR, "Cannot parse URL: " + m.group(7));
            visit = null;
        }

        return visit;
    }
}
