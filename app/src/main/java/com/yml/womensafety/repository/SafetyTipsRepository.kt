package com.yml.womensafety.repository

import android.content.Context
import com.yml.womensafety.R
import com.yml.womensafety.navigationdrawer.SafetyTips

class SafetyTipsRepository() {
    private val tipsDataSet = ArrayList<SafetyTips>()

    fun getSafetyTips(): ArrayList<SafetyTips> {
        displaySafetyTips()
        return tipsDataSet
    }

    private fun displaySafetyTips() {
        tipsDataSet.apply {
            add(SafetyTips(R.string.safety_tip_1))
            add(SafetyTips(R.string.safety_tip_2))
            add(SafetyTips(R.string.safety_tip_3))
            add(SafetyTips(R.string.safety_tip_4))
            add(SafetyTips(R.string.safety_tip_5))
            add(SafetyTips(R.string.safety_tip_6))
            add(SafetyTips(R.string.safety_tip_7))
            add(SafetyTips(R.string.safety_tip_8))
            add(SafetyTips(R.string.safety_tip_9))
            add(SafetyTips(R.string.safety_tip_10))
            add(SafetyTips(R.string.safety_tip_11))
            add(SafetyTips(R.string.safety_tip_12))
            add(SafetyTips(R.string.safety_tip_13))
            add(SafetyTips(R.string.safety_tip_14))
        }
    }
}