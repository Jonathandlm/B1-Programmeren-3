package be.leerstad.collections.ordening;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Peter Hardeel
 */
public class DemoSorting {

    /**
     * @param args
     */
    public static void main(String[] args) {
        demo();

    }

    private static void demo() {
        Set<Person> treeset = new HashSet<>();
        treeset.add(new Person(1, "Hardeel", "Peter"));
        treeset.add(new Person(2, "Van Hijfte", "Diederik"));
        treeset.add(new Person(2, "De Cock", "Bart"));
        System.out.println(treeset.size());
        System.out.println(treeset);
        Set<Person> myTreeSet = new TreeSet<>(treeset);
        System.out.println(myTreeSet);

    }

}
