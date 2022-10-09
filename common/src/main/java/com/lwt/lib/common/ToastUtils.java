package com.lwt.lib.common;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ToastUtils {

    private static Toast mToast;
    private static boolean isDevelopModel;
    private static Context mApplication;

    public static void init(Context context){
        if(context != null) {
            mApplication = context.getApplicationContext();
        }
    }

    public static void show(String msg){
        if(mApplication != null) {
            show(mApplication, msg);
        }else {
            Log.e("ToastUtils", "请先初始化:ToastUtils.init(Application)");
        }
    }

    public static void setDevModel(boolean isDevModel) {
        isDevelopModel = isDevModel;
    }

    public static void debug(Context context, int stringId) {
        if (isDevelopModel) {
            show(context, "(debug)\n" + context.getResources().getString(stringId));
        }
    }

    public static void debug(Context context, Class c, String msg) {
        if (c != null) {
            ToastUtils.debug(context, c.getSimpleName() + "\t" + msg);
        } else {
            ToastUtils.debug(context, msg);
        }
    }

    public static void debugClientMsg(Context context, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            debug(context, "客户端消息提示\n" + msg);
        }
    }

    public static void debug(Context context, String msg) {
        if (isDevelopModel) {
            show(context, "(debug)\n" + msg, 10000);
        }
    }

    public static void showLong(Context context, String msg) {
        show(context, msg, 5000);
    }

    public static void show(Context context, int stringId, int time) {
        show(context, context.getResources().getString(stringId), time);
    }

    public static void show(Context context, String msg) {
        show(context, msg, 3000);
    }

    public static void show(Context context, int stringId) {
        show(context, stringId, 3000);
    }

    private static Handler toastHandler;
    private static String toastHandlerText;
    private static int toastHandlerTime;


    public static void show(Context context, String msg, int time) {

        if (TextUtils.isEmpty(msg)) {
            return;
        }
        try {
            if (toastHandler == null) {
                toastHandler = new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        try {
                            if (mToast != null) {
                                mToast.cancel();
                            }
                            mToast = new Toast(context);
                            mToast.setView(getToastView(context, toastHandlerText));
                            if (toastHandlerTime <= 3000) {
                                mToast.setDuration(Toast.LENGTH_SHORT);
                            } else {
                                mToast.setDuration(Toast.LENGTH_LONG);
                            }
                            mToast.setGravity(Gravity.TOP, 0, DisplayUtil.getWindowHeight(context) / 2 - 30);
                            mToast.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
            }
            toastHandlerText = msg;
            toastHandlerTime = time;
            toastHandler.sendEmptyMessage(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cancel() {
        try {
            if (mToast != null) {
                mToast.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final ViewGroup.LayoutParams M_LAYOUT_PARAMS = new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    private static final int TEXT_SIZE = 15;

    public static TextView getToastView(Context context, String msg) {
        int padding = DisplayUtil.dip2px(context, 10);
        TextView mTextView = new TextView(context);
        mTextView.setLayoutParams(M_LAYOUT_PARAMS);
        mTextView.getBackground().setAlpha(180);
        mTextView.setPadding(padding, padding / 2, padding, padding / 2);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE);
        mTextView.setTextColor(Color.WHITE);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setTextSize(12);
        mTextView.setText(msg);
        return mTextView;
    }
}
