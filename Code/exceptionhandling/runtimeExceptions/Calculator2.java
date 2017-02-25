package be.leerstad.exceptionhandling.runtimeExceptions;

public class Calculator2 {

    public Calculator2() {
    }

    public static void main(String[] args) {

        Calculator2 cal = new Calculator2();
        System.out.println("Result of division: " + cal.divide(10, 0));

    }

    private double divide(int first, int second) {

        double result = 0;
        try {
            result = first / second;
            System.out.println("Second statement of try block");

        } catch (ArithmeticException e) {
            System.out.println("An Exception occured in divide():");
            System.out.println("Exception Message: " + e.getMessage());
            System.out.println("Stack Trace of the exception:");

            e.printStackTrace(System.out);
        }
        System.out
                .println("A Runtime Exception does not necessary interrupt the execution of the program if catched!");
        System.out.println("After the catch clause!");
        return result;

    }
}
