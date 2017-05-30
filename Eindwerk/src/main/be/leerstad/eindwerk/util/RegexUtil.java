package be.leerstad.eindwerk.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.regex.Pattern.quote;

public final class RegexUtil {

    private static final Logger LOG = Logger.getLogger(RegexUtil.class.getName());

    private RegexUtil() {
    }

    public static String getDomainName(String url) throws URISyntaxException {
        if (url.endsWith(":443")) {
            url = "https://" + url.substring(0, url.length() - 4);
        }
        URI uri = new URI(url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    public static String getApplication(String serverPath) {
        try {
            // Parameters after '?' are dropped with '?'
            Path path = Paths.get(serverPath.split("\\?")[0]);
            // If first part is a filename or empty String, return 'root', otherwise return first directory
            String app = path.getName(0).toString();
            return (app.contains(".") || app.equals("")) ? "root" : app;
        } catch (IllegalArgumentException e) {
            return "root"; // If no first part, return 'root'
        } catch (NullPointerException e) {
            LOG.log(Level.ERROR, "Cannot resolve site application from null");
            return null;
        }
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

        for (int i = 0; i < 4; i++) {
            if (i == 2 && octets[i].equals("c")) continue;
            if (i == 3 && octets[i].equals("d")) continue;
            try {
                short sh = Short.parseShort(octets[i]);
                if (sh > 255 || sh < 0) {
                    LOG.log(Level.ERROR, "Invalid IP Address: " + ipAddress);
                    return null;
                }
            } catch (NumberFormatException e) {
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