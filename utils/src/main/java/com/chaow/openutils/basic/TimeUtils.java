package com.chaow.openutils.basic;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * @author : Char
 * @date : 2019/6/27
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public class TimeUtils {

    private final static String DEFAULT_DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";

    private TimeUtils() {
    }

    private static SimpleDateFormat getDefaultFormat() {
        return getFormat(DEFAULT_DATE_FORMAT);
    }

    private static SimpleDateFormat getFormat(String format) {
        String dateFormat = StringUtils.checkEmpty(format, DEFAULT_DATE_FORMAT);
        return new SimpleDateFormat(dateFormat, Locale.getDefault());
    }


    public static String getCurrentTime() {
        return getCurrentTime(getDefaultFormat());
    }

    public static String getCurrentTime(@NonNull String dateFormat) {
        return getCurrentTime(getFormat(dateFormat));
    }

    public static String getCurrentTime(@NonNull DateFormat dateFormat) {
        long currentTime = System.currentTimeMillis();
        return dateFormat.format(new Date(currentTime));
    }

    public static String millis2String(final long millis) {
        return millis2String(millis, getDefaultFormat());
    }

    public static String millis2String(final long millis, @NonNull final String dateFormat) {
        return millis2String(millis, getFormat(dateFormat));
    }

    public static String millis2String(final long millis, @NonNull DateFormat dateFormat) {
        return dateFormat.format(new Date(millis));
    }

    public static long string2Millis(final String time) {
        return string2Millis(time, getDefaultFormat());
    }

    public static long string2Millis(final String time, @NonNull final String dateFormat) {
        return string2Millis(time, getFormat(dateFormat));
    }

    public static long string2Millis(final String time, @NonNull final DateFormat dateFormat) {
        try {
            return dateFormat.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static long date2Millis(final Date date) {
        return date.getTime();
    }

    public static Date millis2Date(final long millis) {
        return new Date(millis);
    }

    public static Date string2Date(final String date, @NonNull DateFormat dateFormat) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取两个时间的时间差
     *
     * @return
     */
    public static long getTimeSpan(final Date date1, final Date date2) {
        return getTimeSpan(date2Millis(date1), date2Millis(date2));
    }

    public static long getTimeSpan(final String time1, final String time2, @NonNull final String dateFormat) {
        return getTimeSpan(time1, time2, getFormat(dateFormat));
    }


    public static long getTimeSpan(final String time1, final String time2, @NonNull final DateFormat format) {
        return getTimeSpan(string2Millis(time1, format), string2Millis(time2, format));
    }

    public static long getTimeSpan(final long time1, final long time2) {
        return Math.abs(time1 - time2);
    }

    public static long getTimeSpanWithUnit(long timeSpan, TimeUnit timeUnit) {
        return timeUnit.convert(timeSpan, TimeUnit.MILLISECONDS);
    }

    public static boolean isLeapYear(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        return isLeapYear(year);
    }

    public static boolean isLeapYear(final int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    public static String getChineseWeek(final Date date) {
        return new SimpleDateFormat("E", Locale.CHINA).format(date);
    }

    public static String getChineseWeek(final String time, @NonNull final DateFormat format) {
        return getChineseWeek(string2Date(time, format));
    }
}
