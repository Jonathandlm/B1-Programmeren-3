package be.leerstad.exceptionhandling.exceptionGenerator;

public class ExceptionThree extends ExceptionMaster {

    private static final long serialVersionUID = -1047615095164083655L;

    public ExceptionThree(String message) {
        super(message);
    }

    public ExceptionThree() {
        this("Exception Tree is throwed");
    }
}
