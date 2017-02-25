package be.leerstad.collections.ordening;

import java.util.Comparator;

/**
 * @author Peter Hardeel
 */
public class StringComparator implements Comparator<String> {

    public int compare(String string1, String string2) {


        return string2.compareTo(string1);
    }

}
