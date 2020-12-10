package com.yml.womensafety.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.yml.womensafety.R
import com.yml.womensafety.navigationdrawer.LawsAndEscapeThreat

class EscapeThreatRepository(val context: Context) {
    private var escapeThreatDataSet = ArrayList<LawsAndEscapeThreat>()

    fun getEscapeThreatData(): MutableLiveData<List<LawsAndEscapeThreat>> {
        displaySituationsAndSolutions()
        val escapeThreatData: MutableLiveData<List<LawsAndEscapeThreat>> = MutableLiveData()
        escapeThreatData.value = escapeThreatDataSet
        return escapeThreatData
    }

    private fun displaySituationsAndSolutions() {
        escapeThreatDataSet.apply {
            add(
                LawsAndEscapeThreat(
                    context.getString(R.string.situation_1),
                    context.getString(R.string.situation_1_solution)
                )
            )
            add(
                LawsAndEscapeThreat(
                    context.getString(R.string.situation_2),
                    context.getString(R.string.situation_2_solution)
                )
            )
            add(
                LawsAndEscapeThreat(
                    context.getString(R.string.situation_3),
                    context.getString(R.string.situation_3_solution)
                )
            )
            add(
                LawsAndEscapeThreat(
                    context.getString(R.string.situation_4),
                    context.getString(R.string.situation_4_solution)
                )
            )
            add(
                LawsAndEscapeThreat(
                    context.getString(R.string.situation_5),
                    context.getString(R.string.situation_5_solution)
                )
            )
            add(
                LawsAndEscapeThreat(
                    context.getString(R.string.situation_6),
                    context.getString(R.string.situation_6_solution)
                )
            )
        }
    }
}