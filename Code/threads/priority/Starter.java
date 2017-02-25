package be.leerstad.threads.priority;

/**
 * @author Peter Hardeel
 */
public class Starter {

    public static void main(String[] args) {
        Thread tr1 = new CountingThread("One");
        Thread tr2 = new CountingThread("Two");
        Thread tr3 = new CountingThread("three");
        System.out.println(tr1.getPriority());
        System.out.println(Thread.NORM_PRIORITY);
        tr1.setPriority(10);
        tr2.setPriority(1); //highest number goes first
        tr3.setPriority(4);
        tr1.start();
        tr2.start();
        tr3.start();

    }
}
