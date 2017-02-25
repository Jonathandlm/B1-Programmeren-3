package be.leerstad.collections.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionDemo {

    public static void main(String[] args) {
        List<String> myList = new ArrayList<>();
        myList.add("een");
        myList.add("twee");
        myList.add("drie");

        List<String> myList2 = new ArrayList<>(myList);

        String s  = myList2.set(2,"Peter");
        System.out.println(s);
        myList2.add("vier");
        myList2.add("vijf");
        myList2.add("vijffff");
        //System.out.println(myList2);

        myList2.remove("vijffff");
        //System.out.println(myList2);

        myList2.addAll(1,myList);


//        System.out.println("size: " + myList2.size());
//        System.out.println("contains drie?: " + myList2.contains("drie"));
//        System.out.println("contains vijffff?: " + myList2.contains("vijffff"));
//        System.out.println("is empty? " + myList2.isEmpty());
//
//        System.out.println(myList2);

    }
}
