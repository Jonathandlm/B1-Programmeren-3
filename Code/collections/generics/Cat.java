package be.leerstad.collections.generics;

/**
 * Created by peterhardeel on 4/01/16.
 */
public class Cat extends Animal {
    private String name;
    private int id;

    public Cat(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Cat() {
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {

        return String.join(" ", getName(), String.valueOf(getId()));
    }
}
