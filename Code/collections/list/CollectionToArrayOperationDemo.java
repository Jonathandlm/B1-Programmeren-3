package be.leerstad.collections.list;

import java.util.ArrayList;
import java.util.Collection;


public class CollectionToArrayOperationDemo {

    public static void main(String[] args) {
        Collection<Object> list1 = new ArrayList<>();
        list1.add("one");
        list1.add("two");
        list1.add("three");
        list1.add("four");
        try {
            Object[] array = list1.toArray();
        } catch (ClassCastException cce) {
            System.out.println("ClassCastException");
        }


        // list1.add(new String("zes")); //creates an ArrayStoreException


        String[] array2 = list1.toArray(new String[0]);

        for (String anArray2 : array2) {
            System.out.println(anArray2);
        }
    }
}
