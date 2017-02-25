
package be.leerstad.javabasics.exercises.ex4;


public class BoxStarter {

    public static void main(String[] args) {
        Box box;
        box = new Box("TVBox");
        System.out.println("Must be 5: " + box.getWeight());
        System.out.println("Must be null: " + box.retrieveItem());
        System.out.println("Must be true: " + box.fillBox(new Item(15, "TV")));
        System.out.println("Must be false: " + box.fillBox(new Item(15, "TV")));
        System.out.println("Must be 20: " + box.getWeight());
        System.out.println("Must be TV: " + box.retrieveItem().getName());
        System.out.println("Must be null: " + box.retrieveItem());
        System.out.println("Must be true: " + box.fillBox(new Item(35, "Frigo")));
        System.out.println("Must be false: " + box.fillBox(new Item(15, "TV")));
        System.out.println("Must be 40: " + box.getWeight());
        System.out.println("Must be Frigo: " + box.retrieveItem().getName());
    }
}
