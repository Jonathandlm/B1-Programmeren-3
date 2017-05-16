package be.leerstad.eindwerk.model;

import be.leerstad.eindwerk.service.LogfileDAOImpl;

import java.util.List;

public class LogAnalyser {
    private List<Logfile> logfiles;

    public LogAnalyser() {
        this.logfiles = LogfileDAOImpl.getInstance().getLogfiles();
    }

    public List<Logfile> getLogfiles() {
        return logfiles;
    }
    public void setLogfiles(List<Logfile> logfiles) {
        this.logfiles = logfiles;
    }

    public List<Logfile> getAllLogfiles() {
        return this.logfiles = LogfileDAOImpl.getInstance().getLogfiles();
    }

    public void insertLogfiles(List<Logfile> logfiles) {
        System.out.println("START INSERT");
        LogfileDAOImpl.getInstance().insertLogfiles(logfiles);
    }

    public void addLogfile(Logfile logfile) {
        this.logfiles.add(logfile);
    }
}
