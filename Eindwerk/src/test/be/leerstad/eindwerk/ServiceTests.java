package be.leerstad.eindwerk;

import be.leerstad.eindwerk.service.BaseDAOTest;
import be.leerstad.eindwerk.service.DAOExceptionTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BaseDAOTest.class,
        DAOExceptionTest.class
})

public class ServiceTests {

}
