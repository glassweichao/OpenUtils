package com.chaow.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.chaow.openutils.ToastUtils;
import com.chaow.sample.databinding.ActivityToastSampleBinding;

/**
 * @author : Char
 * @date : 2019/7/3
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public class ToastSampleActivity extends AppCompatActivity {

    ActivityToastSampleBinding mBinding;

    public static void start(Context context) {
        Intent starter = new Intent(context, ToastSampleActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_toast_sample);
        initEvent();
    }

    private void initEvent() {
        mBinding.btCustomToast.setOnClickListener(v -> {
            View toastLayout = LayoutInflater.from(this).inflate(R.layout.toast_custom_layout, null);
            ToastUtils.showToast(this, "自定义", toastLayout, Gravity.CENTER);
        });
    }
}
