package be.leerstad.eindwerk.model;

import be.leerstad.eindwerk.utils.DateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Logfile {
    private String logFile;
    private LocalDate logFileDate;
    private List<Interaction> interactions;

    public Logfile(){
        logFile = "";
        logFileDate = LocalDate.now();
        interactions = new ArrayList<>();
    }

    public Logfile(String logFile){
        this.logFile = logFile;
        this.logFileDate = DateUtil.parse(logFile);
        interactions = new ArrayList<>();
    }

    public Logfile(String logFile, LocalDate logFileDate){
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

    public LocalDate getLogFileDate() {
        return logFileDate;
    }
    public void setLogFileDate(LocalDate logFileDate) {
        this.logFileDate = logFileDate;
    }

    public List<Interaction> getInteractions() {
        return interactions;
    }
    public void setInteractions(List<Interaction> interactions) {
        this.interactions = interactions;
    }

    public void addInteraction(Interaction interaction) {
        this.interactions.add(interaction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Logfile that = (Logfile) o;

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
