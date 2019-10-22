package com.chaow.openutils.basic;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : Char
 * @date : 2019/10/16
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public class StringUtilsTest {

    String mString1 = null;
    String mString2 = "";
    String mString3 = "abc";

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void isEmpty() {

        assertTrue(StringUtils.isEmpty(mString1));
        assertTrue(StringUtils.isEmpty(mString2));
        assertFalse(StringUtils.isEmpty(mString3));
    }

    @Test
    public void checkEmpty() {
        assertThat(StringUtils.checkEmpty(mString1, "123"), Is.is("123"));
        assertThat(StringUtils.checkEmpty(mString2, "123"), Is.is("123"));
        assertThat(StringUtils.checkEmpty(mString3, "123"), Is.is("abc"));
    }

    @Test
    public void isSpace() {
        String string4 = " ";
        String string5 = "a b c";
        String string6 = " abc ";
        String string7 = "\n";

        assertFalse(StringUtils.isSpace(mString1));
        assertFalse(StringUtils.isSpace(mString2));
        assertFalse(StringUtils.isSpace(mString3));
        assertTrue(StringUtils.isSpace(string4));
        assertFalse(StringUtils.isSpace(string5));
        assertFalse(StringUtils.isSpace(string6));
        assertTrue(StringUtils.isSpace(string7));
    }

    @Test
    public void upperFirstLetter() {
        String string4 = "BbC";
        String string5 = "1Abc";

        assertThat(StringUtils.upperFirstLetter(mString1), Is.is(""));
        assertThat(StringUtils.upperFirstLetter(mString2), Is.is(""));
        assertThat(StringUtils.upperFirstLetter(mString3), Is.is("Abc"));
        assertThat(StringUtils.upperFirstLetter(string4), Is.is("BbC"));
        assertThat(StringUtils.upperFirstLetter(string5), Is.is("1Abc"));
    }

    @Test
    public void lowerFirstLetter() {
        String string4 = "BbC";
        String string5 = "1Abc";

        assertThat(StringUtils.lowerFirstLetter(mString1), Is.is(""));
        assertThat(StringUtils.lowerFirstLetter(mString2), Is.is(""));
        assertThat(StringUtils.lowerFirstLetter(mString3), Is.is("abc"));
        assertThat(StringUtils.lowerFirstLetter(string4), Is.is("bbC"));
        assertThat(StringUtils.lowerFirstLetter(string5), Is.is("1Abc"));
    }

    @Test
    public void reverse() {
        String string5 = "123abc";

        assertThat(StringUtils.reverse(mString1), Is.is(""));
        assertThat(StringUtils.reverse(mString2), Is.is(""));
        assertThat(StringUtils.reverse(mString3), Is.is("cba"));
        assertThat(StringUtils.reverse(string5), Is.is("cba321"));
    }
}