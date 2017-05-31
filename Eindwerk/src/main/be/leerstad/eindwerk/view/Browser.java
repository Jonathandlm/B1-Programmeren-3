package be.leerstad.eindwerk.view;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class is to be used to create a browser window.
 *
 * The home page can be given as a {@link File file} to the constructor.
 */
public class Browser extends Region{

    private final WebView webView;

    /**
     * Creates a {@link Region region} that acts as a browser.
     *
     * @param file file that will be used as the home page
     * @throws MalformedURLException if the home page {@link File file} can't be set to a proper {@link URL url}.
     */
    public Browser(File file) throws MalformedURLException {
        webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        URL url = file.toURI().toURL();
        webEngine.load(url.toExternalForm());
        getChildren().add(webView);
    }

    /**
     * Makes the browser always fill up the window. Overrides the {@link Parent#layoutChildren()}.
     */
    @Override
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(webView,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }

    /**
     * Sets the computed preference width to a fixed value of 1200.
     *
     * @param width double which can normally be set as the width
     * @return will always return 1200
     */
    @Override
    protected double computePrefWidth(double width) {
        return 1200;
    }

    /**
     * Sets the computed preference height to a fixed value of 1200.
     *
     * @param height double which can normally be set as the height
     * @return will always return 1200
     */
    @Override
    protected double computePrefHeight(double height) {
        return 800;
    }
}
