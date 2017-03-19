package be.leerstad.eindwerk.view;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.controlsfx.control.StatusBar;

import java.io.File;

public class MainController {

    public TextField firstName;
    public TextField name;
    public Label result;
    public StatusBar statusBar;

    public void handleButtonAction() {
        result.setText(createUsername(firstName.getText(), name.getText()));
    }

    private String createUsername(String firstName, String name) {
        StringBuilder result = new StringBuilder();
        if (firstName.length() >= 1) result.append(firstName.charAt(0));
        if (name.length() >= 7) result.append(name.substring(0,7));
        else if (name.length() >= 1) result.append(name);
        return result.toString();
    }

    public void openFile() {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(result.getScene().getWindow());
        if (file != null) {
            result.setText(file.getName());
            statusBar.setText("File successfully opened!");
        }
    }

    public void exitProgram() {
        Platform.exit();
    }

    public void generateVisitsReport() {
    }

    public void generateSessionsReport() {
    }
}
