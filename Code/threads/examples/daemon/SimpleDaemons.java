package be.leerstad.threads.examples.daemon;

public class SimpleDaemons extends Thread {
    public SimpleDaemons() {
        // must be called before start()
        setDaemon(true);
        start();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++)
            new SimpleDaemons();
    }

    public void run() {
        while (true) {
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(this);
        }
    }
}
