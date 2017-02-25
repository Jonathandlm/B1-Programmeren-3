package be.leerstad.collections.set;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Peter Hardeel
 */
public class TreeSetDemo {

    /**
     * Creates a new instance of TreeSetDemo
     */
    public TreeSetDemo() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Set<String> ts = new HashSet<>();
        ts.add("one");
        ts.add("two");
        ts.add("three");
        ts.add("four");
        System.out.println(ts);

        ts = new TreeSet<>(ts);

        System.out.println(ts);
    }

}

