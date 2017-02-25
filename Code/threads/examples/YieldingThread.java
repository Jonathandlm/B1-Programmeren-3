package be.leerstad.threads.examples;


public class YieldingThread extends Thread {
    private static int threadCount = 0;
    private int countDown = 50;

    public YieldingThread() {
        // store the thread name
        super("" + ++threadCount);
        start();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            new YieldingThread();
    }

    public String toString() {
        return "#" + getName() + ": " + countDown;
    }

    public void run() {
        while (true) {
            System.out.println(this);
            if (--countDown == 0)
                return;
            Thread.yield();
        }
    }
}
