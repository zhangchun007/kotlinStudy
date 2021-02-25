package com.jimmy.custom.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.TextView

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-11-04
 * @Version:        1.0
 */
class RoundTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : TextView(context, attrs, defStyle) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //通过画笔Paint在画布Canvas上绘制图案
        val paint = Paint()
        paint.color = Color.RED
        paint.strokeWidth = 2f
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true

        val rectF = RectF(1f, 1f, (this.width - 1).toFloat(), (this.height - 1).toFloat())
        //方法drawRoundRect表示绘制圆角钜形
        canvas.drawRoundRect(rectF, 10f, 10f, paint)
    }
}