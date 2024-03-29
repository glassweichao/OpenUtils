package com.chaow.openutils.basic;

import java.util.List;

/**
 * @author : Char
 * @date : 2019/7/11
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    : 列表工具
 */
public final class ListUtils {

    public static boolean isListEmpty(List list) {
        return list == null || list.size() <= 0;
    }

}
