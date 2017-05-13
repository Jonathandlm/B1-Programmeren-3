package be.leerstad.eindwerk.business;

import be.leerstad.eindwerk.model.LogFile;

import java.io.File;
import java.util.regex.Pattern;

public abstract class Parser<T> {
    protected String REGEX;
    protected Pattern PATTERN;
    private LogFile logFile;

    public LogFile getLogFile() {
        return logFile;
    }
    public void setLogFile(LogFile logFile) {
        this.logFile = logFile;
    }

    public abstract void parseLogFile(File file);
    public abstract T parseLogLine(String logline);
}
