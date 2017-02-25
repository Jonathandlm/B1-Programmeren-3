package be.leerstad.collections.set;

import java.util.HashSet;
import java.util.Set;


public class SetBulkOperations {

    public static void main(String[] args) {
        Set<String> set1 = new HashSet<>();
        set1.add("one");
        set1.add("two");
        set1.add("three");
        set1.add("four");


        Set<String> set2 = new HashSet<>();
        set2.add("five");
        set2.add("six");
        set2.add("seven");


        System.out.println(set1.containsAll(set2)); //false
        System.out.println(set1.addAll(set2)); //true
        System.out.println(set1); //[one, two, five, four, three, seven, six]
        System.out.println(set1.removeAll(set2)); //true
        System.out.println(set1);  //[one, two, four, three]
        set2.add("one");
        System.out.println(set1.retainAll(set2)); //true
        System.out.println(set1); //[one]
        set1.clear();
        System.out.println(set1);//[];
    }
}
