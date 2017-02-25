package be.leerstad.threads.examples.runnable;

/**
 * @author Peter Hardeel
 */
public class DemoRunnable implements Runnable {
    private String name;

    public DemoRunnable(String name) {
        this.name = name;
    }

    public void run() {
        for (int teller = 0; teller < 10; teller++) {
            System.out.println(teller + " " + getName());
            try {
                Thread.sleep((long) (Math.random() * 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(getName() + " Terminated .....");
    }

    public String getName() {
        return name;
    }
}
