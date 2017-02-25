package be.leerstad.eindwerk.main.business;

import be.leerstad.eindwerk.main.model.Interaction;

import java.io.File;
import java.util.regex.Pattern;

public abstract class Parser {
    private static String REGEX;
    private static Pattern PATTERN;

    public abstract void parseLogFile(File file);
    public abstract boolean isValidLogFile(String fileName);
    public abstract Interaction parseLogLine(String logline);
}
