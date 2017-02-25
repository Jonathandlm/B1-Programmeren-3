package be.leerstad.collections.map;

import be.leerstad.collections.set.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class MapDemo {

    public static void main(String[] args) {
        Person p1 = new Person(1, "Bergen", "Andre");
        Person p2 = new Person(2, "Schouters", "Ann");
        Person p3 = new Person(3, "Vermeyen", "Wouter");
        Person p4 = new Person(4, "Eggers", "Johan");
        Person p5 = new Person(5, "Hardeel", "Peter");


        MapDemo demo = new MapDemo();

        Map<Integer, Person> map = new HashMap<>();
        map.put(p2.getId(), p2);

        map.put(p3.getId(), p3);
        map.put(p4.getId(), p4);
        map.put(p1.getId(), p1);
        map.put(p5.getId(), p5);
        map.put(p1.getId(), p5);

        System.out.println(map.containsKey(1));
        System.out.println(map.containsValue(p3));

        //demo.iterateValues(map);
        //demo.iterateKeySet(map);
        demo.iterateEntrySet(map);

    }

    public void iterateEntrySet(Map<Integer, Person> map) {
        System.out.println("Entry set:");
        System.out.println(map);
        for (Entry<Integer, Person> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
        }
        System.out.println();
    }

    public void iterateKeySet(Map<Integer, Person> map) {
        System.out.println("KeySet:");
        for (Integer key : map.keySet()) {
            System.out.println(key);
        }

    }

    public void iterateValues(Map<Integer, Person> map) {
        System.out.println("Values:");
        for (Person object : map.values()) {
            System.out.println(object);
        }
        System.out.println();
    }
}
