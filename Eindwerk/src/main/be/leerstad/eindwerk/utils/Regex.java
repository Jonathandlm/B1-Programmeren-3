package be.leerstad.eindwerk.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Regex {

    private static final Logger LOG = Logger.getLogger(Regex.class.getName());

    public static String getDomainName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    public static Date getLogFileDate(String fileDate) {
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);      // Checks if month and day are in valid ranges
        try {
            return sdf.parse(fileDate);
        } catch (ParseException e) {
            LOG.log(Level.ERROR, fileDate + " is not a valid date or has incorrect format (" + dateFormat + ").");
            return null;
        }
    }
}
