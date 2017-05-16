package be.leerstad.eindwerk.viewmodel;

import be.leerstad.eindwerk.model.LogAnalyser;
import be.leerstad.eindwerk.model.Logfile;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LogfileView extends Logfile {

    private final StringProperty name;
    private final ObjectProperty<LocalDate> date;
    private ListProperty<LogfileView> logfileViews;

    public LogfileView() {
        this(null, null);
    }

    public LogfileView(String name) {
        this(name, null);
    }

    public LogfileView(Logfile logfile) {
        this(logfile.getLogFile(), logfile.getLogFileDate());
    }

    public LogfileView(String name, LocalDate date) {
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleObjectProperty<>(date);
        this.logfileViews = new SimpleListProperty<>();
    }

    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public StringProperty nameProperty() {
        return name;
    }

    public LocalDate getDate() {
        return date.get();
    }
    public void setDate(LocalDate date) {
        this.date.set(date);
    }
    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public ObservableList<LogfileView> getLogfileViews() {
        return logfileViews.get();
    }

    public ListProperty<LogfileView> logfileViewsProperty() {
        LogAnalyser logAnalyser = new LogAnalyser();
        List<LogfileView> tempList = new ArrayList<>();
        logAnalyser.getAllLogfiles().forEach(
                logfile -> tempList.add(new LogfileView(logfile)));
        logfileViews.set(FXCollections.observableArrayList(tempList));
        return logfileViews;
    }
}
