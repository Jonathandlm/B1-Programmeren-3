package be.leerstad.evaluatieoefeningen.parking;

import java.util.Collection;
import java.util.Set;

public class Motorcycle extends Vehicle {

    public Motorcycle(String model, String type, String color, int power) {
        super(model, type, color);
        this.motorized = true;
        this.power = power;
        this.nbrPassengerSeats = 1;
    }

    @Override
    public boolean takePassengers(Collection passengers) {
        Set<Person> currentPassengers = getPassengerList();
        // Check if passenger is a Person and not already in vehicle
        for (Object passenger : passengers) {
            if (!(passenger instanceof Person) || currentPassengers.contains(passenger)) return false;
        }
        // Check if enough room left to add all passengers
        if (getNbrPassengerSeats() - currentPassengers.size() < passengers.size()) return false;
        // All checks passed: add passengers
        return currentPassengers.addAll(passengers);
    }
}
