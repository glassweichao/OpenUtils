package com.chaow.openutils.basic;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

/**
 * @author : Char
 * @date : 2019/7/25
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public final class SpannableUtils {

    /**
     * 格式化字符串，只颜色
     *
     * @param str   需要格式化的字符串
     * @param color 颜色 -1：不改变颜色
     * @param start 开始位置
     * @param end   结束位置
     * @return
     */
    public static SpannableString formatStrColor(String str, int color, int start, int end) {
        return formatStr(str, color, -1, start, end);
    }

    /**
     * 格式化字符串，只大小
     *
     * @param str   需要格式化的字符串
     * @param size  大小 <=0：不改变大小
     * @param start 开始位置
     * @param end   结束位置
     * @return
     */
    public static SpannableString formatStrSize(String str, int size, int start, int end) {
        return formatStr(str, -1, size, start, end);
    }

    /**
     * 使用spannable格式化字符串
     *
     * @param str   需要格式化的字符串
     * @param color 颜色 -1：不改变颜色
     * @param size  大小 <=0：不改变大小
     * @param start 开始位置
     * @param end   结束位置
     * @return
     */
    public static SpannableString formatStr(String str, int color, int size, int start, int end) {
        if (StringUtils.isEmpty(str)) {
            str = "";
        }
        SpannableString ss = new SpannableString(str);
        if (start >= end) {
            return ss;
        }
        if (end > str.length()) {
            return ss;
        }
        if (size > 0) {
            ss.setSpan(new AbsoluteSizeSpan(size, true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (color != -1) {
            ss.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }


}
