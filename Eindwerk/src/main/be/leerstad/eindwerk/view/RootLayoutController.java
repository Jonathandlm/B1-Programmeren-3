package be.leerstad.eindwerk.view;

import be.leerstad.eindwerk.App;
import be.leerstad.eindwerk.business.ParseFactory;
import be.leerstad.eindwerk.business.Parser;
import be.leerstad.eindwerk.model.Interaction;
import be.leerstad.eindwerk.model.LogAnalyser;
import be.leerstad.eindwerk.service.InteractionDAOImpl;
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
import java.util.ArrayList;
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
            InteractionDAOImpl.getInstance().insertInteractions(parse(files));
            LogAnalyser.getInstance().refreshCaches();
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
            InteractionDAOImpl.getInstance().insertInteractions(parse(Arrays.asList(files)));
            LogAnalyser.getInstance().refreshCaches();
            statusBar.setText("Directory successfully opened!");
        }
    }

    // TODO
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

    private List<Interaction> parse(List<File> files) {
        List<Interaction> interactions = new ArrayList<>();
        ParseFactory parseFactory = new ParseFactory();
        Parser parser;
        String fileName;
        for (File file : files) {
            fileName = file.getName();
            parser = parseFactory.getType(fileName);
            if (parser.isDuplicateLogFile(fileName)) {
                if (!confirmLogfileUpdate()) continue;
                else {
                    InteractionDAOImpl.getInstance().deleteInteraction(fileName);
                }
            }
            interactions.addAll(parser.parseLogFile(file));
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

        return result.get() == buttonTypeOk;

    }
}
