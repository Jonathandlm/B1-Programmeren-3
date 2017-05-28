package be.leerstad.eindwerk.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CacheUtilTest.class,
        DateUtilTest.class,
        MySqlUtilTest.class,
        PropertyUtilTest.class,
        RegexUtilTest.class
})

public class UtilTests {

}
