package be.leerstad.exercise1;

import java.util.Locale;

public class TestPayRollSystem {

    public static void main(String[] args) {
        PayRollSystem payRollSystem = new PayRollSystem();
        payRollSystem.setPayRollBonus(0.10);

        Employee peter = new SalariedEmployee("Peter", "Hardeel", "111-11-1111", 800);
        Employee johan = new HourlyEmployee("Johan", "Van Roste", "222-22-2222", 16.75, 40);
        Employee sacha = new CommissionEmployee("Sacha", "Demaere", "333-33-3333", 10000, 0.06);
        Employee manuela = new BasePlusCommissionEmployee("Manuela", "Deplucker", "444-44-4444", 5000, 0.04, 300);

        payRollSystem.addEmployee(peter);
        payRollSystem.addEmployee(johan);
        payRollSystem.addEmployee(sacha);
        payRollSystem.addEmployee(manuela);

        // Title
        System.out.printf("%s :\n\n", payRollSystem.getClass().getSimpleName());

        // Print Employee Earnings - add Bonus for BasePlusCommissionEmployee
        for (Employee employee : payRollSystem.getEmployeeList()) {
            System.out.printf(Locale.US, "%s\n", employee);
            if (employee instanceof BasePlusCommissionEmployee){
                BasePlusCommissionEmployee bonusEmployee = (BasePlusCommissionEmployee) employee;
                bonusEmployee.setBaseSalary(bonusEmployee.getBaseSalary() * (1 + payRollSystem.getPayRollBonus()));
                System.out.printf(Locale.US,"base salary with %,.2f%% bonus: €%,.2f\n",
                        100*payRollSystem.getPayRollBonus(), bonusEmployee.getBaseSalary());
            }
            System.out.printf(Locale.US, "earned: €%,.2f\n\n", employee.earnings());
        }

        // Print Employee Types
        for (int employeeIndex = 0; employeeIndex < payRollSystem.getEmployeeList().size(); employeeIndex++) {
            System.out.printf("Employee %d is a %s\n",
                    employeeIndex, payRollSystem.getEmployeeList().get(employeeIndex).getClass().getCanonicalName());
        }
    }
}
