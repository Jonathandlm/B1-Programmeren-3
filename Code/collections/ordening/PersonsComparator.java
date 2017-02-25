package be.leerstad.collections.ordening;

import java.util.Comparator;


/**
 * @author Peter Hardeel
 */
public class PersonsComparator implements Comparator<Person> {

    public int compare(Person o1, Person o2) {
        //Person p1 =o1;
        //Person p2 = o2;
        if (!(o2.getId() == o1.getId())) {
            return o2.getId() - o1.getId();
        }
        return o2.getName().compareTo(o1.getName());
    }

}
