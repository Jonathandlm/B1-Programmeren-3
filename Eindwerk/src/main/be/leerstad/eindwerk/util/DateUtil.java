package be.leerstad.eindwerk.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DateUtil {

    private static final Logger LOG = Logger.getLogger(DateUtil.class.getName());
    private static final String DATE_LOGFILE_PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter DATE_LOGFILE_FORMATTER = DateTimeFormatter.ofPattern(DATE_LOGFILE_PATTERN);
    private static final String DATE_GUI_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_GUI_FORMATTER = DateTimeFormatter.ofPattern(DATE_GUI_PATTERN);
    private static final String DATE_LOGFILE_REGEX = "(\\d{4}-\\d{2}-\\d{2})";
    private static final Pattern DATE_REGEX_PATTERN = Pattern.compile(DATE_LOGFILE_REGEX);

    private DateUtil() {
    }

    public static LocalDate parseLogfileDate(String fileName) {
        if (fileName == null) {
            LOG.log(Level.ERROR, "Filename is null.");
            return null;
        }

        Matcher matcher = DATE_REGEX_PATTERN.matcher(fileName);
        if (matcher.find()) {
            String date = matcher.group();
            try {
                return LocalDate.parse(date, DATE_LOGFILE_FORMATTER);
            } catch (DateTimeParseException e) {
                LOG.log(Level.ERROR, date + " is not a valid date or has incorrect format " +
                        "(" + DATE_LOGFILE_PATTERN + ").");
                return null;
            }
        } else {
            LOG.log(Level.ERROR, fileName + " has no valid date or has incorrect format " +
                    "(" + DATE_LOGFILE_PATTERN + ").");
            return null;
        }
    }

    public static LocalDate parseDate(String fileDate) {
        if (fileDate == null) {
            LOG.log(Level.ERROR, "Date is null.");
            return null;
        }

        try {
            return LocalDate.parse(fileDate, DATE_LOGFILE_FORMATTER);
        } catch (DateTimeParseException e) {
            LOG.log(Level.DEBUG, fileDate + " is not a valid date or has incorrect format " +
                    "(" + DATE_LOGFILE_PATTERN + ").");
            return null;
        }
    }

    public static String format(LocalDate date) {
        if (date == null) {
            LOG.log(Level.ERROR, "Date is null.");
            return null;
        }
        return DATE_GUI_FORMATTER.format(date);
    }

    public static String today() {
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("EEE d MMM yyyy", new Locale("nl", "BE"));
        return formatter.format(today);
    }
}
