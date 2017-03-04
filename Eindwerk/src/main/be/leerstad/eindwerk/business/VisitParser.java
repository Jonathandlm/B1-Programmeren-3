package main.be.leerstad.eindwerk.business;

import main.be.leerstad.eindwerk.model.Visit;
import main.be.leerstad.eindwerk.model.Interaction;
import main.be.leerstad.eindwerk.model.LogFile;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VisitParser extends Parser {
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
        if(isValidLogFile(fileName)) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String logLine;
                Visit visit;
                List<Interaction> visitList = getLogFile().getInteractions();
                while ((logLine = br.readLine()) != null) {
                    visit = parseLogLine(logLine);
                    if (visit == null) continue;
                    boolean isNewSession = true;
                    for (Interaction i : visitList) {
                        if (i.equals(visit)) {
                            Visit s = (Visit) i;
                            s.add(visit);
                            isNewSession = false;
                        }
                    }
                    if (isNewSession) visitList.add(visit);
                }
                getLogFile().setInteractions(visitList);
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
        int positionFirstDot = fileName.indexOf('.');
        int positionLastDot = fileName.lastIndexOf('.');
        if (positionFirstDot == positionLastDot) {
            LOG.log(Level.ERROR, fileName + " is not a valid Visit Logfile filename.");
            return false;
        }

        String fileType = fileName.substring(0, positionFirstDot);
        String fileDate = fileName.substring(positionFirstDot + 1, positionLastDot);
        String fileExtension = fileName.substring(positionLastDot + 1);

        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);      // Checks if month and day are in valid ranges
        try {
            sdf.parse(fileDate);
        } catch (ParseException e) {
            LOG.log(Level.ERROR, fileName + " - Filename does not contain a valid date (" + dateFormat + ").");
            return false;
        }

        if (fileType.equals("localhost_access_log") && fileExtension.equals("txt")) {
            setLogFile(new LogFile(fileName, Date.valueOf(fileDate)));
            return true;
        }
        LOG.log(Level.ERROR, fileName + " is not a valid Visit Logfile filename.");
        return false;
    }

    @Override
    public Visit parseLogLine(String logline){
        Matcher m = PATTERN.matcher(logline);

        if (!m.find()) {
            LOG.log(Level.ERROR, "Cannot parse logline: " + logline);
            throw new RuntimeException("Error parsing logline");
        }

        Visit visit;
        try {
            visit = new Visit(getLogFile(), m.group(1), Time.valueOf(m.group(5)),
                    (m.group(7).equals("-") ? 0 : new Integer(m.group(7))),
                    m.group(3), m.group(6), m.group(2));
        } catch (NullPointerException e) {
            LOG.log(Level.ERROR, "Cannot parse URL: " + m.group(7));
            visit = null;
        }
        return visit;
    }
}
