package be.leerstad.junit.test.opdracht05;

import be.leerstad.junit.opdracht05.Temperature;
import be.leerstad.junit.opdracht05.Thermostat;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ThermostatTest {
    private Thermostat thermostat;

    @Before
    public void init() {
        thermostat = new Thermostat();
    }

    @Test
    public void testChangeCurrent() {
        Temperature target = new Temperature(20F);
        Temperature current = new Temperature(0);
        thermostat.setTargetTemperature(target);

        for (float t = 15; t < 20; t += 0.1F) {
            current.setValue(t);
            thermostat.setCurrentTemperature(current);
            assertTrue(thermostat.isHeating());
        }
        for (float t = 20; t < 25; t += 0.1F) {
            current.setValue(t);
            thermostat.setCurrentTemperature(current);
            assertFalse(thermostat.isHeating());
        }
        for (float t = 25; t >= 20; t -= 0.1F) {
            current.setValue(t);
            thermostat.setCurrentTemperature(current);
            assertFalse(thermostat.isHeating());
        }
        for (float t = 19.9F; t >= 15; t -= 0.1F) {
            current.setValue(t);
            thermostat.setCurrentTemperature(current);
            assertTrue(thermostat.isHeating());
        }
    }

    @Test
    public void testChangeTarget() {
        Temperature target = new Temperature(0F);
        Temperature current = new Temperature(20F);
        thermostat.setCurrentTemperature(current);

        for (float t = 15; t <= 20; t += 0.1F) {
            target.setValue(t);
            thermostat.setTargetTemperature(target);
            assertFalse(thermostat.isHeating());
        }
        for (float t = 20.01F; t < 25; t += 0.1F) {
            target.setValue(t);
            thermostat.setTargetTemperature(target);
            assertTrue(thermostat.isHeating());
        }
        for (float t = 25; t > 20; t -= 0.1F) {
            target.setValue(t);
            thermostat.setTargetTemperature(target);
            assertTrue(thermostat.isHeating());
        }
        for (float t = 20F; t >= 15; t -= 0.1F) {
            target.setValue(t);
            thermostat.setTargetTemperature(target);
            assertFalse(thermostat.isHeating());
        }
    }
}
