package main.be.leerstad.eindwerk.business;

import main.be.leerstad.eindwerk.model.Interaction;
import main.be.leerstad.eindwerk.model.LogFile;

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
