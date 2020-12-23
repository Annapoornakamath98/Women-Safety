package com.yml.womensafety

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

object FragmentUtil {
    fun replaceFragment(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int) {
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment).commit()
    }

}