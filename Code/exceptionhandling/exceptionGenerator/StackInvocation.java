package be.leerstad.exceptionhandling.exceptionGenerator;


public class StackInvocation {

    public static void main(String[] args) {

        new StackInvocation().method1();
    }

    private void method1() {
        try {
            method2();
        } catch (ExceptionMaster e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void method2() throws ExceptionMaster {
        method3();
    }

    private void method3() throws ExceptionMaster {
        throw new ExceptionMaster();
    }
}
