package be.leerstad.eindwerk.utils;

import org.apache.log4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;

public class RegexUtil {

    private static final Logger LOG = Logger.getLogger(RegexUtil.class.getName());

    public static String getDomainName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

}
