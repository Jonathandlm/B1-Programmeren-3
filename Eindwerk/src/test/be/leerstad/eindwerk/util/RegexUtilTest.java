package be.leerstad.eindwerk.util;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import static be.leerstad.eindwerk.util.RegexUtil.*;
import static org.junit.Assert.*;

public class RegexUtilTest {

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor constructor = RegexUtil.class.getDeclaredConstructor();
        assertTrue("Constructor is not private", Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        constructor.newInstance();
    }
    
    @Test
    public void testGetDomainName() throws URISyntaxException, MalformedURLException {
        assertEquals(getDomainName("http://10.10.10.10"), "10.10.10.10");
        assertEquals(getDomainName("http://www.google.com"), "google.com");
        assertEquals(getDomainName("http://maps.google.com"), "maps.google.com");
        assertEquals(getDomainName("http://www.google.com/search?q=google"), "google.com");
        assertEquals(getDomainName("vip.megaproxy.com:443"), "vip.megaproxy.com");
        try {
            assertNull(getDomainName("http://www.google;com"));
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            // This exception is expected
        }
    }

    @Test
    public void testGetApplication() {
        assertEquals(getApplication("/ELO/Language.do?language=Nl"), "ELO");
        assertEquals(getApplication("/ELO/index.html"), "ELO");
        assertEquals(getApplication("/ELO/"), "ELO");
        assertEquals(getApplication("/ELO"), "ELO");
        assertEquals(getApplication("ELO"), "ELO");
        assertEquals(getApplication("/index.html"), "root");
        assertEquals(getApplication("/"), "root");
        assertEquals(getApplication(".."), "root");
        assertEquals(getApplication("."), "root");
        assertEquals(getApplication(""), "root");
        assertNull(getApplication(null));
    }
    
    @Test
    public void testGetNetworkAddress() {
        assertNull(getNetworkAddress(null));
        assertNull(getNetworkAddress(""));
        assertNull(getNetworkAddress("ipAddress"));
        assertNull(getNetworkAddress("1.1.1"));
        assertNull(getNetworkAddress("1.1.1.1.1"));
        assertNull(getNetworkAddress("256.256.256.256"));
        assertNull(getNetworkAddress("c.d.0.0"));
        assertNull(getNetworkAddress("c.0.0.d"));
        assertNull(getNetworkAddress("c.c.c.d"));
        assertNull(getNetworkAddress("-1.0.c.d"));
        assertEquals(getNetworkAddress("0.0.0.0"), "0.0.c.d");
        assertEquals(getNetworkAddress("0.0.0.d"), "0.0.c.d");
        assertEquals(getNetworkAddress("0.0.c.0"), "0.0.c.d");
        assertEquals(getNetworkAddress("0.0.c.d"), "0.0.c.d");
        assertEquals(getNetworkAddress("255.255.255.255"), "255.255.c.d");
        assertEquals(getNetworkAddress("0.0.0.0"), "0.0.c.d");
        assertEquals(getNetworkAddress("0.10.0.0"), "0.10.c.d");
        assertEquals(getNetworkAddress("10.0.0.0"), "10.0.c.d");
        assertEquals(getNetworkAddress("10.10.0.0"), "10.10.c.d");
    }

}
