package com.lwt.lib.common;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class StringUtil {
    public static DecimalFormat sFormat = new DecimalFormat("#.0");

    public static int toInt(String text) {
        long result = toLong(text);
        return result > Integer.MAX_VALUE ? 0 : (int) result;
    }

    public static int toInt(String text, int def) {
        return (int) toDouble(text, def);
    }

    public static double toDouble(String text) {
        return toDouble(text, 0);
    }

    public static double toDouble(String text, double def) {
        double result = def;
        try {
            if (TextUtils.isEmpty(text)) {
                return def;
            }
            if (text.indexOf(".") != -1)
                result = Double.valueOf(text);
            else
                result = Long.valueOf(text);
        } catch (Exception e) {

        }
        return result;
    }

    public static long toLong(String text) {
        return toLong(text, 0);
    }

    public static long toLong(String text, long def) {
        return (long) toDouble(text, def);
    }

    public static float parseFloat(String text) {
        return toFloat(text, 0);
    }

    public static float toFloat(String text, float def) {
        return (float) toDouble(text, def);
    }

    public static void setText(TextView textView, String text){
        if(textView == null)
            return;
        if(TextUtils.isEmpty(text)){
            textView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.VISIBLE);
            textView.setText(text);
        }
    }

    public static int getVisibile(String text) {
        if (TextUtils.isEmpty(text))
            return View.GONE;
        return View.VISIBLE;
    }
}
