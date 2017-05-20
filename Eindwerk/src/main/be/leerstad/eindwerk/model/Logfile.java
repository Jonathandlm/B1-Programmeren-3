package be.leerstad.eindwerk.model;

import be.leerstad.eindwerk.utils.DateUtil;

import java.time.LocalDate;

public class Logfile {
    private String logFile;
    private LocalDate logFileDate;

    public Logfile(){
        this("", LocalDate.now());
    }

    public Logfile(String logFile){
        this(logFile, DateUtil.parseLogfileDate(logFile));
    }

    public Logfile(String logFile, LocalDate logFileDate){
        this.logFile = logFile;
        this.logFileDate = logFileDate;
    }

    public String getLogFile() {
        return logFile;
    }
    public void setLogFile(String logFile) {
        this.logFile = logFile;
    }

    public LocalDate getLogFileDate() {
        return logFileDate;
    }
    public void setLogFileDate(LocalDate logFileDate) {
        this.logFileDate = logFileDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Logfile logfile = (Logfile) o;

        return logFile.equals(logfile.logFile);
    }

    @Override
    public int hashCode() {
        return logFile.hashCode();
    }

    @Override
    public String toString() {
        return "Filename: " + logFile;
    }
}
