package be.leerstad.collections.ordening;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Garage {
    private Set<Car> cars;


    public Garage() {
        cars = new HashSet<>();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Garage garage = new Garage();
        garage.fillGarage();

        System.out.println("before sorting");
        System.out.println(garage.getCars());
        System.out.println("after sorting");
        garage.sort();
        System.out.println(garage.getCars());
        System.out.println("special sorting");
        garage.sort(new ChassisNumberComparator());
        System.out.println(garage.getCars());


    }

    private void sort(ChassisNumberComparator chassisNumberComparator) {


    }

    private void fillGarage() {
        Car bmw = new Car("123", "BMW");
        Car mercedes = new Car("124", "Mercedes");
        Car audi = new Car("125", "Audi");
        Car vw = new Car("123", "VW");
        cars.add(mercedes);
        cars.add(vw);
        cars.add(audi);
        cars.add(bmw);


    }

    private void sort() {
        cars = new TreeSet<>(cars);

    }

    public Set<Car> getCars() {
        return cars;
    }

}
