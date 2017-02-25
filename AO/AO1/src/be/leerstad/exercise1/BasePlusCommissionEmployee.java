package be.leerstad.exercise1;

import java.util.Locale;

class BasePlusCommissionEmployee extends CommissionEmployee {
    private double baseSalary;

    BasePlusCommissionEmployee(String firstName, String lastName, String socialSecurityNumber,
                                      double grossSales, double commissionRate, double baseSalary) {
        super(firstName, lastName, socialSecurityNumber, grossSales, commissionRate);
        this.baseSalary = baseSalary;
    }

    double getBaseSalary() { return baseSalary; }
    void setBaseSalary(double baseSalary) { this.baseSalary = baseSalary; }

    @Override
    public double earnings() {
        return super.earnings() + getBaseSalary();
    }

    @Override
    public String toString(){
        return String.format(Locale.US, "base-salaried %s; base salary: €%,.2f",
                super.toString(), getBaseSalary());
    }
}
