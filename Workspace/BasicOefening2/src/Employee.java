public class Employee {
    private String foreName;
    private String name;
    private int employeeID = 1;
    private static int nextEmployeeID = 1;

    public Employee(String foreName, String name){
        this.foreName = foreName;
        this.name = name;
        this.employeeID = nextEmployeeID;
        this.nextEmployeeID++;
    }

    public String toString(){
        return String.format("%s %s (%d)", foreName, name, employeeID);
    }
}
