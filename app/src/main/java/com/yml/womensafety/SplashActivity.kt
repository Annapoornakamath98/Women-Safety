package com.yml.womensafety

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.cuberto.liquid_swipe.LiquidPager
import com.yml.womensafety.adapters.ScreenSlidePagerAdapter
import com.yml.womensafety.navigationdrawer.home.IntroductionFragmentContacts
import com.yml.womensafety.navigationdrawer.home.IntroductionFragmentLocation
import com.yml.womensafety.navigationdrawer.home.IntroductionFragmentTips
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    companion object {
        const val NUM_PAGES = 3
        private const val SPLASH_IMAGE_TRANSLATION_Y = -2400f
        private const val ANIMATION_DURATION: Long = 1000
        private const val ANIMATION_DELAY: Long = 3000
        private const val ANIMATION_TRANSLATION_Y = 2400f
        const val INTRODUCTION_FRAGMENT_ONE = 0
        const val INTRODUCTION_FRAGMENT_TWO = 1
        const val INTRODUCTION_FRAGMENT_THREE = 2
    }

    private lateinit var viewPager: LiquidPager
    lateinit var pagerAdapter: ScreenSlidePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewPager = findViewById(R.id.pager)
        pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter

        splashImage.animate().translationY(SPLASH_IMAGE_TRANSLATION_Y)
            .setDuration(ANIMATION_DURATION)
            .startDelay = ANIMATION_DELAY
        appName.animate().translationY(ANIMATION_TRANSLATION_Y)
            .setDuration(ANIMATION_DURATION)
            .startDelay = ANIMATION_DELAY
        animationView.animate().translationY(ANIMATION_TRANSLATION_Y)
            .setDuration(ANIMATION_DURATION)
            .startDelay = ANIMATION_DELAY
    }


}