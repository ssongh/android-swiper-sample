package com.ssongh.swipersample

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class CustomViewPager(context: Context?, attrs: AttributeSet?) :
    ViewPager(context!!, attrs) {

    private var swipeEnabled = true

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return if (swipeEnabled) super.onTouchEvent(ev) else false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return if (swipeEnabled) super.onInterceptTouchEvent(ev) else false

    }

    fun swipeEnabled(swipeEnabled: Boolean) {
        this.swipeEnabled = swipeEnabled
    }
}