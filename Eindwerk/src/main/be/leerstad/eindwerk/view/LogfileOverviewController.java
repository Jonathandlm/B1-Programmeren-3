package be.leerstad.eindwerk.view;

import be.leerstad.eindwerk.App;
import be.leerstad.eindwerk.utils.DateUtil;
import be.leerstad.eindwerk.viewmodel.LogAnalyserView;
import be.leerstad.eindwerk.viewmodel.LogfileView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;

public class LogfileOverviewController {

    @FXML
    private TableView<LogfileView> logfileTable;
    @FXML
    private TableColumn<LogfileView, String> nameColumn;
    @FXML
    private TableColumn<LogfileView, LocalDate> dateColumn;
    @FXML
    private Label nameLabel;
    @FXML
    private Label dateLabel;


    private App app;

    private LogAnalyserView logAnalyserView;

    public LogfileOverviewController() {
    }

    @FXML
    private void initialize() {
        // Initialize the logfile table with the two columns.
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        // Clear person details.
        showLogfileDetails(null);

        // Listen for selection changes and show the person details when changed.
        logfileTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showLogfileDetails(newValue));
    }

    public void setApp(App app) {
        this.app = app;

        // Add observable list data to the table
        logfileTable.setItems(app.getLogfileData());
    }

    public void setLogAnalyserView (LogAnalyserView logAnalyserView) {
        this.logAnalyserView = logAnalyserView;
    }

    private void showLogfileDetails(LogfileView logfileView) {
        if (logfileView != null) {
            nameLabel.setText(logfileView.getName());
            dateLabel.setText(DateUtil.format(logfileView.getDate()));
        } else {
            nameLabel.setText("");
            dateLabel.setText("");
        }
    }
}
