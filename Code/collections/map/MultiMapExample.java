package be.leerstad.collections.map;

import be.leerstad.collections.set.Person;

import java.util.*;


public class MultiMapExample {

    private Map<Person, List<String>> map;

    public MultiMapExample() {
        map = new HashMap<>();
        fillMap();
    }

    public static void main(String[] args) {
        MultiMapExample multiMapExample = new MultiMapExample();
        System.out.println(multiMapExample.toString());
    }

    private void fillMap() {
        List<String> list;
        map.put(new Person(1, "hardeel", "peter"), list = new ArrayList<>());
        list.add("123");
        list.add("321");
        map.put(new Person(2, "de plucker", "manuela"), list = new ArrayList<>());
        list.add("yo CVO");

    }

    public String toString() {
        StringBuilder sb = new StringBuilder("items in map:");
        sb.append('\n');

        for (Map.Entry<Person, List<String>> element : map.entrySet()) {
            Person person = element.getKey();
            sb.append(person);
            List<String> list = element.getValue();
            for (String tf : list) {
                sb.append(" ");
                sb.append(tf);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
