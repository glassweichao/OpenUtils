package com.chaow.openutils;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author : Char
 * @date : 2019/7/4
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public final class PhoneUtils {

    private PhoneUtils() {

    }

    /**
     * 获取手机震动管理器
     *
     * @param context
     * @return
     */
    private static Vibrator getVibrator(Context context) {
        return (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    /**
     * 当前设备是否是手机
     *
     * @return
     */
    public static boolean isPhone() {
        TelephonyManager tm = getTelephonyManager();
        return tm.getPhoneType() != TelephonyManager.PHONE_TYPE_NONE;
    }

    private static TelephonyManager getTelephonyManager() {
        return (TelephonyManager) OpenUtils.getApp().getSystemService(Context.TELEPHONY_SERVICE);
    }

    /**
     * 启动手机一次震动
     *
     * @param context
     * @param milliseconds 震动时长
     */
    public static void startOneShotVibrator(Context context, @IntRange(from = 0) final long milliseconds) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startVibrator(context, new long[]{milliseconds}, VibrationEffect.DEFAULT_AMPLITUDE, -1);
        } else {
            startVibrator(context, new long[]{milliseconds}, -1, -1);
        }
    }

    /**
     * 启动手机连续震动
     *
     * @param context
     * @param milliseconds 震动间隔时长
     */
    public static void startRepeatVibrator(Context context, final long[] milliseconds) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startVibrator(context, milliseconds, VibrationEffect.DEFAULT_AMPLITUDE, milliseconds.length - 1);
        } else {
            startVibrator(context, milliseconds, -1, milliseconds.length - 1);
        }
    }

    /**
     * 启动手机震动
     *
     * @param context
     * @param milliseconds
     * @param amplitude
     * @param repeat
     */
    public static void startVibrator(Context context, final long[] milliseconds, final int amplitude, final int repeat) {
        Vibrator vibrator = getVibrator(context);
        if (vibrator == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (repeat > -1) {
                vibrator.vibrate(VibrationEffect.createWaveform(milliseconds, repeat));
            } else {
                vibrator.vibrate(VibrationEffect.createOneShot(milliseconds[0], amplitude));
            }
        } else {
            vibrator.vibrate(milliseconds[0]);
        }
    }

    /**
     * 取消手机震动
     *
     * @param context
     */
    public static void cancelVibrator(Context context) {
        Vibrator vibrator = getVibrator(context);
        if (vibrator == null) {
            return;
        }
        vibrator.cancel();
    }

    /**
     * 检查是否有手机网络
     *
     * @return
     */
    public static boolean isNetConnected() {
        try {
            return isWifiConnected() || is4GConnected();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * wifi是否已连接
     *
     * @return
     */
    public static boolean isWifiConnected() {
        try {
            boolean flag = true;
            ConnectivityManager connMgr = (ConnectivityManager) OpenUtils.getApp().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            boolean isWifiConn = networkInfo.isConnected();
            if (!isWifiConn) {
                flag = false;
            }
            return flag;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 4G是否已连接
     *
     * @return
     */
    public static boolean is4GConnected() {
        try {
            boolean flag = true;
            ConnectivityManager connMgr = (ConnectivityManager) OpenUtils.getApp().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            boolean isMobileConn = networkInfo.isConnected();
            if (!isMobileConn) {
                flag = false;
            }
            return flag;
        } catch (Exception e) {
            return false;
        }
    }



}
