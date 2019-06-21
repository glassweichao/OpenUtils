package com.chaow.openutils.component;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Char
 * @date : 2019/6/19
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public final class ActivityUtils {

    private static final ActivityLifecycle ACTIVITY_LIFECYCLE = new ActivityLifecycle();

    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;

    private ActivityUtils() {
    }

    public static void init(Application app) {
        if (sApplication == null) {
            if (app == null) {
                sApplication = getApplicationByReflect();
            } else {
                sApplication = app;
            }

            sApplication.registerActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE);
        } else {
            if (app != null && app.getClass() != sApplication.getClass()) {
                sApplication.unregisterActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE);
                ACTIVITY_LIFECYCLE.mActivities.clear();
                sApplication = app;
                sApplication.registerActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE);
            }
        }
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

    public static void finishAllActivity() {
        if (ACTIVITY_LIFECYCLE.mActivities.size() <= 0) {
            return;
        }
        for (Activity activity : ACTIVITY_LIFECYCLE.mActivities) {
            activity.finish();
        }
        ACTIVITY_LIFECYCLE.mActivities.clear();
    }

    /**
     * Start home activity.
     */
    public static void startHomeActivity() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        startActivity(homeIntent);
    }

    public static boolean startActivity(@NonNull final Intent intent) {
        return startActivity(intent, getTopActivity(), null);
    }

    private static boolean startActivity(final Intent intent,
                                         final Context context,
                                         final Bundle options) {
        if (!isIntentAvailable(intent)) {
            return false;
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (options != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            context.startActivity(intent, options);
        } else {
            context.startActivity(intent);
        }
        return true;
    }

    public static Context getTopActivity() {
        if (isAppForeground()) {
            Activity topActivity = ACTIVITY_LIFECYCLE.getTopActivity();
            return topActivity == null ? getApplication() : topActivity;
        } else {
            return getApplication();
        }
    }

    /**
     * APP是否在前台
     *
     * @return
     */
    public static boolean isAppForeground() {
        ActivityManager am =
                (ActivityManager) getApplication().getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) {
            return false;
        }
        List<ActivityManager.RunningAppProcessInfo> info = am.getRunningAppProcesses();
        if (info == null || info.size() == 0) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo aInfo : info) {
            if (aInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return aInfo.processName.equals(getApplication().getPackageName());
            }
        }
        return false;
    }

    public static Application getApplication() {
        if (sApplication != null) {
            return sApplication;
        }
        try {
            @SuppressLint("PrivateApi")
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Object at = activityThread.getMethod("currentActivityThread").invoke(null);
            Object app = activityThread.getMethod("getApplication").invoke(at);
            if (app == null) {
                throw new NullPointerException("please init first");
            }
            init((Application) app);
            return sApplication;
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

    private static boolean isIntentAvailable(final Intent intent) {
        return getApplication()
                .getPackageManager()
                .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
                .size() > 0;
    }

    static class ActivityLifecycle implements Application.ActivityLifecycleCallbacks {

        final List<Activity> mActivities = new ArrayList<>();


        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            pushActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            popActivity(activity);
        }

        /**
         * @param activity 添加一个activity到管理里
         */
        void pushActivity(Activity activity) {
            mActivities.add(activity);
        }

        /**
         * @param activity 删除一个activity在管理里
         */
        void popActivity(Activity activity) {
            mActivities.remove(activity);
        }

        Activity getTopActivity() {
            if (!mActivities.isEmpty()) {
                int lastPosition = mActivities.size() - 1;
                final Activity topActivity = mActivities.get(lastPosition);
                if (topActivity != null) {
                    return topActivity;
                }
            }
            return null;
        }
    }

}
