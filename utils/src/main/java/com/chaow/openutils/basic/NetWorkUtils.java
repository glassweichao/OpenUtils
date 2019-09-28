package com.chaow.openutils.basic;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;

import androidx.annotation.RequiresPermission;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;

import com.chaow.openutils.OpenUtils;
import com.chaow.openutils.thread.ThreadUtils;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.CHANGE_WIFI_STATE;
import static android.Manifest.permission.INTERNET;
import static android.content.Context.WIFI_SERVICE;

/**
 * @author : Char
 * @date : 2019/8/29
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    : 网络相关工具
 */
public final class NetWorkUtils {
    private NetWorkUtils() {

    }

    /** 网络类型 */
    public enum NetworkType {
        /** 以太网 */
        NETWORK_ETHERNET,
        /** wifi */
        NETWORK_WIFI,
        /** 4G网 */
        NETWORK_4G,
        /** 3G网 */
        NETWORK_3G,
        /** 2G网 */
        NETWORK_2G,
        /** 未知网络 */
        NETWORK_UNKNOWN,
        /** 没有网络 */
        NETWORK_NO
    }

    /**
     * 打开手机网络设置页
     */
    public static void openWirelessSettings() {
        OpenUtils.getApp().startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    /**
     * 是否有网络连接
     *
     * @return {@code true}: 已连接 <br>{@code false}: 未连接
     */
    public static boolean isConnected() {
        NetworkInfo info = getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    /**
     * 网络是否可用
     *
     * @return {@code true}: 可用 <br>{@code false}: 不可用
     */
    @WorkerThread
    @RequiresPermission(INTERNET)
    public static boolean isAvailable() {
        return isAvailableByDns();
    }

    @WorkerThread
    @RequiresPermission(INTERNET)
    public static boolean isAvailableByDns() {
        return isAvailableByDns("");
    }

    @WorkerThread
    @RequiresPermission(INTERNET)
    public static boolean isAvailableByDns(final String domain) {
        if (ThreadUtils.isMainThread()) {
            return false;
        }
        final String realDomain = StringUtils.isEmpty(domain) ? "www.baidu.com" : domain;
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getByName(realDomain);
            return inetAddress != null;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 手机移动数据是否可用
     *
     * @return {@code true}: 可用 <br>{@code false}: 不可用
     */
    public static boolean isMobileDataEnabled() {
        try {
            TelephonyManager tm = (TelephonyManager) OpenUtils.getApp().getSystemService(Context.TELEPHONY_SERVICE);
            if (tm == null) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return tm.isDataEnabled();
            }
            @SuppressLint("PrivateApi")
            Method getMobileDataEnabledMethod = tm.getClass().getDeclaredMethod("getDataEnabled");
            return (boolean) getMobileDataEnabledMethod.invoke(tm);
        } catch (Exception e) {
            LogUtils.e("NetworkUtils", "isMobileDataEnabled: " + e.toString());
        }
        return false;
    }

    /**
     * 是否有移动数据网络
     *
     * @return {@code true}: 有 <br>{@code false}: 没有
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    public static boolean isMobileData() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ConnectivityManager connectivity = (ConnectivityManager) OpenUtils.getApp().getSystemService(Context.CONNECTIVITY_SERVICE);
            Network[] networks = connectivity.getAllNetworks();
            NetworkCapabilities capabilities = null;
            for (Network network : networks) {
                capabilities = connectivity.getNetworkCapabilities(network);
            }
            if (capabilities == null) {
                return false;
            }
            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
        } else {
            NetworkInfo info = getActiveNetworkInfo();
            return null != info
                    && info.isAvailable()
                    && info.getType() == ConnectivityManager.TYPE_MOBILE;
        }
    }

    /**
     * 是否有4G网络
     *
     * @return {@code true}: 有 <br>{@code false}: 没有
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    public static boolean is4G() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ConnectivityManager connectivity = (ConnectivityManager) OpenUtils.getApp().getSystemService(Context.CONNECTIVITY_SERVICE);
            Network[] networks = connectivity.getAllNetworks();
            NetworkCapabilities capabilities = null;
            for (Network network : networks) {
                capabilities = connectivity.getNetworkCapabilities(network);
            }
            if (capabilities == null) {
                return false;
            }
            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
        } else {
            NetworkInfo info = getActiveNetworkInfo();
            return info != null
                    && info.isAvailable()
                    && info.getSubtype() == TelephonyManager.NETWORK_TYPE_LTE;
        }
    }

    /**
     * 是否有wifi连接
     *
     * @return {@code true}: 有 <br>{@code false}: 没有
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    public static boolean isWifiConnected() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ConnectivityManager connectivity = (ConnectivityManager) OpenUtils.getApp().getSystemService(Context.CONNECTIVITY_SERVICE);
            Network[] networks = connectivity.getAllNetworks();
            NetworkCapabilities capabilities = null;
            for (Network network : networks) {
                capabilities = connectivity.getNetworkCapabilities(network);
            }
            if (capabilities == null) {
                return false;
            }
            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
        } else {
            ConnectivityManager cm = (ConnectivityManager) OpenUtils.getApp().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm == null) {
                return false;
            }
            NetworkInfo ni = cm.getActiveNetworkInfo();
            return ni != null && ni.getType() == ConnectivityManager.TYPE_WIFI;
        }
    }

    /**
     * 是否是以太网
     *
     * @return {@code true}: 是 <br>{@code false}: 不是
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    public static boolean isEthernet() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ConnectivityManager connectivity = (ConnectivityManager) OpenUtils.getApp().getSystemService(Context.CONNECTIVITY_SERVICE);
            Network[] networks = connectivity.getAllNetworks();
            NetworkCapabilities capabilities = null;
            for (Network network : networks) {
                capabilities = connectivity.getNetworkCapabilities(network);
            }
            if (capabilities == null) {
                return false;
            }
            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET);
        } else {
            final ConnectivityManager cm = (ConnectivityManager) OpenUtils.getApp().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm == null) {
                return false;
            }
            final NetworkInfo info = cm.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
            if (info == null) {
                return false;
            }
            NetworkInfo.State state = info.getState();
            if (null == state) {
                return false;
            }
            return state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING;
        }
    }

    /**
     * wifi是否可用
     *
     * @return {@code true}: 可用 <br>{@code false}: 不可用
     */
    @WorkerThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    public static boolean isWifiEnabled() {
        @SuppressLint("WifiManagerLeak")
        WifiManager manager = (WifiManager) OpenUtils.getApp().getSystemService(WIFI_SERVICE);
        if (manager == null) {
            return false;
        }
        return manager.isWifiEnabled();
    }

    /**
     * 开启wifi
     *
     * @param enabled true - 开启，false - 关闭
     */
    @RequiresPermission(CHANGE_WIFI_STATE)
    public static void setWifiEnabled(final boolean enabled) {
        @SuppressLint("WifiManagerLeak")
        WifiManager manager = (WifiManager) OpenUtils.getApp().getSystemService(WIFI_SERVICE);
        if (manager == null) {
            return;
        }
        if (enabled == manager.isWifiEnabled()) {
            return;
        }
        manager.setWifiEnabled(enabled);
    }

    /**
     * wifi是否可用
     *
     * @return {@code true}: 可用 <br>{@code false}: 不可用
     */
    @WorkerThread
    @RequiresPermission(allOf = {ACCESS_WIFI_STATE, INTERNET})
    public static boolean isWifiAvailable() {
        return isWifiEnabled() && isAvailable();
    }

    /**
     * 获取运营商
     *
     * @return 运营商名
     */
    public static String getNetworkOperatorName() {
        TelephonyManager tm = (TelephonyManager) OpenUtils.getApp().getSystemService(Context.TELEPHONY_SERVICE);
        if (tm == null) {
            return "";
        }
        return tm.getNetworkOperatorName();
    }

    /**
     * 网络详情信息
     *
     * @return 网络信息
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    private static NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager cm =
                (ConnectivityManager) OpenUtils.getApp().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return null;
        }
        return cm.getActiveNetworkInfo();
    }

    /**
     * 获取IP地址
     *
     * @param ipv4 true - ipv4格式
     * @return IP地址
     */
    @RequiresPermission(INTERNET)
    public static String getIPAddress(final boolean ipv4) {
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            LinkedList<InetAddress> adds = new LinkedList<>();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                // To prevent phone of xiaomi return "10.0.2.15"
                if (!ni.isUp() || ni.isLoopback()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    adds.addFirst(addresses.nextElement());
                }
            }
            for (InetAddress add : adds) {
                if (!add.isLoopbackAddress()) {
                    String hostAddress = add.getHostAddress();
                    boolean isIPv4 = hostAddress.indexOf(':') < 0;
                    if (ipv4) {
                        if (isIPv4) {
                            return hostAddress;
                        }
                    } else {
                        if (!isIPv4) {
                            int index = hostAddress.indexOf('%');
                            return index < 0
                                    ? hostAddress.toUpperCase()
                                    : hostAddress.substring(0, index).toUpperCase();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获广播ip地址
     *
     * @return 广播ip地址
     */
    public static String getBroadcastIpAddress() {
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                if (!ni.isUp() || ni.isLoopback()) {
                    continue;
                }
                List<InterfaceAddress> ias = ni.getInterfaceAddresses();
                for (int i = 0, size = ias.size(); i < size; i++) {
                    InterfaceAddress ia = ias.get(i);
                    InetAddress broadcast = ia.getBroadcast();
                    if (broadcast != null) {
                        return broadcast.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取域名地址
     *
     * @param domain 域名
     * @return 域名地址
     */
    @RequiresPermission(INTERNET)
    public static String getDomainAddress(final String domain) {
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getByName(domain);
            return inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 通过wifi获取ip地址
     *
     * @return ip地址
     */
    @RequiresPermission(ACCESS_WIFI_STATE)
    public static String getIpAddressByWifi() {
        @SuppressLint("WifiManagerLeak")
        WifiManager wm = (WifiManager) OpenUtils.getApp().getSystemService(Context.WIFI_SERVICE);
        if (wm == null) {
            return "";
        }
        return Formatter.formatIpAddress(wm.getDhcpInfo().ipAddress);
    }

    /**
     * 通过网关获取ip地址
     *
     * @return ip地址
     */
    @RequiresPermission(ACCESS_WIFI_STATE)
    public static String getGatewayByWifi() {
        @SuppressLint("WifiManagerLeak")
        WifiManager wm = (WifiManager) OpenUtils.getApp().getSystemService(Context.WIFI_SERVICE);
        if (wm == null) {
            return "";
        }
        return Formatter.formatIpAddress(wm.getDhcpInfo().gateway);
    }

    /**
     * @return
     */
    @RequiresPermission(ACCESS_WIFI_STATE)
    public static String getNetMaskByWifi() {
        @SuppressLint("WifiManagerLeak")
        WifiManager wm = (WifiManager) OpenUtils.getApp().getSystemService(Context.WIFI_SERVICE);
        if (wm == null) {
            return "";
        }
        return Formatter.formatIpAddress(wm.getDhcpInfo().netmask);
    }

    /**
     * @return
     */
    @RequiresPermission(ACCESS_WIFI_STATE)
    public static String getServerAddressByWifi() {
        @SuppressLint("WifiManagerLeak")
        WifiManager wm = (WifiManager) OpenUtils.getApp().getSystemService(Context.WIFI_SERVICE);
        if (wm == null) {
            return "";
        }
        return Formatter.formatIpAddress(wm.getDhcpInfo().serverAddress);
    }

    /**
     * 获取网络类型
     *
     * @return 网络类型 {@link NetworkType}
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    public static NetworkType getNetworkType() {
        if (isEthernet()) {
            return NetworkType.NETWORK_ETHERNET;
        }
        NetworkInfo info = getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                return NetworkType.NETWORK_WIFI;
            } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                switch (info.getSubtype()) {
                    case TelephonyManager.NETWORK_TYPE_GSM:
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        return NetworkType.NETWORK_2G;

                    case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        return NetworkType.NETWORK_3G;

                    case TelephonyManager.NETWORK_TYPE_IWLAN:
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        return NetworkType.NETWORK_4G;

                    default:
                        String subtypeName = info.getSubtypeName();
                        if (subtypeName.equalsIgnoreCase("TD-SCDMA")
                                || subtypeName.equalsIgnoreCase("WCDMA")
                                || subtypeName.equalsIgnoreCase("CDMA2000")) {
                            return NetworkType.NETWORK_3G;
                        } else {
                            return NetworkType.NETWORK_UNKNOWN;
                        }
                }
            } else {
                return NetworkType.NETWORK_UNKNOWN;
            }
        }
        return NetworkType.NETWORK_NO;
    }


    public static void registerNetworkStatusChangedListener(OnNetworkStatusChangedListener listener) {
        NetworkChangedReceiver.getInstance().registerListener(listener);
    }

    public static void unregisterNetworkStatusChangedListener(OnNetworkStatusChangedListener listener) {
        NetworkChangedReceiver.getInstance().unregisterListener(listener);
    }

    /**
     * 网络改变接收器
     */
    public static final class NetworkChangedReceiver extends BroadcastReceiver {

        private static NetworkChangedReceiver getInstance() {
            return LazyHolder.INSTANCE;
        }

        private NetworkType mType;
        private Set<OnNetworkStatusChangedListener> mListeners = new HashSet<>();

        /**
         * 注册监听
         *
         * @param listener 网络状态监听
         */
        void registerListener(final OnNetworkStatusChangedListener listener) {
            if (listener == null) {
                return;
            }
            ThreadUtils.runOnUIThread(new Runnable() {
                @SuppressLint("MissingPermission")
                @Override
                public void run() {
                    int preSize = mListeners.size();
                    mListeners.add(listener);
                    if (preSize == 0 && mListeners.size() == 1) {
                        mType = getNetworkType();
                        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
                        OpenUtils.getApp().registerReceiver(NetworkChangedReceiver.getInstance(), intentFilter);
                    }
                }
            });
        }

        /**
         * 取消注册
         *
         * @param listener 网络状态监听
         */
        void unregisterListener(final OnNetworkStatusChangedListener listener) {
            if (listener == null) {
                return;
            }
            ThreadUtils.runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    int preSize = mListeners.size();
                    mListeners.remove(listener);
                    if (preSize == 1 && mListeners.size() == 0) {
                        OpenUtils.getApp().unregisterReceiver(NetworkChangedReceiver.getInstance());
                    }
                }
            });
        }

        @SuppressLint("MissingPermission")
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                // debouncing
                ThreadUtils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        NetworkType networkType = NetWorkUtils.getNetworkType();
                        if (mType == networkType) {
                            return;
                        }
                        mType = networkType;
                        if (networkType == NetworkType.NETWORK_NO) {
                            for (OnNetworkStatusChangedListener listener : mListeners) {
                                listener.onDisconnected();
                            }
                        } else {
                            for (OnNetworkStatusChangedListener listener : mListeners) {
                                listener.onConnected(networkType);
                            }
                        }
                    }
                });
            }
        }

        private static class LazyHolder {
            private static final NetworkChangedReceiver INSTANCE = new NetworkChangedReceiver();
        }
    }

    /**
     * 网络状态回调
     */
    public interface OnNetworkStatusChangedListener {
        void onDisconnected();

        void onConnected(NetworkType networkType);
    }
}
