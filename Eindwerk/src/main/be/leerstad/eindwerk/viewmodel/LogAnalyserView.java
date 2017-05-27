package be.leerstad.eindwerk.viewmodel;

import be.leerstad.eindwerk.business.cache.LogfileCache;

import java.util.ArrayList;
import java.util.List;

public class LogAnalyserView {

    private LogfileCache logfileCache;
    private List<LogfileView> logfileViews;

    private static LogAnalyserView instance;

    private LogAnalyserView() {
        this.logfileCache = LogfileCache.getInstance();
        this.logfileViews = new ArrayList<>();
        logfileCache.forEach(logfile -> logfileViews.add(new LogfileView(logfile)));
    }

    public synchronized static LogAnalyserView getInstance() {
        if (instance == null) {
            instance = new LogAnalyserView();
        }
        return instance;
    }

    public List<LogfileView> getAllLogfileViews() {
        logfileViews.clear();
        logfileCache.forEach(logfile -> logfileViews.add(new LogfileView(logfile)));
        return logfileViews;
    }
}
