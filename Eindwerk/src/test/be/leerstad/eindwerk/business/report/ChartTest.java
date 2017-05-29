package be.leerstad.eindwerk.business.report;

import org.jfree.chart.JFreeChart;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class ChartTest {

    private Map<String, Number> smallMap;
    private Map<String, Map<String, Number>> nestedMap;
    private JFreeChart jFreeChart;

    @Before
    public void setUp() {
        smallMap = new HashMap<>();
        nestedMap = new HashMap<>();
        smallMap.put("Small 1", 1);
        smallMap.put("Small 2", 2);
        smallMap.put("Small 3", 3);
        nestedMap.put("Nested 1", smallMap);
        nestedMap.put("Nested 2", smallMap);
        nestedMap.put("Nested 3", smallMap);
    }

    @Test
    public void testConstructor() {
        Chart chart = new Chart();
        assertNotNull(chart);
    }

    @Test
    public void testGetSimpleChart() {
        jFreeChart = Chart.getSimpleChart(smallMap);
        assertNotNull(jFreeChart);
    }

    @Test
    public void testGetComplexChart() {
        jFreeChart = Chart.getComplexChart(nestedMap);
        assertNotNull(jFreeChart);
    }

}