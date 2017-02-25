package be.leerstad.junit.test.opdracht05;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({TemperatureTest.class, ThermostatTest.class})
public class AllTests {

}
