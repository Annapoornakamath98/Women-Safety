package com.yml.womensafety.repository

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
            add(SafetyTips(R.string.safety_tip_title_1,R.string.safety_tip_1))
            add(SafetyTips(R.string.safety_tip_title_2,R.string.safety_tip_2))
            add(SafetyTips(R.string.safety_tip_title_3,R.string.safety_tip_3))
            add(SafetyTips(R.string.safety_tip_title_4,R.string.safety_tip_4))
            add(SafetyTips(R.string.safety_tip_title_5,R.string.safety_tip_5))
            add(SafetyTips(R.string.safety_tip_title_6,R.string.safety_tip_6))
            add(SafetyTips(R.string.safety_tip_title_7,R.string.safety_tip_7))
            add(SafetyTips(R.string.safety_tip_title_8,R.string.safety_tip_8))
            add(SafetyTips(R.string.safety_tip_title_9,R.string.safety_tip_9))
            add(SafetyTips(R.string.safety_tip_title_10,R.string.safety_tip_10))
            add(SafetyTips(R.string.safety_tip_title_11,R.string.safety_tip_11))
            add(SafetyTips(R.string.safety_tip_title_12,R.string.safety_tip_12))
            add(SafetyTips(R.string.safety_tip_title_13,R.string.safety_tip_13))
            add(SafetyTips(R.string.safety_tip_title_14,R.string.safety_tip_14))
        }
    }
}