package be.leerstad.eindwerk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;

public class LogAnalyser extends Application {

    private static final Logger LOG = Logger.getLogger(LogAnalyser.class.getName());

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Logfile Analyser");

        initRootLayout();


    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LogAnalyser.class.getResource("/be/leerstad/eindwerk/view/RootLayout.fxml"));
            rootLayout = loader.load();

            primaryStage.getIcons().add(new Image("/logo.png"));
            primaryStage.setResizable(false);
            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.show();
        } catch (IOException e) {
            LOG.log(Level.FATAL, "Unable to load main view", e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
