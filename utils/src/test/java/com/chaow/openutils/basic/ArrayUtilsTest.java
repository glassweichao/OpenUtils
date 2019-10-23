package com.chaow.openutils.basic;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author : Char
 * @date : 2019/10/23
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public class ArrayUtilsTest {

    @Test
    public void arrayOf() {
        Integer[] ints = ArrayUtils.arrayOf(1, 2, 3);
        Integer[] ints1 = {1, 2, 3};
        assertArrayEquals(ints, ints1);

        int[] ints2 = ArrayUtils.intArrayOf(1, 2, 3);
        int[] ints3 = {1, 2, 3};
        assertArrayEquals(ints2, ints3);

        String[] strings = ArrayUtils.arrayOf("1", "2", "3");
        String[] strings1 = {"1", "2", "3"};
        assertArrayEquals(strings, strings1);

        boolean[] booleans = ArrayUtils.booleanArrayOf(false, false, true);
        boolean[] booleans1 = {false, false, true};
        assertArrayEquals(booleans, booleans1);
    }

    @Test
    public void isEmpty() {
        int[] ints = new int[0];
        int[] ints1 = {};
        int[] ints2 = null;
        int[] ints3 = ArrayUtils.intArrayOf();
        int[] ints4 = new int[5];
        assertTrue(ArrayUtils.isEmpty(ints));
        assertTrue(ArrayUtils.isEmpty(ints1));
        assertTrue(ArrayUtils.isEmpty(ints2));
        assertTrue(ArrayUtils.isEmpty(ints3));
        assertFalse(ArrayUtils.isEmpty(ints4));
    }

    @Test
    public void getLength() {
        int[] ints = new int[0];
        int[] ints1 = {};
        int[] ints2 = null;
        int[] ints3 = ArrayUtils.intArrayOf();
        int[] ints4 = new int[5];
        int[] ints5 = {1, 2, 3, 4, 5, 6};

        System.out.println(ArrayUtils.getLength(ints));
        System.out.println(ArrayUtils.getLength(ints1));
        System.out.println(ArrayUtils.getLength(ints2));
        System.out.println(ArrayUtils.getLength(ints3));
        System.out.println(ArrayUtils.getLength(ints4));
        System.out.println(ArrayUtils.getLength(ints5));
    }

    @Test
    public void get() {
        int[] ints = {};
        System.out.println(ArrayUtils.get(ints, 0, 555));
        System.out.println(ArrayUtils.get(ints, 6, 666));
        System.out.println(ArrayUtils.get(ints, 7, 777));
    }

    @Test
    public void set() {
        int[] ints = {1, 2, 3};
        ArrayUtils.set(ints, 1, 5);
        System.out.println(Arrays.toString(ints));
        ArrayUtils.set(ints, 2, 6);
        System.out.println(Arrays.toString(ints));
        ArrayUtils.set(ints, 7, -1);
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void equals1() {
        Integer[] ints1 = {1, 2, 3};
        Integer[] ints2 = {1, 2, 3};
        assertTrue(ArrayUtils.equals(ints1, ints2));
        Integer[] ints3 = {1, 2};
        Integer[] ints4 = {1, 2, 3};
        assertFalse(ArrayUtils.equals(ints3, ints4));
        assertFalse(ArrayUtils.equals(null, ints4));
        assertTrue(ArrayUtils.equals(null, null));
        assertFalse(ArrayUtils.equals(null, new Integer[2]));
    }

    @Test
    public void reverse() {
        String[] s = {"1", "2", "a"};
        ArrayUtils.reverse(s);
        System.out.println(Arrays.toString(s));
    }

    @Test
    public void copy() {
        String[] s = {"1", "2", "a"};
        String[] s1 = s;
        String[] s2 = ArrayUtils.copy(s);
        assertArrayEquals(s, s1);
        assertArrayEquals(s, s2);
        assertArrayEquals(s1, s2);
        System.out.println(s == s1);
        System.out.println(s == s2);
        System.out.println(s1 == s2);
        System.out.println(s.toString());
        System.out.println(s1.toString());
        System.out.println(s2.toString());
    }

    @Test
    public void subArray() {
        Integer[] ints = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(Arrays.toString(ArrayUtils.subArray(ints, 2, 5)));
    }

    @Test
    public void add() {
        Integer[] ints = {1, 2, 3, 4, 5, 6, 7, 8};
        ints = ArrayUtils.add(ints, -1);
        Integer[] ints1 = {0, 0, 0};
        ints = ArrayUtils.add(ints, ints1);
        ints = ArrayUtils.add(ints, 3, -2);
        ints = ArrayUtils.add(ints, 5, ints1);
        System.out.println(Arrays.toString(ints));
    }
}