package be.leerstad.evaluatieoefeningen.parking;

import java.util.Comparator;

public class VehicleComparator implements Comparator<Vehicle> {

    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        // First not motorized vehicles
        int i = Boolean.compare(o1.isMotorized(), o2.isMotorized());
        if (i != 0) return i;

        // Then order by increasing nbr passenger seats
        i = Integer.compare(o1.getNbrPassengerSeats(), o2.getNbrPassengerSeats());
        if (i != 0) return i;

        // Then 'natural' order of vehicles: model and type
        return o1.compareTo(o2);
    }
}
