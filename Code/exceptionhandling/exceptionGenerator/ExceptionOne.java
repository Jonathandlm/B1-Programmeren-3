package be.leerstad.exceptionhandling.exceptionGenerator;

public class ExceptionOne extends ExceptionMaster {

    private static final long serialVersionUID = 4729530516384727338L;

    public ExceptionOne(String message) {
        super(message);
    }

    public ExceptionOne() {
        this("Exception One is throwed");
    }
}
