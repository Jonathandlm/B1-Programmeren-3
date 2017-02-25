public class EmptyException extends Exception {
    public EmptyException() {
        super();
    }
    public EmptyException(String message) {
        super(message);
    }
    public EmptyException(Throwable cause) {
        super(cause);
    }
    public EmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}

