public class NotEmptyException extends Exception {
    public NotEmptyException() {
        super();
    }
    public NotEmptyException(String message) {
        super(message);
    }
    public NotEmptyException(Throwable cause) {
        super(cause);
    }
    public NotEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
