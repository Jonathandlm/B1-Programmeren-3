package be.leerstad.eindwerk.view;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import org.apache.log4j.Logger;

import java.util.Map;

public class ChartChooserController {

    private static final Logger LOG = Logger.getLogger(ChartChooserController.class.getName());






    @FXML
    private void initialize() {

    }



    public BarChart<String, Number> getSimpleChart(Map<String, Number> data) {
        BarChart<String, Number> barChart = new BarChart<>(new CategoryAxis(), new NumberAxis());

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (Map.Entry<String, Number> entry : data.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        barChart.getData().add(series);

        return barChart;
    }

    public BarChart<String, Number> getComplexChart(Map<String, Map<String, Number>> data, String title) {
        BarChart<String, Number> barChart = new BarChart<>(new CategoryAxis(), new NumberAxis());
        barChart.setTitle(title);

        XYChart.Series<String, Number> series;

        for (Map.Entry<String, Map<String, Number>> entry : data.entrySet()) {
            series = new XYChart.Series<>();
            series.setName(entry.getKey());

            for (Map.Entry<String, Number> innerEntry : entry.getValue().entrySet()) {
                series.getData().add(new XYChart.Data<>(innerEntry.getKey(), innerEntry.getValue()));
            }

            barChart.getData().add(series);
        }

        return barChart;
    }

}
