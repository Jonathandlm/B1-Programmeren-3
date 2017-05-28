package be.leerstad.eindwerk.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Field;
import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Logfile.class)
public class LogfileTest {

    private String filename;
    private LocalDate mockDate;
    private LocalDate logfileDate;
    private Logfile logfileEmptyConstructor;
    private Logfile logfileBasicConstructor;
    private Logfile logfileCompleteConstructor;

    @Before
    public void setUp() {
        mockDate = LocalDate.of(2017,5,30);
        logfileDate = LocalDate.of(2016, 12, 6);
        
        mockStatic(LocalDate.class);
        when(LocalDate.now()).thenReturn(mockDate);
        when(LocalDate.of(anyInt(), anyInt(), anyInt())).thenReturn(logfileDate);
        when(LocalDate.parse(anyString(), any())).thenReturn(logfileDate);
        
        filename = "ProxyLog_2016-12-06.log";
        logfileEmptyConstructor = new Logfile();
        logfileBasicConstructor = new Logfile(filename);
        logfileCompleteConstructor = new Logfile(filename, logfileDate);
    }

    @Test
    public void testEmptyConstructor() {
        assertEquals(logfileEmptyConstructor.getLogfile(), "");
        assertEquals(logfileEmptyConstructor.getLogfileDate(), mockDate);
    }

    @Test
    public void testBasicConstructor() {
        assertEquals(logfileBasicConstructor.getLogfile(), filename);
        assertEquals(logfileBasicConstructor.getLogfileDate(), logfileDate);
    }

    @Test
    public void testCompleteConstructor() {
        assertEquals(logfileCompleteConstructor.getLogfile(), filename);
        assertEquals(logfileCompleteConstructor.getLogfileDate(), logfileDate);
    }

    @Test
    public void testGetLogfile() throws NoSuchFieldException, IllegalAccessException {
        final Field field = logfileEmptyConstructor.getClass().getDeclaredField("logfile");
        field.setAccessible(true);
        field.set(logfileEmptyConstructor, filename);

        final String result = logfileEmptyConstructor.getLogfile();

        assertEquals("field wasn't retrieved properly", result, filename);
    }

