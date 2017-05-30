package be.leerstad.eindwerk.view;

import be.leerstad.eindwerk.App;
import be.leerstad.eindwerk.business.cache.LogfileCache;
import be.leerstad.eindwerk.business.parser.ParseFactory;
import be.leerstad.eindwerk.business.parser.Parser;
import be.leerstad.eindwerk.model.Interaction;
import be.leerstad.eindwerk.service.InteractionDAOImpl;
import be.leerstad.eindwerk.service.LogAnalyserDAOImpl;
import be.leerstad.eindwerk.util.PropertyUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.controlsfx.control.StatusBar;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RootLayoutController {

    private App app;

    @FXML
    public StatusBar statusBar;

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    private void initialize() {
    }

    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file(s)");
        fileChooser.setInitialDirectory(PropertyUtil.getFileLocation("INPUT_FILE_LOCATION"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All files", "*.*"),
                new FileChooser.ExtensionFilter("Sessions", "*.log"),
                new FileChooser.ExtensionFilter("Visits", "*.txt")
        );
        List<File> files = fileChooser.showOpenMultipleDialog(app.getPrimaryStage().getScene().getWindow());
        if (files != null) {
            InteractionDAOImpl.getInstance().insertInteractions(parse(files));
            app.resetCaches();
            statusBar.setText("File(s) successfully opened!");
        }
    }

    public void openDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open directory");
        directoryChooser.setInitialDirectory(PropertyUtil.getFileLocation("INPUT_DIRECTORY_LOCATION"));
        File directory = directoryChooser.showDialog(app.getPrimaryStage().getScene().getWindow());
        if (directory != null) {
            File[] files = directory.listFiles();
            InteractionDAOImpl.getInstance().insertInteractions(parse(Arrays.asList(files)));
            app.resetCaches();
            statusBar.setText("Directory successfully opened!");
        }
    }

    public void clearDatabase() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirm clear database");
        alert.initOwner(app.getPrimaryStage());
        alert.setHeaderText("Clear database?");
        alert.setContentText("Do you really want to clear the database?\n(There is no way to undo this!)\n");

        ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeOk) {
            LogAnalyserDAOImpl.getInstance().clearDatabase();
            app.resetCaches();
            statusBar.setText("Database has been cleared!");
        } else {
            statusBar.setText("Clear database cancelled");
        }

    }

    public void exitProgram() {
        Platform.exit();
    }

    public void showLogfiles() {
        app.showLogfileOverview();
    }

    public void generateReports() {
        app.showReportChooser();
    }

    public void openInfo() {
        app.showInformation();
    }

    public void openHelp() {
        app.showHelp();
    }

    private List<Interaction> parse(List<File> files) {
        List<Interaction> interactions = new ArrayList<>();
        ParseFactory parseFactory = new ParseFactory();
        Parser parser;
        String fileName;
        for (File file : files) {
            fileName = file.getName();
            parser = parseFactory.getType(fileName);
            if (LogfileCache.getInstance().containsKey(fileName)) {
                if (!confirmLogfileUpdate()) continue;
                else {
                    InteractionDAOImpl.getInstance().deleteInteraction(fileName);
                }
            }
            interactions.addAll(parser.parseLogfile(file));
        }
        return interactions;
    }

    private boolean confirmLogfileUpdate() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Duplicate logfile found!");
        alert.initOwner(app.getPrimaryStage());
        alert.setHeaderText("Duplicate logfile");
        alert.setContentText("Do you want to skip this file or replace the existing file with this file?\n");

        ButtonType buttonTypeOk = new ButtonType("Replace", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Skip", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();

        return result.isPresent() && result.get() == buttonTypeOk;

    }

}
