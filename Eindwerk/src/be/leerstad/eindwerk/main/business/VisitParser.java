package be.leerstad.eindwerk.main.business;

import be.leerstad.eindwerk.main.model.Visit;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VisitParser extends Parser {
    private static final Logger LOG = Logger.getLogger(VisitParser.class.getName());
    private static final String REGEX = "^((?:(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.){3}" +
            "(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])) " +   // Group 1: IP
            "(\\S+) " +                                             // Group 2: username
            "(\\S+) " +                                             // Group 3: remote user
            "\\[([\\w:/]+\\s[+\\-]\\d{4})\\] " +                    // Group 4: date and time
            "\"(\\S+ \\S+ \\S+)\" " +                               // Group 5: request
            "(\\d{3}) " +                                           // Group 6: status
            "(\\d+)";                                               // Group 7: transferred bytes
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    @Override
    public void parseLogFile(File file) {

    }

    @Override
    public boolean isValidLogFile(String fileName) {
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
