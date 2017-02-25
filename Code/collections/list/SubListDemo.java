package be.leerstad.collections.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Peter Hardeel
 */
public class SubListDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("een");
        list.add("een");
        list.add("twee");
        list.add("drie");
        list.add("zestien");


        List<String> newList = list.subList(2, 4);

        newList.addAll(list);


        System.out.println(newList);


        newList.remove("drie");
        System.out.println(list);
    }

}
