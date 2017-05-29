package be.leerstad.eindwerk.business.report;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.Map;

public class Chart {

    public static JFreeChart getSimpleChart(Map<String, Number> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Number> entry : data.entrySet()) {
            dataset.addValue(entry.getValue(), "", entry.getKey());
        }

        return ChartFactory.createBarChart(null, null, null,
                dataset, PlotOrientation.VERTICAL, false, false, false);
    }

    public static JFreeChart getComplexChart(Map<String, Map<String, Number>> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Map<String, Number>> entry : data.entrySet()) {
            for (Map.Entry<String, Number> innerEntry : entry.getValue().entrySet()) {
                dataset.addValue(innerEntry.getValue(), entry.getKey(), innerEntry.getKey());
            }
        }

        return ChartFactory.createBarChart(null, null, null,
                dataset, PlotOrientation.VERTICAL, true, true, true);
    }

}
