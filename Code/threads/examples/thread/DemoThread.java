package be.leerstad.threads.examples.thread;

/**
 * @author Peter Hardeel
 */
public class DemoThread extends Thread {
    public DemoThread(String string) {
        super(string);
    }


    public void run() {
        for (int teller = 0; teller <= 10; teller++) {
            System.out.println(teller + " " + getName());
            System.out.println(getThreadGroup().getName());
            try {
                sleep((long) (Math.random() * 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(getName() + " Terminated .....");
    }
}
