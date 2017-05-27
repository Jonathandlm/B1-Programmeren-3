package be.leerstad.eindwerk.business.parser;

import be.leerstad.eindwerk.util.DateUtil;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class ParseFactory {

    private static final Logger LOG = Logger.getLogger(ParseFactory.class.getName());

    public Parser getType(String logfile) {
        if (isValidSessionFile(logfile)) {
            return new SessionParser();
        } else if (isValidVisitFile(logfile)) {
            return new VisitParser();
        }
        LOG.log(Level.ERROR, "Unable to get parse type of " + logfile);
        return null;
    }

    protected boolean isValidSessionFile(String fileName) {
        int positionUnderscore = fileName.lastIndexOf('_');
        int positionDot = fileName.lastIndexOf('.');
        if ((positionUnderscore < 0) || (positionDot < 0)) {
            LOG.log(Level.ERROR, fileName + " is not a valid Session Logfile filename.");
            return false;
        }

        String fileType = fileName.substring(0, positionUnderscore);
        String fileDate = fileName.substring(positionUnderscore + 1, positionDot);
        String fileExtension = fileName.substring(positionDot + 1);

        if(DateUtil.parseDate(fileDate) == null) {
            return false;
        }

        if (fileType.equals("ProxyLog") && fileExtension.equals("log")) {
            LOG.log(Level.DEBUG, fileName + " is a valid Session Logfile filename.");
            return true;
        }

        LOG.log(Level.ERROR, fileName + " is not a valid Session Logfile filename.");
        return false;
    }

    protected boolean isValidVisitFile(String fileName) {
        int positionFirstDot = fileName.indexOf('.');
        int positionLastDot = fileName.lastIndexOf('.');
        if (positionFirstDot == positionLastDot) {
            LOG.log(Level.ERROR, fileName + " is not a valid Visit Logfile filename.");
            return false;
        }

        String fileType = fileName.substring(0, positionFirstDot);
        String fileDate = fileName.substring(positionFirstDot + 1, positionLastDot);
        String fileExtension = fileName.substring(positionLastDot + 1);

        if(DateUtil.parseDate(fileDate) == null) {
            return false;
        }

        if (fileType.equals("localhost_access_log") && fileExtension.equals("txt")) {
            LOG.log(Level.DEBUG, fileName + " is a valid Visit Logfile filename.");
            return true;
        }

        LOG.log(Level.ERROR, fileName + " is not a valid Visit Logfile filename.");
        return false;
    }
}
