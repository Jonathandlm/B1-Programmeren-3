package be.leerstad.eindwerk.viewmodel;

import be.leerstad.eindwerk.model.Logfile;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.List;

public class LogfileView extends Logfile {

    private final StringProperty name;
    private final ObjectProperty<LocalDate> date;

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
    }

    public LogfileView(String name, LocalDate date, List<Logfile> list) {
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleObjectProperty<>(date);
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

}
