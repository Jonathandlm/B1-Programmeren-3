package be.leerstad.collections.map;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Peter Hardeel
 */

public class SortedMapDemo {


    public static void main(String[] args) {
        SortedMap<Integer, String> map = new TreeMap<>();
        map.put(new Integer(1), "een");
        map.put(new Integer(2), "twee");
        map.put(new Integer(3), "drie");
        map.put(new Integer(4), "vier");
        map.put(new Integer(5), "vijf");
        map.put(new Integer(6), "zes");
        System.out.println(map.entrySet());
        System.out.println(map.values());
        System.out.println(map.keySet());
        System.out.println(map.firstKey());
        System.out.println(map.lastKey());
        //submap
        System.out.println(map.subMap(new Integer(2), new Integer(5)));
        //headMap
        System.out.println(map.headMap(new Integer(3)));
        //tailmap
        System.out.println(map.tailMap(new Integer(3)));
    }
}
