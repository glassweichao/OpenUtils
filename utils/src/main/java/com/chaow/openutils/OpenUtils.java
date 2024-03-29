package com.chaow.openutils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.chaow.openutils.component.ActivityUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @author : Char
 * @date : 2019/7/8
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public final class OpenUtils {
    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;

    private static boolean isDebug;

    private OpenUtils() {

    }

    public static void init(final Context context) {
        if (context == null) {
            init(getApplicationByReflect());
            return;
        }
        init((Application) context.getApplicationContext());
    }

    public static void init(final Application app) {
        if (sApplication == null) {
            if (app == null) {
                sApplication = getApplicationByReflect();
            } else {
                sApplication = app;
            }
        } else {
            if (app != null && app.getClass() != sApplication.getClass()) {
                sApplication = app;
            }
        }
        ActivityUtils.init();
    }

    private static Application getApplicationByReflect() {
        try {
            @SuppressLint("PrivateApi")
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Object thread = activityThread.getMethod("currentActivityThread").invoke(null);
            Object app = activityThread.getMethod("getApplication").invoke(thread);
            if (app == null) {
                throw new NullPointerException("please init first");
            }
            return (Application) app;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("please init first");
    }

    public static Application getApp() {
        if (sApplication != null) {
            return sApplication;
        }
        Application application = getApplicationByReflect();
        init(application);
        return sApplication;
    }

    public static void setDebug(boolean debug) {
        isDebug = debug;
    }

    public static boolean isDebug() {
        return isDebug;
    }

}
