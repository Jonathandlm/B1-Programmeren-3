package be.leerstad.exercise1;

import java.util.Locale;

class HourlyEmployee extends Employee {
    private double hourlyWage;
    private double hoursWorked;

    HourlyEmployee(String firstName, String lastName, String socialSecurityNumber,
                          double hourlyWage, double hoursWorked) {
        super(firstName, lastName, socialSecurityNumber);
        this.hourlyWage = hourlyWage;
        this.hoursWorked = hoursWorked;
    }

    private double getHourlyWage() { return hourlyWage; }
    private double getHoursWorked() { return hoursWorked; }

    @Override
    public double earnings() {
        double overtime = 40;
        double premium = 0.50;
        return getHourlyWage() * getHoursWorked() +
                ((getHoursWorked() > overtime) ? (getHoursWorked() - overtime) * getHourlyWage() * premium : 0);
    }

    @Override
    public String toString(){
        return String.format(Locale.US, "hourly employee: %s\n" +
                        "hourly wage: €%,.2f; hours worked: %,.2f",
                super.toString(), getHourlyWage(), getHoursWorked());
    }
}
