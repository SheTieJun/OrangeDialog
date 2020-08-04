package me.shetj.dialog


import android.content.res.Resources
import android.util.DisplayMetrics

internal object ScreenUtil {
    private var density = -1f

    private fun getDensity(): Float {
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
}
