package com.chaow.openutils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.chaow.openutils.builder.Builder;

/**
 * @author : Char
 * @date : 2019/7/3
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public final class ToastUtils {

    private ToastUtils() {

    }

//    public static void showToast() {
//        Toast toast = new Toast(context);
//        View toastLayout = LayoutInflater.from(context).inflate(R.layout.layout_custom_toast_with_error_code, null);
//        TextView textViewToastText = toastLayout.findViewById(R.id.tv_message);
//        textViewToastText.setText(StringUtil.checkEmpty(errMsg, "未知结果"));
//        String strErrCode = String.format("[%s|%s]", errCode, BuildConfig.VERSION_CODE);
//        TextView textViewErrCode = toastLayout.findViewById(R.id.tvErrorCode);
//        textViewErrCode.setText(StringUtil.checkEmpty(strErrCode, "未知结果"));
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.setView(toastLayout);
//        toast.show();
//    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, String message, View view, int gravity) {
        Toast toast = new Toast(context);
        toast.setView(view);
        if (view instanceof ViewGroup) {
            setContent((ViewGroup) view, message);
        }
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

    private static void setContent(ViewGroup viewGroup, String message) {
        if (viewGroup == null) {
            return;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View tvContent = viewGroup.getChildAt(i);
            if (tvContent instanceof TextView) {
                ((TextView) tvContent).setText(message);
                return;
            }
        }
    }

    public static class Builder implements com.chaow.openutils.builder.Builder<Toast> {

        private View view;
        private int viewResId;
        private int gravity;
        private int xOffset;
        private int yOffset;
        private int duration;

        public Builder setCustomView(View view) {
            this.view = view;
            return this;
        }

        public Builder setCustomView(int viewResId) {
            this.viewResId = viewResId;
            return this;
        }

        public Builder setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder setxOffset(int xOffset) {
            this.xOffset = xOffset;
            return this;
        }

        public Builder setyOffset(int yOffset) {
            this.yOffset = yOffset;
            return this;
        }

        public Builder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        @Override
        public Toast build() {
            return null;
        }
    }
}
