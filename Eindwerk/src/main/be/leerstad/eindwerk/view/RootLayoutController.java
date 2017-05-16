package be.leerstad.eindwerk.view;

import be.leerstad.eindwerk.App;
import be.leerstad.eindwerk.business.ParseFactory;
import be.leerstad.eindwerk.business.Parser;
import be.leerstad.eindwerk.model.LogAnalyser;
import be.leerstad.eindwerk.model.Logfile;
import be.leerstad.eindwerk.service.LogAnalyserDAOImpl;
import be.leerstad.eindwerk.viewmodel.LogAnalyserView;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.controlsfx.control.StatusBar;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RootLayoutController {

    public StatusBar statusBar;
    private App app;
    private LogAnalyserView logAnalyserView;
    private LogAnalyser logAnalyser;

    public void setApp(App app) {
        this.app = app;
    }

    public void setLogAnalyserView (LogAnalyserView logAnalyserView) {
        this.logAnalyserView = logAnalyserView;
    }

    // TODO
    public void openFile() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open file(s)");
        chooser.setInitialDirectory(new File("input"));
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All files", "*.*"),
                new FileChooser.ExtensionFilter("Sessions", "*.log"),
                new FileChooser.ExtensionFilter("Visits", "*.txt")
        );
        List<File> files = chooser.showOpenMultipleDialog(statusBar.getScene().getWindow());
        if (files != null) {
            logAnalyser.insertLogfiles(parse(files));
            statusBar.setText("File(s) successfully opened!");
        }
    }

    // TODO
    public void openDirectory() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Open directory");
        chooser.setInitialDirectory(new File("input"));
        File dir = chooser.showDialog(statusBar.getScene().getWindow());
        File[] files = dir.listFiles();
        if (files != null) {
            logAnalyser.insertLogfiles(parse(Arrays.asList(files)));
            statusBar.setText("Directory successfully opened!");
        }
    }

    // TODO
    public void clearDatabase() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirm clear database");
        alert.initOwner(app.getPrimaryStage());
        alert.setHeaderText("Clear database?");
        alert.setContentText("Do you really want to clear the database?\n(There is no way to undo this!)");

        ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOk) {
            LogAnalyserDAOImpl.getInstance().clearDatabase();
            statusBar.setText("Database has been cleared!");
        } else {
            statusBar.setText("Clear database cancelled");
        }

    }

    public void exitProgram() {
        Platform.exit();
    }

    // TODO
    public void generateReports() {
    }

    // TODO
    public void generateStats() {
    }

    // TODO
    public void openInfo() {
    }

    // TODO
    public void openHelp() {
    }

    private List<Logfile> parse(Iterable<File> files) {
        logAnalyser = new LogAnalyser();
        ParseFactory parseFactory = new ParseFactory();
        Parser parser;
        for (File file : files) {
            parser = parseFactory.getType(file.getName());
            logAnalyser.addLogfile(parser.parseLogFile(file));
        }
        return logAnalyser.getLogfiles();
    }
}
