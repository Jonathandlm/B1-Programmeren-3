package be.leerstad.eindwerk.business;

import be.leerstad.eindwerk.model.Interaction;
import be.leerstad.eindwerk.model.Logfile;
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
                "\\[[\\w/]+:((?:2[0-3]|[0-1][0-9]):[0-5][0-9]:[0-5][0-9]) -?\\d{4}\\] " +   // Group 5: time
                "(?:\\\"\\S+ \\/(ELO|HOI|\\S*)(?:\\/\\S*)? HTTP\\/\\d\\.\\d\\\") " +    // Group 6: site-app
                "(?:\\d{3}) " +                                                         // No group: status
                "(\\d+|-)";                                                             // Group 7: transferred bytes
        super.PATTERN = Pattern.compile(REGEX);
    }

    @Override
    public Logfile parseLogFile(File file) {
        String fileName = file.getName();
        setLogfile(new Logfile(fileName));
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String logLine;
            Visit visit;
            List<Interaction> visitList = getLogfile().getInteractions();

            while ((logLine = br.readLine()) != null) {
                visit = parseLogLine(logLine);
                // Ignore invalid loglines
                if (visit == null) continue;
                // Equal visits are put together, i.e. visits within time limit
                if (visitList.contains(visit)) {
                    int index = visitList.indexOf(visit);
                    Visit v = (Visit) visitList.get(index);
                    v.concatenate(visit);
                } else visitList.add(visit);
            }

            getLogfile().setInteractions(visitList);
            LOG.log(Level.DEBUG, "Parsed logfile " + fileName +": " + visitList.size() + " visits.");

        } catch (IOException e) {
            LOG.log(Level.ERROR, "Unable to read " + fileName, e);
        }
        return getLogfile();
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
            visit = new Visit(getLogfile(), m.group(1), LocalTime.parse(m.group(5)),
                    (m.group(7).equals("-") ? 0 : new Integer(m.group(7))),
                    m.group(3),
                    (m.group(6).equals("ELO") || m.group(6).equals("HOI") ? m.group(6) : "ROOT") ,
                    m.group(2));

        } catch (NullPointerException e) {
            LOG.log(Level.ERROR, "Cannot parse URL: " + m.group(7));
            visit = null;
        }

        return visit;
    }
}
