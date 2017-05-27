package be.leerstad.eindwerk;

import be.leerstad.eindwerk.business.parser.ParseFactoryTest;
import be.leerstad.eindwerk.business.parser.SessionParserTest;
import be.leerstad.eindwerk.business.parser.VisitParserTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ParseFactoryTest.class,
        SessionParserTest.class,
        VisitParserTest.class
})

public class ParserTests {

}
