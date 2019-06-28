package com.chaow.openutils.basic;

import androidx.annotation.Nullable;

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

    private static SimpleDateFormat getFormat(String format) {
        String dateFormat = StringUtils.checkEmpty(format, DEFAULT_DATE_FORMAT);
        return new SimpleDateFormat(dateFormat, Locale.getDefault());
    }

    public static String getCurrentTime() {
        return getCurrentTime(null);
    }

    public static String getCurrentTime(@Nullable String dateFormat) {
        long currentTime = System.currentTimeMillis();
        return getFormat(dateFormat).format(new Date(currentTime));
    }


}
