package com.yml.womensafety.repository

import com.yml.womensafety.R
import com.yml.womensafety.navigationdrawer.LawsAndEscapeThreat

class EscapeThreatRepository() {
    private var escapeThreatDataSet = ArrayList<LawsAndEscapeThreat>()

    fun getEscapeThreatData(): ArrayList<LawsAndEscapeThreat> {
        displaySituationsAndSolutions()
        return escapeThreatDataSet
    }

    private fun displaySituationsAndSolutions() {
        escapeThreatDataSet.apply {
            add(
                LawsAndEscapeThreat(
                    R.string.situation_1,
                    R.string.situation_1_solution
                )
            )
            add(
                LawsAndEscapeThreat(
                    R.string.situation_2,
                    R.string.situation_2_solution
                )
            )
            add(
                LawsAndEscapeThreat(
                    R.string.situation_3,
                    R.string.situation_3_solution
                )
            )
            add(
                LawsAndEscapeThreat(
                    R.string.situation_4,
                    R.string.situation_4_solution
                )
            )
            add(
                LawsAndEscapeThreat(
                    R.string.situation_5,
                    R.string.situation_5_solution
                )
            )
            add(
                LawsAndEscapeThreat(
                    R.string.situation_6,
                    R.string.situation_6_solution
                )
            )
        }
    }
}