package be.leerstad.collections.ordening;

import java.util.Comparator;

/**
 * @author Peter Hardeel
 */
public class IntegerComparator implements Comparator<Integer> {

    public int compare(Integer o1, Integer o2) {
        return o2.compareTo(o1);
    }


}

