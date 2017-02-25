package be.leerstad.threads.examples.thread;

/**
 * @author Peter Hardeel
 */
public class Starter {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Thread tr1 = new DemoThread("One");
        Thread tr2 = new DemoThread("Two");
        tr2.start();
        tr1.start();

    }
}
