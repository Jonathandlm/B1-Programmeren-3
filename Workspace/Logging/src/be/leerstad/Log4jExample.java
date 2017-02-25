package be.leerstad;

import org.apache.log4j.Logger;

public class Log4jExample {

    private static Logger logger = Logger.getLogger(Log4jExample.class.getName());

    public static void main(String args[]) {
        CustomerOrder order1 = new CustomerOrder("Beer", 101, 20);
        CustomerOrder order2 = new CustomerOrder("Lemonade", 95, 10);
        CustomerOrder order3 = new CustomerOrder("Chocolate", 223, 5);

        Log4jExample demo = new Log4jExample();
        demo.processOrder(order1);
        demo.processOrder(order2);
        demo.processOrder(order3);
    }

    public void processOrder(CustomerOrder order) {

        logger.error(order.getProductName());

        logger.debug(order.getProductName() + " in debug");
/*
        logger.trace("Trace Message!");
        logger.debug("Debug Message!");
        logger.info("Info Message!");
        logger.warn("Warn Message!");
        logger.error("Error Message!");
        logger.fatal("Fatal Message!");*/
    }
}
