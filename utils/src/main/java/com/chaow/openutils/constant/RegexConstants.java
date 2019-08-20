package com.chaow.openutils.constant;

/**
 * @author : Char
 */
public interface RegexConstants {
    /**
     * 简要手机号匹配
     */
    String REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$";
    /**
     * 严格手机号匹配
     * <p>移动: 134(0-8), 135, 136, 137, 138, 139, 147, 150, 151, 152, 157, 158, 159, 178, 182, 183, 184, 187, 188, 198</p>
     * <p>联通: 130, 131, 132, 145, 155, 156, 166, 171, 175, 176, 185, 186</p>
     * <p>电信: 133, 153, 173, 177, 180, 181, 189, 199, 191</p>
     * <p>虚拟手机号: 170</p>
     */
    String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(16[6])|(17[0,1,3,5-8])|(18[0-9])|(19[1,8,9]))\\d{8}$";

    /**
     * 座机号匹配
     */
    String REGEX_TEL = "^0\\d{2,3}[- ]?\\d{7,8}";
    /**
     * email匹配
     */
    String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    /**
     * url匹配
     */
    String REGEX_URL = "[a-zA-z]+://[^\\s]*";
    /**
     * 中文匹配
     */
    String REGEX_ZH = "^[\\u4e00-\\u9fa5]+$";

    /**
     * ip 地址匹配
     */
    String REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";

    /**
     * 换行匹配
     */
    String REGEX_BLANK_LINE = "\\n\\s*\\r";

    /**
     * 正数
     */
    String REGEX_POSITIVE_INTEGER = "^[1-9]\\d*$";

    /**
     * 负数
     */
    String REGEX_NEGATIVE_INTEGER = "^-[1-9]\\d*$";

    /**
     * 整数
     */
    String REGEX_INTEGER = "^-?[1-9]\\d*$";

    /**
     * 非负整数
     */
    String REGEX_NOT_NEGATIVE_INTEGER = "^[1-9]\\d*|0$";

    /**
     * 正浮点数（正小数）
     */
    String REGEX_POSITIVE_FLOAT = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$";
}
