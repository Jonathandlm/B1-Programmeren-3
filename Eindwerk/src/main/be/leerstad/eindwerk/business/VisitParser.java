package be.leerstad.eindwerk.business;

import be.leerstad.eindwerk.model.*;
import be.leerstad.eindwerk.utils.RegexUtil;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VisitParser extends Parser<Visit> {

    private static final Logger LOG = Logger.getLogger(VisitParser.class.getName());

    public VisitParser() {
        super.REGEX = "^((?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\." +
                "(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\." +
                "(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\." +
                "(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])) " +                      // Group 1: IP address
                "(\\S+) " +                                                                // Group 2: username
                "(\\S+) " +                                                                // Group 3: remote user
                "\\[[\\w/]+:((?:2[0-3]|[0-1][0-9]):[0-5][0-9]:[0-5][0-9]) -?\\d{4}\\] " +  // Group 4: time
                "(?:\\\"\\S+ (\\S*) HTTP\\/\\d\\.\\d\\\") " +                              // Group 5: site-app
                "(?:\\d{3}) " +                                                            // No group: status
                "(\\d+|-)";                                                                // Group 6: transferred bytes
        super.PATTERN = Pattern.compile(REGEX);
    }

    @Override
    public List<Visit> parseLogFile(File file) {
        List<Visit> visits = new ArrayList<>();
        String fileName = file.getName();
        setLogfile(new Logfile(fileName));
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String logLine;
            Visit visit;

            while ((logLine = br.readLine()) != null) {
                visit = parseLogLine(logLine);
                // Ignore invalid loglines
                if (visit == null) continue;
                // Equal visits are put together, i.e. visits within time limit
                if (visits.contains(visit)) {
                    int index = visits.indexOf(visit);
                    Visit v = visits.get(index);
                    v.concatenate(visit);
                } else visits.add(visit);
            }
            LOG.log(Level.DEBUG, "Parsed logfile " + fileName +": " + visits.size() + " visits.");

        } catch (IOException e) {
            LOG.log(Level.ERROR, "Unable to read " + fileName, e);
        }
        return visits;
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
            visit = new Visit(getLogfile(), m.group(1), LocalTime.parse(m.group(4)),
                    (m.group(6).equals("-") ? 0 : new Integer(m.group(6))),
                    m.group(2),
                    getSiteApplicationFromCache(RegexUtil.getApplication(m.group(5))),
                    getSchoolFromCache(m.group(1)));

        } catch (NullPointerException e) {
            LOG.log(Level.WARN, "Cannot parse URL: " + m.group(7));
            visit = null;
        }

        return visit;
    }

    private School getSchoolFromCache(String ipAddress) {
        return LogAnalyser.getInstance().getSchoolCache().stream()
                .filter(school -> school.getIpAddress().equals(ipAddress))
                .findFirst()
                .orElse(new School(ipAddress));
    }

    private SiteApplication getSiteApplicationFromCache(String application) {
        List<SiteApplication> cache = LogAnalyser.getInstance().getSiteApplicationCache();
        int newId = cache.stream().map(SiteApplication::getApplicationId).max(Integer::compareTo).orElse(0) + 1;
        return cache.stream()
                .filter(siteApplication -> siteApplication.getApplication().equals(application))
                .findFirst()
                .orElse(updateSiteApplicationCache(new SiteApplication(newId, application)));
    }

    private SiteApplication updateSiteApplicationCache(SiteApplication siteApplication) {
        LogAnalyser.getInstance().getSiteApplicationCache().add(siteApplication);
        return siteApplication;
    }

}
