package be.leerstad.eindwerk;

import be.leerstad.eindwerk.business.report.ChartTest;
import be.leerstad.eindwerk.business.report.QueryTest;
import be.leerstad.eindwerk.business.report.SessionReportTest;
import be.leerstad.eindwerk.business.report.VisitReportTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ChartTest.class,
        QueryTest.class,
        SessionReportTest.class,
        VisitReportTest.class
})

public class ReportTests {

}
