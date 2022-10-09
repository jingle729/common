package com.lwt.lib.common;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;

import java.util.List;

public class SettingUtil {

    public static <T> void setByJson(Context mContext, String key, T t) {
        try {
            String json = JsonUtil.toJson(t);
            set(mContext, key + "_JSON", json);
        } catch (Exception e) {

        }
    }

    public static <T> void set(Context mContext, String key, T t) {
        setByJson(mContext, key, t);
    }

    public static <T> T get(Context mContext, String key, Class<T> c) {
        try {
            String json = get(mContext, key + "_JSON", "");
            if (!TextUtils.isEmpty(json)) {
                return JsonUtil.toObject(json, c);
            }
        } catch (Exception e) {

        }
        return null;
    }

    public static void set(Context mContext, String key, boolean value) {
        Context context = mContext;
        set(context, key, value);
    }

    public static void set(Context mContext, String key, float value) {
        Context context = mContext;
        set(context, key, value);
    }

    public static void set(Context mContext, String key, int value) {
        Context context = mContext;
        set(context, key, value);
    }

    public static void set(Context mContext,  String key, long value) {
        Context context = mContext;
        set(context, key, value);
    }

    public static void set(Context mContext,  String key, double value) {
        Context context = mContext;
        set(context, key, value + "");
    }

    public static void set(Context mContext,  String key, String value) {
        Context context = mContext;
        set(context, key, value);
    }

    public static boolean get(Context mContext,  String key, boolean value) {
        Context context = mContext;
        return get(context, key, value);
    }

    public static float get(Context mContext,  String key, float value) {
        Context context = mContext;
        return get(context, key, value);
    }

    public static int get(Context mContext,  String key, int value) {
        Context context = mContext;
        return get(context, key, value);
    }

    public static long get(Context mContext,  String key, long value) {
        Context context = mContext;
        return get(context, key, value);
    }

    public static double get(Context mContext,  String key, double value) {
        Context context = mContext;
        String v = get(context, key, value + "");
        return StringUtil.toDouble(v);
    }

    public static String get(Context mContext,  String key, String value) {
        Context context = mContext;
        return get(context, key, value);
    }

    public static <T> void setList(Context mContext, String key, List<T> t) {
        setByJson(mContext, key, t);
    }

    public static <T> List<T> getList(Context mContext, String key, Class<T> c) {
        return getByTypeToken(mContext, key, new TypeToken<List<T>>() {
        });
    }

    public static <T> T getByTypeToken(Context mContext, String key, TypeToken<T> typeToken) {
        try {
            String json = get(mContext, key + "_JSON", "");
            if (!TextUtils.isEmpty(json)) {
                return JsonUtil.toObject(json, typeToken);
            }
        } catch (Exception e) {

        }
        return null;
    }
}
