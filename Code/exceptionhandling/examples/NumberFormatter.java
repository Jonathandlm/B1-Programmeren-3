package be.leerstad.exceptionhandling.examples;

public class NumberFormatter {

    public NumberFormatter() {
    }

    public static void main(String[] args) {
        NumberFormatter formatter = new NumberFormatter();
        System.out.println(formatter.format2("c12"));
        // System.out.println(formatter.format("c12"));

    }

    public int format(String integer) {
        return Integer.parseInt(integer);
    }

    public int format2(String integer) {
        try {
            return Integer.parseInt(integer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("You must enter an Integer");
            return 0;
        }
    }

}
