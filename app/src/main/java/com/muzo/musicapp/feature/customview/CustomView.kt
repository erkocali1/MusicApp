package com.muzo.musicapp.feature.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.muzo.musicapp.R

class CustomView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val squareSize = 190
    private val imageSize = 140
    private val arrowDrawable: Drawable?

    init {
        arrowDrawable = ContextCompat.getDrawable(context, R.drawable.ic_arrow)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = resolveSize(squareSize, widthMeasureSpec)
        val height = resolveSize(squareSize, heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        // Arka plan rengini ayarla
        canvas.drawColor(ContextCompat.getColor(context, R.color.white))

        // Kareyi çiz
        val squarePaint = Paint().apply {
            color = ContextCompat.getColor(context, R.color.square)
        }
        val squareRect = Rect(0, 0, squareSize, squareSize)
        canvas.drawRect(squareRect, squarePaint)

        // Ok resmini çiz
        val drawableLeft = (squareSize - imageSize) / 2
        val drawableTop = (squareSize - imageSize) / 2
        val drawableRight = drawableLeft + imageSize
        val drawableBottom = drawableTop + imageSize
        arrowDrawable?.setBounds(drawableLeft, drawableTop, drawableRight, drawableBottom)
        arrowDrawable?.draw(canvas)
    }
}




