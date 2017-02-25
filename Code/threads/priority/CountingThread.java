package be.leerstad.threads.priority;

/**
 * @author Peter Hardeel
 */
public class CountingThread extends Thread {
    private int tick;

    public CountingThread(String string) {
        super(string);
    }

    public void run() {
        while (tick < 1000000) {
            tick++;
            for (int holding = 0; holding < 2000; holding++) {
            }
            if (tick % 50000 == 0)
                System.out.println(getName() + " is now on " + tick);
        }
        System.out.println(getName() + " is terminated.");
    }
}
