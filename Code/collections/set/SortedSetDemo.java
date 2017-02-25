package be.leerstad.collections.set;

import be.leerstad.collections.ordening.StringComparator;

import java.util.SortedSet;
import java.util.TreeSet;


public class SortedSetDemo {

    private SortedSet<String> set;

    public SortedSetDemo() {
        set = new TreeSet<>();
        set.add("za");
        set.add("er");
        set.add("aa");
        set.add("adr");
        set.add("ed");
        // add duplicate object
        set.add("adr");
        set.add("fadr");
    }

    public static void main(String[] args) {
        SortedSetDemo demo = new SortedSetDemo();
        System.out.println(demo.getSet());
        // first object
        System.out.println(demo.getSet().first());
        // last object
        System.out.println(demo.getSet().last());
        // subset
        System.out.println(demo.getSet().subSet("a", "b"));
        System.out.println(demo.getSet().headSet("f"));
        System.out.println(demo.getSet().tailSet("f"));

        SortedSet<String> set2 = new TreeSet<>(new StringComparator());
        set2.addAll(demo.getSet());
        System.out.println("set2: " + set2);
        System.out.println(set2.comparator());
    }

    public SortedSet<String> getSet() {
        return set;
    }
}
