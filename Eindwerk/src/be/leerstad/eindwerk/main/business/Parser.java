package be.leerstad.eindwerk.main.business;

import be.leerstad.eindwerk.main.model.Interaction;
import be.leerstad.eindwerk.main.model.LogFile;

import java.io.File;
import java.util.regex.Pattern;

public abstract class Parser {
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
    public abstract boolean isValidLogFile(String fileName);
    public abstract Interaction parseLogLine(String logline);
}
