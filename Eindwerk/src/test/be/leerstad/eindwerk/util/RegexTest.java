package be.leerstad.eindwerk.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegexTest {

    @Test
    public void testGetApplication() {
        assertEquals(RegexUtil.getApplication("/ELO/Language.do?language=Nl"), "ELO");
        assertEquals(RegexUtil.getApplication("/ELO/index.html"), "ELO");
        assertEquals(RegexUtil.getApplication("/ELO/"), "ELO");
        assertEquals(RegexUtil.getApplication("/ELO"), "ELO");
        assertEquals(RegexUtil.getApplication("ELO"), "ELO");
        assertEquals(RegexUtil.getApplication("/index.html"), "root");
        assertEquals(RegexUtil.getApplication("/"), "root");
        assertEquals(RegexUtil.getApplication(""), "root");
    }
}
