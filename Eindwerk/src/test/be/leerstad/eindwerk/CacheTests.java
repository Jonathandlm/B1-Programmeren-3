package be.leerstad.eindwerk;

import be.leerstad.eindwerk.business.cache.LogfileCacheTest;
import be.leerstad.eindwerk.business.cache.SchoolCacheTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LogfileCacheTest.class,
        SchoolCacheTest.class
})

public class CacheTests {

}
