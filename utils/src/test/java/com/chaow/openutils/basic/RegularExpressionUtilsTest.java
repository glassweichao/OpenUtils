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

}