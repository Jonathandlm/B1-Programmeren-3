package be.leerstad.eindwerk.main.business;

import be.leerstad.eindwerk.main.model.LogFile;
import be.leerstad.eindwerk.main.model.Visit;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VisitParser extends Parser {
    private static final Logger LOG = Logger.getLogger(VisitParser.class.getName());

    public VisitParser() {
        super.REGEX = "^((?:(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.){3}" +
                "(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])) " +   // Group 1: IP
                "(\\S+) " +                                             // Group 2: username
                "(\\S+) " +                                             // Group 3: remote user
                "\\[([\\w:/]+\\s[+\\-]\\d{4})\\] " +                    // Group 4: date and time
                "\"(\\S+ \\S+ \\S+)\" " +                               // Group 5: request
                "(\\d{3}) " +                                           // Group 6: status
                "(\\d+)";                                               // Group 7: transferred bytes
        super.PATTERN = Pattern.compile(REGEX);
    }

    @Override
    public void parseLogFile(File file) {
        String fileName = file.getName();
        if(isValidLogFile(fileName)) {
            // TODO: Sessions zonder ip-adres en zonder user negeren
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {

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

        // TODO: overload Model constructors
        // m.group(i) als parameters in overladen constructor
        return new Visit();
    }
}
