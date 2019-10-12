package com.chaow.openutils.basic;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.hamcrest.CoreMatchers.*;
import org.hamcrest.MatcherAssert.*;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;


/**
 * @author : Char
 * @date : 2019/10/12
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public class ArrayUtilsTest {

    @Test
    public void arrayOf() {
        Integer[] test = ArrayUtils.arrayOf(1, 2, 3);
    }

    @Test
    public void longArrayOf() {

    }

    @Test
    public void intArrayOf() {
    }

    @Test
    public void shortArrayOf() {
    }

    @Test
    public void charArrayOf() {
    }

    @Test
    public void byteArrayOf() {
    }

    @Test
    public void doubleArrayOf() {
    }

    @Test
    public void floatArrayOf() {
    }

    @Test
    public void booleanArrayOf() {
    }

    @Test
    public void isEmpty() {

    }

    @Test
    public void getLength() {
        int[] test = null;
        MatcherAssert.assertThat(ArrayUtils.getLength(test), Is.is(0));
    }

    @Test
    public void get() {
    }

    @Test
    public void get1() {
    }

    @Test
    public void set() {
    }

    @Test
    public void equals1() {
    }

    @Test
    public void reverse() {
    }

    @Test
    public void copy() {
    }

    @Test
    public void subArray() {
    }

    @Test
    public void add() {
    }

    @Test
    public void add1() {
    }

    @Test
    public void add2() {
    }

    @Test
    public void add3() {
    }
}