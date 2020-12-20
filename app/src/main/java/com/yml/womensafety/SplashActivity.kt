package com.yml.womensafety

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.cuberto.liquid_swipe.LiquidPager
import com.yml.womensafety.navigationdrawer.home.OnBoardingFragment1
import com.yml.womensafety.navigationdrawer.home.OnBoardingFragment2
import com.yml.womensafety.navigationdrawer.home.OnBoardingFragment3
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    companion object {
        private const val NUM_PAGES = 3
        private const val SPLASH_IMAGE_TRANSLATION_Y = -2400f
        private const val ANIMATION_DURATION: Long = 1000
        private const val ANIMATION_DELAY: Long = 3000
        private const val ANIMATION_TRANSLATION_Y = 2400f
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

    inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            when (position) {
                0 ->
                    return OnBoardingFragment1()
                1 ->
                    return OnBoardingFragment2()
                2 ->
                    return OnBoardingFragment3()
            }
            return OnBoardingFragment1()
        }

        override fun getCount(): Int {
            return NUM_PAGES
        }
    }

}