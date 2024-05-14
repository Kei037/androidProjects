package com.kei037.file_stream_ex03

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import java.lang.Exception

class MyPictureView(context: Context, attrs: AttributeSet?): View(context, attrs) {

    var imagePath: String? = null

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        try {
            if (imagePath != null) {
                val bitmap = BitmapFactory.decodeFile(imagePath)
                canvas.scale(2f, 2f, 0f, 0f)
                canvas.drawBitmap(bitmap!!, 0f, 0f, null)
                bitmap.recycle()
            }
        } catch (e: Exception) {

        }
    }

}