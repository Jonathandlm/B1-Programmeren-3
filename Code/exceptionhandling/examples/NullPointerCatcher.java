package be.leerstad.exceptionhandling.examples;

public class NullPointerCatcher {
    private String nullPointer;

    public NullPointerCatcher() {
    }

    public static void main(String[] args) {
        NullPointerCatcher nullPointerCatcher = new NullPointerCatcher();
        nullPointerCatcher.createNullPointer();
        System.out.println("goe bezig");

    }

    public void createNullPointer() {
        try {
            nullPointer.toString();
        } catch (NullPointerException npe) {
            System.out.println(npe.getCause());
        }
    }
}
