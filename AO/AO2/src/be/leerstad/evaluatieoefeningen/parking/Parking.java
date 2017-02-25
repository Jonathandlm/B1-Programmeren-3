package be.leerstad.evaluatieoefeningen.parking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Parking {

    /* EXTRA VRAAG: Antwoord: JA
    **
    ** De elementen van een gesorteerde collectie worden automatisch gerankschikt. Deze objecten moeten instanties zijn
    ** van een klasse die Comparable implementeert en bijgevolg ook de methode compareTo overschrijft.
    **
    ** Indien de constructor geen comparator bevat, dan gebeurt de sortering volgens de overschreven compareTo.
    **      TreeSet<Vehicle> parking = new TreeSet<Vehicle>();
    **
    ** Als een andere sortering gewenst is, kan een Comparator meegegeven worden in de constructor.
    **      TreeSet<Vehicle> parking = new TreeSet<Vehicle>(new VehicleComparator());
    **
    ** Specifiek voor deze Comparator vehicleComparator, zien we dat aan alle voorwaarden voldaan zijn: Vehicle
    ** implementeert Comparable en de methode compareTo is overschreven.
    **
    ** Wel dient de opmerking gegeven worden dat deze comparator geen rekening houdt met kleur. Dus in een parking van
    ** het type HashSet worden voertuigen vergeleken via de methode equals (deze houdt wel rekening met kleur), maar
    ** in een parking van het type TreeSet worden de voertuigen vergeleken via de methode compareTo, die geen rekening
    ** met kleur. Hierdoor kan het zijn dat, na dezelfde voertuigen toe te voegen in beide Sets, er toch minder
    ** voertuigen zijn in de TreeSet parking.
    **
    **      Bike bike1 = new Bike("Cannondale", "Synapse Carbon Ultegra 3", "black");
    **      Bike bike2 = new Bike("Cannondale", "Synapse Carbon Ultegra 3", "green");
    **      HashSet<Vehicle> parking1 = new HashSet<Vehicle>();
    **      parking1.addVehicle(bike1);
    **      parking1.addVehicle(bike2);
    **      TreeSet<Vehicle> parking2 = new TreeSet<Vehicle>();
    **      parking2.addVehicle(bike1);
    **      parking2.addVehicle(bike2);
    **
    ** Hier zal parking1 2 voertuigen bevatten en parking2 slechts 1 voertuig.
    **
    ** Uitleg uit java.util.SortedSet javadoc:
    **  Note that the ordering maintained by a sorted set (whether or not an explicit comparator is provided) must be
    **  consistent with equals if the sorted set is to correctly implement the Set interface. (See the Comparable
    **  interface or Comparator interface for a precise definition of consistent with equals.) This is so because the
    **  Set interface is defined in terms of the equals operation, but a sorted set performs all element comparisons
    **  using its compareTo (or compare) method, so two elements that are deemed equal by this method are, from the
    **  standpoint of the sorted set, equal. The behavior of a sorted set is well-defined even if its ordering is
    **  inconsistent with equals; it just fails to obey the general contract of the Set interface.
    */

    private int nbrVehicles;
    private List<Vehicle> vehicleList;

    public Parking() {
        this.nbrVehicles = 0;
        this.vehicleList = new ArrayList<>();
    }

    public int getNbrVehicles() {
        return nbrVehicles;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public boolean addVehicle(Vehicle vehicle) {
        if (vehicleList.add(vehicle)) {
            nbrVehicles++;
            return true;
        }
        return false;
    }

    public List<Vehicle> selectVehicles(String model, String type) {
        List<Vehicle> resultList = new ArrayList<>();
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getModel() == model && vehicle.getType() == type) resultList.add(vehicle);
        }
        return resultList;
    }

    public void sortVehicles(Comparator vehicleComparator){
        Collections.sort(vehicleList,vehicleComparator);
    }
}
