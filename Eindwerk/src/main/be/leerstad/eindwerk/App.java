package be.leerstad.eindwerk;

import be.leerstad.eindwerk.business.cache.LogfileCache;
import be.leerstad.eindwerk.view.Browser;
import be.leerstad.eindwerk.view.LogfileOverviewController;
import be.leerstad.eindwerk.view.ReportChooserController;
import be.leerstad.eindwerk.view.RootLayoutController;
import be.leerstad.eindwerk.viewmodel.LogAnalyserView;
import be.leerstad.eindwerk.viewmodel.LogfileView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * <h1>LogAnalyser</h1>
 * <p>This class is the main entry point to the application.</p>
 * <p>LogAnalyser is used to parse log files, store the data and create reports from this data.</p>
 *
 * @author <a href="mailto:jonathandlm@hotmail.com">Jonathan De La Marche</a>
 * @version 1.0 05/2017
 */
public class App extends Application {

    private static final String VERSION = "Version 1.0";
    private static final Logger LOG = Logger.getLogger(App.class.getName());

    private final LogAnalyserView logAnalyserView;
    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * The data as an observable list of {@link be.leerstad.eindwerk.model.Logfile logfiles}.
     */
    private ObservableList<LogfileView> logfileData;

    /**
     * Constructor
     * fills the list of logfiles.
     */
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
        primaryStage.setTitle("Logfile Analyser");

        //this makes all stages close and the app exit when the main stage is closed
        primaryStage.setOnCloseRequest(e -> Platform.exit());

        initRootLayout();

        showLogfileOverview();
    }

    /**
     * Initializes the root layout.
     * This includes the menu at the top, the shortcuts and the status bar at the bottom.
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
    public void showLogfileOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/be/leerstad/eindwerk/view/LogfileOverview.fxml"));
            AnchorPane logfileOverview = loader.load();

            // Set logfile overview into the center of root layout.
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
     * Shows the dialog screen with the report chooser on top of the primary stage.
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

    /**
     * Shows the dialog screen with the statistics on top of the primary stage.
     */
    public void showStatistics() {
        if (LogfileCache.getInstance().isEmpty()) {
            showAlert("statistics");
        } else {
            // TODO
        }
    }

    /**
     * Shows the dialog screen with some information on top of the primary stage.
     */
    public void showInformation() {
        String boldCss = "-fx-font-weight: bold";
        String italicCss = "-fx-font-style: italic";

        Dialog dialog = new Dialog();
        dialog.setTitle("About...");
        dialog.initOwner(primaryStage);

        DialogPane dialogPane = new DialogPane();

        Text titleText = new Text("Log Analyser ");
        titleText.setStyle(boldCss);
        Text versionText = new Text(VERSION);
        versionText.setStyle(italicCss);
        Text authorLabelText = new Text("Author: ");
        authorLabelText.setStyle(boldCss);
        Text authorText = new Text("Jonathan De La Marche");
        Text copyrightLabelText = new Text("Copyright: ");
        copyrightLabelText.setStyle(boldCss);
        Text copyrightText = new Text("CVO Leerstad");
        Text javaLabelText = new Text("Java: ");
        javaLabelText.setStyle(boldCss);
        Text javaText = new Text(System.getProperty("java.version"));

        TextFlow flow = new TextFlow(titleText, versionText,
                new Text("\n\n"),
                authorLabelText, authorText,
                new Text("\n\n"),
                copyrightLabelText, copyrightText,
                new Text("\n\n"),
                javaLabelText, javaText);

        dialogPane.setContent(flow);
        dialogPane.getButtonTypes().addAll(ButtonType.OK);
        dialog.setDialogPane(dialogPane);
        dialog.setHeaderText("Log Analyser");

        Image logo = new Image("/logo.png");
        ImageView imageView = new ImageView(logo);
        imageView.setFitWidth(64);
        imageView.setFitHeight(64);
        dialog.setGraphic(imageView);

        dialog.showAndWait();
    }

    /**
     * Shows the overview of the javadoc in a {@link WebView WebView} on top of the primary stage.
     */
    public void showHelp() {
        try {
            // Get the browser dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Browse the documentation");
            dialogStage.getIcons().add(new Image("/logo.png"));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(new Browser(), 1200, 800, Color.web("#666970"));
            dialogStage.setScene(scene);

            // Show the dialog and wait until the user closes it
            dialogStage.show();

        } catch (IOException e) {
            LOG.log(Level.FATAL, "Unable to load browser", e);
        }
    }

    /**
     * Show an alert pop-up to tell that no logfiles have been found in the logfile list.
     *
     * @param type the intended type of dialog to be opened, e.g. report for the report chooser
     */
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
