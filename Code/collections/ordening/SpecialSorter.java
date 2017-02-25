package be.leerstad.collections.ordening;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * @author Peter Hardeel
 */
public class SpecialSorter {

    public static void main(String[] args) {
        SpecialSorter demo = new SpecialSorter();
        //demo.sortIntegers();
        //demo.sortStrings();
        demo.sortPersons();

    }

    public void sortIntegers() {
        List<Integer> list = new ArrayList<>();
        list.add(new Integer(345));
        list.add(new Integer(122));
        list.add(new Integer(999));
        list.add(new Integer(8));
        list.add(100);
        System.out.println("before sorting:");
        System.out.println(list);
        Collections.sort(list);
        System.out.println("natural sorting:");
        System.out.println(list);
        Collections.sort(list, new IntegerComparator());
        System.out.println("my special sorting:");
        System.out.println(list);
    }

    public void sortStrings() {
        List<String> set = new ArrayList<>();
        set.add("bbbb");
        set.add("aaaa");
        set.add("aabb");
        set.add("ccbb");
        set.add("zzzz");
        set.add("zzaa");
        System.out.println("before sorting:");
        System.out.println(set);
        Collections.sort(set);
        System.out.println("natural sorting:");
        //Set<String> sortedList = new TreeSet<>(set);
        System.out.println(set);
        StringComparator stringComparator = new StringComparator();


        System.out.println("my special sorting:");
        //TreeSet<String> set2 = new TreeSet<>(stringComparator);
        Collections.sort(set, stringComparator);
        //set2.addAll(set);
        System.out.println(set);
    }

    public void sortPersons() {
        List<Person> list = new ArrayList<Person>();
        //Set<Person> set = new TreeSet<>(new PersonsComparator());
        list.add(new Person(1, "Van Severen", "Lieven"));
        list.add(new Person(2, "Hardeel", "Peter"));
        list.add(new Person(2, "Van Roste", "Johan"));
        list.add(new Person(4, "Van Dyck", "Alex"));
        list.add(new Person(5, "Poncelet", "Frank"));
        list.add(new Person(16, "Bracke", "Yves"));
        list.add(new Person(16, "De Cock", "Bart"));
        System.out.println("before sorting:");
        System.out.println(list);
        Collections.sort(list);
        System.out.println("natural sorting:");
        System.out.println(list);
        Comparator<Person> reverseComparator = Person.getReverseComparator();
        Collections.sort(list, reverseComparator);
        System.out.println("my special sorting:");
        System.out.println(list);
    }
}

