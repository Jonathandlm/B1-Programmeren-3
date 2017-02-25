package be.leerstad.evaluatieoefeningen.parking;

import java.util.Collection;

public class Bike extends Vehicle {

    public Bike(String model, String type, String color) {
        super(model, type, color);
    }

    @Override
    public boolean takePassengers(Collection passengers) {
        return false;
    }
}
