package be.leerstad.eindwerk;

import be.leerstad.eindwerk.model.SessionTest;
import be.leerstad.eindwerk.model.VisitTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SessionTest.class,
        VisitTest.class
})

public class ModelTests {

}
