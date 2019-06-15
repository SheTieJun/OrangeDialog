package me.shetj.dialog;


import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by Administrator on 2017/1/4 0004.
 */

public class ScreenUtil {
    private static float density = -1f;

    public static float getDensity()  {
        if (density <= 0f) {
            DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
            density = dm.density;
        }
        return density;
    }

    public static  int  dip2px(float dpValue) {
        return (int) (dpValue * getDensity() + 0.5f);
    }

    public static int  px2dip(float pxValue) {
        return (int) (pxValue / getDensity() + 0.5f);
    }
}
