package be.leerstad.eindwerk.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Field;
import java.time.LocalTime;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Visit.class)
public class VisitTest {
    
    private LocalTime mockTime;
    private Visit visitEmptyConstructor;
    private Visit visitBasicConstructor;
    private Visit visitCompleteConstructor;
    private UUID mockUuid;
    private UUID specificUuid;
    private Logfile logfile;
    private String ipAddress;
    private LocalTime time;
    private Integer totalTimeInSec;
    private Integer transferredBytes;
    private Integer numberOfRequests;
    private String user;
    private SiteApplication siteApplication;
    private School school;
    private Field timeoutField;
    private int timeout;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        mockTime = LocalTime.of(11,55);
        time = LocalTime.of(12,34,56);

        mockUuid = UUID.fromString("66d4f844-f4f4-4d90-bd4b-dcdcf16827be");
        specificUuid = UUID.fromString("12345678-1234-1234-1234-1234567890ab");

        mockStatic(LocalTime.class);
        when(LocalTime.now()).thenReturn(mockTime);
        when(LocalTime.of(anyInt(), anyInt(), anyInt())).thenReturn(time);

        mockStatic(UUID.class);
        when(UUID.randomUUID()).thenReturn(mockUuid);
        when(UUID.fromString(anyString())).thenReturn(specificUuid);
        when(UUID.fromString("66d4f844-f4f4-4d90-bd4b-dcdcf16827be")).thenReturn(mockUuid);

        logfile = new Logfile("localhost_access_log.2016-12-06.txt");
        ipAddress = "192.168.1.1";
        totalTimeInSec = 60;
        transferredBytes = 123456;
        numberOfRequests = 42;
        user = "-";
        siteApplication = new SiteApplication(1, "ELO");
        school = new School("10.120.c.d", "CVO Leerstad", "Brouwerijstraat 5", "9160", "Lokeren" );
        visitEmptyConstructor = new Visit();
        visitBasicConstructor = new Visit(logfile, ipAddress, time, transferredBytes, user, siteApplication, school);
        visitCompleteConstructor = new Visit(specificUuid.toString(), logfile, ipAddress, time, totalTimeInSec,
                transferredBytes, numberOfRequests, user, siteApplication, school);

