package com.yml.womensafety.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.yml.womensafety.R
import com.yml.womensafety.navigationdrawer.SafetyTips

class SafetyTipsRepository(val context: Context) {
    private val tipsDataSet = ArrayList<SafetyTips>()

    fun getSafetyTips(): MutableLiveData<List<SafetyTips>> {
        displaySafetyTips()
        val tipsData: MutableLiveData<List<SafetyTips>> = MutableLiveData()
        tipsData.value = tipsDataSet
        return tipsData
    }

    private fun displaySafetyTips() {
        tipsDataSet.apply {
            add(SafetyTips(context.getString(R.string.safety_tip_1)))
            add(SafetyTips(context.getString(R.string.safety_tip_2)))
            add(SafetyTips(context.getString(R.string.safety_tip_3)))
            add(SafetyTips(context.getString(R.string.safety_tip_4)))
            add(SafetyTips(context.getString(R.string.safety_tip_5)))
            add(SafetyTips(context.getString(R.string.safety_tip_6)))
            add(SafetyTips(context.getString(R.string.safety_tip_7)))
            add(SafetyTips(context.getString(R.string.safety_tip_8)))
            add(SafetyTips(context.getString(R.string.safety_tip_9)))
            add(SafetyTips(context.getString(R.string.safety_tip_10)))
            add(SafetyTips(context.getString(R.string.safety_tip_11)))
            add(SafetyTips(context.getString(R.string.safety_tip_12)))
            add(SafetyTips(context.getString(R.string.safety_tip_13)))
            add(SafetyTips(context.getString(R.string.safety_tip_14)))
        }
    }
}