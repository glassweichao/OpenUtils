package com.chaow.openutils.basic;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * @author : Char
 * @date : 2019/10/12
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public class RegularExpressionUtilsTest {

    @Test
    public void isEmail() {
        String email1 = null;
        String email2 = "12345";
        String email3 = "123@gmail.com";

        assertFalse(RegularExpressionUtils.isEmail(email1));
        assertFalse(RegularExpressionUtils.isEmail(email2));
        assertTrue(RegularExpressionUtils.isEmail(email3));

    }

    @Test
    public void isPhoneNumber() {
        String num1 = null;
        String num2 = "100000";
        String num3 = "10000000000";
        String num4 = "13910101235";
        String num5 = "+8613910101235";
        String num6 = "asdaewf";

        assertFalse(RegularExpressionUtils.isPhoneNumber(num1));
        assertFalse(RegularExpressionUtils.isPhoneNumber(num2));
        assertTrue(RegularExpressionUtils.isPhoneNumber(num3));
        assertTrue(RegularExpressionUtils.isPhoneNumber(num4));
        assertFalse(RegularExpressionUtils.isPhoneNumber(num5));
        assertFalse(RegularExpressionUtils.isPhoneNumber(num6));

        assertFalse(RegularExpressionUtils.isCNPhoneNumber(num1));
        assertFalse(RegularExpressionUtils.isCNPhoneNumber(num2));
        assertFalse(RegularExpressionUtils.isCNPhoneNumber(num3));
        assertTrue(RegularExpressionUtils.isCNPhoneNumber(num4));
        assertFalse(RegularExpressionUtils.isCNPhoneNumber(num5));
        assertFalse(RegularExpressionUtils.isCNPhoneNumber(num6));
    }

    @Test
    public void isUrl() {
        String url1 = null;
        String url2 = "http://www.google.com";
        String url3 = "file://openutils.apk";
        String url4 = "asAS://aaaa.bbb";
        String url5 = "*&://aaa.a";
        String url6 = "errorurl";
        String url7 = "://www.google.com";
        String url8 = "www.google.com";
        String url9 = "google.com";

        assertFalse(RegularExpressionUtils.isUrl(url1));
        assertTrue(RegularExpressionUtils.isUrl(url2));
        assertTrue(RegularExpressionUtils.isUrl(url3));
        assertTrue(RegularExpressionUtils.isUrl(url4));
        assertFalse(RegularExpressionUtils.isUrl(url5));
        assertFalse(RegularExpressionUtils.isUrl(url6));
        assertFalse(RegularExpressionUtils.isUrl(url7));
        assertFalse(RegularExpressionUtils.isUrl(url8));
        assertFalse(RegularExpressionUtils.isUrl(url9));
    }

    @Test
    public void isZh() {
        String zn1 = null;
        String zn2 = "中文";
        String zn3 = "123aabb";
        String zn4 = "中文abc";
        String zn5 = "中文123";

        assertFalse(RegularExpressionUtils.isZh(zn1));
        assertTrue(RegularExpressionUtils.isZh(zn2));
        assertFalse(RegularExpressionUtils.isZh(zn3));
        assertFalse(RegularExpressionUtils.isZh(zn4));
        assertFalse(RegularExpressionUtils.isZh(zn5));
    }

    @Test
    public void isIPv4() {
        String ipv4s1 = null;
        String ipv4s2 = "255.255.255.0";
        String ipv4s3 = "25.25.25.0";
        String ipv4s4 = "0.0.0.0";
        String ipv4s5 = "0.0.0";
        String ipv4s6 = "2555.2555.0.0";
        String ipv4s7 = "255,255,2,1";
        String ipv4s8 = "255255";

        assertFalse(RegularExpressionUtils.isIPv4(ipv4s1));
        assertTrue(RegularExpressionUtils.isIPv4(ipv4s2));
        assertTrue(RegularExpressionUtils.isIPv4(ipv4s3));
        assertTrue(RegularExpressionUtils.isIPv4(ipv4s4));
        assertFalse(RegularExpressionUtils.isIPv4(ipv4s5));
        assertFalse(RegularExpressionUtils.isIPv4(ipv4s6));
        assertFalse(RegularExpressionUtils.isIPv4(ipv4s7));
        assertFalse(RegularExpressionUtils.isIPv4(ipv4s8));

    }

    @Test
    public void hasBlankLine() {
        String content1 = null;
        String content2 = "123\n123";
        String content3 = "123";
        System.out.println(content2);
        assertFalse(RegularExpressionUtils.hasBlankLine(content1));
        assertTrue(RegularExpressionUtils.hasBlankLine(content2));
        assertFalse(RegularExpressionUtils.hasBlankLine(content3));
    }
}