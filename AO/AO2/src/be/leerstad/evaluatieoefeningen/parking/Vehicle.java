package be.leerstad.evaluatieoefeningen.parking;

import java.awt.Color;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class Vehicle implements Comparable<Vehicle> {

    private String model;
    private String type;
    private Color color;
    protected boolean motorized;
    protected int power;
    protected int nbrPassengerSeats;
    protected Set<Person> passengerList;

    public Vehicle(String model, String type, String color) {
        this.model = model;
        this.type = type;
        this.motorized = false;
        this.power = 0;
        this.nbrPassengerSeats = 0;
        this.passengerList = new HashSet();

        switch (color.toLowerCase()){
            case "black":
                this.color = Color.black;
                break;
            case "white":
                this.color = Color.white;
                break;
            case "blue":
                this.color = Color.blue;
                break;
            case "green":
                this.color = Color.green;
                break;
            case "red":
                this.color = Color.red;
                break;
            default:
                throw new IllegalArgumentException("Unknown color!");
        }
    }

    public String getModel() {
        return model;
    }
    public String getType() {
        return type;
    }
    public Color getColor() {
        return color;
    }
    public boolean isMotorized() {
        return motorized;
    }
    public int getPower() {
        return power;
    }
    public int getNbrPassengerSeats() {
        return nbrPassengerSeats;
    }
    public Set<Person> getPassengerList() {
        return passengerList;
    }

    public abstract boolean takePassengers(Collection passengers);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (!model.equals(vehicle.model)) return false;
        if (!type.equals(vehicle.type)) return false;
        return color.equals(vehicle.color);
    }

    @Override
    public int hashCode() {
        int result = model.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + color.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return color + " " + model + " " + type +
                ", with " + power +
                "hp and " + nbrPassengerSeats +
                " passenger seat" + (nbrPassengerSeats == 1 ? "" : "s");
    }

    @Override
    public int compareTo(Vehicle o) {
        if (!(this.getModel().equals(o.getModel())))
            return this.getModel().compareTo(o.getModel());
        return this.getType().compareTo(o.getType());
    }
}
