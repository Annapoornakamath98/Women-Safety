package com.yml.womensafety

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

object FragmentUtil {
    fun addFragmentToActivity(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int) {
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment).commit()
    }
}