package be.leerstad.exercise1;

import java.util.Locale;

class CommissionEmployee extends Employee {
    private double grossSales;
    private double commissionRate;

    CommissionEmployee(String firstName, String lastName, String socialSecurityNumber,
                              double grossSales, double commissionRate) {
        super(firstName, lastName, socialSecurityNumber);
        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
    }

    private double getGrossSales() { return grossSales; }
    private double getCommissionRate() { return commissionRate; }

    @Override
    public double earnings() {
        return getGrossSales() * getCommissionRate();
    }

    @Override
    public String toString(){
        return String.format(Locale.US, "commission employee: %s\n" +
                        "gross sales: €%,.2f; commission rate: %,.2f",
                super.toString(), getGrossSales(), getCommissionRate());
    }
}