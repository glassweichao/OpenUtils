package com.chaow.openutils.basic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : Char
 * @date : 2019/10/12
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public class IDCardUtilsTest {

    private IDCardUtils mIdcardutils;

    @Before
    public void init() {
        mIdcardutils = new IDCardUtils();
    }

    @Test
    public void checkIDCard_null() {
        String idcard = null;
        assertFalse(mIdcardutils.checkIDCard(idcard));
    }

    @Test
    public void checkIDCard_errorid() {
        String idcard1 = "310202111";
        String idcard2 = "31020219899999999990";
        String idcard3 = "10020219890101010x";
        String idcard4 = "10020219890101010X";
        String idcard5 = "10020219890101010*";

        assertFalse(mIdcardutils.checkIDCard(idcard1));
        assertFalse(mIdcardutils.checkIDCard(idcard2));
        assertFalse(mIdcardutils.checkIDCard(idcard3));
        assertFalse(mIdcardutils.checkIDCard(idcard4));
        assertFalse(mIdcardutils.checkIDCard(idcard5));
    }

}