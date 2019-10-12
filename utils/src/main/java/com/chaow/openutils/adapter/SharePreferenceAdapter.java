package com.chaow.openutils.adapter;

import java.util.Map;
import java.util.Set;

/**
 * @author Char
 * @date 2019/8/26
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    : SharedPreferences适配器接口，继承此接口以实现自己的SharedPreferences。
 */
public interface SharePreferenceAdapter {

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
     * 获取String类型值
     *
     * @param key      键
     * @param defValue 默认值
     * @return 找到的值，找不到返回默认值
     */
    String getString(final String key, String defValue);

    /**
     * 获取int类型值
     *
     * @param key      键
     * @param defValue 默认值
     * @return 找到的值，找不到返回默认值
     */
    int getInt(final String key, int defValue);

    /**
     * 获取float类型值
     *
     * @param key      键
     * @param defValue 默认值
     * @return 找到的值，找不到返回默认值
     */
    float getFloat(final String key, float defValue);

    /**
     * 获取long类型值
     *
     * @param key      键
     * @param defValue 默认值
     * @return 找到的值，找不到返回默认值
     */
    long getLong(final String key, long defValue);

    /**
     * 获取boolean类型值
     *
     * @param key      键
     * @param defValue 默认值
     * @return 找到的值，找不到返回默认值
     */
    boolean getBoolean(final String key, boolean defValue);

    /**
     * 获取set类型值
     *
     * @param key      键
     * @param defValue 默认值
     * @return 找到的值，找不到返回默认值
     */
    Set<String> getStringSet(final String key, Set<String> defValue);

    /**
     * 获取所有的缓存值
     *
     * @return 缓存值
     */
    Map<String, ?> getAll();

    /**
     * 是否包含该key
     *
     * @param key 键
     * @return {@code true}: 包含<br>{@code false}: 不包含
     */
    boolean contains(final String key);

    /**
     * 清空缓存
     *
     * @param isCommit 是否同步提交
     * @return 如果isCommit=true,返回true=缓存成功，false=缓存失败。如果isCommit=false，则总是返回true
     */
    boolean clear(boolean isCommit);

    /**
     * 移除相应键值
     *
     * @param key      键
     * @param isCommit 是否同步提交
     * @return 如果isCommit=true,返回true=缓存成功，false=缓存失败。如果isCommit=false，则总是返回true
     */
    boolean remove(final String key, boolean isCommit);

    /**
     * 修改存储文件
     *
     * @param name 文件名
     */
    void changeName(final String name);
}
