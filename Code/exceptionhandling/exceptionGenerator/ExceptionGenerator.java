package be.leerstad.exceptionhandling.exceptionGenerator;

public class ExceptionGenerator {

    public static void main(String[] args) {
        ExceptionGenerator generator = new ExceptionGenerator();
        generator.method1(2);
    }

    private void method1(int i) {
        try {
            method2(i);
        } catch (ExceptionOne e) {
            // e.printStackTrace();
            System.out.println(e.getMessage());

        }
    }

    private void method2(int i) throws ExceptionOne {
        try {
            method3(i);
        } catch (ExceptionTwo e) {
            // e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void method3(int i) throws ExceptionOne, ExceptionTwo {
        try {
            exceptionGeneratingMethod(i);
        } catch (ExceptionThree e) {
            // e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (ExceptionFour e) {
            // e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void exceptionGeneratingMethod(int i) throws ExceptionOne,
            ExceptionTwo, ExceptionThree, ExceptionFour {
        switch (i) {
            case 1:
                throw new ExceptionOne();
            case 2:
                throw new ExceptionTwo();
            case 3:
                throw new ExceptionThree();
            case 4:
                throw new ExceptionFour();

        }
    }
}
