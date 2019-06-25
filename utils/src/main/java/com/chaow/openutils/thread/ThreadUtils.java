package com.chaow.openutils.thread;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : Char
 * @date : 2019/6/21
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public final class ThreadUtils {

    /** CPU数 */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    /** 默认线程数-可分配CPU数+1 */
    private static final int DEFAULT_CORE_POOL_SIZE = CPU_COUNT + 1;
    /** 默认最大线程数-可分配CPU数*2 */
    private static final int DEFAULT_MAXIMUM_POOL_SIZE = CPU_COUNT * 2;
    /** 默认保活时长-1秒 */
    private static final int DEFAULT_KEEP_ALIVE_TIME = 1;
    /** 默认保活时长单位-秒 */
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;
    private static final int DEFAULT_PRIOPITY = Thread.NORM_PRIORITY;
    /** 通过ThreadPoolExecutor的代理类来对线程池的管理 */
    private static ThreadPollProxy mThreadPollProxy;

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

    public static ThreadPollProxy getThreadPollProxy() {
        return getThreadPollProxy(null);
    }

    public static ThreadPollProxy getThreadPollProxy(String threadName) {
        return getThreadPollProxy(threadName, DEFAULT_PRIOPITY);
    }

    public static ThreadPollProxy getThreadPollProxy(String threadName, int priority) {
        return getThreadPollProxy(DEFAULT_CORE_POOL_SIZE,
                DEFAULT_MAXIMUM_POOL_SIZE,
                DEFAULT_KEEP_ALIVE_TIME,
                DEFAULT_TIME_UNIT,
                threadName, priority
        );
    }

    public static ThreadPollProxy getThreadPollProxy(int poolSize, int maxPoolSize, int keepAliveTime, TimeUnit timeUnit, String threadName, int priority) {
        synchronized (ThreadPollProxy.class) {
            if (mThreadPollProxy == null) {
                mThreadPollProxy = new ThreadPollProxy(
                        poolSize,
                        maxPoolSize,
                        keepAliveTime,
                        timeUnit,
                        threadName,
                        priority);
            }
        }
        return mThreadPollProxy;
    }

    /** 通过ThreadPoolExecutor的代理类来对线程池的管理 */
    public static class ThreadPollProxy {
        private ThreadPoolExecutor mThreadPoolExecutor;
        private ScheduledExecutorService mScheduledExecutorService;
        private int mCorePoolSize;
        private int mMaximumPoolSize;
        private long mKeepAliveTime;
        private TimeUnit mTimeUnit;
        private BasicThreadFactory mThreadFactory;

        public ThreadPollProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeUnit, String threadName, int priority) {
            this.mCorePoolSize = corePoolSize;
            this.mMaximumPoolSize = maximumPoolSize;
            this.mKeepAliveTime = keepAliveTime;
            this.mTimeUnit = timeUnit;
            mThreadFactory = new BasicThreadFactory.Builder().priority(priority).namingPattern(threadName).build();
        }

        public void execute(Runnable r) {
            if (mThreadPoolExecutor == null || mThreadPoolExecutor.isShutdown()) {
                mThreadPoolExecutor = new ThreadPoolExecutor(
                        mCorePoolSize,
                        mMaximumPoolSize,
                        mKeepAliveTime,
                        mTimeUnit,
                        new LinkedBlockingQueue<Runnable>(),
                        mThreadFactory);
            }
            mThreadPoolExecutor.execute(r);
        }

        public void schedule(Runnable r, long delay, TimeUnit timeUnit) {
            if (mScheduledExecutorService == null || mScheduledExecutorService.isShutdown()) {
                mScheduledExecutorService = new ScheduledThreadPoolExecutor(mCorePoolSize, mThreadFactory);
            }
            mScheduledExecutorService.schedule(r, delay, timeUnit);
        }

        public void scheduleAtRate(Runnable r, long initDelay, long rate, TimeUnit timeUnit) {
            if (mScheduledExecutorService == null || mScheduledExecutorService.isShutdown()) {
                mScheduledExecutorService = new ScheduledThreadPoolExecutor(mCorePoolSize, mThreadFactory);
            }
            mScheduledExecutorService.scheduleAtFixedRate(r, initDelay, rate, timeUnit);
        }

        public void scheduleWithDelay(Runnable r, long initDelay, long delay, TimeUnit timeUnit) {
            if (mScheduledExecutorService == null || mScheduledExecutorService.isShutdown()) {
                mScheduledExecutorService = new ScheduledThreadPoolExecutor(mCorePoolSize, mThreadFactory);
            }
            mScheduledExecutorService.scheduleWithFixedDelay(r, initDelay, delay, timeUnit);
        }
    }

}
