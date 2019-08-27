package com.chaow.openutils.basic;

import androidx.annotation.NonNull;

import com.chaow.openutils.adapter.DefaultSPAdapter;
import com.chaow.openutils.adapter.SharePreferenceAdapter;

import java.util.Map;
import java.util.Set;

/**
 * @author : Char
 * @date : 2019/8/26
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    : SharePreference工具类,需要在application里面初始化，如果不初始化化，将使用默认的SharePreference
 */
public final class SPUtils {

    /** SharePreference适配器，以适配除了Android SharePreference以外的自定义缓存工具 */
    private static SharePreferenceAdapter sAdapter;

    private SPUtils() {
    }

    /**
     * 使用默认的adapter初始化工具类
     */
    public static void initialize() {
        sAdapter = new DefaultSPAdapter();
    }

    /**
     * 工具类初始化adapter，需要在使用SPUtils前调用，如application里的onCreate
     *
     * @param adapter
     */
    public static void initialize(@NonNull SharePreferenceAdapter adapter) {
        sAdapter = adapter;
    }

    /**
     * 获得默认的SP工具
     *
     * @return SP工具对象
     */
    public static SPUtils defaultSP() {
        if (sAdapter == null) {
            throw new IllegalStateException("You should Call SPUtils.initialize() first.");
        } else {
            return new SPUtils();
        }
    }

    /**
     * 按命名获得SP工具
     *
     * @param name 名字
     * @return SP工具
     */
    public static SPUtils spWithName(final String name) {
        if (sAdapter == null) {
            throw new IllegalStateException("You should Call SPUtils.initialize() first.");
        } else {
            sAdapter.changeName(name);
            return new SPUtils();
        }
    }

    /**
     * 设置String类型值
     *
     * @param key   键
     * @param value 值
     */
    public void put(final String key, String value) {
        sAdapter.put(key, value, false);
    }

    /**
     * 设置int类型值
     *
     * @param key   键
     * @param value 值
     */
    public void put(final String key, int value) {
        sAdapter.put(key, value, false);
    }

    /**
     * 设置float类型值
     *
     * @param key   键
     * @param value 值
     */
    public void put(final String key, float value) {
        sAdapter.put(key, value, false);
    }

    /**
     * 设置long类型值
     *
     * @param key   键
     * @param value 值
     */
    public void put(final String key, long value) {
        sAdapter.put(key, value, false);
    }

    /**
     * 设置boolean类型值
     *
     * @param key   键
     * @param value 值
     */
    public void put(final String key, boolean value) {
        sAdapter.put(key, value, false);
    }

    /**
     * 设置StringSet类型值
     *
     * @param key   键
     * @param value 值
     */
    public void put(final String key, Set<String> value) {
        sAdapter.put(key, value, false);
    }

    /**
     * 设置String类型值,并返回缓存结果
     *
     * @param key   键
     * @param value 值
     * @return {@code true}: 成功<br>{@code false}: 失败
     */
    public boolean putString(final String key, String value) {
        return sAdapter.put(key, value, true);
    }

    /**
     * 设置int类型值,并返回缓存结果
     *
     * @param key   键
     * @param value 值
     * @return {@code true}: 成功<br>{@code false}: 失败
     */
    public boolean putInt(final String key, int value) {
        return sAdapter.put(key, value, true);
    }

    /**
     * 设置float类型值,并返回缓存结果
     *
     * @param key   键
     * @param value 值
     * @return {@code true}: 成功<br>{@code false}: 失败
     */
    public boolean putFloat(final String key, float value) {
        return sAdapter.put(key, value, true);
    }

    /**
     * 设置long类型值,并返回缓存结果
     *
     * @param key   键
     * @param value 值
     * @return {@code true}: 成功<br>{@code false}: 失败
     */
    public boolean putLong(final String key, long value) {
        return sAdapter.put(key, value, true);
    }

