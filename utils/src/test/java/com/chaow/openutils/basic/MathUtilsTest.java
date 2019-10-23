package com.chaow.openutils.basic;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author : Char
 * @date : 2019/10/23
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public class MathUtilsTest {

    @Test
    public void add() {
        double num1 = 1.253;
        double num2 = 2.103;
        System.out.println(MathUtils.add(num1, num2));
        System.out.println(MathUtils.add(num1, num2, BigDecimal.ROUND_UP));
        System.out.println(MathUtils.add(num1, num2, 3, BigDecimal.ROUND_DOWN));
    }

    @Test
    public void sub() {
        double num1 = 1.221;
        double num2 = 2.103;
        System.out.println(MathUtils.sub(num1, num2));
        System.out.println(MathUtils.sub(num1, num2, BigDecimal.ROUND_UP));
        System.out.println(MathUtils.sub(num1, num2, 1, BigDecimal.ROUND_DOWN));
    }

    @Test
    public void mul() {
        double num1 = 5;
        double num2 = 2;
        System.out.println(MathUtils.mul(num1, num2));
        System.out.println(MathUtils.mul(num1, num2, BigDecimal.ROUND_UP));
        System.out.println(MathUtils.mul(num1, num2, 1, BigDecimal.ROUND_DOWN));
    }

    @Test
    public void div() {
        double num1 = 5;
        double num2 = 3;
        System.out.println(MathUtils.div(num1, num2));
        System.out.println(MathUtils.div(num1, num2, BigDecimal.ROUND_UP));
        System.out.println(MathUtils.div(num1, num2, 3, BigDecimal.ROUND_DOWN));
    }
}