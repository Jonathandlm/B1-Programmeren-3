package be.leerstad.junit.opdracht07;

public class Thermostat {

    private Temperature currentTemperature;
    private Temperature targetTemperature;
    private boolean status;

    public Thermostat() {
        this.currentTemperature = new Temperature(0);
        this.targetTemperature = new Temperature(0);
    }

    public void setCurrentTemperature(Temperature t) {
        currentTemperature = t;
        evaluate();
    }

    public void setTargetTemperature(Temperature t) {
        targetTemperature = t;
        evaluate();
    }

    public boolean isHeating() {
        return status;
    }

    private void evaluate() {
        status = currentTemperature.getValue() < targetTemperature.getValue();
    }
}
