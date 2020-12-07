package com.ssongh.swipersample

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ssongh.swipersample.databinding.ActivityMainBinding

private const val NUM_PAGES = 2

class MainActivity : AppCompatActivity(), WebViewCallback {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        binding.vpMain.adapter = pagerAdapter

    }


    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount(): Int = NUM_PAGES

        override fun getItem(position: Int): Fragment = when (position) {
            0 -> FirstFragment()
            else -> SecondFragment()
        }
    }

    override fun onSwipeWebView(viewPagerEnable: Boolean) {
        binding.vpMain.swipeEnabled(viewPagerEnable)
    }
}