package be.leerstad.collections.ordening;

import java.util.Comparator;

public class ChassisNumberComparator implements Comparator<Car> {

    @Override
    public int compare(Car car1, Car car2) {
        return car2.getChassisNbr().compareTo(car1.getChassisNbr());
    }

}