    @Test
    public void testSetLogfile() throws NoSuchFieldException, IllegalAccessException {
        logfileEmptyConstructor.setLogfile(filename);

        final Field field = logfileEmptyConstructor.getClass().getDeclaredField("logfile");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(logfileEmptyConstructor), filename);
    }

    @Test
    public void testGetLogfileDate() throws NoSuchFieldException, IllegalAccessException {
        final Field field = logfileEmptyConstructor.getClass().getDeclaredField("logfileDate");
        field.setAccessible(true);
        field.set(logfileEmptyConstructor, logfileDate);

        final LocalDate result = logfileEmptyConstructor.getLogfileDate();

        assertEquals("field wasn't retrieved properly", result, logfileDate);
    }

    @Test
    public void testSetLogfileDate() throws NoSuchFieldException, IllegalAccessException {
        logfileEmptyConstructor.setLogfileDate(logfileDate);

        final Field field = logfileEmptyConstructor.getClass().getDeclaredField("logfileDate");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(logfileEmptyConstructor), logfileDate);
    }

    @Test
    public void testEquals() {
        Logfile logfileEmptyConstructor2 = new Logfile();
        Logfile logfileEmptyConstructor3 = new Logfile();
        Logfile logfileBasicConstructor2 = new Logfile(filename);
        Logfile logfileBasicConstructor3 = new Logfile(filename);
        Logfile logfileCompleteConstructor2 = new Logfile(filename, logfileDate);
        Logfile logfileCompleteConstructor3 = new Logfile(filename, logfileDate);

        // Rule 1: reflexive
        assertTrue(logfileEmptyConstructor.equals(logfileEmptyConstructor));
        assertTrue(logfileBasicConstructor.equals(logfileBasicConstructor));
        assertTrue(logfileCompleteConstructor.equals(logfileCompleteConstructor));

        // Rule 2: transitive
        assertTrue(logfileEmptyConstructor.equals(logfileEmptyConstructor2));
        assertTrue(logfileEmptyConstructor2.equals(logfileEmptyConstructor3));
        assertTrue(logfileEmptyConstructor.equals(logfileEmptyConstructor3));
        assertTrue(logfileBasicConstructor.equals(logfileBasicConstructor2));
        assertTrue(logfileBasicConstructor2.equals(logfileBasicConstructor3));
        assertTrue(logfileBasicConstructor.equals(logfileBasicConstructor3));
        assertTrue(logfileCompleteConstructor.equals(logfileCompleteConstructor2));
        assertTrue(logfileCompleteConstructor2.equals(logfileCompleteConstructor3));
        assertTrue(logfileCompleteConstructor.equals(logfileCompleteConstructor3));

        // Rule 3: symmetric
        assertTrue(logfileEmptyConstructor2.equals(logfileEmptyConstructor));
        assertTrue(logfileBasicConstructor2.equals(logfileBasicConstructor));
        assertTrue(logfileCompleteConstructor2.equals(logfileCompleteConstructor));

        // Rule 4: null
        assertFalse(logfileEmptyConstructor.equals(null));
        assertFalse(logfileBasicConstructor.equals(null));
        assertFalse(logfileCompleteConstructor.equals(null));

        // Rule 5: hashcode
        assertEquals(logfileEmptyConstructor.hashCode(), logfileEmptyConstructor2.hashCode());
        assertEquals(logfileBasicConstructor.hashCode(), logfileBasicConstructor2.hashCode());
        assertEquals(logfileCompleteConstructor.hashCode(), logfileCompleteConstructor2.hashCode());

        // Inconvertible types
        assertFalse(logfileEmptyConstructor.equals("a string"));
        assertFalse(logfileBasicConstructor.equals("a string"));
        assertFalse(logfileCompleteConstructor.equals("a string"));

        // Changing logfile should change equals
        logfileEmptyConstructor2.setLogfile(filename);
        assertFalse(logfileEmptyConstructor.equals(logfileEmptyConstructor2));
    }

    @Test
    public void testHashCode() {
        Logfile logfileEmptyConstructor2 = new Logfile();
        Logfile logfileEmptyConstructor3 = new Logfile();
        
        assertEquals(logfileEmptyConstructor.hashCode(), logfileEmptyConstructor.hashCode());
        assertTrue(logfileEmptyConstructor.equals(logfileEmptyConstructor2));
        assertEquals(logfileEmptyConstructor.hashCode(), logfileEmptyConstructor2.hashCode());
        assertEquals(logfileEmptyConstructor.hashCode(), logfileEmptyConstructor3.hashCode());
        assertNotEquals(logfileEmptyConstructor.hashCode(), logfileBasicConstructor.hashCode());

        // Changing Date shouldn't change hashcode
        logfileEmptyConstructor2.setLogfileDate(logfileDate);
        assertEquals(logfileEmptyConstructor.hashCode(), logfileEmptyConstructor2.hashCode());
        assertNotEquals(logfileEmptyConstructor2.hashCode(), logfileBasicConstructor.hashCode());

        // Changing filename should change hashcode
        logfileEmptyConstructor3.setLogfile(filename);
        assertNotEquals(logfileEmptyConstructor.hashCode(), logfileEmptyConstructor3.hashCode());
        assertEquals(logfileEmptyConstructor3.hashCode(), logfileBasicConstructor.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals(logfileEmptyConstructor.toString(), "");
        assertEquals(logfileEmptyConstructor.toString(), logfileEmptyConstructor.getLogfile());
        assertEquals(logfileBasicConstructor.toString(), filename);
        assertEquals(logfileBasicConstructor.toString(), logfileBasicConstructor.getLogfile());
        assertEquals(logfileCompleteConstructor.toString(), filename);
        assertEquals(logfileCompleteConstructor.toString(), logfileCompleteConstructor.getLogfile());
    }

}