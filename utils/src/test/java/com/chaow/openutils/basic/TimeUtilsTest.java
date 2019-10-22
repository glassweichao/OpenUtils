package com.chaow.openutils.basic;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.creation.MockSettingsImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

import javax.xml.transform.Source;

import static org.junit.Assert.*;

/**
 * @author : Char
 * @date : 2019/10/22
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public class TimeUtilsTest {

    private String otherFormat;
    private SimpleDateFormat mSimpleDateFormat;

    @Before
    public void setUp() throws Exception {
        otherFormat = "yyyy-MM-dd HH-mm-ss";
        mSimpleDateFormat = new SimpleDateFormat(otherFormat);
    }

    @Test
    public void getCurrentTime() {
        System.out.println(TimeUtils.getCurrentTime());
        System.out.println(TimeUtils.getCurrentTime(otherFormat));
        System.out.println(TimeUtils.getCurrentTime(new SimpleDateFormat(otherFormat)));
    }

    @Test
    public void millis2String() {
        long currTime = System.currentTimeMillis();
        System.out.println(TimeUtils.millis2String(currTime));
        System.out.println(TimeUtils.millis2String(currTime, otherFormat));
        System.out.println(TimeUtils.millis2String(currTime, new SimpleDateFormat(otherFormat)));
        System.out.println(TimeUtils.millis2String(currTime, ""));
        System.out.println(TimeUtils.millis2String(currTime, new SimpleDateFormat()));
        System.out.println(TimeUtils.millis2String(0, new SimpleDateFormat()));
        System.out.println(TimeUtils.millis2String(0L, new SimpleDateFormat()));
    }

    @Test
    public void string2Millis() {
        String stringTime0 = null;
        String stringTime1 = "";
        String stringTime2 = "2019-10-22 16-37-04";
        String stringTime3 = "2019/10/22 16:37:04";
        String stringTime4 = "abcabc";
        System.out.println(TimeUtils.string2Millis(stringTime0));
        System.out.println(TimeUtils.string2Millis(stringTime1));
        System.out.println(TimeUtils.string2Millis(stringTime2));
        System.out.println(TimeUtils.string2Millis(stringTime3));
        System.out.println(TimeUtils.string2Millis(stringTime4));

        System.out.println(TimeUtils.string2Millis(stringTime0, otherFormat));
        System.out.println(TimeUtils.string2Millis(stringTime1, otherFormat));
        System.out.println(TimeUtils.string2Millis(stringTime2, otherFormat));
        System.out.println(TimeUtils.string2Millis(stringTime3, otherFormat));
        System.out.println(TimeUtils.string2Millis(stringTime4, otherFormat));

        System.out.println(TimeUtils.string2Millis(stringTime0, new SimpleDateFormat(otherFormat)));
        System.out.println(TimeUtils.string2Millis(stringTime1, new SimpleDateFormat(otherFormat)));
        System.out.println(TimeUtils.string2Millis(stringTime2, new SimpleDateFormat(otherFormat)));
        System.out.println(TimeUtils.string2Millis(stringTime3, new SimpleDateFormat(otherFormat)));
        System.out.println(TimeUtils.string2Millis(stringTime4, new SimpleDateFormat(otherFormat)));
    }

    @Test
    public void date2Millis() {
        Date date = new Date();
        Date date1 = new Date(System.currentTimeMillis());

        System.out.println(TimeUtils.date2Millis(date));
        System.out.println(TimeUtils.date2Millis(date1));
    }

    @Test
    public void millis2Date() {
        long date1 = -1;
        long date2 = 0;
        long date3 = System.currentTimeMillis();

        System.out.println(TimeUtils.millis2Date(date1));
        System.out.println(TimeUtils.millis2Date(date2));
        System.out.println(TimeUtils.millis2Date(date3));
    }

    @Test
    public void string2Date() {
        assertNull(TimeUtils.string2Date(null, new SimpleDateFormat(otherFormat)));
    }

    @Test
    public void getTimeSpan() {
        Date date = new Date(TimeUtils.string2Millis("2019-10-22 16-37-04", otherFormat));
        Date date1 = new Date();
        System.out.println(TimeUtils.getTimeSpan(date, date1));
        System.out.println(TimeUtils.getTimeSpan("2019-10-22 16-37-04", "2019-11-22 16-37-04", mSimpleDateFormat));
        System.out.println(TimeUtils.getTimeSpan(null, null, mSimpleDateFormat));
        System.out.println(TimeUtils.getTimeSpan("2019-10-22 16-37-04", "2019-12-22 16-37-04", otherFormat));

    }

    @Test
    public void getTimeSpanWithUnit() {
        long span1 = TimeUtils.getTimeSpan("2019-10-22 16-37-04", "2019-10-23 16-37-04", otherFormat);
        System.out.println(TimeUtils.getTimeSpanWithUnit(span1, TimeUnit.DAYS));

    }

    @Test
    public void isLeapYear() {
        Date date1 = new Date();
        assertFalse(TimeUtils.isLeapYear(date1));
        Date date2 = TimeUtils.string2Date("2000", new SimpleDateFormat("yyyy"));
        assertTrue(TimeUtils.isLeapYear(date2));
    }

    @Test
    public void isLeapYear1() {
        assertTrue(TimeUtils.isLeapYear(2000));
        assertTrue(TimeUtils.isLeapYear(2004));
        assertFalse(TimeUtils.isLeapYear(2005));
    }

    @Test
    public void getChineseWeek() {
        System.out.println(TimeUtils.getChineseWeek(new Date()));
        System.out.println(TimeUtils.getChineseWeek("2019-10-23", new SimpleDateFormat("yyyy-MM-dd")));
        System.out.println(TimeUtils.getChineseWeek("", new SimpleDateFormat("yyyy-MM-dd")));
        System.out.println(TimeUtils.getChineseWeek(null, new SimpleDateFormat("yyyy-MM-dd")));
    }
}