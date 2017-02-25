package be.leerstad.transport;

public class Vehicle {
    protected double weight;
    protected int volume;

    public Vehicle(double weight, int volume){
        this.weight = weight;
        this.volume = volume;
    }

    public double getWeight(){
        return weight;
    }

    public int getVolume(){
        return volume;
    }
}
