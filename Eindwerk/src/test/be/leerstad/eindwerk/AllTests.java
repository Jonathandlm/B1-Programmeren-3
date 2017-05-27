package be.leerstad.eindwerk;

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
