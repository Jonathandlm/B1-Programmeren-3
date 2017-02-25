package be.leerstad.javabasics.enums.coffee;


public class CoffeeTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Coffee drink = new Coffee();
        drink.size = CoffeeSize.BIG;

        Coffee drink2 = new Coffee();
        drink2.size = CoffeeSize.OVERWHELMING;

        System.out.println(drink2.size.ordinal()); // prints 8

        System.out.println(drink2.size.getInhoud()); //prints 32

        for (CoffeeSize cs : CoffeeSize.values()) {
            System.out.println(cs + " " + cs.getInhoud());
        }

    }

}
