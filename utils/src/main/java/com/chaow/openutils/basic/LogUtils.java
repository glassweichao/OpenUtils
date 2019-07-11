package com.chaow.openutils.basic;

import android.text.TextUtils;
import android.util.Log;

import com.chaow.openutils.OpenUtils;

import java.util.Arrays;

/**
 * @author : Char
 * @date : 2019/7/11
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public final class LogUtils {

    private LogUtils() {
    }

    public static void v(final String contents) {
        v(null, contents);
    }

    public static void v(final String tag, final String contents) {
        log(Log.VERBOSE, tag, contents);
    }

    public static void d(final String contents) {
        d(null, contents);
    }

    public static void d(final String tag, final String contents) {
        log(Log.DEBUG, tag, contents);
    }

    public static void i(final String contents) {
        i(null, contents);
    }

    public static void i(final String tag, final String contents) {
        log(Log.INFO, tag, contents);
    }

    public static void w(final String contents) {
        w(null, contents);
    }

    public static void w(final String tag, final String contents) {
        log(Log.WARN, tag, contents);
    }

    public static void e(final String contents) {
        e(null, contents);
    }

    public static void e(final String tag, final String contents) {
        log(Log.ERROR, tag, contents);
    }

    public static void a(final String contents) {
        a(null, contents);
    }

    public static void a(final String tag, final String contents) {
        log(Log.ASSERT, tag, contents);
    }


    public static void log(final int type, String tag, final String contents) {
        if (!OpenUtils.isDebug()) {
            return;
        }
        if (TextUtils.isEmpty(contents)) {
            return;
        }
        if (TextUtils.isEmpty(tag)) {
            tag = getDefaultTag();
        }
        Log.println(type, tag, buildLogBody(contents));
    }

    private static String buildLogBody(String contents) {
        return contents;
    }

    private static String getDefaultTag() {
        String packageName = OpenUtils.getApp().getPackageName();
        return packageName.substring(packageName.lastIndexOf(".") + 1);
    }

}
