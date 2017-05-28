package be.leerstad.eindwerk.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DateUtil.class)
public class DateUtilTest {

    private LocalDate testDate;

    @Before
    public void setUp() {
        testDate = LocalDate.of(2017,5,30);
    }

    @Test
    public void testParseLogfileDate() {
        assertNull(DateUtil.parseLogfileDate(null));
        assertNull(DateUtil.parseLogfileDate(""));
        assertNull(DateUtil.parseLogfileDate("abc.txt"));
        assertNull(DateUtil.parseLogfileDate("2017.05.30.txt"));
        assertNull(DateUtil.parseLogfileDate("2017-mei-30.txt"));
        assertNull(DateUtil.parseLogfileDate("30-05-2017.txt"));
        assertNull(DateUtil.parseLogfileDate("17-05-30.txt"));
        assertNull(DateUtil.parseLogfileDate("2017-05-32.txt"));
        assertNull(DateUtil.parseLogfileDate("2017-13-30.txt"));
        assertEquals(DateUtil.parseLogfileDate("2017-05-30.txt"), testDate);
        assertEquals(DateUtil.parseLogfileDate("text2017-05-30.txt"), testDate);
        assertEquals(DateUtil.parseLogfileDate("2017-05-30text.txt"), testDate);
    }

    @Test
    public void testParseDate() {
        assertNull(DateUtil.parseDate(null));
        assertNull(DateUtil.parseDate(""));
        assertNull(DateUtil.parseDate("abc.txt"));
        assertNull(DateUtil.parseDate("2017.05.30"));
        assertNull(DateUtil.parseDate("2017-mei-30"));
        assertNull(DateUtil.parseDate("30-05-2017"));
        assertNull(DateUtil.parseDate("17-05-30"));
        assertNull(DateUtil.parseDate("2017-05-32"));
        assertNull(DateUtil.parseDate("2017-13-30"));
        assertNull(DateUtil.parseDate("text2017-05-30"));
        assertNull(DateUtil.parseDate("2017-05-30text"));
        assertEquals(DateUtil.parseDate("2017-05-30"), testDate);
    }

    @Test
    public void testFormat() {
        assertNull(DateUtil.format(null));
        assertEquals(DateUtil.format(testDate), "30.05.2017");
    }

    @Test
    public void testToday() throws Exception {
        Date date = Date.from(testDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        whenNew(Date.class).withNoArguments().thenReturn(date);

        assertEquals(DateUtil.today(), "di 30 mei 2017");
    }

}