package be.leerstad.exceptionhandling.withTryCatchExceptions;

public class TooHighDebetException extends Exception {

    private static final long serialVersionUID = -4720915609777915211L;

    public TooHighDebetException() {
        this("teveel afgehaald");

    }

    public TooHighDebetException(String arg0) {
        super(arg0);

    }

}
