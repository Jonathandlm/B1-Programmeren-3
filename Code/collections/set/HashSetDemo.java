package be.leerstad.collections.set;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Peter Hardeel
 */
public class HashSetDemo {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Create HashSet object
        Set<String> hs = new HashSet<>();
        System.out.println(hs.add("one"));
        System.out.println(hs.add("two"));
        System.out.println(hs.add("three"));
        System.out.println(hs.add("four"));
        System.out.println(hs.add("five"));

        // Print out the HashSet object
        System.out.println(hs);

        // Add a duplicate item to the HashSet
        Boolean b = hs.add("one");
        System.out.println("Duplicate item allowed = " + b);
        System.out.println(hs);


    }

}

