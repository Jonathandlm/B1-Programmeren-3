package be.leerstad.eindwerk.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.regex.Pattern.quote;

public class RegexUtil {

    private static final Logger LOG = Logger.getLogger(RegexUtil.class.getName());

    public static String getDomainName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    public static String getApplication(String serverPath) {
        // Parameters after '?' are dropped with '?'
        Path path = Paths.get(serverPath.split("\\?")[0]);
        try {
            // If first part is a filename or empty String, return 'root', otherwise return first directory
            String app = path.getName(0).toString();
            return (app.contains(".") || app.equals("")) ? "root" : app;
        } catch (IllegalArgumentException iae) {
            // If no first part, return 'root'
            return "root";
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Cannot resolve site application from " + serverPath);
        }
        return null;
    }

    public static String getNetworkAddress(String ipAddress) {
        if (ipAddress == null || ipAddress.isEmpty()) {
            LOG.log(Level.ERROR, "IP Address cannot be null or empty: " + ipAddress);
            return null;
        }

        String[] octets = ipAddress.split(quote("."));
        if (octets.length != 4) {
            LOG.log(Level.ERROR, "Invalid IP Address: " + ipAddress);
            return null;
        }

        for (String octet : octets) {
            if (octet.equals("c") || octet.equals("d")) continue;
            short sh = Short.parseShort(octet);
            if (sh > 255 || sh < 0) {
                LOG.log(Level.ERROR, "Invalid IP Address: " + ipAddress);
                return null;
            }
        }

        octets[2] = "c";
        octets[3] = "d";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(octets[0]);
        for (int i = 1; i < 4; i++) {
            stringBuilder.append(".");
            stringBuilder.append(octets[i]);
        }

        return stringBuilder.toString();
    }

}
