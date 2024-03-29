package com.chaow.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.chaow.openutils.OpenUtils;
import com.chaow.openutils.basic.LogUtils;
import com.chaow.sample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        OpenUtils.init(this);
        mBinding.btThreadUtils.setOnClickListener(v -> ThreadSampleActivity.start(this));
        mBinding.btToastUtils.setOnClickListener(v -> ToastSampleActivity.start(this));
        mBinding.btPhoneUtils.setOnClickListener(v -> PhoneDeviceSampleActivity.start(this));
        mBinding.btNetworkUtils.setOnClickListener(v -> NetWorkSampleActivity.start(this));
        LogUtils.v(this.getClass().getSimpleName(), "");
    }
}
