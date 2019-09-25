package com.chaow.openutils.basic;


import com.chaow.openutils.constant.RegexConstants;

import java.nio.charset.Charset;
import java.util.regex.Matcher;
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
     * 是否是email
     *
     * @param input email
     * @return {@code true}: 是 <br>{@code false}: 否
     */
    public static boolean isEmail(final CharSequence input) {
        return isMatch(REGEX_EMAIL, input);
    }

    /**
     * 是否是链接
     *
     * @param input 链接
     * @return {@code true}: 是 <br>{@code false}: 否
     */
    public static boolean isUrl(final CharSequence input) {
        return isMatch(REGEX_URL, input);
    }

    /**
     * 是否是中文
     *
     * @param input 输入内容
     * @return {@code true}: 是 <br>{@code false}: 否
     */
    public static boolean isZh(final CharSequence input) {
        return isMatch(REGEX_ZH, input);
    }

    /**
     * 是否是IPv4的地址
     *
     * @param input IP地址
     * @return {@code true}: 是 <br>{@code false}: 否
     */
    public static boolean isIPv4(final CharSequence input) {
        return isMatch(REGEX_IP, input);
    }

    /**
     * 是否包含换行
     *
     * @param input 输入呢绒
     * @return {@code true}: 是 <br>{@code false}: 否
     */
    public static boolean hasBlankLine(final CharSequence input) {
        return isContain(REGEX_BLANK_LINE, input);
    }

    /**
     * 是否是正整数
     *
     * @param input 输入
     * @return {@code true}: 是 <br>{@code false}: 否
     */
    public static boolean isPositiveIntegerNumber(final CharSequence input) {
        return isMatch(REGEX_POSITIVE_INTEGER, input);
    }

    /**
     * 是否是负数
     *
     * @param input 输入
     * @return {@code true}: 是 <br>{@code false}: 否
     */
    public static boolean isNegativeIntegerNumber(final CharSequence input) {
        return isMatch(REGEX_NEGATIVE_INTEGER, input);
    }

    /**
     * 是否是整数
     *
     * @param input 输入
     * @return {@code true}: 是 <br>{@code false}: 否
     */
    public static boolean isIntegerNumber(final CharSequence input) {
        return isMatch(REGEX_INTEGER, input);
    }

    /**
     * 是否是纯数字
     *
     * @param input 输入
     * @return {@code true}: 是 <br>{@code false}: 否
     */
    public static boolean isNumber(final CharSequence input) {
        return isMatch(REGEX_NUMBER, input);
    }

    /**
     * 是否是“yyyy-MM-dd”格式的日期
     *
     * @param input 日期
     * @return {@code true}: 是 <br>{@code false}: 否
     */
    public static boolean isDate(final CharSequence input) {
        return isMatch(REGEX_DATE, input);
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
        return !StringUtils.isEmpty(input) && Pattern.compile(regex).matcher(input).find();
    }

}

