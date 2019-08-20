package com.chaow.openutils.basic;


import androidx.annotation.NonNull;

/**
 * @author : Char
 * @date : 2019/6/25
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public class StringUtils {

    /**
     * 字符串是否为空
     *
     * @param str 字符串
     * @return 给定字符串
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * 检查字符串是否为空 若为空则返回默认值
     *
     * @param str 待检测的字符串
     * @param def 默认值
     * @return
     */
    public static String checkEmpty(String str, @NonNull String def) {
        if (isEmpty(str)) {
            return def;
        } else {
            return str;
        }
    }
}
