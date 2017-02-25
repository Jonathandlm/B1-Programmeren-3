package be.leerstad.collections.ordening;

import java.util.Comparator;


public class Person implements Comparable<Person> {
    private static final char TAB = ' ';
    private int id;
    private String name;
    private String firstName;

    public Person(int id, String name, String firstName) {
        super();
        this.id = id;
        this.name = name;
        this.firstName = firstName;
    }

    public static Comparator<Person> getReverseComparator() {
        return new PersonsComparator();
    }

    public int getAge() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append(TAB);
        sb.append(name);
        sb.append(TAB);
        sb.append(firstName);
        return sb.toString();

    }


    public int getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getName() {
        return name;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return 31 * firstName.hashCode() + name.hashCode();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object personObject) {
        if (personObject instanceof Person) {
            Person person = (Person) personObject;
            return (name.equals(person.getName()) && firstName.equals(person
                    .getFirstName()));
        }
        return false;
    }

    public int compareTo(Person objectPerson) {
        if (!(this.getId() == objectPerson.getId())) {
            return this.getId() - objectPerson.getId();
        }
        return this.getName().compareTo(objectPerson.getName());
    }

}
