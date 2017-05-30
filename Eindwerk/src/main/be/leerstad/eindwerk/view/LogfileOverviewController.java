package be.leerstad.eindwerk.view;

import be.leerstad.eindwerk.App;
import be.leerstad.eindwerk.business.cache.LogfileCache;
import be.leerstad.eindwerk.model.Logfile;
import be.leerstad.eindwerk.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Comparator;

import static javafx.collections.FXCollections.observableArrayList;

public class LogfileOverviewController {

    @FXML
    private ListView<Logfile> logfileListView;
    @FXML
    private Label nameLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private ImageView iconImageView;

    private App app;

    @FXML
    private void initialize() {
        // Add sorted observable list data to the listView
        ObservableList<Logfile> logfileObservableList = observableArrayList(LogfileCache.getInstance().values());
        FXCollections.sort(logfileObservableList, Comparator.comparing(Logfile::getLogfile));
        logfileListView.setItems(logfileObservableList);

        // Initialize the logfile listView
        logfileListView.setCellFactory(param -> new ListCell<Logfile>() {
            @Override
            protected void updateItem(Logfile logfile, boolean empty) {
                super.updateItem(logfile, empty);
                if (empty || logfile == null || logfile.getLogfile() == null) {
                    setText(null);
                } else {
                    setText(logfile.getLogfile());
                }
            }
        });

        // Clear logfile details.
        showLogfileDetails(null);

        // Listen for selection changes and show the logfile details when changed.
        logfileListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showLogfileDetails(newValue));
    }

    public void setApp(App app) {
        this.app = app;
    }

    private void showLogfileDetails(Logfile logfile) {
        if (logfile != null) {
            nameLabel.setText(logfile.getLogfile());
            dateLabel.setText(DateUtil.format(logfile.getLogfileDate()));
            showImageFromFileType(getExtensionFromLogfile(logfile));
        } else {
            nameLabel.setText("");
            dateLabel.setText("");
            iconImageView.setImage(null);
        }
    }

    private String getExtensionFromLogfile(Logfile logfile) {
        return logfile.getLogfile().substring(logfile.getLogfile().lastIndexOf('.') + 1);
    }

    private void showImageFromFileType(String extension) {
        iconImageView.setImage(new Image("/icons/" + extension + ".png"));
    }

}
