package be.leerstad.eindwerk.model;

import be.leerstad.eindwerk.util.DateUtil;

import java.time.LocalDate;

public class Logfile {
    private String logfile;
    private LocalDate logfileDate;

    public Logfile(){
        this("", LocalDate.now());
    }

    public Logfile(String logfile){
        this(logfile, DateUtil.parseLogfileDate(logfile));
    }

    public Logfile(String logfile, LocalDate logfileDate){
        this.logfile = logfile;
        this.logfileDate = logfileDate;
    }

    public String getLogfile() {
        return logfile;
    }
    public void setLogfile(String logfile) {
        this.logfile = logfile;
    }

    public LocalDate getLogfileDate() {
        return logfileDate;
    }
    public void setLogfileDate(LocalDate logfileDate) {
        this.logfileDate = logfileDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Logfile other = (Logfile) o;

        return logfile.equals(other.logfile);
    }

    @Override
    public int hashCode() {
        return logfile.hashCode();
    }

    @Override
    public String toString() {
        return "Logfile: " + logfile;
    }
}
