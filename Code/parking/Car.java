package be.leerstad.collections.parking;

/**
 * Created by peterhardeel on 13/12/2016.
 */
public class Car {
    private String model;
    private String make;
    private String VIN;

    public Car(String model, String make, String VIN){
        this.make = make;
        this.model = model;
        this.VIN =VIN;

    }

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public String getVIN() {
        return VIN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return VIN.equals(car.VIN);
    }

    @Override
    public int hashCode() {
        return VIN.hashCode();
    }
}
