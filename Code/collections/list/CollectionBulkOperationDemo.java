package be.leerstad.collections.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hardeel.p
 */
public class CollectionBulkOperationDemo {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("one");
        list1.add("two");
        list1.add("three");
        list1.add("four");


        List<String> list2 = new ArrayList<>();
        list2.add("one");
        list2.add("two");
        list2.add("three");


        System.out.println(list1.containsAll(list2)); //true
        System.out.println(list1.addAll(list2)); //true
        System.out.println(list1); //[one, two, three, four, one, two, three]
        System.out.println(list1.removeAll(list2)); //true
        System.out.println(list1);  //[four]
        list1.add("one");
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list1.retainAll(list2)); //true
        System.out.println(list1); //[one]
        list1.clear();
        System.out.println(list1);//[];
    }
}
