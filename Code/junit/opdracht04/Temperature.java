package be.leerstad.junit.opdracht04;

public class Temperature {
    private static final float FREEZING_TEMPERATURE = 0.00F;
    private static final float BOILING_TEMPERATURE = 100.00F;
    private static final float PRECISION = 0.01F;
    private static final float LOWEST_TEMPERATURE = -273.15F;
    private float value;

    public Temperature(float value) {
        check(value);
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        check(value);
        this.value = value;
    }

    public boolean isFreezing() {
        return value <= FREEZING_TEMPERATURE;
    }

    public boolean isBoiling() {
        return value >= BOILING_TEMPERATURE;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Temperature)) return false;
        float dif = ((Temperature) o).value - this.value;
        return (Math.abs(dif) < PRECISION);
    }

    @Override
    public int hashCode() {
        return (int) (value * PRECISION);
    }

    private void check(float value) {
        if (value < LOWEST_TEMPERATURE) {
            throw new InvalidTemperatureException();
        }
    }
}
