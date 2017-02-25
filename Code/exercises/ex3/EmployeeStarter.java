
package be.leerstad.javabasics.exercises.ex3;


public class EmployeeStarter {

    public static void main(String[] args) {
        final Employee e1 = new Employee("Bart", "De Cock");
        final Employee e2 = new Employee("Lucas", "Sterckx");
        final Employee e3 = new Employee("Tars", "Bracke");
        final Employee e4 = new Employee("Johan", "Van Roste");
        final Employee e5 = new Employee("Peter", "Hardeel");

        System.out.println(e1.getFullName());
        System.out.println(e2.getFullName());
        System.out.println(e3.getFullName());
        System.out.println(e4.getFullName());
        System.out.println(e5.getFullName());
        System.out.println();

        System.out.println(e1.toString());
        System.out.println(e2.toString());
        System.out.println(e3.toString());
        System.out.println(e4.toString());
        System.out.println(e5.toString());
        System.out.println();

        //calls also toString()
        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);
        System.out.println(e4);
        System.out.println(e5);
    }
}
