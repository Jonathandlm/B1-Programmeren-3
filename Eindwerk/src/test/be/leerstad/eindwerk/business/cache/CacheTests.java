package be.leerstad.eindwerk.business.cache;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LogfileCacheTest.class,
        SchoolCacheTest.class,
        SessionCacheTest.class,
        SiteApplicationCacheTest.class,
        SiteCacheTest.class,
        UserCacheTest.class,
        VisitCacheTest.class
})

public class CacheTests {

}
