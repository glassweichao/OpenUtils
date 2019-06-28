package com.chaow.openutils.thread;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.TimeUnit;

/**
 * @author : Char
 * @date : 2019/6/21
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public final class ThreadUtils {

    private ThreadUtils() {
    }

    /**
     * 是否在主线程
     *
     * @return
     */
    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * 在主线程运行
     */
    public static void runOnUIThread(Runnable run) {
        new Handler(Looper.getMainLooper()).post(run);
    }

    public static void execute(Runnable r, String threadName) {
        ThreadPoolProxy.INSTANCE.findThreadPoolExecutorByName(threadName).execute(r);
    }

    public static void schedule(Runnable r, String threadName, long delay, TimeUnit timeUnit) {
        ThreadPoolProxy.INSTANCE.findScheduledExecutorServiceByName(threadName)
                .schedule(r, delay, timeUnit);
    }

    public static void scheduleAtRate(Runnable r, String threadName, long initDelay, long rate, TimeUnit timeUnit) {
        ThreadPoolProxy.INSTANCE.findScheduledExecutorServiceByName(threadName)
                .scheduleAtFixedRate(r, initDelay, rate, timeUnit);
    }

    public static void scheduleWithDelay(Runnable r, String threadName, long initDelay, long delay, TimeUnit timeUnit) {
        ThreadPoolProxy.INSTANCE.findScheduledExecutorServiceByName(threadName)
                .scheduleWithFixedDelay(r, initDelay, delay, timeUnit);
    }

}
