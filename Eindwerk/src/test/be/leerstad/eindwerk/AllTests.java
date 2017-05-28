package be.leerstad.eindwerk;

import be.leerstad.eindwerk.business.cache.CacheTests;
import be.leerstad.eindwerk.business.parser.ParserTests;
import be.leerstad.eindwerk.business.printer.PrinterTests;
import be.leerstad.eindwerk.business.report.ReportTests;
import be.leerstad.eindwerk.model.ModelTests;
import be.leerstad.eindwerk.service.ServiceTests;
import be.leerstad.eindwerk.util.UtilTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CacheTests.class,
        ModelTests.class,
        ParserTests.class,
        PrinterTests.class,
        ReportTests.class,
        ServiceTests.class,
        UtilTests.class
})

public class AllTests {
}
