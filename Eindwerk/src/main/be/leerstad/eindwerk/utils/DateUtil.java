package be.leerstad.eindwerk.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {

    private static final Logger LOG = Logger.getLogger(DateUtil.class.getName());
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static LocalDate parse(String fileName) {
        final String REGEX = "(\\d{4}-\\d{2}-\\d{2})";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(fileName);
        System.out.println(matcher.group(0));
        String dateFormat = "yyyy-MM-dd";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
        try {
            return LocalDate.parse(matcher.group(0), dtf);
        } catch (DateTimeParseException e) {
            LOG.log(Level.ERROR, matcher.group(0) + " is not a valid date or has incorrect format (" + dateFormat + ").");
            return null;
        }
    }

    public static LocalDate check(String fileDate) {
        String dateFormat = "yyyy-MM-dd";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
        try {
            return LocalDate.parse(fileDate, dtf);
        } catch (DateTimeParseException e) {
            LOG.log(Level.DEBUG, fileDate + " is not a valid date or has incorrect format (" + dateFormat + ").");
            return null;
        }
    }

    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }
}
