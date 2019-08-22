package com.chaow.openutils.basic;


import com.chaow.openutils.constant.RegexConstants;

import java.nio.charset.Charset;
import java.util.regex.Pattern;

/**
 * @author : Char
 * @date : 2019/8/20
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    : 正则表达式工具
 */
public final class RegularExpressionUtils implements RegexConstants {

    /**
     * 是否是正浮点数
     *
     * @param input 输入
     * @return {@code true}: 是 <br>{@code false}: 否
     */
    public static boolean isPositiveFloat(final CharSequence input) {
        return isMatch(REGEX_POSITIVE_FLOAT, input);
    }

    /**
     * 是否是手机号
     *
     * @param input 手机号
     * @return {@code true}: 是 <br>{@code false}: 否
     */
    public static boolean isPhoneNumber(final CharSequence input) {
        return isMatch(REGEX_MOBILE_SIMPLE, input);
    }

    /**
     * 是否为大陆三大运营商手机号，规则见{@link RegexConstants#REGEX_MOBILE_EXACT}
     *
     * @param input 手机号
     * @return {@code true}: 是 <br>{@code false}: 否
     */
    public static boolean isCNPhoneNumber(final CharSequence input) {
        return isMatch(REGEX_MOBILE_EXACT, input);
    }

    /**
     * 是否是座机号
     *
     * @param input 座机号
     * @return {@code true}: 是 <br>{@code false}: 否
     */
    public static boolean isTelephoneNumber(final CharSequence input) {
        return isMatch(REGEX_TEL, input);
    }

    /**
     * 是否匹配
     *
     * @param regex 正则表达式
     * @param input 检查内容
     * @return {@code true}: 匹配 <br>{@code false}: 不匹配
     */
    public static boolean isMatch(final String regex, final CharSequence input) {
        return !StringUtils.isEmpty(input) && Pattern.matches(regex, input);
    }

    public static boolean isContain(final String regex, final CharSequence input) {
        return !StringUtils.isEmpty(input) && Pattern.matches(regex, input);
    }

}
