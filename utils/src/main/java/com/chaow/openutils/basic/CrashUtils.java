package com.chaow.openutils.basic;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;

import com.chaow.openutils.OpenUtils;
import com.chaow.openutils.thread.ThreadUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * @author : Char
 * @date : 2019/8/26
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    : 崩溃工具类
 */
public final class CrashUtils {
    /** 默认日志目录 */
    private static String defaultDir;
    /** 日志目录 */
    private static String dir;
    /** 版本名 */
    private static String versionName;
    /** 版本号 */
    private static int versionCode;

    private static final String FILE_SEP = System.getProperty("file.separator");
    @SuppressLint("SimpleDateFormat")
    private static final Format FORMAT = new SimpleDateFormat("MM-dd_HH-mm-ss");

    private static final Thread.UncaughtExceptionHandler DEFAULT_UNCAUGHT_EXCEPTION_HANDLER;
    private static final Thread.UncaughtExceptionHandler UNCAUGHT_EXCEPTION_HANDLER;

    /** 崩溃监听 */
    private static OnCrashListener sOnCrashListener;

    static {
        try {
            PackageInfo pi = OpenUtils.getApp()
                    .getPackageManager()
                    .getPackageInfo(OpenUtils.getApp().getPackageName(), 0);
            if (pi != null) {
                versionName = pi.versionName;
                versionCode = pi.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        DEFAULT_UNCAUGHT_EXCEPTION_HANDLER = Thread.getDefaultUncaughtExceptionHandler();

        UNCAUGHT_EXCEPTION_HANDLER = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                if (e == null) {
                    if (DEFAULT_UNCAUGHT_EXCEPTION_HANDLER != null) {
                        DEFAULT_UNCAUGHT_EXCEPTION_HANDLER.uncaughtException(t, null);
                    } else {
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                    return;
                }

                final String time = FORMAT.format(new Date(System.currentTimeMillis()));
                final StringBuilder sb = new StringBuilder();
                final String head = "************* Log Head ****************" +
                        "\nTime Of Crash      : " + time +
                        "\nDevice Manufacturer: " + Build.MANUFACTURER +
                        "\nDevice Model       : " + Build.MODEL +
                        "\nAndroid Version    : " + Build.VERSION.RELEASE +
                        "\nAndroid SDK        : " + Build.VERSION.SDK_INT +
                        "\nApp VersionName    : " + versionName +
                        "\nApp VersionCode    : " + versionCode +
                        "\n************* Log Head ****************\n\n";
                sb.append(head).append(e.toString());
                final String crashInfo = sb.toString();
                final String fullPath = (dir == null ? defaultDir : dir) + time + ".txt";
                if (FileUtils.createFile(fullPath)) {
                    input2File(crashInfo, fullPath);
                } else {
                    LogUtils.e("CrashUtils", "create " + fullPath + " failed!");
                }

                if (sOnCrashListener != null) {
                    sOnCrashListener.onCrash(crashInfo, e);
                }

                if (DEFAULT_UNCAUGHT_EXCEPTION_HANDLER != null) {
                    DEFAULT_UNCAUGHT_EXCEPTION_HANDLER.uncaughtException(t, e);
                }
            }
        };
    }

    /**
     * 将崩溃信息输出到文件
     *
     * @param input    崩溃信息
     * @param filePath 文件地址
     */
    private static void input2File(final String input, final String filePath) {
        ThreadUtils.execute(new Runnable() {
            @Override
            public void run() {
                FileIOUtils.writeFileFromString(filePath, input);
            }
        }, CrashUtils.class.getSimpleName());
    }

    private CrashUtils() {
    }

    /**
     * 初始化
     * <p>需要添加权限 {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     */
    @SuppressLint("MissingPermission")
    public static void init() {
        init("");
    }

    /**
     * 初始化
     * <p>需要添加权限 {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     *
     * @param crashDir crash报告缓存目录
     */
    @RequiresPermission(WRITE_EXTERNAL_STORAGE)
    public static void init(@NonNull final File crashDir) {
        init(crashDir.getAbsolutePath(), null);
    }

    /**
     * 初始化
     * <p>需要添加权限 {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     *
     * @param crashDirPath crash报告缓存目录路径
     */
    @RequiresPermission(WRITE_EXTERNAL_STORAGE)
    public static void init(final String crashDirPath) {
        init(crashDirPath, null);
    }

    /**
     * 初始化
     * <p>需要添加权限 {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     *
     * @param onCrashListener 崩溃监听
     */
    @SuppressLint("MissingPermission")
    public static void init(final OnCrashListener onCrashListener) {
        init("", onCrashListener);
    }

    /**
     * 初始化
     * <p>需要添加权限 {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     *
     * @param crashDir        crash报告缓存目录
     * @param onCrashListener 崩溃监听
     */
    @RequiresPermission(WRITE_EXTERNAL_STORAGE)
    public static void init(@NonNull final File crashDir, final OnCrashListener onCrashListener) {
        init(crashDir.getAbsolutePath(), onCrashListener);
    }

    /**
     * 初始化
     * <p>需要添加权限 {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     *
     * @param crashDirPath    crash报告缓存目录路径
     * @param onCrashListener 崩溃监听
     */
    @RequiresPermission(WRITE_EXTERNAL_STORAGE)
    public static void init(final String crashDirPath, final OnCrashListener onCrashListener) {
        if (StringUtils.isSpace(crashDirPath)) {
            dir = null;
        } else {
            dir = crashDirPath.endsWith(FILE_SEP) ? crashDirPath : crashDirPath + FILE_SEP;
        }
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                && OpenUtils.getApp().getExternalCacheDir() != null)
            defaultDir = OpenUtils.getApp().getExternalCacheDir() + FILE_SEP + "crash" + FILE_SEP;
        else {
            defaultDir = OpenUtils.getApp().getCacheDir() + FILE_SEP + "crash" + FILE_SEP;
        }
        sOnCrashListener = onCrashListener;
        Thread.setDefaultUncaughtExceptionHandler(UNCAUGHT_EXCEPTION_HANDLER);
    }

    public interface OnCrashListener {
        void onCrash(String crashInfo, Throwable e);
    }

}
