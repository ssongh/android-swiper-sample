package com.ssongh.swipersample

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView

/**
 * Default 웹뷰
 */
class CustomWebView : WebView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var mOnOverScrolledCallback: OnOverScrolledCallback? = null
    private var mOnTouchEventCallback: OnTouchEventCallback? = null

    init {
        webSettings()
    }

    /**
     * 웹뷰 설정
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun webSettings() {
        this.settings.run {
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            loadsImagesAutomatically = true
            useWideViewPort = true
            domStorageEnabled = true
            databaseEnabled = true
            allowFileAccess = true
            loadWithOverviewMode = true
            cacheMode = WebSettings.LOAD_NO_CACHE
            layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL

            setSupportMultipleWindows(true)

            // 롤리팝 이상
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // Mixed Content 항상 허용
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
        }

        // 킷캣 이상
        // 개발계, 웹 디버깅 허용
        if (BuildConfig.DEBUG) {
            setWebContentsDebuggingEnabled(true)
        }


        // 롤리팝 이상
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 쿠키 설정
            val cookieManager = CookieManager.getInstance()
            cookieManager.setAcceptCookie(true)
            cookieManager.setAcceptThirdPartyCookies(this, true)
        }
    }


    interface OnOverScrolledCallback {
        fun onOverScrolled(scrollX: Int, scrollY: Int, clampedX: Boolean, clampedY: Boolean)
    }

    interface OnTouchEventCallback {
        fun onTouchEvent(event: MotionEvent?)
    }

    fun onOverScrolledCallback(onOverScrolledCallback: OnOverScrolledCallback) {
        mOnOverScrolledCallback = onOverScrolledCallback
    }

    fun onTouchEventCallback(onTouchEventCallback: OnTouchEventCallback) {
        mOnTouchEventCallback = onTouchEventCallback
    }

    override fun onOverScrolled(scrollX: Int, scrollY: Int, clampedX: Boolean, clampedY: Boolean) {
        mOnOverScrolledCallback?.onOverScrolled(scrollX, scrollY, clampedX, clampedY)
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mOnTouchEventCallback?.onTouchEvent(event)
        return super.onTouchEvent(event)
    }
}