package com.jimmy.custom.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-11-04
 * @Version:        1.0
 */
class RoundLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs) {

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        val paint = Paint()
        paint.color = Color.BLUE
        paint.strokeWidth = 2f
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true

        val rectF = RectF(1f, 1f, (this.width - 1).toFloat(), (this.height - 1).toFloat())
        canvas.drawRoundRect(rectF, 10f, 10f, paint)
    }
}