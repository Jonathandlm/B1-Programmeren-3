package be.leerstad.javabasics.exercises.ex4;


public class Item {

    /*
     * Create a class Item. An item has a name and a weight (given as arguments
     * in a constructor).
     */

    private int weight;
    private String name;

    public Item(int weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
}
