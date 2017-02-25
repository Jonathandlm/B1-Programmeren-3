package be.leerstad.exceptionhandling.withFinallyStatement;

public class BelowZeroException extends Exception {

    private static final long serialVersionUID = -6668638338686078822L;

    public BelowZeroException(String message) {
        super(message);
    }

    public BelowZeroException() {
        super("account below zero, transaction not executed");
    }
}
