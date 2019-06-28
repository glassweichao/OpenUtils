package com.chaow.openutils.thread;

import android.os.Build;
import android.text.TextUtils;

import com.chaow.openutils.Validate;
import com.chaow.openutils.basic.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : Char
 * @date : 2019/6/26
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public enum ThreadPoolProxy {

    /** 实例 */
    INSTANCE;

    private static final String DEFAULT_NAMING_PATTERN = "ThreadUtils-pool-thread-%d";
    /** CPU数 */
    public static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    /** 默认线程数-可分配CPU数 */
    public static final int DEFAULT_CORE_POOL_SIZE = CPU_COUNT;
    /** 默认最大线程数-可分配CPU数*2 */
    public static final int DEFAULT_MAXIMUM_POOL_SIZE = CPU_COUNT * 2;
    /** 默认保活时长-1秒 */
    public static final long DEFAULT_KEEP_ALIVE_TIME = 1;
    /** 默认保活时长单位-秒 */
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;
    private final Map<String, ThreadPoolExecutor> THREAD_POOL_EXECUTOR_MAP = new HashMap<>();
    private final Map<String, ScheduledExecutorService> SCHEDULED_EXECUTOR_SERVICE_MAP = new HashMap<>();

    private ThreadPoolExecutor createDefaultThreadPoolByName(String poolName) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                DEFAULT_CORE_POOL_SIZE,
                DEFAULT_MAXIMUM_POOL_SIZE,
                DEFAULT_KEEP_ALIVE_TIME,
                DEFAULT_TIME_UNIT,
                new LinkedBlockingQueue<Runnable>(),
                new BasicThreadFactory.Builder().daemon(false).priority(Thread.NORM_PRIORITY).namingPattern(poolName).build());
        THREAD_POOL_EXECUTOR_MAP.put(poolName, threadPoolExecutor);
        return threadPoolExecutor;
    }

    public ThreadPoolExecutor createDefaultThreadPool(int poolSize, int maxPoolSize, int keepAliveTime, TimeUnit timeUnit, String poolName, int priority, boolean daemon) {
        ThreadPoolExecutor threadPoolExecutor = findThreadPoolExecutorByName(poolName);
        if (threadPoolExecutor == null) {
            threadPoolExecutor = new ThreadPoolExecutor(
                    poolSize,
                    maxPoolSize,
                    keepAliveTime,
                    timeUnit,
                    new LinkedBlockingQueue<Runnable>(),
                    new BasicThreadFactory.Builder().daemon(daemon).priority(priority).namingPattern(poolName).build());
            THREAD_POOL_EXECUTOR_MAP.put(poolName, threadPoolExecutor);
        }
        return threadPoolExecutor;
    }

    private ScheduledExecutorService createDefaultScheduleExecutorServiceByName(String poolName) {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(DEFAULT_CORE_POOL_SIZE, new BasicThreadFactory.Builder().daemon(false).priority(Thread.NORM_PRIORITY).namingPattern(poolName).build());
        SCHEDULED_EXECUTOR_SERVICE_MAP.put(poolName, scheduledExecutorService);
        return scheduledExecutorService;
    }

    public ScheduledExecutorService createDefaultScheduleExecutorService(String poolName, int priority, boolean daemon) {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(DEFAULT_CORE_POOL_SIZE, new BasicThreadFactory.Builder().daemon(daemon).priority(priority).namingPattern(poolName).build());
        SCHEDULED_EXECUTOR_SERVICE_MAP.put(poolName, scheduledExecutorService);
        return scheduledExecutorService;
    }

    public final ThreadPoolExecutor findThreadPoolExecutorByName(String name) {
        String threadName = StringUtils.checkEmpty(name, DEFAULT_NAMING_PATTERN);
        ThreadPoolExecutor threadPoolExecutor = THREAD_POOL_EXECUTOR_MAP.get(threadName);
        if (threadPoolExecutor == null) {
            threadPoolExecutor = createDefaultThreadPoolByName(threadName);
        }
        return threadPoolExecutor;
    }

    public final ScheduledExecutorService findScheduledExecutorServiceByName(String name) {
        String threadName = StringUtils.checkEmpty(name, DEFAULT_NAMING_PATTERN);
        ScheduledExecutorService scheduledExecutorService = SCHEDULED_EXECUTOR_SERVICE_MAP.get(threadName);
        if (scheduledExecutorService == null) {
            scheduledExecutorService = createDefaultScheduleExecutorServiceByName(threadName);
        }
        return scheduledExecutorService;
    }

}
