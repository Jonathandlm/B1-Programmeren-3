package be.leerstad;

import org.apache.log4j.Logger;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class.getClass());

    public static void main(String[] args) {
	// write your code here
        CustomerOrder order = new CustomerOrder("Duvel",1,3);

        Main main = new Main();
        main.processOrder(order);
    }

    public void processOrder(CustomerOrder order){
        logger.error("Order ERROR");
    }
}
