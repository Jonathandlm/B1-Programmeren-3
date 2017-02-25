package be.leerstad.exercise1;

import java.util.ArrayList;
import java.util.List;

class PayRollSystem {
    private List<Employee> employeeList = new ArrayList<>();
    private double payRollBonus;

    List<Employee> getEmployeeList() { return employeeList; }
    double getPayRollBonus() { return payRollBonus; }
    void setPayRollBonus(double payRollBonus) { this.payRollBonus = payRollBonus; }

    void addEmployee(Employee employee){
        employeeList.add(employee);
    }

}