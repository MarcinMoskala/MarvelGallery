package com.sample.marvelgallery.view.views

import android.util.AttributeSet
import android.widget.FrameLayout
import android.content.Context

class SquareFrameLayout @JvmOverloads constructor( // 1
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec) // 2
    }
}
