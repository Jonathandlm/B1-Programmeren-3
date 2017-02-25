package be.leerstad.evaluatieoefeningen.parking.test;

import be.leerstad.evaluatieoefeningen.parking.Person;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PersonTest {

    private Person person;

    @Before
    public void init() {
        person = new Person("123456", "John", "Doe");
    }

    @Test
    public void testConstructor() {
        assertEquals("Should return 'John'", "John", person.getFirstName());
    }

    @Test
    public void testGetSsn() {
        assertEquals("Should return '123456'", "123456", person.getSsn());
    }

    @Test
    public void testGetFirstName() {
        assertEquals("Should return 'John'", "John", person.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Should return 'Doe'", "Doe", person.getLastName());
    }

    @Test
    public void testEquals() {
        Person person1 = new Person("123456", "John", "Doe");
        Person person2 = new Person("123456", "John", "Doe");

        // Rule 1: reflexive
        assertTrue(person.equals(person));

        // Rule 2: transitive
        assertTrue(person.equals(person1));
        assertTrue(person1.equals(person2));
        assertTrue(person2.equals(person));

        // Rule 3: symmetric
        assertTrue(person1.equals(person));

        // Rule 4: other object
        assertFalse(person.equals("a string"));

        // Rule 5: null
        assertFalse(person.equals(null));

        // Rule 6: hashcode
        assertEquals(person.hashCode(), person1.hashCode());
    }

    @Test
    public void testHashCode() throws Exception {
        Person person1 = new Person("123456", "John", "Doe");
        Person person2 = new Person("123456", "John", "Doe");

        assertEquals(person1.hashCode(), person1.hashCode());
        assertTrue(person1.equals(person2));
        assertEquals(person1.hashCode(), person2.hashCode());
    }

}
