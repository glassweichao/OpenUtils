package com.chaow.openutils.basic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Locale;

/**
 * @author : Char
 * @date : 2019/8/23
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    : 身份证校验工具
 */
public final class IDCardUtils {

    /** 错误信息 */
    private String mErrorInfo = "";

    /**
     * 获取错误信息
     *
     * @return 错误信息
     */
    public String getErrorInfo() {
        return mErrorInfo;
    }

    /**
     * 校验身份证是否合规
     *
     * @param strIDCard 身份证号
     * @return {@code true}: 合规 <br>{@code false}: 不合规
     */
    public boolean checkIDCard(String strIDCard) {
        try {
            String[] verifyCodes = {"1", "0", "X", "9", "8", "7", "6", "5", "4",
                    "3", "2"};
            String[] wCodes = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                    "9", "10", "5", "8", "4", "2"};
            //除最后一位
            String exEnd = "";
            // ================ 号码的长度 15位或18位 ================
            if (strIDCard.length() != 15 && strIDCard.length() != 18) {
                mErrorInfo = "身份证号码长度应该为15位或18位";
                return false;
            }

            // ================ 数字 除最后一位都为数字 ================
            if (strIDCard.length() == 18) {
                exEnd = strIDCard.substring(0, 17);
            } else if (strIDCard.length() == 15) {
                exEnd = strIDCard.substring(0, 6) + "19" + strIDCard.substring(6, 15);
            }
            if (!RegularExpressionUtils.isNumber(exEnd)) {
                mErrorInfo = "身份证15位号码都应为数字；18位号码除最后一位外，都应为数字";
                return false;
            }

            // ================ 出生年月是否有效 ================
            String strYear = exEnd.substring(6, 10);// 年份
            String strMonth = exEnd.substring(10, 12);// 月份
            String strDay = exEnd.substring(12, 14);// 月份
            if (!RegularExpressionUtils.isDate(strYear + "-" + strMonth + "-" + strDay)) {
                mErrorInfo = "身份证出生日期无效";
                return false;
            }
            GregorianCalendar gc = new GregorianCalendar();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(
                    strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                mErrorInfo = "身份证出生日期不在有效范围";
                return false;
            }
            if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
                mErrorInfo = "身份证月份无效";
                return false;
            }
            if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
                mErrorInfo = "身份证日期无效";
                return false;
            }

            // ================ 地区码是否有效 ================
            Hashtable h = getAreaCode();
            if (h.get(exEnd.substring(0, 2)) == null) {
                mErrorInfo = "身份证地区编码错误。";
                return false;
            }

            // ================ 最后一位校验位计算 ================
            int totalMul = 0;
            for (int i = 0; i < 17; i++) {
                totalMul = totalMul
                        + Integer.parseInt(String.valueOf(exEnd.charAt(i)))
                        * Integer.parseInt(wCodes[i]);
            }
            int modValue = totalMul % 11;
            String strVerifyCode = verifyCodes[modValue];
            exEnd = exEnd + strVerifyCode;

            if (strIDCard.length() == 18) {
                if (exEnd.charAt(17) == 'x') {
                    mErrorInfo = "最后一位需为大写X";
                    return false;
                } else if (!exEnd.equals(strIDCard)) {
                    mErrorInfo = "身份证无效，不是合法的身份证号码";
                    return false;
                }
            } else {
                return true;
            }
            return true;
        } catch (Exception e) {
            LogUtils.e(e.toString());
            return false;
        }
    }


    /**
     * 功能：设置地区编码
     *
     * @return Hashtable 对象
     */
    private static Hashtable<String, String> getAreaCode() {
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

}
