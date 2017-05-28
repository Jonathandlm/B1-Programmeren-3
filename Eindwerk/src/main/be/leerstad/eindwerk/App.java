package be.leerstad.eindwerk;

import be.leerstad.eindwerk.business.cache.LogfileCache;
import be.leerstad.eindwerk.view.LogfileOverviewController;
import be.leerstad.eindwerk.view.ReportChooserController;
import be.leerstad.eindwerk.view.RootLayoutController;
import be.leerstad.eindwerk.viewmodel.LogAnalyserView;
import be.leerstad.eindwerk.viewmodel.LogfileView;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;

import static javafx.collections.FXCollections.observableArrayList;

public class App extends Application {

    private static final Logger LOG = Logger.getLogger(App.class.getName());

    private final LogAnalyserView logAnalyserView;
    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<LogfileView> logfileData;

    public App() {
        logAnalyserView = LogAnalyserView.getInstance();
        logfileData = observableArrayList(logAnalyserView.getAllLogfileViews());
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObservableList<LogfileView> getLogfileData() {
        return logfileData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Logfile Analyser");

        initRootLayout();

        showLogfileOverview();
    }

    /**
     * Initializes the root layout.
     */
    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/be/leerstad/eindwerk/view/RootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(getClass().getResource("/css/loganalyser.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image("/logo.png"));
            primaryStage.setResizable(false);
            primaryStage.show();

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setApp(this);
            controller.setLogAnalyserView(logAnalyserView);

        } catch (IOException e) {
            LOG.log(Level.FATAL, "Unable to load root layout", e);
        }
    }

    /**
     * Shows the logfile overview inside the root layout.
     */
    private void showLogfileOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/be/leerstad/eindwerk/view/LogfileOverview.fxml"));
            AnchorPane logfileOverview = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(logfileOverview);

            // Give the controller access to the main app.
            LogfileOverviewController controller = loader.getController();
            controller.setApp(this);
            controller.setLogAnalyserView(logAnalyserView);

        } catch (IOException e) {
            LOG.log(Level.FATAL, "Unable to load logfile overview", e);
        }
    }

    /**
     * Shows the report overview inside the root layout.
     */
    public void showReportChooser() {
        if (LogfileCache.getInstance().isEmpty()) {
            showAlert("reports");
        } else {
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(App.class.getResource("/be/leerstad/eindwerk/view/ReportChooser.fxml"));
                AnchorPane reportChooser = loader.load();

                // Get the report chooser dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Choose report");
                dialogStage.getIcons().add(new Image("/logo.png"));
                dialogStage.initOwner(primaryStage);
                dialogStage.initModality(Modality.WINDOW_MODAL);
                Scene scene = new Scene(reportChooser);
                dialogStage.setScene(scene);

                // Give the controller access to the dialog stage.
                ReportChooserController controller = loader.getController();
                controller.setDialogStage(dialogStage);

                // Show the dialog and wait until the user closes it
                dialogStage.showAndWait();

            } catch (IOException e) {
                LOG.log(Level.FATAL, "Unable to load report chooser", e);
            }
        }

    }

    public void showStatistics() {
        if (LogfileCache.getInstance().isEmpty()) {
            showAlert("statistics");
        } else {
            // TODO
        }
    }

    public void showInformation() {

    }

    public void showHelp() {

    }

    private void showAlert(String type) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No logfiles found!");
        alert.initOwner(primaryStage);
        alert.setHeaderText("Unable to create " + type);
        alert.setContentText("No logfiles are found. Please parse some files first.\n" +
                "To open the dialog box, click 'File' in the menu and then 'Open...'\n");

        alert.showAndWait();
    }

}
