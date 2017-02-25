package be.leerstad.exceptionhandling.withFailTest;

public class BelowZeroException extends Exception {

    private static final long serialVersionUID = 6176392133174518900L;
    private int saldo;

    public BelowZeroException(String message) {
        super(message);
    }

    public BelowZeroException() {
        this("Default:account below zero, transaction not executed");
    }

    public BelowZeroException(String message, int saldo) {
        this(message);
        this.saldo = saldo;
    }

    public int getSaldo() {
        return saldo;
    }

}
