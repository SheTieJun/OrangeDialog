package me.shetj.dialog


import android.content.res.Resources
import android.util.DisplayMetrics

/**
 * Created by Administrator on 2017/1/4 0004.
 */

object ScreenUtil {
    private var density = -1f

    fun getDensity(): Float {
        if (density <= 0f) {
            val dm = Resources.getSystem().displayMetrics
            density = dm.density
        }
        return density
    }

    @JvmStatic
    fun dip2px(dpValue: Float): Int {
        return (dpValue * getDensity() + 0.5f).toInt()
    }

    @JvmStatic
    fun px2dip(pxValue: Float): Int {
        return (pxValue / getDensity() + 0.5f).toInt()
    }
}
