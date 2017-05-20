package be.leerstad.eindwerk.service;

public class DAOException extends Exception {
    private static final long serialVersionUID = 16061981L;
    private String message;

    public DAOException() {
    }

    public DAOException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }
}