    /**
     * 设置boolean类型值,并返回缓存结果
     *
     * @param key   键
     * @param value 值
     * @return {@code true}: 成功<br>{@code false}: 失败
     */
    public boolean putBoolean(final String key, boolean value) {
        return sAdapter.put(key, value, true);
    }

    /**
     * 设置StringSet类型值,并返回缓存结果
     *
     * @param key   键
     * @param value 值
     * @return {@code true}: 成功<br>{@code false}: 失败
     */
    public boolean putStringSet(final String key, Set<String> value) {
        return sAdapter.put(key, value, true);
    }


    /**
     * 获取String类型值
     *
     * @param key 键
     * @return String类型值
     */
    public String getString(final String key) {
        return getString(key, null);
    }

    /**
     * 获取String类型值
     *
     * @param key      键
     * @param defValue 默认值
     * @return String类型值
     */
    public String getString(final String key, String defValue) {
        return sAdapter.getString(key, defValue);
    }

    /**
     * 获取boolean类型值
     *
     * @param key 键
     * @return boolean类型值
     */
    public boolean getBoolean(final String key) {
        return getBoolean(key, false);
    }

    /**
     * 获取Boolean类型值
     *
     * @param key      键
     * @param defValue 默认值
     * @return Boolean类型值
     */
    public boolean getBoolean(final String key, boolean defValue) {
        return sAdapter.getBoolean(key, defValue);
    }

    /**
     * 获取int类型值
     *
     * @param key 键
     * @return int类型值
     */
    public int getInt(final String key) {
        return getInt(key, 0);
    }

    /**
     * 获取int类型值
     *
     * @param key      键
     * @param defValue 默认值
     * @return int类型值
     */
    public int getInt(final String key, int defValue) {
        return sAdapter.getInt(key, defValue);
    }

    /**
     * 获取float类型值
     *
     * @param key 键
     * @return float类型值
     */
    public float getFloat(final String key) {
        return getFloat(key, 0.0f);
    }

    /**
     * 获取float类型值
     *
     * @param key      键
     * @param defValue 默认值
     * @return float类型值
     */
    public float getFloat(final String key, float defValue) {
        return sAdapter.getFloat(key, defValue);
    }

    /**
     * 获取long类型值
     *
     * @param key 键
     * @return long类型值
     */
    public long getLong(final String key) {
        return getLong(key, 0L);
    }

    /**
     * 获取long类型值
     *
     * @param key      键
     * @param defValue 默认值
     * @return long类型值
     */
    public long getLong(final String key, long defValue) {
        return sAdapter.getLong(key, defValue);
    }

    /**
     * 获取StringSet类型值
     *
     * @param key 键
     * @return StringSet类型值
     */
    public Set<String> getStringSet(final String key) {
        return getStringSet(key, null);
    }

    /**
     * 获取StringSet类型值
     *
     * @param key      键
     * @param defValue 默认值
     * @return StringSet类型值
     */
    public Set<String> getStringSet(final String key, Set<String> defValue) {
        return sAdapter.getStringSet(key, defValue);
    }

    /**
     * 获取缓存的所有值
     *
     * @return 值集合
     */
    public Map<String, ?> getAll() {
        return sAdapter.getAll();
    }


    /**
     * 检查缓存里是否有给定key
     *
     * @param key 键
     * @return {@code true}: 有<br>{@code false}: 没有
     */
    public boolean contains(final String key) {
        return sAdapter.contains(key);
    }

    /**
     * 清空当前缓存
     */
    public void clear() {
        sAdapter.clear(false);
    }

    /**
     * 清空当前缓存，并等待操作结果
     *
     * @return {@code true}: 清理完成<br>{@code false}: 清理失败
     */
    public boolean clearAndCommit() {
        return sAdapter.clear(true);
    }

    /**
     * 删除指定键值
     *
     * @param key 键
     */
    public void remove(final String key) {
        sAdapter.remove(key, false);
    }

    /**
     * 删除指定键值，并等待操作结果
     *
     * @param key 键
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public boolean removeAndCommit(final String key) {
        return sAdapter.remove(key, true);
    }

}
