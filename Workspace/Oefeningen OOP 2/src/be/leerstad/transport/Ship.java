package be.leerstad.transport;

public class Ship {
    private final int MAXIMUM_PLACES = 100;
    private final int MAXIMUM_WEIGHT = 95000;
    private int totalPlaces;
    private double totalWeight;
    private int numberVehicles;

    public Ship(){
        this.totalPlaces = 0;
        this.totalWeight = 0;
        this.numberVehicles = 0;
    }

    Vehicle[] vehicles = new Vehicle[numberVehicles];

    public void addVehicle(Vehicle newVehicle){
        if (checkPlaces(newVehicle) && checkWeight(newVehicle)){
            numberVehicles++;
            Vehicle[] tempVehicles = new Vehicle[numberVehicles];
            for (int vehicleIndex = 0; vehicleIndex < vehicles.length; vehicleIndex++) {
                tempVehicles[vehicleIndex] = vehicles[vehicleIndex];
            }
            tempVehicles[numberVehicles - 1] = newVehicle;
            vehicles = tempVehicles;
            totalPlaces += newVehicle.getVolume();
            totalWeight += newVehicle.getWeight();
        }
        else System.out.println("Vehicle can't be loaded on this ship!");
    }

    public void removeVehicle(){
        if (numberVehicles > 0) {
            numberVehicles--;
            Vehicle[] tempVehicles = new Vehicle[numberVehicles];
            for (int vehicleIndex = 0; vehicleIndex < tempVehicles.length; vehicleIndex++) {
                tempVehicles[vehicleIndex] = vehicles[vehicleIndex];
            }
            totalPlaces -= vehicles[numberVehicles].getVolume();
            totalWeight -= vehicles[numberVehicles].getWeight();
            vehicles = tempVehicles;
        }
        else System.out.println("No vehicles to be removed!");
    }

    public double totalWeight(){
        return totalWeight;
    }

    public String getContent(){
        return String.format("%f kg and %d places", totalWeight, totalPlaces);
    }

    private boolean checkPlaces(Vehicle vehicle){
        return MAXIMUM_PLACES >= totalPlaces + vehicle.getVolume();
    }

    private boolean checkWeight(Vehicle vehicle){
        return MAXIMUM_WEIGHT >= totalWeight + vehicle.getWeight();
    }

}
