package com.chaow.openutils.thread;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.TimerTask;

/**
 * 进程工具
 *
 * @author : Char
 * @date : 2019/6/25
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public class ProcessUtils {

    public static boolean isMainProcess(Context context) {
        return context.getApplicationContext().getPackageName().equals(getCurrentProcessName(context));
    }

    public static String getCurrentProcessName(Context context) {
        String name = getCurrentProcessNameByAms(context);
        if (!TextUtils.isEmpty(name)) {
            return name;
        }
        name = getCurrentProcessNameByFile(context);
        if (!TextUtils.isEmpty(name)) {
            return name;
        }
        name = getCurrentProcessNameByReflect(context);
        return name;
    }

    private static String getCurrentProcessNameByFile(Context context) {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getCurrentProcessNameByAms(Context context) {
        ActivityManager am = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) {
            return null;
        }
        List<ActivityManager.RunningAppProcessInfo> info = am.getRunningAppProcesses();
        if (info == null || info.size() == 0) {
            return null;
        }
        int pid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo aInfo : info) {
            if (aInfo.pid == pid) {
                if (aInfo.processName != null) {
                    return aInfo.processName;
                }
            }
        }
        return null;
    }

    private static String getCurrentProcessNameByReflect(Context context) {
        try {
            String processName = "";
            Field loadedApkField = context.getApplicationContext().getClass().getField("mLoadedApk");
            loadedApkField.setAccessible(true);
            Object loadedApk = loadedApkField.get(context.getApplicationContext());

            Field activityThreadField = loadedApk.getClass().getDeclaredField("mActivityThread");
            activityThreadField.setAccessible(true);
            Object activityThread = activityThreadField.get(loadedApk);

            Method getProcessName = activityThread.getClass().getDeclaredMethod("getProcessName");
            return (String) getProcessName.invoke(activityThread);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
