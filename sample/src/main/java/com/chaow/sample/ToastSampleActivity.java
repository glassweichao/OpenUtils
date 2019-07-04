package com.chaow.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
//            ToastUtils.showToast(this, R.layout.toast_custom_layout, Toast.LENGTH_SHORT, "toast内容1");
            TextView textView = new TextView(this);
            textView.setTextColor(0xff000000);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15.0f);
            textView.setBackgroundColor(0x60000000);
            ToastUtils.showToast(this, textView, Toast.LENGTH_SHORT, Gravity.CENTER, "toast内容1", "toast内容1");
        });
    }
}
