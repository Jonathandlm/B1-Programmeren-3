package be.leerstad.evaluatieoefeningen.parking;

public class Person {

    private String ssn;
    private String firstName;
    private String lastName;

    public Person(String ssn, String firstName, String lastName) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "ssn='" + ssn + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return ssn.equals(person.ssn);
    }

    @Override
    public int hashCode() {
        return ssn.hashCode();
    }
}
