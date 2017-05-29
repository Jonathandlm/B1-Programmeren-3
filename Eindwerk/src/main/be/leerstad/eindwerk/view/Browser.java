package be.leerstad.eindwerk.view;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Browser extends Region{

    final WebView webView = new WebView();
    final WebEngine webEngine = webView.getEngine();

    public Browser() throws MalformedURLException {
        File file = new File("docs/be/leerstad/eindwerk/App.html");
        URL url = file.toURI().toURL();
        webEngine.load(url.toExternalForm());
        getChildren().add(webView);
    }

    @Override
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(webView,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }

    @Override
    protected double computePrefWidth(double height) {
        return 1200;
    }

    @Override
    protected double computePrefHeight(double width) {
        return 800;
    }
}
