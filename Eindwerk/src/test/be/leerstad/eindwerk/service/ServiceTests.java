package be.leerstad.eindwerk.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BaseDAOTest.class,
        DAOExceptionTest.class,
        InteractionDAOImplTest.class,
        SessionDAOImplTest.class,
        SiteApplicationDAOImplTest.class,
        SiteDAOImplTest.class,
        VisitDAOImplTest.class
})

public class ServiceTests {

}
