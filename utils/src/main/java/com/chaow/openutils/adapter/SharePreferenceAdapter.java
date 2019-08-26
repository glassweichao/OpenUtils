package com.chaow.openutils.adapter;

import java.util.Set;

/**
 * Creator Char 2019/8/26
 */
public interface SharePreferenceAdapter {

    /**
     * 初始化缓存适配器
     *
     * @param zone 域
     */
    void init(final String zone);

    /**
     * 缓存值
     *
     * @param key      键
     * @param value    值
     * @param isCommit 是否同步提交
     * @return 如果isCommit=true,返回true=缓存成功，false=缓存失败。如果isCommit=false，则总是返回true
     */
    boolean put(final String key, Object value, boolean isCommit);

    /**
     * 获取值
     *
     * @param key      键
     * @param defValue 默认值
     * @return 缓存的值
     */
    Object get(final String key, Object defValue);

    boolean clear(boolean isCommit);

    boolean remove(final String key, boolean isCommit);

    String getString(final String key, String defValue);

    int getInt(final String key, int defValue);

    float getFloat(final String key, float defValue);

    long getLong(final String key, long defValue);

    boolean getBoolean(final String key, boolean defValue);

    Set<String> getStringSet(final String key, Set<String> defValue);
}
