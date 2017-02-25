package be.leerstad.collections.parking;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Parking {

	private Set<Car> vehicles;

	public Parking() {
		vehicles = new TreeSet<>();
	}

	public Set<Car> getVehicles() {
		return vehicles;
	}


	public boolean addVehicle(Car car){
		return vehicles.add(car);
	}

}