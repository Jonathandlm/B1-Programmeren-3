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
@PrepareForTest(Session.class)
public class SessionTest {

    private Session sessionEmptyConstructor;
    private Session sessionBasicConstructor;
    private Session sessionCompleteConstructor;
    private UUID mockUuid;
    private UUID specificUuid;
    private Logfile logfile;
    private String ipAddress;
    private LocalTime mockTime;
    private LocalTime time;
    private Integer totalTimeInSec;
    private Integer transferredBytes;
    private Integer numberOfRequests;
    private User user;
    private Site site;
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

        logfile = new Logfile("ProxyLog_2016-11-04.log");
        ipAddress = "192.168.1.1";
        totalTimeInSec = 60;
        transferredBytes = 123456;
        numberOfRequests = 42;
        user = new User("HKJ");
        site = new Site(1, "www.google.com");
        sessionEmptyConstructor = new Session();
        sessionBasicConstructor = new Session(logfile, ipAddress, time, transferredBytes, user, site);
        sessionCompleteConstructor = new Session(specificUuid.toString(), logfile, ipAddress, time, totalTimeInSec,
                transferredBytes, numberOfRequests, user, site);

        Field timeoutField = sessionBasicConstructor.getClass().getSuperclass().getDeclaredField("TIMEOUT_IN_MINUTES");
        timeoutField.setAccessible(true);
        timeout = timeoutField.getInt(sessionBasicConstructor);
    }

    @Test
    public void testEmptyConstructor() {
        assertEquals(sessionEmptyConstructor.getId(), mockUuid.toString());
        assertEquals(sessionEmptyConstructor.getLogfile(), new Logfile());
        assertEquals(sessionEmptyConstructor.getIpAddress(), "");
        assertEquals(sessionEmptyConstructor.getTime(), mockTime);
        assertEquals(sessionEmptyConstructor.getTotalTimeInSec(), new Integer(1));
        assertEquals(sessionEmptyConstructor.getTransferredBytes(), new Integer(0));
        assertEquals(sessionEmptyConstructor.getNumberOfRequests(), new Integer(1));
        assertEquals(sessionEmptyConstructor.getUser().getUserId(), "");
        assertEquals(sessionEmptyConstructor.getSite().getSite(), "");
    }

    @Test
    public void testBasicConstructor() {
        assertEquals(sessionBasicConstructor.getId(), mockUuid.toString());
        assertEquals(sessionBasicConstructor.getLogfile(), logfile);
        assertEquals(sessionBasicConstructor.getIpAddress(), ipAddress);
        assertEquals(sessionBasicConstructor.getTime(), time);
        assertEquals(sessionBasicConstructor.getTotalTimeInSec(), new Integer(1));
        assertEquals(sessionBasicConstructor.getTransferredBytes(), transferredBytes);
        assertEquals(sessionBasicConstructor.getNumberOfRequests(), new Integer(1));
        assertEquals(sessionBasicConstructor.getUser(), user);
        assertEquals(sessionBasicConstructor.getSite(), site);
    }

    @Test
    public void testCompleteConstructor() {
        assertEquals(sessionCompleteConstructor.getId(), specificUuid.toString());
        assertEquals(sessionCompleteConstructor.getLogfile(), logfile);
        assertEquals(sessionCompleteConstructor.getIpAddress(), ipAddress);
        assertEquals(sessionCompleteConstructor.getTime(), time);
        assertEquals(sessionCompleteConstructor.getTotalTimeInSec(), totalTimeInSec);
        assertEquals(sessionCompleteConstructor.getTransferredBytes(), transferredBytes);
        assertEquals(sessionCompleteConstructor.getNumberOfRequests(), numberOfRequests);
        assertEquals(sessionCompleteConstructor.getUser(), user);
        assertEquals(sessionCompleteConstructor.getSite(), site);
    }

    @Test
    public void testGetId() throws NoSuchFieldException, IllegalAccessException {
        final Field field = sessionBasicConstructor.getClass().getSuperclass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(sessionBasicConstructor, specificUuid);

        final String result = sessionBasicConstructor.getId();

        assertEquals("field wasn't retrieved properly", result, specificUuid.toString());
    }

    @Test
    public void testSetId() throws NoSuchFieldException, IllegalAccessException {
        sessionBasicConstructor.setId(specificUuid.toString());

        final Field field = sessionBasicConstructor.getClass().getSuperclass().getDeclaredField("id");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(sessionBasicConstructor), specificUuid);
    }

    @Test
    public void testGetLogfile() throws NoSuchFieldException, IllegalAccessException {
        final Field field = sessionBasicConstructor.getClass().getSuperclass().getDeclaredField("logfile");
        field.setAccessible(true);
        field.set(sessionBasicConstructor, logfile);

        final Logfile result = sessionBasicConstructor.getLogfile();

        assertEquals("field wasn't retrieved properly", result, logfile);
    }

    @Test
    public void testSetLogfile() throws NoSuchFieldException, IllegalAccessException {
        sessionBasicConstructor.setLogfile(logfile);

        final Field field = sessionBasicConstructor.getClass().getSuperclass().getDeclaredField("logfile");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(sessionBasicConstructor), logfile);
    }

    @Test
    public void testGetIpAddress() throws NoSuchFieldException, IllegalAccessException {
        final Field field = sessionBasicConstructor.getClass().getSuperclass().getDeclaredField("ipAddress");
        field.setAccessible(true);
        field.set(sessionBasicConstructor, ipAddress);

        final String result = sessionBasicConstructor.getIpAddress();

        assertEquals("field wasn't retrieved properly", result, ipAddress);
    }

    @Test
    public void testSetIpAddress() throws NoSuchFieldException, IllegalAccessException {
        sessionBasicConstructor.setIpAddress(ipAddress);

        final Field field = sessionBasicConstructor.getClass().getSuperclass().getDeclaredField("ipAddress");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(sessionBasicConstructor), ipAddress);
    }

    @Test
    public void testGetTime() throws NoSuchFieldException, IllegalAccessException {
        final Field field = sessionBasicConstructor.getClass().getSuperclass().getDeclaredField("time");
        field.setAccessible(true);
        field.set(sessionBasicConstructor, time);

        final LocalTime result = sessionBasicConstructor.getTime();

        assertEquals("field wasn't retrieved properly", result, time);
    }

    @Test
    public void testSetTime() throws NoSuchFieldException, IllegalAccessException {
        sessionBasicConstructor.setTime(time);

        final Field field = sessionBasicConstructor.getClass().getSuperclass().getDeclaredField("time");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(sessionBasicConstructor), time);
    }

    @Test
    public void testGetTotalTimeInSec() throws NoSuchFieldException, IllegalAccessException {
        final Field field = sessionBasicConstructor.getClass().getSuperclass().getDeclaredField("totalTimeInSec");
        field.setAccessible(true);
        field.set(sessionBasicConstructor, totalTimeInSec);

        final Integer result = sessionBasicConstructor.getTotalTimeInSec();

        assertEquals("field wasn't retrieved properly", result, totalTimeInSec);
    }

    @Test
    public void testSetTotalTimeInSec() throws NoSuchFieldException, IllegalAccessException {
        sessionBasicConstructor.setTotalTimeInSec(totalTimeInSec);

        final Field field = sessionBasicConstructor.getClass().getSuperclass().getDeclaredField("totalTimeInSec");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(sessionBasicConstructor), totalTimeInSec);
    }

    @Test
    public void testGetTransferredBytes() throws NoSuchFieldException, IllegalAccessException {
        final Field field = sessionBasicConstructor.getClass().getSuperclass().getDeclaredField("transferredBytes");
        field.setAccessible(true);
        field.set(sessionBasicConstructor, transferredBytes);

        final Integer result = sessionBasicConstructor.getTransferredBytes();

        assertEquals("field wasn't retrieved properly", result, transferredBytes);
    }

    @Test
    public void testSetTransferredBytes() throws NoSuchFieldException, IllegalAccessException {
        sessionBasicConstructor.setTransferredBytes(transferredBytes);

        final Field field = sessionBasicConstructor.getClass().getSuperclass().getDeclaredField("transferredBytes");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(sessionBasicConstructor), transferredBytes);
    }

    @Test
    public void testGetNumberOfRequests() throws NoSuchFieldException, IllegalAccessException {
        final Field field = sessionBasicConstructor.getClass().getSuperclass().getDeclaredField("numberOfRequests");
        field.setAccessible(true);
        field.set(sessionBasicConstructor, numberOfRequests);

        final Integer result = sessionBasicConstructor.getNumberOfRequests();

        assertEquals("field wasn't retrieved properly", result, numberOfRequests);
    }

    @Test
    public void testSetNumberOfRequests() throws NoSuchFieldException, IllegalAccessException {
        sessionBasicConstructor.setNumberOfRequests(numberOfRequests);

        final Field field = sessionBasicConstructor.getClass().getSuperclass().getDeclaredField("numberOfRequests");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(sessionBasicConstructor), numberOfRequests);
    }

    @Test
    public void testGetUser() throws NoSuchFieldException, IllegalAccessException {
        final Field field = sessionBasicConstructor.getClass().getDeclaredField("user");
        field.setAccessible(true);
        field.set(sessionBasicConstructor, user);

        final User result = sessionBasicConstructor.getUser();

        assertEquals("field wasn't retrieved properly", result, user);
    }

    @Test
    public void testSetUser() throws NoSuchFieldException, IllegalAccessException {
        sessionBasicConstructor.setUser(user);

        final Field field = sessionBasicConstructor.getClass().getDeclaredField("user");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(sessionBasicConstructor), user);
    }

    @Test
    public void testGetSite() throws NoSuchFieldException, IllegalAccessException {
        final Field field = sessionBasicConstructor.getClass().getDeclaredField("site");
        field.setAccessible(true);
        field.set(sessionBasicConstructor, site);

        final Site result = sessionBasicConstructor.getSite();

        assertEquals("field wasn't retrieved properly", result, site);
    }

    @Test
    public void testSetSite() throws NoSuchFieldException, IllegalAccessException {
        sessionBasicConstructor.setSite(site);

        final Field field = sessionBasicConstructor.getClass().getDeclaredField("site");
        field.setAccessible(true);

        assertEquals("field wasn't set properly", field.get(sessionBasicConstructor), site);
    }
    
    @Test
    public void testConcatenate() {
        sessionEmptyConstructor.concatenate(sessionBasicConstructor);
        sessionBasicConstructor.concatenate(sessionEmptyConstructor);
        sessionCompleteConstructor.concatenate(sessionCompleteConstructor);

        assertEquals(sessionEmptyConstructor.getTotalTimeInSec(), new Integer(39 * 60 + 56));
        assertEquals(sessionEmptyConstructor.getTransferredBytes(), transferredBytes);
        assertEquals(sessionEmptyConstructor.getNumberOfRequests(), new Integer(1 + 1 ));
        assertEquals(sessionBasicConstructor.getTotalTimeInSec(), new Integer(-(39 * 60 + 56)));
        assertEquals(sessionBasicConstructor.getTransferredBytes(), new Integer(transferredBytes * 2));
        assertEquals(sessionBasicConstructor.getNumberOfRequests(), new Integer(1 + 1));
        assertEquals(sessionCompleteConstructor.getTotalTimeInSec(), new Integer(0));
        assertEquals(sessionCompleteConstructor.getTransferredBytes(), new Integer(transferredBytes * 2));
        assertEquals(sessionCompleteConstructor.getNumberOfRequests(), new Integer(numberOfRequests + 1));
    }

    @Test
    public void testEquals() {
        Session sessionEmptyConstructor2 = new Session();
        Session sessionEmptyConstructor3 = new Session();
        Session sessionBasicConstructor2 = new Session(logfile, ipAddress, time, transferredBytes, user, site);
        Session sessionBasicConstructor3 = new Session(logfile, ipAddress, time, transferredBytes, user, site);
        Session sessionCompleteConstructor2 = new Session(specificUuid.toString(), logfile, ipAddress, time, totalTimeInSec,
                transferredBytes, numberOfRequests, user, site);
        Session sessionCompleteConstructor3 = new Session(specificUuid.toString(), logfile, ipAddress, time, totalTimeInSec,
                transferredBytes, numberOfRequests, user, site);

        // Rule 1: reflexive
        assertTrue(sessionEmptyConstructor.equals(sessionEmptyConstructor));
        assertTrue(sessionBasicConstructor.equals(sessionBasicConstructor));
        assertTrue(sessionCompleteConstructor.equals(sessionCompleteConstructor));

        // Rule 2: transitive
        assertTrue(sessionEmptyConstructor.equals(sessionEmptyConstructor2));
        assertTrue(sessionEmptyConstructor2.equals(sessionEmptyConstructor3));
        assertTrue(sessionEmptyConstructor.equals(sessionEmptyConstructor3));
        assertTrue(sessionBasicConstructor.equals(sessionBasicConstructor2));
        assertTrue(sessionBasicConstructor2.equals(sessionBasicConstructor3));
        assertTrue(sessionBasicConstructor.equals(sessionBasicConstructor3));
        assertTrue(sessionCompleteConstructor.equals(sessionCompleteConstructor2));
        assertTrue(sessionCompleteConstructor2.equals(sessionCompleteConstructor3));
        assertTrue(sessionCompleteConstructor.equals(sessionCompleteConstructor3));

        // Rule 3: symmetric
        assertTrue(sessionEmptyConstructor2.equals(sessionEmptyConstructor));
        assertTrue(sessionBasicConstructor2.equals(sessionBasicConstructor));
        assertTrue(sessionCompleteConstructor2.equals(sessionCompleteConstructor));

        // Rule 4: null
        assertFalse(sessionEmptyConstructor.equals(null));
        assertFalse(sessionBasicConstructor.equals(null));
        assertFalse(sessionCompleteConstructor.equals(null));

        // Rule 5: hashcode
        assertEquals(sessionEmptyConstructor.hashCode(), sessionEmptyConstructor2.hashCode());
        assertEquals(sessionBasicConstructor.hashCode(), sessionBasicConstructor2.hashCode());
        assertEquals(sessionCompleteConstructor.hashCode(), sessionCompleteConstructor2.hashCode());

        // Inconvertible types
        assertFalse(sessionEmptyConstructor.equals("a string"));
        assertFalse(sessionBasicConstructor.equals("a string"));
        assertFalse(sessionCompleteConstructor.equals("a string"));

        // Changing user or site, should change equals
        sessionEmptyConstructor2.setUser(user);
        assertFalse(sessionEmptyConstructor.equals(sessionEmptyConstructor2));
        assertFalse(sessionEmptyConstructor2.equals(sessionEmptyConstructor3));
        sessionEmptyConstructor3.setSite(site);
        assertFalse(sessionEmptyConstructor.equals(sessionEmptyConstructor3));
        assertFalse(sessionEmptyConstructor2.equals(sessionEmptyConstructor3));

        // Sessions within the time limit are equal
        LocalTime limitTime = sessionCompleteConstructor.getTime()
                .plusSeconds(sessionCompleteConstructor.getTotalTimeInSec())
                .plusMinutes(timeout);
        LocalTime excessTime = limitTime.plusMinutes(1);
        sessionCompleteConstructor2.setTime(limitTime);
        sessionCompleteConstructor3.setTime(excessTime);
        assertTrue(sessionCompleteConstructor2.equals(sessionCompleteConstructor));
        assertFalse(sessionCompleteConstructor3.equals(sessionCompleteConstructor));
    }

    @Test
    public void testHashCode() {
        Session sessionEmptyConstructor2 = new Session();
        Session sessionEmptyConstructor3 = new Session();
        Session sessionEmptyConstructor4 = new Session();

        assertEquals(sessionEmptyConstructor.hashCode(), sessionEmptyConstructor.hashCode());
        assertTrue(sessionEmptyConstructor.equals(sessionEmptyConstructor2));
        assertEquals(sessionEmptyConstructor.hashCode(), sessionEmptyConstructor2.hashCode());
        assertEquals(sessionEmptyConstructor.hashCode(), sessionEmptyConstructor3.hashCode());
        assertEquals(sessionEmptyConstructor.hashCode(), sessionEmptyConstructor4.hashCode());

        // Changing Logfile, IpAddress, Time, TotalTime, TransferredBytes or NumberOfRequests, shouldn't change hashcode
        sessionEmptyConstructor2.setLogfile(logfile);
        sessionEmptyConstructor2.setIpAddress(ipAddress);
        sessionEmptyConstructor2.setTime(time);
        sessionEmptyConstructor2.setTotalTimeInSec(totalTimeInSec);
        sessionEmptyConstructor2.setTransferredBytes(transferredBytes);
        sessionEmptyConstructor2.setNumberOfRequests(numberOfRequests);
        assertEquals(sessionEmptyConstructor.hashCode(), sessionEmptyConstructor2.hashCode());

        // Changing Id, user or site, should change hashcode
        sessionEmptyConstructor2.setId(specificUuid.toString());
        assertNotEquals(sessionEmptyConstructor.hashCode(), sessionEmptyConstructor2.hashCode());
        sessionEmptyConstructor3.setUser(user);
        assertNotEquals(sessionEmptyConstructor.hashCode(), sessionEmptyConstructor3.hashCode());
        sessionEmptyConstructor4.setSite(site);
        assertNotEquals(sessionEmptyConstructor.hashCode(), sessionEmptyConstructor4.hashCode());
    }

    @Test
    public void testToString() {
        String sessionCompleteConstructorToString = "Interaction{id='" + specificUuid +
                "', logfile='" + logfile +
                "', ipAddress='" + ipAddress +
                "', time=" + time +
                ", totalTimeInSec=" + totalTimeInSec +
                ", transferredBytes=" + transferredBytes +
                ", numberOfRequests=" + numberOfRequests +
                '}' +
                "\n\tSession{user='" + user +
                "', site='" + site +
                "'}";

        assertEquals(sessionCompleteConstructor.toString(), sessionCompleteConstructorToString);
    }

}
