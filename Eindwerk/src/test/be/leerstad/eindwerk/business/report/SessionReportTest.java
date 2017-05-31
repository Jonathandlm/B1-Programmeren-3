package be.leerstad.eindwerk.business.report;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SessionReportTest {

    @Test
    public void testGetListName() {
        assertEquals(SessionReport.USER_BYTES_BY_MONTH.getListName(), "User Bytes By Month");
        assertEquals(SessionReport.USER_TIME_BY_MONTH.getListName(), "User Time By Month");
    }

    @Test
    public void testGetFileName() {
        assertEquals(SessionReport.MONTHlY_BYTES_BY_YEAR.getFileName(), "MonthlyBytesByYear");
        assertEquals(SessionReport.values()[3].getFileName(), "TopSitesByVisits");
    }

    @Test
    public void testGetDescription() {
        assertEquals(SessionReport.valueOf("TOP_SITES_BY_TIME").getDescription(),
                "This report gives an overview of the most visited sites in number of total visit time.");
    }

    @Test
    public void testGetSelector() {
        assertEquals(SessionReport.USER_BYTES_BY_MONTH.getSelector(),"month");
        assertEquals(SessionReport.TOP_SITES_BY_BYTES.getSelector(),"");
        assertEquals(SessionReport.TOP_SITES_BY_TIME.getSelector(),"");
        assertEquals(SessionReport.TOP_SITES_BY_VISITS.getSelector(),"");
    }

}