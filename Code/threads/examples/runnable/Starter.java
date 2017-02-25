package be.leerstad.threads.examples.runnable;

/**
 * @author Peter Hardeel
 */
public class Starter {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Thread tr1 = new Thread(new DemoRunnable("One"));
        Thread tr2 = new Thread(new DemoRunnable("Two"));
        tr1.start();
        tr2.start();
        System.out.println("main stops here");

    }
}
