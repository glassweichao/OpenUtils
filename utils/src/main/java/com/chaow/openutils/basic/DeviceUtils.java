package com.chaow.openutils.basic;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;

import com.chaow.openutils.OpenUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.CHANGE_WIFI_STATE;
import static android.Manifest.permission.INTERNET;
import static android.content.Context.WIFI_SERVICE;

/**
 * @author : Char
 * @date : 2019/7/4
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public final class DeviceUtils {

    private DeviceUtils() {

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
     * @return {@code true}: 有网络<br>{@code false}: 无网络
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
     * @return {@code true}: wifi已连接<br>{@code false}: wifi未连接
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
     * @return {@code true}: 4g已连接<br>{@code false}: 未连接
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

    /**
     * 手机是否root权限
     *
     * @return {@code true}: 已root <br>{@code false}: 未root
     */
    public static boolean isRooted() {
        String su = "su";
        String[] locations = {"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/",
                "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/",
                "/system/sbin/", "/usr/bin/", "/vendor/bin/"};
        for (String location : locations) {
            if (new File(location + su).exists()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取设备android版本号
     *
     * @return android版本号
     */
    public static String getSDKVersionName() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取设备android版本号
     *
     * @return android版本号
     */
    public static int getSDKVersionCode() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取Android ID
     *
     * @return
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidID() {
        String id = Settings.Secure.getString(
                OpenUtils.getApp().getContentResolver(),
                Settings.Secure.ANDROID_ID
        );
        return id == null ? "" : id;
    }

    public static String getMacAddress() {
        String macAddress = getMacAddress((String[]) null);
        if (!"".equals(macAddress) || getWifiEnabled()) {
            return macAddress;
        }
        setWifiEnabled(true);
        setWifiEnabled(false);
        return getMacAddress((String[]) null);
    }

    /**
     * wifi是否可用
     *
     * @return {@code true}: 可用 <br>{@code false}: 不可用
     */
    public static boolean getWifiEnabled() {
        @SuppressLint("WifiManagerLeak")
        WifiManager manager = (WifiManager) OpenUtils.getApp().getSystemService(WIFI_SERVICE);
        if (manager == null) {
            return false;
        }
        return manager.isWifiEnabled();
    }

    /**
     * 设置wifi状态
     *
     * @param enabled {@code true}: 可用 <br>{@code false}: 不可用
     */
    @RequiresPermission(CHANGE_WIFI_STATE)
    public static void setWifiEnabled(final boolean enabled) {
        @SuppressLint("WifiManagerLeak")
        WifiManager manager = (WifiManager) OpenUtils.getApp().getSystemService(WIFI_SERVICE);
        if (manager == null) {
            return;
        }
        if (enabled == manager.isWifiEnabled()) {
            return;
        }
        manager.setWifiEnabled(enabled);
    }

    @RequiresPermission(allOf = {ACCESS_WIFI_STATE, INTERNET})
    public static String getMacAddress(final String... excepts) {
        String macAddress = getMacAddressByNetworkInterface();
        if (isAddressNotInExcepts(macAddress, excepts)) {
            return macAddress;
        }
        macAddress = getMacAddressByInetAddress();
        if (isAddressNotInExcepts(macAddress, excepts)) {
            return macAddress;
        }
        macAddress = getMacAddressByWifiInfo();
        if (isAddressNotInExcepts(macAddress, excepts)) {
            return macAddress;
        }
        return "";
    }

    private static boolean isAddressNotInExcepts(final String address, final String... excepts) {
        if (excepts == null || excepts.length == 0) {
            return !"02:00:00:00:00:00".equals(address);
        }
        for (String filter : excepts) {
            if (address.equals(filter)) {
                return false;
            }
        }
        return true;
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    private static String getMacAddressByWifiInfo() {
        try {
            final WifiManager wifi = (WifiManager) OpenUtils.getApp()
                    .getApplicationContext().getSystemService(WIFI_SERVICE);
            if (wifi != null) {
                final WifiInfo info = wifi.getConnectionInfo();
                if (info != null) {
                    return info.getMacAddress();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    private static String getMacAddressByNetworkInterface() {
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                if (ni == null || !"wlan0".equalsIgnoreCase(ni.getName())) {
                    continue;
                }
                byte[] macBytes = ni.getHardwareAddress();
                if (macBytes != null && macBytes.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (byte b : macBytes) {
                        sb.append(String.format("%02x:", b));
                    }
                    return sb.substring(0, sb.length() - 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    private static String getMacAddressByInetAddress() {
        try {
            InetAddress inetAddress = getInetAddress();
            if (inetAddress != null) {
                NetworkInterface ni = NetworkInterface.getByInetAddress(inetAddress);
                if (ni != null) {
                    byte[] macBytes = ni.getHardwareAddress();
                    if (macBytes != null && macBytes.length > 0) {
                        StringBuilder sb = new StringBuilder();
                        for (byte b : macBytes) {
                            sb.append(String.format("%02x:", b));
                        }
                        return sb.substring(0, sb.length() - 1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    private static InetAddress getInetAddress() {
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                if (!ni.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress inetAddress = addresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String hostAddress = inetAddress.getHostAddress();
                        if (hostAddress.indexOf(':') < 0) {
                            return inetAddress;
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得设备型号
     *
     * @return 设备型号
     */
    public static String getModel() {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");
        } else {
            model = "";
        }
        return model;
    }

    /**
     * 是否是平板
     *
     * @return {@code true}: 是 <br>{@code false}: 不是
     */
    public static boolean isTablet() {
        return (OpenUtils.getApp().getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * 是否是模拟器
     *
     * @return {@code true}: 是 <br>{@code false}: 不是
     */
    public static boolean isEmulator() {
        boolean checkProperty = Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.toLowerCase().contains("vbox")
                || Build.FINGERPRINT.toLowerCase().contains("test-keys")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
        if (checkProperty) {
            return true;
        }

        String operatorName = "";
        TelephonyManager tm = (TelephonyManager) OpenUtils.getApp().getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null) {
            String name = tm.getNetworkOperatorName();
            if (name != null) {
                operatorName = name;
            }
        }
        boolean checkOperatorName = "android".equals(operatorName.toLowerCase());
        if (checkOperatorName) {
            return true;
        }

        String url = "tel:999999";
        Intent intent = new Intent();
        intent.setData(Uri.parse(url));
        intent.setAction(Intent.ACTION_DIAL);
        return intent.resolveActivity(OpenUtils.getApp().getPackageManager()) == null;

    }
}
