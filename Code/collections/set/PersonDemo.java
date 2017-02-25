package be.leerstad.collections.set;

import java.util.Collection;
import java.util.HashSet;

public class PersonDemo {


    public static void main(String[] args) {
        PersonDemo demo = new PersonDemo();
        demo.demoHashSet();

    }

    private void demoHashSet() {
        Collection<Person> set = new HashSet<>();

        Person p1 = new Person(2, "Peter", "Hardeel");
        Person p2 = new Person(2, "Peter", "Hardeel");
        Person p3 = new Person(3, "Amedee", "Van Gasse");

        set.add(p1);
        set.add(p2);
        set.add(p3);


        System.out.println(set);

    }

}
