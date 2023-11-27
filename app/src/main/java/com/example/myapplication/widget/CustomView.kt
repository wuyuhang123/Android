package com.example.myapplication.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

/**
 * @Author wuyuhang
 * @Date 2023/6/8 16:11
 * @Describe
 */
class CustomView : View {

    companion object {
        const val TAG = "CustomView"
    }

    private lateinit var paint: Paint


    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) {
        initDraw()
    }

    private fun initDraw() {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.RED
        paint.strokeWidth = 2.0f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
//        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
//        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
//        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
//        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
//            setMeasuredDimension(200, 200)
//        } else if (widthMode == MeasureSpec.AT_MOST) {
//            setMeasuredDimension(200, heightSize)
//        } else if (heightMode == MeasureSpec.AT_MOST) {
//            setMeasuredDimension(widthSize, 200)
//        }
        Log.d(TAG, "onMeasure")

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d(TAG, "onLayout")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRect(0F, 0F, width.toFloat(), height.toFloat(), paint)
        Log.d(TAG, "onDraw")
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(TAG, "onTouchEvent: DOWN, x:${event.x}}, y:${event.y}" +
                        ", rawX:${event.rawX}, rawY:${event.rawY}")
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(TAG, "onTouchEvent: MOVE, x:${event.x}}, y:${event.y}" +
                        ", rawX:${event.rawX}, rawY:${event.rawY}")
            }
            MotionEvent.ACTION_CANCEL -> {
                Log.d(TAG, "onTouchEvent: CANCEL, x:${event.x}}, y:${event.y}" +
                        ", rawX:${event.rawX}, rawY:${event.rawY}")
            }
            MotionEvent.ACTION_UP -> {
                Log.d(TAG, "onTouchEvent: UP, x:${event.x}}, y:${event.y}" +
                        ", rawX:${event.rawX}, rawY:${event.rawY}")
            }
        }
        return true
    }


}