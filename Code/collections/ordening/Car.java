package be.leerstad.collections.ordening;

public class Car implements Comparable<Car> {
    private String chassisNbr;
    private String make;

    public Car(String chassisNbr, String make) {
        this.chassisNbr = chassisNbr;
        this.make = make;
    }

    @Override
    public String toString() {

        return chassisNbr + " " + make;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return (getChassisNbr() != null ? getChassisNbr().equals(car.getChassisNbr()) : car.getChassisNbr() == null)
                && (getMake() != null ? getMake().equals(car.getMake()) : car.getMake() == null);

    }

    @Override
    public int hashCode() {
        int result = getChassisNbr() != null ? getChassisNbr().hashCode() : 0;
        result = 31 * result + (getMake() != null ? getMake().hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Car o) {
        if (!(this.getChassisNbr().equals(o.getChassisNbr()))) {
            return this.chassisNbr.compareTo(o.getChassisNbr());
        }
        return this.getMake().compareTo(o.getMake());
    }

    public String getChassisNbr() {
        return chassisNbr;
    }

    public String getMake() {
        return make;
    }

}
