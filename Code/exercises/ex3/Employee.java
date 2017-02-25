package be.leerstad.javabasics.exercises.ex3;

public class Employee {

    private static int counter;
    private String firstName;
    private String lastName;
    private int employeeID;

    public Employee() {
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        employeeID = ++counter;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o != null && o instanceof Employee) {
            Employee employee = (Employee) o;
            return getFullName().equals(employee.getFullName());
        }
        return false;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(getFullName());
        stringBuffer.append(", employee n? ");
        stringBuffer.append(employeeID);
        return stringBuffer.toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getFullName() {
        StringBuffer stringBuffer = new StringBuffer(firstName);
        stringBuffer.append(" ");
        stringBuffer.append(lastName);
        return stringBuffer.toString();
    }

}
