package be.leerstad.exceptionhandling.exceptionGenerator;

public class ExceptionMaster extends Exception {

    private static final long serialVersionUID = -4478229975353483261L;

    public ExceptionMaster(String message) {
        super(message);
    }

    public ExceptionMaster() {
        this("ExceptionMaster is throwed");
    }
}
