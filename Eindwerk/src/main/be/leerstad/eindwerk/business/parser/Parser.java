package be.leerstad.eindwerk.business.parser;

import be.leerstad.eindwerk.model.Logfile;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

public abstract class Parser<T> {
    String REGEX;
    Pattern PATTERN;
    private Logfile logfile;

    public Logfile getLogfile() {
        return logfile;
    }
    public void setLogfile(Logfile logfile) {
        this.logfile = logfile;
    }

    public abstract List<T> parseLogfile(File file);
    public abstract T parseLogLine(String logLine);

}
