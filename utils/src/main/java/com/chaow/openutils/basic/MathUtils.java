package com.chaow.openutils.basic;

import java.math.BigDecimal;

/**
 * @author : Char
 * @date : 2019/8/15
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    : 高精度计算
 */
public final class MathUtils {

    /** 默认精确度 */
    private final static int DEFAULT_ACCURACY = 2;
    private final static int DEFAULT_ROUND_TYPE = BigDecimal.ROUND_DOWN;

    public static double add(double d1, double d2) {
        return add(d1, d2, DEFAULT_ACCURACY, DEFAULT_ROUND_TYPE);
    }

    public static double add(double d1, double d2, int roundType) {
        return add(d1, d2, DEFAULT_ACCURACY, roundType);
    }

    public static double add(double d1, double d2, int accuracy, int roundType) {
        return BigDecimal.valueOf(d1).add(BigDecimal.valueOf(d2)).setScale(accuracy, roundType).doubleValue();
    }

    public static double sub(double d1, double d2) {
        return sub(d1, d2, DEFAULT_ACCURACY, DEFAULT_ROUND_TYPE);
    }

    public static double sub(double d1, double d2, int roundType) {
        return sub(d1, d2, DEFAULT_ACCURACY, roundType);
    }

    public static double sub(double d1, double d2, int accuracy, int roundType) {
        return BigDecimal.valueOf(d1).subtract(BigDecimal.valueOf(d2)).setScale(accuracy, roundType).doubleValue();
    }

    public static double mul(double d1, double d2) {
        return mul(d1, d2, DEFAULT_ACCURACY, DEFAULT_ROUND_TYPE);
    }

    public static double mul(double d1, double d2, int roundType) {
        return mul(d1, d2, DEFAULT_ACCURACY, roundType);
    }

    public static double mul(double d1, double d2, int accuracy, int roundType) {
        return BigDecimal.valueOf(d1).multiply(BigDecimal.valueOf(d2)).setScale(accuracy, roundType).doubleValue();
    }

    public static double div(double d1, double d2) {
        return div(d1, d2, DEFAULT_ACCURACY, DEFAULT_ROUND_TYPE);
    }

    public static double div(double d1, double d2, int roundType) {
        return div(d1, d2, DEFAULT_ACCURACY, roundType);
    }

    public static double div(double d1, double d2, int accuracy, int roundType) {
        return BigDecimal.valueOf(d1).divide(BigDecimal.valueOf(d2), accuracy, roundType).doubleValue();
    }

}
