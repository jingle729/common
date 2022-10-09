package com.lwt.lib.common;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class JsonUtil {
    private static Gson gson = new Gson();

    public static String toJson(String... keyValues) {
        Map map = new HashMap();
        for (int i = 0; i < keyValues.length; i++) {
            if (i % 2 == 0) {
                map.put(keyValues[i], keyValues[i + 1]);
            }
        }
        return gson.toJson(map);
    }

    public static String toJson(Object o) {
        try {
            if (o != null) {
                return gson.toJson(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static <T> T toObject(String responseBody, TypeToken<T> typeToken) {
        if (TextUtils.isEmpty(responseBody)) {
            return null;
        }
        Type type = typeToken.getType();
        T bean = gson.fromJson(responseBody, type);
        return bean;
    }

    public static <T> T toObject(String responseBody, Class<T> c) {
        if (TextUtils.isEmpty(responseBody)) {
            return null;
        }
        T bean = gson.fromJson(responseBody, c);
        return bean;
    }
}
