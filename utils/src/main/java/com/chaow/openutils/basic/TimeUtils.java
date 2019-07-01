package com.chaow.openutils.basic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

}
