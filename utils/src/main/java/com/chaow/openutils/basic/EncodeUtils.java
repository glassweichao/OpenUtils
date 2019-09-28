package com.chaow.openutils.basic;

import android.os.Build;
import android.text.Html;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author : Char
 * @date : 2019/8/26
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    : 编码相关工具
 */
public final class EncodeUtils {

    private EncodeUtils() {
    }

    /**
     * url编码（默认utf-8）
     *
     * @param input 输入
     * @return url编码字符串
     */
    public static String urlEncode(final String input) {
        return urlEncode(input, "UTF-8");
    }

    /**
     * url编码
     *
     * @param input       输入
     * @param charsetName 编码格式
     * @return url编码字符串
     */
    public static String urlEncode(final String input, final String charsetName) {
        if (input == null || input.length() == 0) {
            return "";
        }
        try {
            return URLEncoder.encode(input, charsetName);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * url解码（默认utf-8）
     *
     * @param input 输入
     * @return url解码字符串
     */
    public static String urlDecode(final String input) {
        return urlDecode(input, "UTF-8");
    }

    /**
     * url解码
     *
     * @param input       输入
     * @param charsetName 编码格式
     * @return url解码字符串
     */
    public static String urlDecode(final String input, final String charsetName) {
        if (input == null || input.length() == 0) {
            return "";
        }
        try {
            return URLDecoder.decode(input, charsetName);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Base64编码
     *
     * @param input 输入
     * @return base64编码byte
     */
    public static byte[] base64Encode(final String input) {
        return base64Encode(input.getBytes());
    }

    /**
     * Base64编码
     *
     * @param input 输入
     * @return base64编码byte
     */
    public static byte[] base64Encode(final byte[] input) {
        if (input == null || input.length == 0) {
            return new byte[0];
        }
        return Base64.encode(input, Base64.NO_WRAP);
    }

    /**
     * Base64编码字符串
     *
     * @param input 输入
     * @return Base64编码字符串
     */
    public static String base64Encode2String(final byte[] input) {
        if (input == null || input.length == 0) {
            return "";
        }
        return Base64.encodeToString(input, Base64.NO_WRAP);
    }

    /**
     * base64解码
     *
     * @param input 输入
     * @return base64解码byte
     */
    public static byte[] base64Decode(final String input) {
        if (input == null || input.length() == 0) {
            return new byte[0];
        }
        return Base64.decode(input, Base64.NO_WRAP);
    }

    /**
     * base64解码
     *
     * @param input 输入
     * @return base64解码字符串
     */
    public static byte[] base64Decode(final byte[] input) {
        if (input == null || input.length == 0) {
            return new byte[0];
        }
        return Base64.decode(input, Base64.NO_WRAP);
    }

    /**
     * html编码字符串
     *
     * @param input 输入
     * @return html编码字符串
     */
    public static String htmlEncode(final CharSequence input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char c;
        for (int i = 0, len = input.length(); i < len; i++) {
            c = input.charAt(i);
            switch (c) {
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '\'':
                    //http://www.w3.org/TR/xhtml1
                    // The named character reference &apos; (the apostrophe, U+0027) was
                    // introduced in XML 1.0 but does not appear in HTML. Authors should
                    // therefore use &#39; instead of &apos; to work as expected in HTML 4
                    // user agents.
                    sb.append("&#39;");
                    break;
                case '"':
                    sb.append("&quot;");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * html解码
     *
     * @param input 输入
     * @return html解码字符串
     */
    @SuppressWarnings("deprecation")
    public static CharSequence htmlDecode(final String input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(input, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(input);
        }
    }
}
