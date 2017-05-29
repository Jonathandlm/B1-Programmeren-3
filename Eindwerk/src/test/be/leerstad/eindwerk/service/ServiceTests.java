package be.leerstad.eindwerk.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BaseDAOTest.class,
        DAOExceptionTest.class,
        InteractionDAOImplTest.class,
        LogfileDAOImplTest.class,
        SchoolDAOImplTest.class,
        SessionDAOImplTest.class,
        SiteApplicationDAOImplTest.class,
        SiteDAOImplTest.class,
        UserDAOImplTest.class,
        VisitDAOImplTest.class
})

public class ServiceTests {

}
