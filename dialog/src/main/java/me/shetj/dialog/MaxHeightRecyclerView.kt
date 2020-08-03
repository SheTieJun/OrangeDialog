package me.shetj.dialog

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class MaxHeightRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {
    private var maxHeight: Int = 0

    init {
        if (attrs != null) {
            val a = getContext().obtainStyledAttributes(attrs, R.styleable.MaxHeightListView)
            maxHeight =
                a.getDimensionPixelSize(R.styleable.MaxHeightListView_maxHeight, Integer.MAX_VALUE)
            a.recycle()
        } else {
            maxHeight = 0
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var heightMeasureSpecE = heightMeasureSpec
        val measuredHeight = MeasureSpec.getSize(heightMeasureSpec)
        if (maxHeight in 1 until measuredHeight) {
            val measureMode = MeasureSpec.getMode(heightMeasureSpec)
            heightMeasureSpecE = MeasureSpec.makeMeasureSpec(maxHeight, measureMode)
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpecE)
    }

    fun setMaxHeight(maxHeight: Int) {
        this.maxHeight = maxHeight
    }
}
