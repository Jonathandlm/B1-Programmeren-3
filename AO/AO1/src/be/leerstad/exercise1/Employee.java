package be.leerstad.exercise1;

import java.util.Locale;

abstract class Employee {
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;

    Employee(String firstName, String lastName, String socialSecurityNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    private String getFirstName() { return firstName; }
    private String getLastName() { return lastName; }
    private String getSocialSecurityNumber() { return socialSecurityNumber; }

    public abstract double earnings();

    @Override
    public String toString() {
        return String.format(Locale.US, "%s %s\nsocial security number: %s",
                getFirstName(), getLastName(), getSocialSecurityNumber());
    }
}
