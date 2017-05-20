package be.leerstad.eindwerk.business;

import be.leerstad.eindwerk.model.LogAnalyser;
import be.leerstad.eindwerk.model.Logfile;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

public abstract class Parser<T> {
    protected String REGEX;
    protected Pattern PATTERN;
    private Logfile logfile;

    public Logfile getLogfile() {
        return logfile;
    }
    public void setLogfile(Logfile logfile) {
        this.logfile = logfile;
    }

    public abstract List<T> parseLogFile(File file);
    public abstract T parseLogLine(String logline);

    public boolean isDuplicateLogFile(String fileName) {
        return LogAnalyser.getInstance().getInteractions().stream().anyMatch(
                interaction -> interaction.getLogfile().getLogFile().equals(fileName));
    }
}
