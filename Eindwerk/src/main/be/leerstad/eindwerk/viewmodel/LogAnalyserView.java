package be.leerstad.eindwerk.viewmodel;

import be.leerstad.eindwerk.model.LogAnalyser;
import be.leerstad.eindwerk.model.Logfile;

import java.util.ArrayList;
import java.util.List;

public class LogAnalyserView {

    private List<Logfile> logfiles;
    private List<LogfileView> logfileViews;

    private static LogAnalyserView instance;

    private LogAnalyserView() {
        this.logfiles = LogAnalyser.getInstance().getLogfileCache();
        this.logfileViews = new ArrayList<>();
        logfiles.forEach(logfile -> logfileViews.add(new LogfileView(logfile)));
    }

    public synchronized static LogAnalyserView getInstance() {
        if (instance == null) {
            instance = new LogAnalyserView();
        }
        return instance;
    }

    public List<LogfileView> getAllLogfileViews() {
        logfileViews.clear();
        this.logfiles = LogAnalyser.getInstance().getLogfileCache();
        logfiles.forEach(logfile -> logfileViews.add(new LogfileView(logfile)));
        return logfileViews;
    }
}
