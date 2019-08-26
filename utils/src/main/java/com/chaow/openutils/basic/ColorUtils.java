package com.chaow.openutils.basic;

import android.graphics.Color;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.chaow.openutils.OpenUtils;

/**
 * @author : Char
 * @date : 2019/8/26
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    : 颜色相关工具
 */
public final class ColorUtils {

    private ColorUtils() {

    }

    /**
     * 通过资源ID获取颜色值
     *
     * @param id 颜色资源id
     * @return 0xAARRGGBB颜色值
     */
    public static int getColor(@ColorRes int id) {
        return ContextCompat.getColor(OpenUtils.getApp(), id);
    }

    /**
     * 设置颜色透明度
     *
     * @param color AARRGGBB颜色值
     * @param alpha 透明度，范围从0-255
     * @return 指定透明度的颜色值
     */
    public static int setAlphaComponent(@ColorInt final int color,
                                        @IntRange(from = 0x0, to = 0xFF) int alpha) {
        return (color & 0x00ffffff) | (alpha << 24);
    }

    /**
     * 设置颜色透明度
     *
     * @param color AARRGGBB颜色值
     * @param alpha 透明度，分为0-1
     * @return 指定透明度的颜色值
     */
    public static int setAlphaComponent(@ColorInt int color,
                                        @FloatRange(from = 0, to = 1) float alpha) {
        return (color & 0x00ffffff) | ((int) (alpha * 255.0f + 0.5f) << 24);
    }


    /**
     * Color-string to color-int.
     * <p>Supported formats are:</p>
     *
     * <ul>
     * <li><code>#RRGGBB</code></li>
     * <li><code>#AARRGGBB</code></li>
     * </ul>
     *
     * <p>The following names are also accepted: <code>red</code>, <code>blue</code>,
     * <code>green</code>, <code>black</code>, <code>white</code>, <code>gray</code>,
     * <code>cyan</code>, <code>magenta</code>, <code>yellow</code>, <code>lightgray</code>,
     * <code>darkgray</code>, <code>grey</code>, <code>lightgrey</code>, <code>darkgrey</code>,
     * <code>aqua</code>, <code>fuchsia</code>, <code>lime</code>, <code>maroon</code>,
     * <code>navy</code>, <code>olive</code>, <code>purple</code>, <code>silver</code>,
     * and <code>teal</code>.</p>
     *
     * @param colorString The color-string.
     * @return color-int
     * @throws IllegalArgumentException The string cannot be parsed.
     */
    public static int string2Int(@NonNull String colorString) {
        return Color.parseColor(colorString);
    }

    /**
     * 颜色值转 RGB 字符串
     *
     * @param colorInt 整形颜色值
     * @return RGB 字符串串
     */
    public static String int2RgbString(@ColorInt int colorInt) {
        colorInt = colorInt & 0x00ffffff;
        String color = Integer.toHexString(colorInt);
        while (color.length() < 6) {
            color = "0" + color;
        }
        return "#" + color;
    }

    /**
     * 颜色值转 ARGB 字符串
     *
     * @param colorInt 整形颜色值
     * @return ARGB 字符串串
     */
    public static String int2ArgbString(@ColorInt final int colorInt) {
        String color = Integer.toHexString(colorInt);
        while (color.length() < 6) {
            color = "0" + color;
        }
        while (color.length() < 8) {
            color = "f" + color;
        }
        return "#" + color;
    }

}
