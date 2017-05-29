package be.leerstad.eindwerk.business.report;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VisitReportTest {

    @Test
    public void testGetListName() {
        assertEquals(VisitReport.MONTHLY_APPLICATION_BYTES.getListName(), "Monthly Application Traffic");
        assertEquals(VisitReport.SCHOOL_VISITS_BY_MONTH.getListName(), "School Visits By Month");
    }

    @Test
    public void testGetFileName() {
        assertEquals(VisitReport.MONTHLY_APPLICATION_VISITS.getFileName(), "MonthlyApplicationVisits");
        assertEquals(VisitReport.values()[2].getFileName(), "MonthlyApplicationBytes");
    }

    @Test
    public void testGetDescription() {
        assertEquals(VisitReport.valueOf("MONTHLY_APPLICATION_BYTES").getDescription(),
                "This report gives an overview of the total transferred bytes per month, grouped by every application.");
    }

    @Test
    public void testGetSelector() {
        assertTrue(VisitReport.SCHOOL_VISITS_BY_MONTH.getSelector());
        assertFalse(VisitReport.MONTHLY_APPLICATION_BYTES.getSelector());
    }
}