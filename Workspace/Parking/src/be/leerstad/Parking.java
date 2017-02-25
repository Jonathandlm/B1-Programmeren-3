package be.leerstad;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Parking {

    private Set<Auto> parkingSet;

    public Parking(){
        parkingSet = new TreeSet<>();
    }

    public Set<Auto> getParkingSet() {
        return parkingSet;
    }

    void addAuto(Auto auto){
        parkingSet.add(auto);
    }
    void removeAuto(Auto auto){
        parkingSet.remove(auto);
    }
    int aantalAutos() { return parkingSet.size(); }

    @Override
    public String toString() {
        return "Parking met " + aantalAutos() + " auto(s):\n" +
                "parkingSet=" + parkingSet;
    }
}
