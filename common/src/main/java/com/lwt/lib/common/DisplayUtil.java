package com.lwt.lib.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class DisplayUtil {

    public static float getDimenY(Context mContext, int px) {
        int dimensId = mContext.getResources().getIdentifier("y" + Math.abs(px), "dimen", mContext.getPackageName());
        if (px < 0) {
            return 0 - mContext.getResources().getDimension(dimensId);
        } else {
            return mContext.getResources().getDimension(dimensId);
        }
    }

    public static float getDimenX(Context mContext, int px) {
        int dimensId = mContext.getResources().getIdentifier(px > 0 ? "x" + px : "xr" + px, "dimen", mContext.getPackageName());
        return mContext.getResources().getDimension(dimensId);
    }

    /**
     * 获取屏幕的宽高（PX）
     *
     * @return int [] {宽， 高}
     */
    public static int[] getDisplay(Context mContext) {
        if (mContext != null) {
            WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            return new int[]{display.getWidth(), display.getHeight()};
        }
        return new int[2];
    }

    /**
     * 获取状态栏的高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context mContext) {
        int statusBarHeight1 = -1;
        //获取status_bar_height资源的ID
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = mContext.getResources().getDimensionPixelSize(resourceId);
            return statusBarHeight1;
        }
        return 0;
    }

    /**
     * 获取屏幕的宽（PX）
     *
     * @return
     */
    public static int getWindowWith(Context mContext) {
        return getDisplay(mContext)[0];
    }

    /**
     * 获取屏幕的高（PX）
     *
     * @return
     */
    public static int getWindowHeight(Context mContext) {
        return getDisplay(mContext)[1];
    }

    private static float scale;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context mContext, double dip) {
        if (scale <= 0) {
            if (mContext == null) {
                return (int) dip;
            }
            scale = mContext.getResources().getDisplayMetrics().density;
        }
        return (int) (dip * scale + 0.5f);
    }

    public static int px2dip(int pxValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕宽度和高度，单位为px
     *
     * @param context
     * @return
     */
    public static Point getScreenMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        return new Point(w_screen, h_screen);

    }

    /**
     * 获取屏幕长宽比
     *
     * @param context
     * @return
     */
    public static float getScreenRate(Context context) {
        Point P = getScreenMetrics(context);
        float H = P.y;
        float W = P.x;
        return (H / W);
    }

    /**
     * M�todo que devuelve el alto de la pantalla
     *
     * @param context Context de la aplicaci�n
     * @return int con la altura
     */
    public static int getScreenHeight(Context context) {

        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getHeight();

    }

    @SuppressWarnings("deprecation")
    /**
     * M�todo que devuelve el ancho de la pantalla
     *
     * @param context Context de la aplicaci�n
     *
     * @return int con la ancho
     */
    public static int getScreenWidth(Context context) {

        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getWidth();

    }

    public static int getScreenWidth(Context context, boolean isDp) {
        int screenWidth = 0;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;       // 屏幕高度（像素）
        if (!isDp) {
            return width;
        }
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        screenWidth = (int) (width / density);// 屏幕高度(dp)
        return screenWidth;
    }
}
