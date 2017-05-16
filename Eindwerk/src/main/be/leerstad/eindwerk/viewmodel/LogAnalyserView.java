package be.leerstad.eindwerk.viewmodel;

import be.leerstad.eindwerk.model.LogAnalyser;
import be.leerstad.eindwerk.model.Logfile;
import be.leerstad.eindwerk.service.LogfileDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class LogAnalyserView extends LogAnalyser {

    private List<Logfile> logfiles;
    private List<LogfileView> logfileViews;

    public LogAnalyserView() {
        this.logfiles = LogfileDAOImpl.getInstance().getLogfiles();
        this.logfileViews = new ArrayList<>();
        logfiles.forEach(logfile -> logfileViews.add(new LogfileView(logfile)));
    }

    public List<LogfileView> getAllLogfileViews() {
        logfileViews.clear();
        this.logfiles = LogfileDAOImpl.getInstance().getLogfiles();
        logfiles.forEach(logfile -> logfileViews.add(new LogfileView(logfile)));
        return logfileViews;
    }
}
