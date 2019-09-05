package com.chaow.openutils.basic;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author : Char
 * @date : 2019/7/3
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public final class ToastUtils {

    private ToastUtils() {

    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, int layoutId, String... message) {
        showToast(context, layoutId, Toast.LENGTH_SHORT, message);
    }

    public static void showToast(Context context, int layoutId, int duration, String... message) {
        showToast(context, layoutId, duration, Gravity.BOTTOM, message);
    }

    public static void showToast(Context context, int layoutId, int duration, int gravity, String... message) {
        showToast(context, layoutId, duration, gravity, 0, 0, message);
    }

    public static void showToast(Context context, int layoutId, int duration, int gravity, int xOffset, int yOffset, String... message) {
        View toastLayout = LayoutInflater.from(context).inflate(layoutId, null);
        showToast(context, toastLayout, duration, gravity, xOffset, yOffset, message);
    }

    public static void showToast(Context context, View view, String... message) {
        showToast(context, view, Toast.LENGTH_SHORT, Gravity.BOTTOM, 0, 0, message);
    }

    public static void showToast(Context context, View view, int duration, String... message) {
        showToast(context, view, duration, Gravity.BOTTOM, message);
    }

    public static void showToast(Context context, View view, int duration, int gravity, String... message) {
        showToast(context, view, duration, gravity, 0, 0, message);
    }

    public static void showToast(Context context, View view, int duration, int gravity, int xOffset, int yOffset, String... message) {
        Toast toast = new Toast(context);
        toast.setView(view);
        if (view instanceof ViewGroup) {
            setContent((ViewGroup) view, message);
        } else if (view instanceof TextView) {
            setContent((TextView) view, message);
        }
        toast.setGravity(gravity, xOffset, yOffset);
        toast.setDuration(duration);
        toast.show();
    }

    private static void setContent(TextView textView, String... message) {
        if (message == null) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (String msg : message) {
            builder.append(msg);
        }
        textView.setText(builder.toString());
    }

    private static void setContent(ViewGroup viewGroup, String... message) {
        if (viewGroup == null || message == null) {
            return;
        }
        int size = Math.min(viewGroup.getChildCount(), message.length);
        for (int i = 0; i < size; i++) {
            View tvContent = viewGroup.getChildAt(i);
            if (tvContent == null) {
                return;
            }
            if (tvContent instanceof TextView) {
                ((TextView) tvContent).setText(message[i]);
            }
        }
    }

}
