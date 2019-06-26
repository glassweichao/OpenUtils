package com.chaow.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.chaow.openutils.thread.ThreadUtils;
import com.chaow.sample.databinding.ActivityThreadSampleBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : Char
 * @date : 2019/6/26
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public class ThreadSampleActivity extends AppCompatActivity {

    ActivityThreadSampleBinding mBinding;
    final static AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);
    StringBuffer mMessage = new StringBuffer();

    public static void start(Context context) {
        Intent starter = new Intent(context, ThreadSampleActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_thread_sample);
        mBinding.etMsg.setMovementMethod(ScrollingMovementMethod.getInstance());
        initEvent();
    }

    private void initEvent() {
        mBinding.btThreadPool.setOnClickListener(v -> {
            String threadName = mBinding.etThreadName.getText().toString().trim();
            ThreadUtils.getThreadPollProxy().execute(() -> printMsg("default thread poll"));
            ThreadUtils.getThreadPollProxy(threadName).execute(() -> printMsg("custom name thread"));
            ThreadUtils.getThreadPollProxy(threadName + "_anther").execute(() -> printMsg("custom name anther thread"));
            ThreadUtils.getThreadPollProxy(threadName, 1).execute(() -> printMsg("custom priority thread"));
        });
        mBinding.btDelayPost.setOnClickListener(v -> ThreadUtils.getThreadPollProxy().schedule(() -> printMsg("delay thread" + ATOMIC_INTEGER.addAndGet(1)), 5, TimeUnit.SECONDS));
        mBinding.btScheduleDelay.setOnClickListener(v -> ThreadUtils.getThreadPollProxy("schedule_with_delay").scheduleWithDelay(() -> printMsg("schedule with delay " + ATOMIC_INTEGER.addAndGet(1)), 1, 1, TimeUnit.SECONDS));
        mBinding.btScheduleRate.setOnClickListener(v -> ThreadUtils.getThreadPollProxy("schedule_at_rate").scheduleAtRate(() -> printMsg("schedule at rate " + ATOMIC_INTEGER.addAndGet(1)), 1, 1, TimeUnit.SECONDS));
    }

    private synchronized void printMsg(String msg) {
        boolean isMainLoop = ThreadUtils.isMainThread();
        String msgText = String.format("%s%s[UI Thtread:%s]", getCurrDate(), msg, isMainLoop);
        mMessage.append(msgText).append("\n");
        mBinding.etMsg.post(() -> mBinding.etMsg.setText(mMessage));
        System.out.println("Thread test msg : " + msg);
    }

    private String getCurrDate() {
        long currTime = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss", Locale.CHINA);
        return String.format("[%s]", simpleDateFormat.format(new Date(currTime)));
    }

}
