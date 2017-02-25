package be.leerstad.threads.examples.animation;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class CarApplication extends JFrame implements Runnable {
    /**
     *
     */
    private static final long serialVersionUID = -5215129558230245851L;

    private Image image;

    private MyComponent area;

    private JScrollBar speedBar;

    private int x;

    private int y = 20;

    private Thread animationThread;

    public CarApplication(String text) {
        super(text);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 200);
        setLocation(20, 20);

        Container pane = getContentPane();
        area = new MyComponent();
        pane.add(area);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        pane.add(BorderLayout.SOUTH, controlPanel);

        speedBar = new JScrollBar(Scrollbar.HORIZONTAL, 50, 10,
                1, 100);
        controlPanel.add(BorderLayout.CENTER, speedBar);

        JButton startButton = new JButton("Start");
        controlPanel.add(BorderLayout.WEST, startButton);
        startButton.addActionListener(new StartButtonListener());

        JButton stopButton = new JButton("Stop");
        controlPanel.add(BorderLayout.EAST, stopButton);
        stopButton.addActionListener(new StopButtonListener());

        URL url = getClass().getResource("/be/leerstad/threads/examples/animation/auto.jpg");

        image = Toolkit.getDefaultToolkit().getImage(url);

        //image = Toolkit.getDefaultToolkit().getImage("auto.jpg");

        setVisible(true);
    }

    public static void main(String[] args) {
        new CarApplication("My Car");
    }

    public void run() {
        while (Thread.currentThread() == animationThread) {
            int delay = speedBar.getMaximum()
                    - speedBar.getValue()
                    - speedBar.getVisibleAmount();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
            if (++x >= area.getWidth()) {
                x = 0;
            }
            area.repaint(x - 1, y, image
                    .getWidth(CarApplication.this) + 1, image
                    .getHeight(CarApplication.this));
        }
    }

    private class StartButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            animationThread = new Thread(CarApplication.this);
            animationThread.setDaemon(true);
            animationThread.start();
        }
    }

    private class StopButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            animationThread = null;
        }
    }

    private class MyComponent extends JComponent {
        /**
         *
         */
        private static final long serialVersionUID = 3202017414979682711L;

        public void paintComponent(Graphics g) {
            g.clearRect(0, 0, getWidth(), getHeight());
            g.drawImage(image, x, y, this);
        }
    }

}