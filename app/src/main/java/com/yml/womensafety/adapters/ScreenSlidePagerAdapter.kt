package com.yml.womensafety.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.yml.womensafety.SplashActivity
import com.yml.womensafety.navigationdrawer.home.IntroductionFragmentContacts
import com.yml.womensafety.navigationdrawer.home.IntroductionFragmentLocation
import com.yml.womensafety.navigationdrawer.home.IntroductionFragmentTips

class ScreenSlidePagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            SplashActivity.INTRODUCTION_FRAGMENT_ONE ->
                return IntroductionFragmentContacts()
            SplashActivity.INTRODUCTION_FRAGMENT_TWO ->
                return IntroductionFragmentLocation()
            SplashActivity.INTRODUCTION_FRAGMENT_THREE ->
                return IntroductionFragmentTips()
        }
        return IntroductionFragmentContacts()
    }

    override fun getCount(): Int {
        return SplashActivity.NUM_PAGES
    }
}