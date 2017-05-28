package be.leerstad.eindwerk.business.report;

import be.leerstad.eindwerk.business.printer.Printer;
import be.leerstad.eindwerk.model.Session;
import be.leerstad.eindwerk.model.Visit;
import org.junit.Before;
import org.junit.Test;

import java.time.Year;
import java.time.YearMonth;
import java.util.Map;

public class QueryTest {

    private Query query;

    @Before
    public void init() {
        query = new Query();
    }

    @Test
    public void testGetMonthlyApplicationTotals() {
        Map<String, Map<String, Integer>> map = query.getMonthlyApplicationTotals(Visit::getNumberOfRequests);
        for (Map.Entry<String, Map<String, Integer>> entry : map.entrySet()) {
            System.out.println("*********************" + entry.getKey() + "*********************");
            for (Map.Entry<String, Integer> ent : entry.getValue().entrySet()) {
                System.out.println(" - " + ent.getKey() + " - " + ent.getValue());
            }
        }
        System.out.println("\n\n\n");
        Map<String, Map<String, Integer>> b = query.getMonthlyApplicationTotals(Visit::getTransferredBytes);
        for (Map.Entry<String, Map<String, Integer>> entry : b.entrySet()) {
            System.out.println("*********************" + entry.getKey() + "*********************");
            for (Map.Entry<String, Integer> ent : entry.getValue().entrySet()) {
                System.out.println(" - " + ent.getKey() + " - " + ent.getValue());
            }
        }
    }

    @Test
    public void testGetSchoolVisitsByMonth() {
        Map<String, Integer> map = query.getSchoolVisitsByMonth(YearMonth.of(2017, 1));
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(" - " + entry.getKey() + " - " + entry.getValue());
        }
    }

    @Test
    public void testGetUserTotalsByMonth() {
        Map<String, Integer> map = query.getUserTotalsByMonth(YearMonth.of(2017, 1), Session::getTotalTimeInSec);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(" - " + entry.getKey() + " - " + entry.getValue());
        }
    }

    @Test
    public void testGetMonthTotalsByYear() {
        Map<String, Integer> map = query.getMonthTotalsByYear(Year.of(2016), Session::getTransferredBytes);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(" - " + entry.getKey() + " - " + entry.getValue());
        }
    }

    @Test
    public void testGetSiteTotals() {
        Map<String, Integer> map1 = query.getSiteTotals(Session::getNumberOfRequests);
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            System.out.println(" - " + entry.getKey() + " - " + entry.getValue());
        }

        Map<String, Integer> map2 = query.getSiteTotals(Session::getTotalTimeInSec);
        for (Map.Entry<String, Integer> entry : map2.entrySet()) {
            System.out.println(" - " + entry.getKey() + " - " + entry.getValue());
        }

        Map<String, Integer> map3 = query.getSiteTotals(Session::getTransferredBytes);
        for (Map.Entry<String, Integer> entry : map3.entrySet()) {
            System.out.println(" - " + entry.getKey() + " - " + entry.getValue());
        }
    }

    @Test
    public void brol() {
        Printer printer = new Printer();
        try {
            printer.createPdfFromTemplate("test2.pdf", "Monthly Application Totals", query.getMonthlyApplicationTotals(Visit::getNumberOfRequests));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void broller() {

    }

}
