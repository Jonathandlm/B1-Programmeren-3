package be.leerstad.exceptionhandling.exceptionGenerator;

public class ExceptionTwo extends ExceptionMaster {

    private static final long serialVersionUID = -5384026224790140506L;

    public ExceptionTwo(String message) {
        super(message);
    }

    public ExceptionTwo() {
        this("Exception Two is throwed");
    }
}
