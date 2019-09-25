package com.chaow.subutils.gson;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Char
 * @date : 2019/9/25
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public final class GsonUtils {

    private GsonUtils() {

    }

    /**
     * 解析没有标题的json数组，例如[{},{},{}]
     *
     * @param jArray    json数组
     * @param itemClass 数组项类型
     * @return 解析后的数组
     */
    public static List<?> parseNoHeaderJArray(String jArray, Class itemClass) {
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(jArray).getAsJsonArray();
        Gson gson = new Gson();
        List<Object> itemObjects = new ArrayList<>();
        for (JsonElement jsonElement : jsonArray) {
            itemObjects.add(gson.fromJson(jsonElement, itemClass));
        }
        return itemObjects;
    }

}
