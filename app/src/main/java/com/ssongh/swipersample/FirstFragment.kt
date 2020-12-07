package com.ssongh.swipersample

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.ssongh.swipersample.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private var webViewCallback: WebViewCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        when (context) {
            is WebViewCallback -> {
                webViewCallback = context
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.wvFirst.webViewClient = WebViewClient()
        binding.wvFirst.webChromeClient = WebChromeClient()
        binding.wvFirst.loadUrl("file:///android_asset/swiper_sample.html")

        binding.wvFirst.onOverScrolledCallback(object : CustomWebView.OnOverScrolledCallback {
            override fun onOverScrolled(
                scrollX: Int,
                scrollY: Int,
                clampedX: Boolean,
                clampedY: Boolean
            ) {
                webViewCallback?.onSwipeWebView(true)
            }
        })

        binding.wvFirst.onTouchEventCallback(object : CustomWebView.OnTouchEventCallback {
            override fun onTouchEvent(event: MotionEvent?) {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        webViewCallback?.onSwipeWebView(false)
                    }

                    MotionEvent.ACTION_UP -> {
                        webViewCallback?.onSwipeWebView(true)
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}