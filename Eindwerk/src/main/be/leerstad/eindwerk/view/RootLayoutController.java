package be.leerstad.eindwerk.view;

import javafx.application.Platform;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.controlsfx.control.StatusBar;

import java.io.File;
import java.util.List;

public class RootLayoutController {

    public StatusBar statusBar;

    // TODO
    public void openFile() {
        FileChooser chooser = new FileChooser();
        List<File> file = chooser.showOpenMultipleDialog(statusBar.getScene().getWindow());
        if (file != null) {
            statusBar.setText("File(s) successfully opened!");
        }
    }

    // TODO
    public void openDirectory() {
        DirectoryChooser chooser = new DirectoryChooser();
        File dir = chooser.showDialog(statusBar.getScene().getWindow());
        File[] file = dir.listFiles();
        if (file != null) {
            statusBar.setText("Directory successfully opened!");
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

}
