package com.chaow.openutils.basic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.chaow.openutils.OpenUtils;
import com.chaow.openutils.constant.MemoryConstants;
import com.chaow.openutils.constant.TimeConstants;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author : Char
 * @date : 2019/8/26
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    : 转换工具
 */
public final class ConvertUtils {

    private ConvertUtils() {

    }

    private static final char[] hexDigits =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * bytes转bits
     *
     * @param bytes 给定byte数组
     * @return bit串
     */
    public static String bytes2Bits(final byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            for (int j = 7; j >= 0; --j) {
                sb.append(((aByte >> j) & 0x01) == 0 ? '0' : '1');
            }
        }
        return sb.toString();
    }

    /**
     * bit转byte
     *
     * @param bits 给定bit
     * @return byte数组
     */
    public static byte[] bits2Bytes(String bits) {
        int l = 8;
        int lenMod = bits.length() % l;
        int byteLen = bits.length() / l;
        // add "0" until length to 8 times
        if (lenMod != 0) {
            StringBuilder bitsBuilder = new StringBuilder(bits);
            for (int i = lenMod; i < l; i++) {
                bitsBuilder.insert(0, "0");
            }
            bits = bitsBuilder.toString();
            byteLen++;
        }
        byte[] bytes = new byte[byteLen];
        for (int i = 0; i < byteLen; ++i) {
            for (int j = 0; j < l; ++j) {
                bytes[i] <<= 1;
                bytes[i] |= bits.charAt(i * l + j) - '0';
            }
        }
        return bytes;
    }

    /**
     * byte转Char
     *
     * @param bytes 给定byte数组
     * @return Char数组
     */
    public static char[] bytes2Chars(final byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        int len = bytes.length;
        if (len <= 0) {
            return null;
        }
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            chars[i] = (char) (bytes[i] & 0xff);
        }
        return chars;
    }

    /**
     * char转byte
     *
     * @param chars 给定char数组
     * @return byte数组
     */
    public static byte[] chars2Bytes(final char[] chars) {
        if (chars == null || chars.length <= 0) {
            return null;
        }
        int len = chars.length;
        byte[] bytes = new byte[len];
        for (int i = 0; i < len; i++) {
            bytes[i] = (byte) (chars[i]);
        }
        return bytes;
    }

    /**
     * byte转hexString
     *
     * @param bytes 给定byte数组
     * @return hexString
     */
    public static String bytes2HexString(final byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        int len = bytes.length;
        if (len <= 0) {
            return "";
        }
        char[] ret = new char[len << 1];
        for (int i = 0, j = 0; i < len; i++) {
            ret[j++] = hexDigits[bytes[i] >> 4 & 0x0f];
            ret[j++] = hexDigits[bytes[i] & 0x0f];
        }
        return new String(ret);
    }

    /**
     * hexString转byte
     *
     * @param hexString 给定hexString
     * @return byte
     */
    public static byte[] hexString2Bytes(String hexString) {
        if (StringUtils.isSpace(hexString)) {
            return null;
        }
        int len = hexString.length();
        if (len % 2 != 0) {
            hexString = "0" + hexString;
            len = len + 1;
        }
        char[] hexBytes = hexString.toUpperCase().toCharArray();
        byte[] ret = new byte[len >> 1];
        for (int i = 0; i < len; i += 2) {
            ret[i >> 1] = (byte) (hex2Int(hexBytes[i]) << 4 | hex2Int(hexBytes[i + 1]));
        }
        return ret;
    }

    /**
     * hex转int
     *
     * @param hexChar hex字符
     * @return int
     */
    private static int hex2Int(final char hexChar) {
        if (hexChar >= '0' && hexChar <= '9') {
            return hexChar - '0';
        } else if (hexChar >= 'A' && hexChar <= 'F') {
            return hexChar - 'A' + 10;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 按给定单位返回存储空间大小
     *
     * @param memorySize 存储空间大小
     * @param unit       单位{@link MemoryConstants}
     * @return 指定单位的存储空间大小
     */
    public static long memorySize2Byte(final long memorySize, @MemoryConstants.Unit final int unit) {
        if (memorySize < 0) {
            return -1;
        }
        return memorySize * unit;
    }

    /**
     * 按给定单位返回存储空间大小
     *
     * @param byteSize 存储空间大小
     * @param unit     单位{@link MemoryConstants}
     * @return 指定单位的存储空间大小
     */
    public static double byte2MemorySize(final long byteSize,
                                         @MemoryConstants.Unit final int unit) {
        if (byteSize < 0) {
            return -1;
        }
        return (double) byteSize / unit;
    }

    /**
     * 给定单位的时间转时间戳
     *
     * @param timeSpan 给定单位的时间
     * @param unit     单位
     * @return 时间戳
     */
    public static long timeSpan2Millis(final long timeSpan, @TimeConstants.Unit final int unit) {
        return timeSpan * unit;
    }

    /**
     * 时间戳转给定单位的时间
     *
     * @param millis 时间戳
     * @param unit   单位
     * @return 给定单位的时间
     */
    public static long millis2TimeSpan(final long millis, @TimeConstants.Unit final int unit) {
        return millis / unit;
    }

    /**
     * input stream 转 output stream
     *
     * @param is input stream
     * @return output stream
     */
    public static ByteArrayOutputStream input2OutputStream(final InputStream is) {
        if (is == null) {
            return null;
        }
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] b = new byte[MemoryConstants.KB];
            int len;
            while ((len = is.read(b, 0, MemoryConstants.KB)) != -1) {
                os.write(b, 0, len);
            }
            return os;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * output stream 转 input stream
     *
     * @param out output stream
     * @return input stream
     */
    public ByteArrayInputStream output2InputStream(final OutputStream out) {
        if (out == null) {
            return null;
        }
        return new ByteArrayInputStream(((ByteArrayOutputStream) out).toByteArray());
    }

    /**
     * dp 转 px
     *
     * @param dpValue dp
     * @return px
     */
    public static int dp2px(final float dpValue) {
        final float scale = OpenUtils.getApp().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px 转 dp
     *
     * @param pxValue px
     * @return dp
     */
    public static int px2dp(final float pxValue) {
        final float scale = OpenUtils.getApp().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
