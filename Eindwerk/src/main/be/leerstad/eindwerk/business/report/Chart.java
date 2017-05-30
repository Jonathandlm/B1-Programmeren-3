package be.leerstad.eindwerk.business.report;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.LinkedHashMap;
import java.util.Map;

public class Chart {

    public static JFreeChart getSimpleChart(LinkedHashMap<String, Number> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Number> entry : data.entrySet()) {
            dataset.addValue(entry.getValue(), "", entry.getKey());
        }

        return ChartFactory.createBarChart(null, null, null,
                dataset, PlotOrientation.VERTICAL, false, false, false);
    }

    public static JFreeChart getComplexChart(LinkedHashMap<String, LinkedHashMap<String, Number>> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, LinkedHashMap<String, Number>> entry : data.entrySet()) {
            for (Map.Entry<String, Number> innerEntry : entry.getValue().entrySet()) {
                dataset.addValue(innerEntry.getValue(), entry.getKey(), innerEntry.getKey());
            }
        }

        return ChartFactory.createBarChart(null, null, null,
                dataset, PlotOrientation.VERTICAL, true, true, true);
    }

}
