package com.chaow.openutils.thread;

import com.chaow.openutils.builder.Builder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : Char
 * @date : 2019/6/26
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public final class ThreadPoolProxy {

    private final static Map<String, ThreadPoolExecutor> THREAD_POOL_EXECUTOR_MAP = new HashMap<>();
    private final static Map<String, ScheduledExecutorService> SCHEDULED_EXECUTOR_SERVICE_MAP = new HashMap<>();

    private ThreadPoolExecutor mThreadPoolExecutor;
    private ScheduledExecutorService mScheduledExecutorService;
    private int mCorePoolSize;
    private int mMaximumPoolSize;
    private long mKeepAliveTime;
    private TimeUnit mTimeUnit;
    private BasicThreadFactory mThreadFactory;

    public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeUnit, String threadName, int priority) {
        this.mCorePoolSize = corePoolSize;
        this.mMaximumPoolSize = maximumPoolSize;
        this.mKeepAliveTime = keepAliveTime;
        this.mTimeUnit = timeUnit;
        mThreadFactory = new BasicThreadFactory.Builder().priority(priority).namingPattern(threadName).build();
    }

    private ThreadPoolProxy(Builder builder) {

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

    public static class Builder implements com.chaow.openutils.builder.Builder<ThreadPoolProxy> {
        private int mCorePoolSize;
        private int mMaximumPoolSize;
        private long mKeepAliveTime;
        private TimeUnit mTimeUnit;

        public void reset() {

        }

        @Override
        public ThreadPoolProxy builder() {
            final ThreadPoolProxy threadPoolProxy = new ThreadPoolProxy(this);
            reset();
            return threadPoolProxy;
        }
    }

}
