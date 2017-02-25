package be.leerstad.exceptionhandling.exceptionGenerator;


public class ExceptionFour extends ExceptionMaster {

    private static final long serialVersionUID = 6572382056294698021L;

    public ExceptionFour(String message) {
        super(message);
    }

    public ExceptionFour() {
        this("Exception Four is throwed");
    }
}
