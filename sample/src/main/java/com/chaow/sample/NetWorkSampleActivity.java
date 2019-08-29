package com.chaow.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.chaow.openutils.ToastUtils;
import com.chaow.openutils.basic.NetWorkUtils;
import com.chaow.openutils.thread.ThreadUtils;

/**
 * @author : Char
 * @date : 2019/8/29
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public class NetWorkSampleActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, NetWorkSampleActivity.class);
        context.startActivity(starter);
    }

    private TextView tvMsg;
    private Button btRefresh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_sample);

        tvMsg = findViewById(R.id.tv_msg);
        btRefresh = findViewById(R.id.bt_refresh);
        btRefresh.setOnClickListener(v -> {
            tvMsg.setText("");
            tvMsg.setText(getNetworkInfo());
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        tvMsg.setText(getNetworkInfo());
        ThreadUtils.execute(() -> System.out.println(formatPrint("手机网络可用:", String.valueOf(NetWorkUtils.isAvailable())).toString()), "networksample");
    }

    public String getNetworkInfo() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(formatPrint("网络已连接:", String.valueOf(NetWorkUtils.isConnected())));
        buffer.append(formatPrint("移动数据可用:", String.valueOf(NetWorkUtils.isMobileDataEnabled())));
//        buffer.append(formatPrint("wifi可用:", String.valueOf(NetWorkUtils.isWifiAvailable())));//要子线程
        buffer.append(formatPrint("有移动数据:", String.valueOf(NetWorkUtils.isMobileData())));
        buffer.append(formatPrint("是4G:", String.valueOf(NetWorkUtils.is4G())));
        buffer.append(formatPrint("是wifi:", String.valueOf(NetWorkUtils.isWifiConnected())));
        buffer.append(formatPrint("是以太网:", String.valueOf(NetWorkUtils.isEthernet())));
        buffer.append(formatPrint("网络操作:", NetWorkUtils.getNetworkOperatorName()));
        buffer.append(formatPrint("ipv4:", NetWorkUtils.getIPAddress(true)));
        buffer.append(formatPrint("ipv6:", NetWorkUtils.getIPAddress(false)));
        buffer.append(formatPrint("ip地址wifi:", NetWorkUtils.getIpAddressByWifi()));
        buffer.append(formatPrint("ip地址Broadcast:", NetWorkUtils.getBroadcastIpAddress()));
        buffer.append(formatPrint("网络格式:", NetWorkUtils.getNetworkType().name()));
        return buffer.toString();
    }

    public StringBuffer formatPrint(String msgTitle, String msg) {
        return new StringBuffer().append(msgTitle).append(msg).append("\n");
    }
}
