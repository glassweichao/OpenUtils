package com.chaow.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.chaow.openutils.PhoneUtils;
import com.chaow.sample.databinding.ActivityPhoneDeviceSampleBinding;

/**
 * @author : Char
 * @date : 2019/7/4
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public class PhoneDeviceSampleActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, PhoneDeviceSampleActivity.class);
        context.startActivity(starter);
    }

    ActivityPhoneDeviceSampleBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_phone_device_sample);
        long[] milliseconds = {600, 300, 600, 300};
        mBinding.btStartVibrator.setOnClickListener(v -> PhoneUtils.startRepeatVibrator(this, milliseconds));
        mBinding.btCancelVibrator.setOnClickListener(v -> PhoneUtils.cancelVibrator(this));
    }
}
