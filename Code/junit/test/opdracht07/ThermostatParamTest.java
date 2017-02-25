package be.leerstad.junit.test.opdracht07;

import be.leerstad.junit.opdracht07.Temperature;
import be.leerstad.junit.opdracht07.Thermostat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ThermostatParamTest {
    boolean isHeating;
    private Temperature target;
    private Temperature current;
    private Thermostat thermostat;

    public ThermostatParamTest(float target, float current, boolean isHeating) {
        this.target = new Temperature(target);
        this.current = new Temperature(current);
        this.isHeating = isHeating;
    }

    @Parameters
    public static Collection<Object[]> getTestData() {
        Object[][] data = {{20F, 15F, true},
                {20F, 25F, false},
                {20F, 20F, false}};
        return Arrays.asList(data);
    }

    @Before
    public void init() {
        thermostat = new Thermostat();
    }

    @Test
    public void testThermostat() {
        System.out.println("in test method");
        thermostat.setCurrentTemperature(current);
        thermostat.setTargetTemperature(target);
        assertEquals(isHeating, thermostat.isHeating());
    }

}
