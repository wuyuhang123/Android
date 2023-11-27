package com.example.myapplication.page

import android.R
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author wuyuhang
 * @Date 2023/11/21 11:03
 * @Describe
 */
class MyItemDecoration: RecyclerView.ItemDecoration {

    companion object {
        const val TAG = "MyItemDecoration"
        private val ATTRS = intArrayOf(R.attr.listDivider)
    }
    private var orientation: Int? = LinearLayoutManager.VERTICAL
    private var drawable: Drawable? = null

    constructor(context: Context, orientation: Int) {
        val typeArray = context.obtainStyledAttributes(ATTRS)
        drawable = typeArray.getDrawable(0)
        this.orientation = orientation
        typeArray.recycle()
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        if (orientation == LinearLayoutManager.VERTICAL) {
            drawVerticalItemDecoration(c, parent)
            Log.d(TAG, "drawVertical")
        } else if (orientation == LinearLayoutManager.HORIZONTAL) {
            drawHorizontalItemDecoration(c, parent)
            Log.d(TAG, "drawHorizontal")
        }
    }

    private fun drawHorizontalItemDecoration(c: Canvas, parent: RecyclerView) {
        val count = parent.childCount
        val top = parent.paddingTop
        val bottom = parent.bottom - parent.paddingBottom
        for (index in 0 until count - 1) {
            val child = parent.getChildAt(index);
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + layoutParams.rightMargin
            val right = left + (drawable?.intrinsicWidth ?: 1)
            // todo 确定canvas和drawable绘制的方式，此处是先设置bounds再draw，实验上来看canvas大小以及坐标和recyclerView一致
            drawable?.setBounds(left, top, right, bottom)
            drawable?.draw(c)
        }
    }

    private fun drawVerticalItemDecoration(c: Canvas, parent: RecyclerView) {
        val count = parent.childCount
        val left = parent.paddingLeft
        val right = parent.right - parent.paddingRight
        for (index in 0 until count - 1) {
            val child = parent[index]
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + layoutParams.bottomMargin
            val bottom = top + (drawable?.intrinsicHeight ?: 1)
            drawable?.setBounds(left, top, right, bottom)
            drawable?.draw(c)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (orientation == LinearLayoutManager.VERTICAL) {
            outRect.set(0, 0, 0, drawable?.intrinsicHeight ?: 0)
        } else {
            outRect.set(0,0, drawable?.intrinsicWidth ?: 0, 0)
        }
    }

}