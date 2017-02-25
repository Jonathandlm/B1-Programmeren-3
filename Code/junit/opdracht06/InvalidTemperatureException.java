package be.leerstad.junit.opdracht06;

public class InvalidTemperatureException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidTemperatureException(String message) {
        super(message);
    }

    public InvalidTemperatureException() {
    }

    public InvalidTemperatureException(String message,
                                       Throwable cause) {
        super(message, cause);

    }

    public InvalidTemperatureException(Throwable cause) {
        super(cause);

    }
}
