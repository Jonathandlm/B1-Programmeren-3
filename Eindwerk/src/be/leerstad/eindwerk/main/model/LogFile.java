package be.leerstad.eindwerk.main.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class LogFile {
    private String logFile;
    private Date logFileDate;
    private List<Interaction> interactions;

    public LogFile(){
        logFile = "";
        logFileDate = Date.valueOf("2000-01-01");
        interactions = new ArrayList<>();
    }

    public LogFile(String logFile, Date logFileDate){
        this.logFile = logFile;
        this.logFileDate = logFileDate;
        interactions = new ArrayList<>();
    }

    public String getLogFile() {
        return logFile;
    }
    public void setLogFile(String logFile) {
        this.logFile = logFile;
    }

    public Date getLogFileDate() {
        return logFileDate;
    }
    public void setLogFileDate(Date logFileDate) {
        this.logFileDate = logFileDate;
    }

    public List<Interaction> getInteractions() {
        return interactions;
    }
    public void setInteractions(List<Interaction> interactions) {
        this.interactions = interactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogFile that = (LogFile) o;

        if (logFile != null ? !logFile.equals(that.logFile) : that.logFile != null) return false;
        if (logFileDate != null ? !logFileDate.equals(that.logFileDate) : that.logFileDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = logFile != null ? logFile.hashCode() : 0;
        result = 31 * result + (logFileDate != null ? logFileDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Filename: " + logFile;
    }
}
