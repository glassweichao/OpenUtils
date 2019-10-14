package com.chaow.openutils.basic;

import org.junit.Test;

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

}