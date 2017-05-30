package be.leerstad.eindwerk.view;

import be.leerstad.eindwerk.App;
import be.leerstad.eindwerk.business.cache.SessionCache;
import be.leerstad.eindwerk.business.cache.VisitCache;
import be.leerstad.eindwerk.business.printer.Printer;
import be.leerstad.eindwerk.business.report.Query;
import be.leerstad.eindwerk.business.report.SessionReport;
import be.leerstad.eindwerk.business.report.VisitReport;
import be.leerstad.eindwerk.model.Session;
import be.leerstad.eindwerk.model.Visit;
import be.leerstad.eindwerk.util.CacheUtil;
import be.leerstad.eindwerk.util.PropertyUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.awt.*;
import java.io.File;
import java.time.Year;
import java.time.YearMonth;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static javafx.collections.FXCollections.observableArrayList;

public class ReportChooserController {

    private static final Logger LOG = Logger.getLogger(ReportChooserController.class.getName());

    private Stage dialogStage;
    private App app;
    private Printer printer;
    private Query query;
    private VisitReport selectedVisitReport;
    private SessionReport selectedSessionReport;
    private String fileNameSuggestion;
    private String reportTitle;
    private String errorMessage;
    private boolean complexChart;

    @FXML
    private ListView<String> visitReportListView;
    @FXML
    private Label visitReportTitleLabel;
    @FXML
    private TextArea visitReportDescriptionTextArea;
    @FXML
    private ComboBox<String> visitReportCombobox;
    @FXML
    private ListView<String> sessionReportListView;
    @FXML
    private Label sessionReportTitleLabel;
    @FXML
    private TextArea sessionReportDescriptionTextArea;
    @FXML
    private ComboBox<String> sessionReportMonthCombobox;
    @FXML
    private ComboBox<String> sessionReportYearCombobox;
    @FXML
    private BarChart<String, Number> visitBarChart;
    @FXML
    private BarChart<String, Number> sessionBarChart;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ReportChooserController() {
        this.printer = new Printer();
        this.query = new Query();
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        visitReportListView.getSelectionModel().selectedItemProperty().addListener(ob -> changeVisitReport());
        visitReportCombobox.getSelectionModel().selectedItemProperty().addListener(ob -> changeVisitReport());
        loadVisitReportList();
        loadVisitReportDropdown();

        sessionReportListView.getSelectionModel().selectedItemProperty().addListener(ob -> changeSessionReport());
        sessionReportMonthCombobox.getSelectionModel().selectedItemProperty().addListener(ob -> changeSessionReport());
        sessionReportYearCombobox.getSelectionModel().selectedItemProperty().addListener(ob -> changeSessionReport());
        loadSessionReportList();
        loadSessionReportDropdown();

    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage the stage to which this dialogs belongs
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setApp(App app) {
        this.app = app;
    }

    /**
     * Called when the user clicks ok in the visits tab.
     */
    @FXML
    private void handleVisitOk() {
        if (isVisitInputValid()) {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save report");
            fileChooser.setInitialDirectory(PropertyUtil.getFileLocation("OUTPUT_FILE_LOCATION"));
            fileChooser.setInitialFileName(fileNameSuggestion);
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Portable Document Format (*.pdf)", "*.pdf"));
            File file = fileChooser.showSaveDialog(dialogStage);

            if (file != null) {
                try {
                    printer.createPdfFromTemplate(file.toString(), reportTitle, getVisitData());
                    Desktop.getDesktop().open(file);
                } catch (Exception e) {
                    LOG.log(Level.ERROR, "Unable to print report to file " + file, e);
                }
            } else {
                LOG.log(Level.DEBUG, "Unable to print report: no file chosen");
            }
        } else {
            showInvalidSelectionAlert(errorMessage);
        }
    }

    /**
     * Called when the user clicks ok in the sessions tab.
     */
    @FXML
    private void handleSessionOk() {
        if (isSessionInputValid()) {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save report");
            fileChooser.setInitialDirectory(PropertyUtil.getFileLocation("OUTPUT_FILE_LOCATION"));
            fileChooser.setInitialFileName(fileNameSuggestion);
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Portable Document Format (*.pdf)", "*.pdf"));
            File file = fileChooser.showSaveDialog(dialogStage);

            if (file != null) {
                try {
                    printer.createPdfFromTemplate(file.toString(), reportTitle, getSessionData());
                    Desktop.getDesktop().open(file);
                } catch (Exception e) {
                    LOG.log(Level.ERROR, "Unable to print report to file " + file, e);
                }
            } else {
                LOG.log(Level.DEBUG, "Unable to print report: no file chosen");
            }
        } else {
            showInvalidSelectionAlert(errorMessage);
        }
    }

    /**
     * Gets called when the user clicks cancel. Reverts the screen to the logfile overview.
     */
    @FXML
    private void handleCancel() {
        app.showLogfileOverview();
    }

    /**
     * Validates the user selections in the visit tab.
     *
     * @return true if the input is valid
     */
    private boolean isVisitInputValid() {
        errorMessage = "";

        if (visitReportListView.getSelectionModel().isEmpty()) {
            errorMessage += "Please select a report from list first!\n";

        } else if (selectedVisitReport.getSelector() && visitReportCombobox.getSelectionModel().isEmpty()) {
            errorMessage += "Please select a month!\n";
        }

        if (errorMessage.length() == 0) {
            fileNameSuggestion = selectedVisitReport.getFileName();
            if (selectedVisitReport.getSelector()) {
                fileNameSuggestion += "_" + visitReportCombobox.getSelectionModel().getSelectedItem();
            }

            reportTitle = selectedVisitReport.getListName();

            return true;

        } else {
            return false;
        }
    }

    private void changeVisitReport() {
        selectedVisitReport = VisitReport.values()[visitReportListView.getSelectionModel().getSelectedIndex()];
        visitReportTitleLabel.setText(selectedVisitReport.getListName());
        visitReportDescriptionTextArea.setText(selectedVisitReport.getDescription());
        visitReportCombobox.setVisible(selectedVisitReport.getSelector());
        if (isVisitInputValid()) {
            visitBarChart.setData(getBarChart(getVisitData()));
            visitBarChart.setLegendVisible(complexChart);
        }
    }

    private void loadVisitReportList() {
        for (VisitReport visitReport : VisitReport.values()) {
            visitReportListView.getItems().add(visitReport.getListName());
        }
    }

    private void loadVisitReportDropdown() {
        VisitCache visitCache = VisitCache.getInstance();
        Set<YearMonth> months = CacheUtil.getMonthsFromCache(visitCache);

        for (YearMonth month : months) {
            visitReportCombobox.getItems().add(month.toString());
        }
    }

    private LinkedHashMap<String, ?> getVisitData() {

        switch (selectedVisitReport) {
            case MONTHLY_APPLICATION_VISITS:
                return query.getMonthlyApplicationTotals(Visit::getNumberOfRequests);
            case MONTHLY_APPLICATION_BYTES:
                return query.getMonthlyApplicationTotals(Visit::getTransferredBytes);
            case SCHOOL_VISITS_BY_MONTH:
                return query.getSchoolVisitsByMonth(YearMonth.parse(visitReportCombobox.getSelectionModel().getSelectedItem()));
            default:
                LOG.log(Level.ERROR, "Unable to get report data from " + selectedVisitReport.getListName());
                return null;
        }
    }

    /**
     * Validates the user selections in the session tab.
     *
     * @return true if the input is valid
     */
    private boolean isSessionInputValid() {
        errorMessage = "";

        if (sessionReportListView.getSelectionModel().isEmpty()) {
            errorMessage += "Please select a report from list first!\n";
        } else if (selectedSessionReport.getSelector().equals("month")
                && sessionReportMonthCombobox.getSelectionModel().isEmpty()) {
            errorMessage += "Please select a month!\n";
        } else if (selectedSessionReport.getSelector().equals("year")
                && sessionReportYearCombobox.getSelectionModel().isEmpty()) {
            errorMessage += "Please select a year!\n";
        }

        if (errorMessage.length() == 0) {
            fileNameSuggestion = selectedSessionReport.getFileName();
            if ( !selectedSessionReport.getSelector().equals("") ) {
                if (selectedSessionReport.getSelector().equals("month")) {
                    fileNameSuggestion += "_" + sessionReportMonthCombobox.getSelectionModel().getSelectedItem();
                } else {
                    fileNameSuggestion += "_" + sessionReportYearCombobox.getSelectionModel().getSelectedItem();
                }
            }

            reportTitle = selectedSessionReport.getListName();

            return true;

        } else {
            return false;
        }
    }

    private void changeSessionReport() {
        selectedSessionReport = SessionReport.values()[sessionReportListView.getSelectionModel().getSelectedIndex()];
        sessionReportTitleLabel.setText(selectedSessionReport.getListName());
        sessionReportDescriptionTextArea.setText(selectedSessionReport.getDescription());
        sessionReportMonthCombobox.setVisible(selectedSessionReport == SessionReport.USER_BYTES_BY_MONTH
                || selectedSessionReport == SessionReport.USER_TIME_BY_MONTH);
        sessionReportYearCombobox.setVisible(selectedSessionReport == SessionReport.MONTHlY_BYTES_BY_YEAR);
        // Combobox sessionReportMonthCombobox and sessionReportYearCombobox are excluded from their parents layout
        // calculations when not visible
        sessionReportMonthCombobox.managedProperty().bind(sessionReportMonthCombobox.visibleProperty());
        sessionReportYearCombobox.managedProperty().bind(sessionReportYearCombobox.visibleProperty());
        if (isSessionInputValid()) {
            sessionBarChart.setData(getBarChart(getSessionData()));
            sessionBarChart.setLegendVisible(complexChart);
        }
    }

    private void loadSessionReportList() {
        for (SessionReport sessionReport : SessionReport.values()) {
            sessionReportListView.getItems().add(sessionReport.getListName());
        }
    }

    private void loadSessionReportDropdown() {
        SessionCache sessionCache = SessionCache.getInstance();
        Set<YearMonth> months = CacheUtil.getMonthsFromCache(sessionCache);
        Set<Year> years = CacheUtil.getYearsFromCache(sessionCache);

        for (YearMonth month : months) {
            sessionReportMonthCombobox.getItems().add(month.toString());
        }

        for (Year year : years) {
            sessionReportYearCombobox.getItems().add(year.toString());
        }
    }

    private LinkedHashMap<String, ?> getSessionData() {

        switch (selectedSessionReport) {
            case USER_BYTES_BY_MONTH:
                return query.getUserTotalsByMonth(
                        YearMonth.parse(sessionReportMonthCombobox.getSelectionModel().getSelectedItem()),
                        Session::getTransferredBytes);
            case USER_TIME_BY_MONTH:
                return query.getUserTotalsByMonth(
                        YearMonth.parse(sessionReportMonthCombobox.getSelectionModel().getSelectedItem()),
                        Session::getTotalTimeInSec);
            case MONTHlY_BYTES_BY_YEAR:
                return query.getMonthTotalsByYear(
                        Year.parse(sessionReportYearCombobox.getSelectionModel().getSelectedItem()),
                        Session::getTransferredBytes);
            case TOP_SITES_BY_VISITS:
                return query.getSiteTotals(Session::getNumberOfRequests);
            case TOP_SITES_BY_TIME:
                return query.getSiteTotals(Session::getTotalTimeInSec);
            case TOP_SITES_BY_BYTES:
                return query.getSiteTotals(Session::getTransferredBytes);
            default:
                LOG.log(Level.ERROR, "Unable to get report data from " + selectedSessionReport.getListName());
                return null;
        }
    }

    private ObservableList<XYChart.Series<String, Number>> getBarChart(LinkedHashMap<String, ?> data) {
        Object object;
        try {
            object = data.values().toArray()[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
        if (object instanceof Integer) {
            complexChart = false;
            return getSimpleChart((LinkedHashMap<String, Number>) data);
        } else if (object instanceof Map) {
            complexChart = true;
            return getComplexChart((LinkedHashMap<String, LinkedHashMap<String, Number> >) data);
        }
        return null;
    }

    private ObservableList<XYChart.Series<String, Number>> getSimpleChart(LinkedHashMap<String, Number> data) {

        ObservableList<XYChart.Series<String, Number>> result = observableArrayList();
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (Map.Entry<String, Number> entry : data.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        result.add(series);

        return result;
    }

    private ObservableList<XYChart.Series<String, Number>> getComplexChart(LinkedHashMap<String, LinkedHashMap<String, Number>> data) {

        ObservableList<XYChart.Series<String, Number>> result = observableArrayList();
        XYChart.Series<String, Number> series;

        for (Map.Entry<String, LinkedHashMap<String, Number>> entry : data.entrySet()) {
            series = new XYChart.Series<>();
            series.setName(entry.getKey());

            for (Map.Entry<String, Number> innerEntry : entry.getValue().entrySet()) {
                series.getData().add(new XYChart.Data<>(innerEntry.getKey(), innerEntry.getValue()));
            }

            result.add(series);
        }

        return result;
    }

    private void showInvalidSelectionAlert(String errorMessage) {
        // Show the error message.
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(app.getPrimaryStage());
        alert.setTitle("Invalid selection");
        alert.setHeaderText("Please correct your selection");
        alert.setContentText(errorMessage);

        alert.showAndWait();
    }
}
