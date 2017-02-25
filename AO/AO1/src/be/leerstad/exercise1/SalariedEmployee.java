package be.leerstad.exercise1;

import java.util.Locale;

class SalariedEmployee extends Employee {
    private double weeklySalary;

    SalariedEmployee(String firstName, String lastName, String socialSecurityNumber, double weeklySalary) {
        super(firstName, lastName, socialSecurityNumber);
        this.weeklySalary = weeklySalary;
    }

    private double getWeeklySalary() { return weeklySalary; }

    @Override
    public double earnings() {
        return getWeeklySalary();
    }

    @Override
    public String toString(){
        return String.format(Locale.US, "salaried employee: %s\n" +
                "weekly salary: €%,.2f",
                super.toString(), getWeeklySalary());
    }
}
