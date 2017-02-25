package be.leerstad.javabasics.exercises.ex4;

public class Box {

	/*
     * Create a class Box. a box has also a name and a empty weight (given as
	 * arguments in a constructor). a box can contain ONE item at time, or be
	 * empty. It must have a method to place an item into it and another one to
	 * retrieve it. the weight of the box when it contains the item is : empty
	 * weight + item weight A standard box has a empty weight of 5 Kg.
	 */

    private String name;
    private int weight;
    private Item item;

    public Box(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    // A standard box has a empty weight of 5 Kg.
    public Box(String name) {
        this(name, 5);
    }

    public String getName() {
        return name;
    }

    public boolean fillBox(Item item) {
        if (!isEmpty()) {
            return false;
        }
        this.item = item;
        return true;
    }

    public Item retrieveItem() {
        if (!isEmpty()) {
            Item tempItem = item;
            item = null;
            return tempItem;
        }
        return null;
    }

    public int getWeight() {
        return weight + (!isEmpty() ? item.getWeight() : 0);
    }

    public boolean isEmpty() {
        return item == null;
    }

}
