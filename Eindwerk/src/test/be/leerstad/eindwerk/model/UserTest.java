package be.leerstad.eindwerk.model;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UserTest {

    private String userIdEmpty;
    private String userId;
    private String nameEmpty;
    private String name;
    private String firstName;
    private String cat;
    private User userEmptyConstructor;
    private User userBasicConstructor;
    private User userCompleteConstructor;

    @Before
    public void testSetUp() throws Exception {
        userIdEmpty = "";
        userId = "HKJ";
        nameEmpty = null;
        name = "Faillard";
        firstName = "Bruno";
        cat = "Lector";
        userEmptyConstructor = new User();
        userBasicConstructor = new User(userId);
        userCompleteConstructor = new User(userId, name, firstName, cat);
    }

    @Test
    public void testEmptyConstructor() {
        assertEquals(userEmptyConstructor.getUserId(), userIdEmpty);
        assertEquals(userEmptyConstructor.getName(), userBasicConstructor.getName());
        assertEquals(userEmptyConstructor.getFirstName(), userBasicConstructor.getFirstName());
        assertEquals(userEmptyConstructor.getCat(), userBasicConstructor.getCat());
    }

    @Test
    public void testBasicConstructor() {
        assertEquals(userBasicConstructor.getUserId(), userId);
        assertEquals(userBasicConstructor.getName(), nameEmpty);
        assertEquals(userBasicConstructor.getFirstName(), null);
        assertEquals(userBasicConstructor.getCat(), null);
    }

    @Test
    public void testCompleteConstructor() {
        assertEquals(userCompleteConstructor.getUserId(), userId);
        assertEquals(userCompleteConstructor.getName(), name);
        assertEquals(userCompleteConstructor.getFirstName(), firstName);
        assertEquals(userCompleteConstructor.getCat(), cat);
    }

    @Test
    public void testGetUserId() throws NoSuchFieldException, IllegalAccessException {
        final Field field = userEmptyConstructor.getClass().getDeclaredField("userId");
        field.setAccessible(true);
        field.set(userEmptyConstructor, userId);

        final String result = userEmptyConstructor.getUserId();

        assertEquals("field wasn't retrieved properly", result, userId);
    }

    @Test
    public void testSetUserId() throws NoSuchFieldException, IllegalAccessException {
        userEmptyConstructor.setUserId(userId);

        final Field field = userEmptyConstructor.getClass().getDeclaredField("userId");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(userEmptyConstructor), userId);
    }

    @Test
    public void testGetName() throws NoSuchFieldException, IllegalAccessException {
        final Field field = userEmptyConstructor.getClass().getDeclaredField("name");
        field.setAccessible(true);
        field.set(userEmptyConstructor, name);

        final String result = userEmptyConstructor.getName();

        assertEquals("field wasn't retrieved properly", result, name);
    }

    @Test
    public void testSetName() throws NoSuchFieldException, IllegalAccessException {
        userEmptyConstructor.setName(name);

        final Field field = userEmptyConstructor.getClass().getDeclaredField("name");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(userEmptyConstructor), name);
    }

    @Test
    public void testGetFirstName() throws NoSuchFieldException, IllegalAccessException {
        final Field field = userEmptyConstructor.getClass().getDeclaredField("firstName");
        field.setAccessible(true);
        field.set(userEmptyConstructor, firstName);

        final String result = userEmptyConstructor.getFirstName();

        assertEquals("field wasn't retrieved properly", result, firstName);
    }

    @Test
    public void testSetFirstName() throws NoSuchFieldException, IllegalAccessException {
        userEmptyConstructor.setFirstName(firstName);

        final Field field = userEmptyConstructor.getClass().getDeclaredField("firstName");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(userEmptyConstructor), firstName);
    }

    @Test
    public void testGetCat() throws NoSuchFieldException, IllegalAccessException {
        final Field field = userEmptyConstructor.getClass().getDeclaredField("cat");
        field.setAccessible(true);
        field.set(userEmptyConstructor, cat);

        final String result = userEmptyConstructor.getCat();

        assertEquals("field wasn't retrieved properly", result, cat);
    }

    @Test
    public void testSetCat() throws NoSuchFieldException, IllegalAccessException {
        userEmptyConstructor.setCat(cat);

        final Field field = userEmptyConstructor.getClass().getDeclaredField("cat");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(userEmptyConstructor), cat);
    }

    @Test
    public void testGetFullName() throws NoSuchFieldException, IllegalAccessException {
        final Field nameField = userEmptyConstructor.getClass().getDeclaredField("name");
        final Field firstNameField = userEmptyConstructor.getClass().getDeclaredField("firstName");
        nameField.setAccessible(true);
        firstNameField.setAccessible(true);
        nameField.set(userEmptyConstructor, name);
        firstNameField.set(userEmptyConstructor, firstName);

        final String result = userEmptyConstructor.getFullName();

        assertEquals("field wasn't retrieved properly", result, firstName + ' ' + name);
        assertEquals("field wasn't retrieved properly", result, userCompleteConstructor.getFullName());
    }

    @Test
    public void testEquals() {
        User userEmptyConstructor2 = new User();
        User userEmptyConstructor3 = new User();
        User userBasicConstructor2 = new User(userId);
        User userBasicConstructor3 = new User(userId);
        User userCompleteConstructor2 = new User(userId, name, firstName, cat);
        User userCompleteConstructor3 = new User(userId, name, firstName, cat);

        // Rule 1: reflexive
        assertTrue(userEmptyConstructor.equals(userEmptyConstructor));
        assertTrue(userBasicConstructor.equals(userBasicConstructor));
        assertTrue(userCompleteConstructor.equals(userCompleteConstructor));

        // Rule 2: transitive
        assertTrue(userEmptyConstructor.equals(userEmptyConstructor2));
        assertTrue(userEmptyConstructor2.equals(userEmptyConstructor3));
        assertTrue(userEmptyConstructor.equals(userEmptyConstructor3));
        assertTrue(userBasicConstructor.equals(userBasicConstructor2));
        assertTrue(userBasicConstructor2.equals(userBasicConstructor3));
        assertTrue(userBasicConstructor.equals(userBasicConstructor3));
        assertTrue(userCompleteConstructor.equals(userCompleteConstructor2));
        assertTrue(userCompleteConstructor2.equals(userCompleteConstructor3));
        assertTrue(userCompleteConstructor.equals(userCompleteConstructor3));

        // Rule 3: symmetric
        assertTrue(userEmptyConstructor2.equals(userEmptyConstructor));
        assertTrue(userBasicConstructor2.equals(userBasicConstructor));
        assertTrue(userCompleteConstructor2.equals(userCompleteConstructor));

        // Rule 4: null
        assertFalse(userEmptyConstructor.equals(null));
        assertFalse(userBasicConstructor.equals(null));
        assertFalse(userCompleteConstructor.equals(null));

        // Rule 5: hashcode
        assertEquals(userEmptyConstructor.hashCode(), userEmptyConstructor2.hashCode());
        assertEquals(userBasicConstructor.hashCode(), userBasicConstructor2.hashCode());
        assertEquals(userCompleteConstructor.hashCode(), userCompleteConstructor2.hashCode());

        // Inconvertible types
        assertFalse(userEmptyConstructor.equals("a string"));
        assertFalse(userBasicConstructor.equals("a string"));
        assertFalse(userCompleteConstructor.equals("a string"));

        // Changing userId should change equals
        userEmptyConstructor2.setUserId(userId);
        assertFalse(userEmptyConstructor.equals(userEmptyConstructor2));

        // Changing name, first name and category shouldn't change equals
        userEmptyConstructor3.setName("De La Marche");
        userEmptyConstructor3.setFirstName("Jonathan");
        userEmptyConstructor3.setCat("Student");
        assertTrue(userEmptyConstructor.equals(userEmptyConstructor3));
    }

    @Test
    public void testHashCode() {
        User userEmptyConstructor2 = new User();
        User userEmptyConstructor3 = new User();

        assertEquals(userEmptyConstructor.hashCode(), userEmptyConstructor.hashCode());
        assertTrue(userEmptyConstructor.equals(userEmptyConstructor2));
        assertEquals(userEmptyConstructor.hashCode(), userEmptyConstructor2.hashCode());
        assertEquals(userEmptyConstructor.hashCode(), userEmptyConstructor3.hashCode());
        assertNotEquals(userEmptyConstructor.hashCode(), userBasicConstructor.hashCode());

        // Changing name, first name and category shouldn't change hashcode
        userEmptyConstructor2.setName("De La Marche");
        userEmptyConstructor2.setFirstName("Jonathan");
        userEmptyConstructor2.setCat("Student");
        assertEquals(userEmptyConstructor.hashCode(), userEmptyConstructor2.hashCode());
        assertNotEquals(userEmptyConstructor2.hashCode(), userBasicConstructor.hashCode());

        // Changing userId should change hashcode
        userEmptyConstructor3.setUserId(userId);
        assertNotEquals(userEmptyConstructor.hashCode(), userEmptyConstructor3.hashCode());
        assertEquals(userEmptyConstructor3.hashCode(), userBasicConstructor.hashCode());
    }

    @Test
    public void testToString() {
        String userEmptyConstructorToString = "User{" + userIdEmpty + '}';
        String userBasicConstructorToString = "User{" + userId + '}';
        String userCompleteConstructorToString = "User{" + userId + '}';
        assertEquals(userEmptyConstructor.toString(), userEmptyConstructorToString);
        assertEquals(userBasicConstructor.toString(), userBasicConstructorToString);
        assertEquals(userCompleteConstructor.toString(), userCompleteConstructorToString);
    }

}