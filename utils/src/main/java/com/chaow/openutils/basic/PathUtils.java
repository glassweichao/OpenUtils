package com.chaow.openutils.basic;

import android.os.Build;
import android.os.Environment;

import com.chaow.openutils.OpenUtils;

import java.io.File;

/**
 * @author : Char
 * @date : 2019/9/2
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :系统相关路径工具类
 */
public final class PathUtils {

    private PathUtils() {

    }

    /**
     * /system 路径
     * 根路径
     *
     * @return the path of /system
     */
    public static String getRootPath() {
        return getAbsolutePath(Environment.getRootDirectory());
    }

    /**
     * /data 路径
     * 数据路径
     *
     * @return the path of /data
     */
    public static String getDataPath() {
        return getAbsolutePath(Environment.getDataDirectory());
    }

    /**
     * /cache 路径
     * 下载缓存路径
     *
     * @return the path of /cache
     */
    public static String getCachePath() {
        return getAbsolutePath(Environment.getDownloadCacheDirectory());
    }

    /**
     * /data/data/package 路径
     * 内存应用数据路径
     *
     * @return the path of /data/data/package
     */
    public static String getInternalAppDataPath() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return OpenUtils.getApp().getApplicationInfo().dataDir;
        }
        return getAbsolutePath(OpenUtils.getApp().getDataDir());
    }

    /**
     * /data/data/package/code_cache 路径
     * 内存应用代码缓存路径
     *
     * @return the path of /data/data/package/code_cache
     */
    public static String getInternalAppCodeCacheDir() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return OpenUtils.getApp().getApplicationInfo().dataDir + "/code_cache";
        }
        return getAbsolutePath(OpenUtils.getApp().getCodeCacheDir());
    }

    /**
     * /data/data/package/cache 路径
     * 内存应用缓存路径
     *
     * @return the path of /data/data/package/cache
     */
    public static String getInternalAppCachePath() {
        return getAbsolutePath(OpenUtils.getApp().getCacheDir());
    }

    /**
     * /data/data/package/databases 路径
     * 内存应用数据库路径
     *
     * @return the path of /data/data/package/databases
     */
    public static String getInternalAppDbsPath() {
        return OpenUtils.getApp().getApplicationInfo().dataDir + "/databases";
    }

    /**
     * /data/data/package/databases/name 路径
     * 内存应用数据库路径
     *
     * @param name The name of database.
     * @return the path of /data/data/package/databases/name
     */
    public static String getInternalAppDbPath(String name) {
        return getAbsolutePath(OpenUtils.getApp().getDatabasePath(name));
    }

    /**
     * /data/data/package/files 路径
     * 内存应用文件路径
     *
     * @return the path of /data/data/package/files
     */
    public static String getInternalAppFilesPath() {
        return getAbsolutePath(OpenUtils.getApp().getFilesDir());
    }

    /**
     * Return the path of /data/data/package/shared_prefs.
     * 内存应用 SP 路径
     *
     * @return the path of /data/data/package/shared_prefs
     */
    public static String getInternalAppSpPath() {
        return OpenUtils.getApp().getApplicationInfo().dataDir + "/shared_prefs";
    }

    /**
     * Return the path of /data/data/package/no_backup.
     * 内存应用未备份文件路径
     *
     * @return the path of /data/data/package/no_backup
     */
    public static String getInternalAppNoBackupFilesPath() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return OpenUtils.getApp().getApplicationInfo().dataDir + "/no_backup";
        }
        return getAbsolutePath(OpenUtils.getApp().getNoBackupFilesDir());
    }

    /**
     * 路径 /storage/emulated/0.
     * 外存路径
     *
     * @return the path of /storage/emulated/0
     */
    public static String getExternalStoragePath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(Environment.getExternalStorageDirectory());
    }

    /**
     * 路径 /storage/emulated/0/Music.
     * 外存音乐路径
     *
     * @return the path of /storage/emulated/0/Music
     */
    public static String getExternalMusicPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC));
    }

    /**
     * 路径 /storage/emulated/0/Podcasts.
     * 外存播客路径
     *
     * @return the path of /storage/emulated/0/Podcasts
     */
    public static String getExternalPodcastsPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS));
    }

    /**
     * 路径 /storage/emulated/0/Ringtones.
     * 外存铃声路径
     *
     * @return the path of /storage/emulated/0/Ringtones
     */
    public static String getExternalRingtonesPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES));
    }

    /**
     * 路径 /storage/emulated/0/Alarms.
     * 外存闹铃路径
     *
     * @return the path of /storage/emulated/0/Alarms
     */
    public static String getExternalAlarmsPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS));
    }

    /**
     * 路径 /storage/emulated/0/Notifications.
     * 外存通知路径
     *
     * @return the path of /storage/emulated/0/Notifications
     */
    public static String getExternalNotificationsPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS));
    }

    /**
     * 路径 /storage/emulated/0/Pictures.
     * 外存图片路径
     *
     * @return the path of /storage/emulated/0/Pictures
     */
    public static String getExternalPicturesPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
    }

    /**
     * 路径 /storage/emulated/0/Movies.
     * 外存影片路径
     *
     * @return the path of /storage/emulated/0/Movies
     */
    public static String getExternalMoviesPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES));
    }

    /**
     * 路径 /storage/emulated/0/Download.
     * 外存下载路径
     *
     * @return the path of /storage/emulated/0/Download
     */
    public static String getExternalDownloadsPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
    }

    /**
     * 路径 /storage/emulated/0/DCIM.
     * 外存数码相机图片路径
     *
     * @return the path of /storage/emulated/0/DCIM
     */
    public static String getExternalDcimPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
    }

    /**
     * 路径 /storage/emulated/0/Documents.
     * 外存文档路径
     *
     * @return the path of /storage/emulated/0/Documents
     */
    public static String getExternalDocumentsPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return getAbsolutePath(Environment.getExternalStorageDirectory()) + "/Documents";
        }
        return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS));
    }

    /**
     * 路径 /storage/emulated/0/Android/data/package.
     * 外存应用数据路径
     *
     * @return the path of /storage/emulated/0/Android/data/package
     */
    public static String getExternalAppDataPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        File externalCacheDir = OpenUtils.getApp().getExternalCacheDir();
        if (externalCacheDir == null) {
            return "";
        }
        return getAbsolutePath(externalCacheDir.getParentFile());
    }

    /**
     * 路径 /storage/emulated/0/Android/data/package/cache.
     * 外存应用缓存路径
     *
     * @return the path of /storage/emulated/0/Android/data/package/cache
     */
    public static String getExternalAppCachePath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(OpenUtils.getApp().getExternalCacheDir());
    }

    /**
     * 路径 /storage/emulated/0/Android/data/package/files.
     * 外存应用文件路径
     *
     * @return the path of /storage/emulated/0/Android/data/package/files
     */
    public static String getExternalAppFilesPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(OpenUtils.getApp().getExternalFilesDir(null));
    }

    /**
     * 路径 /storage/emulated/0/Android/data/package/files/Music.
     * 外存应用音乐路径
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Music
     */
    public static String getExternalAppMusicPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(OpenUtils.getApp().getExternalFilesDir(Environment.DIRECTORY_MUSIC));
    }

    /**
     * 路径 /storage/emulated/0/Android/data/package/files/Podcasts.
     * 外存应用播客路径
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Podcasts
     */
    public static String getExternalAppPodcastsPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(OpenUtils.getApp().getExternalFilesDir(Environment.DIRECTORY_PODCASTS));
    }

    /**
     * 路径 /storage/emulated/0/Android/data/package/files/Ringtones.
     * 外存应用铃声路径
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Ringtones
     */
    public static String getExternalAppRingtonesPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(OpenUtils.getApp().getExternalFilesDir(Environment.DIRECTORY_RINGTONES));
    }

    /**
     * 路径 /storage/emulated/0/Android/data/package/files/Alarms.
     * 外存应用闹铃路径
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Alarms
     */
    public static String getExternalAppAlarmsPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(OpenUtils.getApp().getExternalFilesDir(Environment.DIRECTORY_ALARMS));
    }

    /**
     * 路径 /storage/emulated/0/Android/data/package/files/Notifications.
     * 外存应用通知路径
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Notifications
     */
    public static String getExternalAppNotificationsPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(OpenUtils.getApp().getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS));
    }

    /**
     * 路径 /storage/emulated/0/Android/data/package/files/Pictures.
     * 外存应用图片路径
     *
     * @return path of /storage/emulated/0/Android/data/package/files/Pictures
     */
    public static String getExternalAppPicturesPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(OpenUtils.getApp().getExternalFilesDir(Environment.DIRECTORY_PICTURES));
    }

    /**
     * 路径 /storage/emulated/0/Android/data/package/files/Movies.
     * 外存应用影片路径
     *
     * @return /storage/emulated/0/Android/data/package/files/Movies
     */
    public static String getExternalAppMoviesPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(OpenUtils.getApp().getExternalFilesDir(Environment.DIRECTORY_MOVIES));
    }

    /**
     * 路径 /storage/emulated/0/Android/data/package/files/Download.
     * 外存应用下载路径
     *
     * @return /storage/emulated/0/Android/data/package/files/Download
     */
    public static String getExternalAppDownloadPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(OpenUtils.getApp().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS));
    }

    /**
     * 路径 /storage/emulated/0/Android/data/package/files/DCIM.
     * 外存应用数码相机图片路径
     *
     * @return /storage/emulated/0/Android/data/package/files/DCIM
     */
    public static String getExternalAppDcimPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(OpenUtils.getApp().getExternalFilesDir(Environment.DIRECTORY_DCIM));
    }

    /**
     * 路径 /storage/emulated/0/Android/data/package/files/Documents.
     * 外存应用文档路径
     *
     * @return /storage/emulated/0/Android/data/package/files/Documents
     */
    public static String getExternalAppDocumentsPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return getAbsolutePath(OpenUtils.getApp().getExternalFilesDir(null)) + "/Documents";
        }
        return getAbsolutePath(OpenUtils.getApp().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS));
    }

    /**
     * 路径 /storage/emulated/0/Android/obb/package.
     * 外存应用 OBB 路径
     *
     * @return the path of /storage/emulated/0/Android/obb/package
     */
    public static String getExternalAppObbPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return getAbsolutePath(OpenUtils.getApp().getObbDir());
    }

    /**
     * SD卡是否可用
     *
     * @return
     */
    private static boolean isExternalStorageDisable() {
        return !Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取绝对路径
     *
     * @param file 文件
     * @return 文件的绝对路径名
     */
    private static String getAbsolutePath(final File file) {
        if (file == null) {
            return "";
        }
        return file.getAbsolutePath();
    }

}
