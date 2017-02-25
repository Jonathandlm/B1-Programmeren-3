package be.leerstad.exceptionhandling.runtimeExceptions;

public class Calculator {

    public Calculator() {
    }

    public static void main(String[] args) {

        Calculator cal = new Calculator();
        System.out.println(cal.divide(10, 0));
    }

    private double divide(int first, int second) {
        return first / second;
    }
}
