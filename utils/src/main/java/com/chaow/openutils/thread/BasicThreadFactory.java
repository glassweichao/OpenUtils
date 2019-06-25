package com.chaow.openutils.thread;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.chaow.openutils.Validate;
import com.chaow.openutils.basic.StringUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author : Char
 * @date : 2019/6/25
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 * <p>
 * A <em>builder</em> class for creating instances of {@code
 * BasicThreadFactory}.
 * </p>
 * <p>
 * Using this builder class instances of {@code BasicThreadFactory} can be
 * created and initialized. The class provides methods that correspond to
 * the configuration options supported by {@code BasicThreadFactory}. Method
 * chaining is supported.
 * </p>
 */
public class BasicThreadFactory implements ThreadFactory {

    private final AtomicLong threadCounter;

    /** Stores the wrapped factory. */
    private final ThreadFactory wrappedFactory;

    /** Stores the naming pattern for newly created threads. */
    private final String namingPattern;

    /** Stores the priority. */
    private final Integer priority;

    /** Stores the daemon status flag. */
    private final Boolean daemon;

    private BasicThreadFactory(final Builder builder) {
        if (builder.wrappedFactory == null) {
            wrappedFactory = Executors.defaultThreadFactory();
        } else {
            wrappedFactory = builder.wrappedFactory;
        }
        namingPattern = builder.namingPattern;
        priority = builder.priority;
        daemon = builder.daemon;

        threadCounter = new AtomicLong();
    }

    public final ThreadFactory getWrappedFactory() {
        return wrappedFactory;
    }

    public final String getNamingPattern() {
        return namingPattern;
    }

    public final Boolean getDaemonFlag() {
        return daemon;
    }

    public final Integer getPriority() {
        return priority;
    }

    public long getThreadCount() {
        return threadCounter.get();
    }

    @Override
    public Thread newThread(@NonNull Runnable r) {
        final Thread thread = getWrappedFactory().newThread(r);
        initializeThread(thread);
        return thread;
    }

    private void initializeThread(final Thread thread) {

        if (getNamingPattern() != null) {
            final Long count = threadCounter.incrementAndGet();
            thread.setName(String.format(getNamingPattern(), count));
        }

        if (getPriority() != null) {
            thread.setPriority(getPriority());
        }

        if (getDaemonFlag() != null) {
            thread.setDaemon(getDaemonFlag());
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "thread:{name:" + namingPattern + ",priority:" + priority + ",deamon:" + daemon + "}";
    }

    public static class Builder {
        private static final String DEFAULT_NAMING_PATTERN = "ThreadUtils-pool-thread-%d";

        /** The wrapped factory. */
        private ThreadFactory wrappedFactory;
        /** The naming pattern. */
        private String namingPattern;

        /** The priority. */
        private int priority;

        /** The daemon flag. */
        private boolean daemon;

        public Builder wrappedFactory(final ThreadFactory factory) {
            Validate.notNull(factory, "Wrapped ThreadFactory must not be null!");

            wrappedFactory = factory;
            return this;
        }

        public Builder namingPattern(final String pattern) {
            namingPattern = StringUtils.checkEmpty(pattern, DEFAULT_NAMING_PATTERN);
            return this;
        }

        public Builder daemon(final boolean daemon) {
            this.daemon = daemon;
            return this;
        }

        public Builder priority(final int priority) {
            this.priority = priority;
            return this;
        }

        public void reset() {
            namingPattern = DEFAULT_NAMING_PATTERN;
            priority = Thread.NORM_PRIORITY;
            daemon = false;
        }

        public BasicThreadFactory build() {
            final BasicThreadFactory factory = new BasicThreadFactory(this);
            reset();
            System.out.println(factory.toString());
            return factory;
        }

    }
}
