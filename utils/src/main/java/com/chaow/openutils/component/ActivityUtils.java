package com.chaow.openutils.component;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.chaow.openutils.OpenUtils;
import com.chaow.openutils.basic.ListUtils;

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

    private ActivityUtils() {
    }

    public static void init() {
        if (OpenUtils.getApp() == null) {
            throw new RuntimeException("please do OpenUtils.init first");
        }
        if (!ListUtils.isListEmpty(ACTIVITY_LIFECYCLE.mActivities)) {
            OpenUtils.getApp().unregisterActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE);
            ACTIVITY_LIFECYCLE.mActivities.clear();
        }
        OpenUtils.getApp().registerActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE);
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
            return topActivity == null ? OpenUtils.getApp() : topActivity;
        } else {
            return OpenUtils.getApp();
        }
    }

    /**
     * APP是否在前台
     *
     * @return
     */
    public static boolean isAppForeground() {
        ActivityManager am =
                (ActivityManager) OpenUtils.getApp().getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) {
            return false;
        }
        List<ActivityManager.RunningAppProcessInfo> info = am.getRunningAppProcesses();
        if (info == null || info.size() == 0) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo aInfo : info) {
            if (aInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return aInfo.processName.equals(OpenUtils.getApp().getPackageName());
            }
        }
        return false;
    }

    private static boolean isIntentAvailable(final Intent intent) {
        return OpenUtils.getApp()
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
