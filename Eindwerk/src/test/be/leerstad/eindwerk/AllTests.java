package be.leerstad.eindwerk;

import be.leerstad.eindwerk.business.SessionParserTest;
import be.leerstad.eindwerk.business.VisitParserTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SessionParserTest.class,
        VisitParserTest.class
})
public class AllTests {
}