        timeoutField = visitBasicConstructor.getClass().getSuperclass().getDeclaredField("TIMEOUT_IN_MINUTES");
        timeoutField.setAccessible(true);
        timeout = timeoutField.getInt(visitBasicConstructor);
    }

    @Test
    public void testEmptyConstructor() {
        assertEquals(visitEmptyConstructor.getId(), mockUuid.toString());
        assertEquals(visitEmptyConstructor.getLogfile(), new Logfile());
        assertEquals(visitEmptyConstructor.getIpAddress(), "");
        assertEquals(visitEmptyConstructor.getTime(), mockTime);
        assertEquals(visitEmptyConstructor.getTotalTimeInSec(), new Integer(1));
        assertEquals(visitEmptyConstructor.getTransferredBytes(), new Integer(0));
        assertEquals(visitEmptyConstructor.getNumberOfRequests(), new Integer(1));
        assertEquals(visitEmptyConstructor.getUser(), "");
        assertEquals(visitEmptyConstructor.getSiteApplication().getApplication(), "");
        assertEquals(visitEmptyConstructor.getSchool().getIpAddress(), "10.110.c.d");
    }

    @Test
    public void testBasicConstructor() {
        assertEquals(visitBasicConstructor.getId(), mockUuid.toString());
        assertEquals(visitBasicConstructor.getLogfile(), logfile);
        assertEquals(visitBasicConstructor.getIpAddress(), ipAddress);
        assertEquals(visitBasicConstructor.getTime(), time);
        assertEquals(visitBasicConstructor.getTotalTimeInSec(), new Integer(1));
        assertEquals(visitBasicConstructor.getTransferredBytes(), transferredBytes);
        assertEquals(visitBasicConstructor.getNumberOfRequests(), new Integer(1));
        assertEquals(visitBasicConstructor.getUser(), user);
        assertEquals(visitBasicConstructor.getSiteApplication(), siteApplication);
        assertEquals(visitBasicConstructor.getSchool(), school);
    }

    @Test
    public void testCompleteConstructor() {
        assertEquals(visitCompleteConstructor.getId(), specificUuid.toString());
        assertEquals(visitCompleteConstructor.getLogfile(), logfile);
        assertEquals(visitCompleteConstructor.getIpAddress(), ipAddress);
        assertEquals(visitCompleteConstructor.getTime(), time);
        assertEquals(visitCompleteConstructor.getTotalTimeInSec(), totalTimeInSec);
        assertEquals(visitCompleteConstructor.getTransferredBytes(), transferredBytes);
        assertEquals(visitCompleteConstructor.getNumberOfRequests(), numberOfRequests);
        assertEquals(visitCompleteConstructor.getUser(), user);
        assertEquals(visitCompleteConstructor.getSiteApplication(), siteApplication);
        assertEquals(visitCompleteConstructor.getSchool(), school);
    }

    @Test
    public void testGetId() throws NoSuchFieldException, IllegalAccessException {
        final Field field = visitBasicConstructor.getClass().getSuperclass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(visitBasicConstructor, specificUuid);

        final String result = visitBasicConstructor.getId();

        assertEquals("field wasn't retrieved properly", result, specificUuid.toString());
    }

    @Test
    public void testSetId() throws NoSuchFieldException, IllegalAccessException {
        visitBasicConstructor.setId(specificUuid.toString());

        final Field field = visitBasicConstructor.getClass().getSuperclass().getDeclaredField("id");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(visitBasicConstructor), specificUuid);
    }

    @Test
    public void testGetLogfile() throws NoSuchFieldException, IllegalAccessException {
        final Field field = visitBasicConstructor.getClass().getSuperclass().getDeclaredField("logfile");
        field.setAccessible(true);
        field.set(visitBasicConstructor, logfile);

        final Logfile result = visitBasicConstructor.getLogfile();

        assertEquals("field wasn't retrieved properly", result, logfile);
    }

    @Test
    public void testSetLogfile() throws NoSuchFieldException, IllegalAccessException {
        visitBasicConstructor.setLogfile(logfile);

        final Field field = visitBasicConstructor.getClass().getSuperclass().getDeclaredField("logfile");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(visitBasicConstructor), logfile);
    }

    @Test
    public void testGetIpAddress() throws NoSuchFieldException, IllegalAccessException {
        final Field field = visitBasicConstructor.getClass().getSuperclass().getDeclaredField("ipAddress");
        field.setAccessible(true);
        field.set(visitBasicConstructor, ipAddress);

        final String result = visitBasicConstructor.getIpAddress();

        assertEquals("field wasn't retrieved properly", result, ipAddress);
    }

    @Test
    public void testSetIpAddress() throws NoSuchFieldException, IllegalAccessException {
        visitBasicConstructor.setIpAddress(ipAddress);

        final Field field = visitBasicConstructor.getClass().getSuperclass().getDeclaredField("ipAddress");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(visitBasicConstructor), ipAddress);
    }

    @Test
    public void testGetTime() throws NoSuchFieldException, IllegalAccessException {
        final Field field = visitBasicConstructor.getClass().getSuperclass().getDeclaredField("time");
        field.setAccessible(true);
        field.set(visitBasicConstructor, time);

        final LocalTime result = visitBasicConstructor.getTime();

        assertEquals("field wasn't retrieved properly", result, time);
    }

    @Test
    public void testSetTime() throws NoSuchFieldException, IllegalAccessException {
        visitBasicConstructor.setTime(time);

        final Field field = visitBasicConstructor.getClass().getSuperclass().getDeclaredField("time");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(visitBasicConstructor), time);
    }

    @Test
    public void testGetTotalTimeInSec() throws NoSuchFieldException, IllegalAccessException {
        final Field field = visitBasicConstructor.getClass().getSuperclass().getDeclaredField("totalTimeInSec");
        field.setAccessible(true);
        field.set(visitBasicConstructor, totalTimeInSec);

        final Integer result = visitBasicConstructor.getTotalTimeInSec();

        assertEquals("field wasn't retrieved properly", result, totalTimeInSec);
    }

    @Test
    public void testSetTotalTimeInSec() throws NoSuchFieldException, IllegalAccessException {
        visitBasicConstructor.setTotalTimeInSec(totalTimeInSec);

        final Field field = visitBasicConstructor.getClass().getSuperclass().getDeclaredField("totalTimeInSec");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(visitBasicConstructor), totalTimeInSec);
    }

    @Test
    public void testGetTransferredBytes() throws NoSuchFieldException, IllegalAccessException {
        final Field field = visitBasicConstructor.getClass().getSuperclass().getDeclaredField("transferredBytes");
        field.setAccessible(true);
        field.set(visitBasicConstructor, transferredBytes);

        final Integer result = visitBasicConstructor.getTransferredBytes();

        assertEquals("field wasn't retrieved properly", result, transferredBytes);
    }

    @Test
    public void testSetTransferredBytes() throws NoSuchFieldException, IllegalAccessException {
        visitBasicConstructor.setTransferredBytes(transferredBytes);

        final Field field = visitBasicConstructor.getClass().getSuperclass().getDeclaredField("transferredBytes");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(visitBasicConstructor), transferredBytes);
    }

    @Test
    public void testGetNumberOfRequests() throws NoSuchFieldException, IllegalAccessException {
        final Field field = visitBasicConstructor.getClass().getSuperclass().getDeclaredField("numberOfRequests");
        field.setAccessible(true);
        field.set(visitBasicConstructor, numberOfRequests);

        final Integer result = visitBasicConstructor.getNumberOfRequests();

        assertEquals("field wasn't retrieved properly", result, numberOfRequests);
    }

    @Test
    public void testSetNumberOfRequests() throws NoSuchFieldException, IllegalAccessException {
        visitBasicConstructor.setNumberOfRequests(numberOfRequests);

        final Field field = visitBasicConstructor.getClass().getSuperclass().getDeclaredField("numberOfRequests");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(visitBasicConstructor), numberOfRequests);
    }
    
    @Test
    public void testGetUser() throws NoSuchFieldException, IllegalAccessException {
        final Field field = visitBasicConstructor.getClass().getDeclaredField("user");
        field.setAccessible(true);
        field.set(visitBasicConstructor, user);

        final String result = visitBasicConstructor.getUser();

        assertEquals("field wasn't retrieved properly", result, user);
    }

    @Test
    public void testSetUser() throws NoSuchFieldException, IllegalAccessException {
        visitBasicConstructor.setUser(user);

        final Field field = visitBasicConstructor.getClass().getDeclaredField("user");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(visitBasicConstructor), user);
    }

    @Test
    public void testGetSiteApplicationApplication() throws NoSuchFieldException, IllegalAccessException {
        final Field field = visitBasicConstructor.getClass().getDeclaredField("siteApplication");
        field.setAccessible(true);
        field.set(visitBasicConstructor, siteApplication);

        final SiteApplication result = visitBasicConstructor.getSiteApplication();

        assertEquals("field wasn't retrieved properly", result, siteApplication);
    }

    @Test
    public void testSetSiteApplicationApplication() throws NoSuchFieldException, IllegalAccessException {
        visitBasicConstructor.setSiteApplication(siteApplication);

        final Field field = visitBasicConstructor.getClass().getDeclaredField("siteApplication");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(visitBasicConstructor), siteApplication);
    }

    @Test
    public void testGetSchool() throws NoSuchFieldException, IllegalAccessException {
        final Field field = visitBasicConstructor.getClass().getDeclaredField("school");
        field.setAccessible(true);
        field.set(visitBasicConstructor, school);

        final School result = visitBasicConstructor.getSchool();

        assertEquals("field wasn't retrieved properly", result, school);
    }

    @Test
    public void testSetSchool() throws NoSuchFieldException, IllegalAccessException {
        visitBasicConstructor.setSchool(school);

        final Field field = visitBasicConstructor.getClass().getDeclaredField("school");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(visitBasicConstructor), school);
    }

    @Test
    public void testConcatenate() {
        visitEmptyConstructor.concatenate(visitBasicConstructor);
        visitBasicConstructor.concatenate(visitEmptyConstructor);
        visitCompleteConstructor.concatenate(visitCompleteConstructor);

        assertEquals(visitEmptyConstructor.getTotalTimeInSec(), new Integer(39 * 60 + 56));
        assertEquals(visitEmptyConstructor.getTransferredBytes(), transferredBytes);
        assertEquals(visitEmptyConstructor.getNumberOfRequests(), new Integer(1 + 1 ));
        assertEquals(visitBasicConstructor.getTotalTimeInSec(), new Integer(-(39 * 60 + 56)));
        assertEquals(visitBasicConstructor.getTransferredBytes(), new Integer(transferredBytes * 2));
        assertEquals(visitBasicConstructor.getNumberOfRequests(), new Integer(1 + 1));
        assertEquals(visitCompleteConstructor.getTotalTimeInSec(), new Integer(0));
        assertEquals(visitCompleteConstructor.getTransferredBytes(), new Integer(transferredBytes * 2));
        assertEquals(visitCompleteConstructor.getNumberOfRequests(), new Integer(numberOfRequests + 1));
    }

    @Test
    public void testEquals() {
        Visit visitEmptyConstructor2 = new Visit();
        Visit visitEmptyConstructor3 = new Visit();
        Visit visitEmptyConstructor4 = new Visit();
        Visit visitBasicConstructor2 = new Visit(logfile, ipAddress, time, transferredBytes, user, siteApplication, school);
        Visit visitBasicConstructor3 = new Visit(logfile, ipAddress, time, transferredBytes, user, siteApplication, school);
        Visit visitCompleteConstructor2 = new Visit(specificUuid.toString(), logfile, ipAddress, time, totalTimeInSec,
                transferredBytes, numberOfRequests, user, siteApplication, school);
        Visit visitCompleteConstructor3 = new Visit(specificUuid.toString(), logfile, ipAddress, time, totalTimeInSec,
                transferredBytes, numberOfRequests, user, siteApplication, school);

        // Rule 1: reflexive
        assertTrue(visitEmptyConstructor.equals(visitEmptyConstructor));
        assertTrue(visitBasicConstructor.equals(visitBasicConstructor));
        assertTrue(visitCompleteConstructor.equals(visitCompleteConstructor));

        // Rule 2: transitive
        assertTrue(visitEmptyConstructor.equals(visitEmptyConstructor2));
        assertTrue(visitEmptyConstructor2.equals(visitEmptyConstructor3));
        assertTrue(visitEmptyConstructor.equals(visitEmptyConstructor3));
        assertTrue(visitBasicConstructor.equals(visitBasicConstructor2));
        assertTrue(visitBasicConstructor2.equals(visitBasicConstructor3));
        assertTrue(visitBasicConstructor.equals(visitBasicConstructor3));
        assertTrue(visitCompleteConstructor.equals(visitCompleteConstructor2));
        assertTrue(visitCompleteConstructor2.equals(visitCompleteConstructor3));
        assertTrue(visitCompleteConstructor.equals(visitCompleteConstructor3));

        // Rule 3: symmetric
        assertTrue(visitEmptyConstructor2.equals(visitEmptyConstructor));
        assertTrue(visitBasicConstructor2.equals(visitBasicConstructor));
        assertTrue(visitCompleteConstructor2.equals(visitCompleteConstructor));

        // Rule 4: null
        assertFalse(visitEmptyConstructor.equals(null));
        assertFalse(visitBasicConstructor.equals(null));
        assertFalse(visitCompleteConstructor.equals(null));

        // Rule 5: hashcode
        assertEquals(visitEmptyConstructor.hashCode(), visitEmptyConstructor2.hashCode());
        assertEquals(visitBasicConstructor.hashCode(), visitBasicConstructor2.hashCode());
        assertEquals(visitCompleteConstructor.hashCode(), visitCompleteConstructor2.hashCode());

        // Inconvertible types
        assertFalse(visitEmptyConstructor.equals("a string"));
        assertFalse(visitBasicConstructor.equals("a string"));
        assertFalse(visitCompleteConstructor.equals("a string"));

        // Changing user, siteApplication or school, should change equals
        visitEmptyConstructor2.setUser(user);
        assertFalse(visitEmptyConstructor.equals(visitEmptyConstructor2));
        visitEmptyConstructor3.setSiteApplication(siteApplication);
        assertFalse(visitEmptyConstructor.equals(visitEmptyConstructor3));
        visitEmptyConstructor4.setSchool(school);
        assertFalse(visitEmptyConstructor.equals(visitEmptyConstructor4));

        // Visits within the time limit are equal
        LocalTime limitTime = visitCompleteConstructor.getTime()
                .plusSeconds(visitCompleteConstructor.getTotalTimeInSec())
                .plusMinutes(timeout);
        LocalTime excessTime = limitTime.plusMinutes(1);
        visitCompleteConstructor2.setTime(limitTime);
        visitCompleteConstructor3.setTime(excessTime);
        assertTrue(visitCompleteConstructor.equals(visitCompleteConstructor2));
        assertFalse(visitCompleteConstructor.equals(visitCompleteConstructor3));
    }

    @Test
    public void testHashCode() {
        Visit visitEmptyConstructor2 = new Visit();
        Visit visitEmptyConstructor3 = new Visit();
        Visit visitEmptyConstructor4 = new Visit();
        Visit visitEmptyConstructor5 = new Visit();

        assertEquals(visitEmptyConstructor.hashCode(), visitEmptyConstructor.hashCode());
        assertTrue(visitEmptyConstructor.equals(visitEmptyConstructor2));
        assertEquals(visitEmptyConstructor.hashCode(), visitEmptyConstructor2.hashCode());
        assertEquals(visitEmptyConstructor.hashCode(), visitEmptyConstructor3.hashCode());
        assertEquals(visitEmptyConstructor.hashCode(), visitEmptyConstructor4.hashCode());
        assertEquals(visitEmptyConstructor.hashCode(), visitEmptyConstructor5.hashCode());

        // Changing Logfile, IpAddress, Time, TotalTime, TransferredBytes or NumberOfRequests, shouldn't change hashcode
        visitEmptyConstructor2.setLogfile(logfile);
        visitEmptyConstructor2.setIpAddress(ipAddress);
        visitEmptyConstructor2.setTime(time);
        visitEmptyConstructor2.setTotalTimeInSec(totalTimeInSec);
        visitEmptyConstructor2.setTransferredBytes(transferredBytes);
        visitEmptyConstructor2.setNumberOfRequests(numberOfRequests);
        assertEquals(visitEmptyConstructor.hashCode(), visitEmptyConstructor2.hashCode());

        // Changing Id, user, school or siteApplication, should change hashcode
        visitEmptyConstructor2.setId(specificUuid.toString());
        assertNotEquals(visitEmptyConstructor.hashCode(), visitEmptyConstructor2.hashCode());
        visitEmptyConstructor3.setUser(user);
        assertNotEquals(visitEmptyConstructor.hashCode(), visitEmptyConstructor3.hashCode());
        visitEmptyConstructor4.setSiteApplication(siteApplication);
        assertNotEquals(visitEmptyConstructor.hashCode(), visitEmptyConstructor4.hashCode());
        visitEmptyConstructor5.setSchool(school);
        assertNotEquals(visitEmptyConstructor.hashCode(), visitEmptyConstructor5.hashCode());
    }

    @Test
    public void testToString() {
        String visitCompleteConstructorToString = "Interaction{id='" + specificUuid +
                "', logfile='" + logfile +
                "', ipAddress='" + ipAddress +
                "', time=" + time +
                ", totalTimeInSec=" + totalTimeInSec +
                ", transferredBytes=" + transferredBytes +
                ", numberOfRequests=" + numberOfRequests +
                '}' +
                "\n\tVisit{user='" + user +
                "', siteApplication='" + siteApplication +
                "', school='" + school +
                "'}";

        assertEquals(visitCompleteConstructor.toString(), visitCompleteConstructorToString);
    }

}