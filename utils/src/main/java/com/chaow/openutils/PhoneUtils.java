package com.chaow.openutils;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.annotation.IntRange;

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

}
