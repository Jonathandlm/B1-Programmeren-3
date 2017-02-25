package be.leerstad.collections.set;

import java.util.*;


public class SetDemo {

    public static void main(String[] args) {
        SetDemo demo = new SetDemo();

        Set<String> hashSet = new HashSet<>();
        System.out.println(hashSet.add("een"));
        System.out.println(hashSet.add("twee"));
        System.out.println(hashSet.add("drie"));
        System.out.println(hashSet.add("vier"));
        System.out.println(hashSet.add("vijf"));
        System.out.println(hashSet.add("een"));
        System.out.println();
        //in willekeurige volgorde
        demo.iterate(hashSet);

        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("een");
        linkedHashSet.add("twee");
        linkedHashSet.add("drie");
        linkedHashSet.add("vier");
        linkedHashSet.add("vijf");

        //in volgorde van invoegen
        demo.iterate(linkedHashSet);

        Set<String> treeSet = new TreeSet<>(linkedHashSet);
        //gesorteerd
        demo.iterate(treeSet);
    }

    private void iterate(Collection<String> collection) {
        for (String element : collection) {
            System.out.print(element);
            System.out.print('\t');
        }
        System.out.println();
    }
}
