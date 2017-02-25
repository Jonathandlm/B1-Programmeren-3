package be.leerstad.collections.map;

/**
 * @author Peter Hardeel
 */

public class Name implements Comparable<Name> {

    private String first;
    private String last;

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public String firstName() {
        return first;
    }

    public String lastName() {
        return last;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Name))
            return false;
        Name n = (Name) o;
        return n.firstName().equals(first) && n.lastName().equals(last);
    }

    public int hashCode() {
        return 31 * first.hashCode() + last.hashCode();
    }

    public int compareTo(Name o) {
        Name n = o;
        int result = first.compareTo(n.firstName());
        return (result != 0 ? result : last.compareTo(n.lastName()));
    }

    public String toString() {
        return first + " " + last;
    }
}


