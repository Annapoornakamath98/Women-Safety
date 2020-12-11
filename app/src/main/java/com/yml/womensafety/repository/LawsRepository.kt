package com.yml.womensafety.repository

import android.content.Context
import com.yml.womensafety.R
import com.yml.womensafety.navigationdrawer.LawsAndEscapeThreat

class LawsRepository(val context: Context) {
    var lawsDataSet = ArrayList<LawsAndEscapeThreat>()
    fun getLaws(): ArrayList<LawsAndEscapeThreat> {
        displayLawsRelatedToWomen()
        return lawsDataSet
    }

    private fun displayLawsRelatedToWomen() {
        lawsDataSet.apply {
            add(
                LawsAndEscapeThreat(
                    R.string.prohibition_of_child_marriage_act,
                    R.string.prohibition_of_child_marriage_act_description

                )
            )

            add(
                LawsAndEscapeThreat(
                    R.string.special_marriage_act,
                    R.string.special_marriage_act_description
                )
            )

            add(
                LawsAndEscapeThreat(
                    R.string.dowry_prohibition_act,
                    R.string.dowry_prohibition_act_description
                )
            )

            add(
                LawsAndEscapeThreat(
                    R.string.indian_divorce_act,
                    R.string.indian_divorce_act_description
                )
            )

            add(
                LawsAndEscapeThreat(
                    R.string.maternity_benefit_act,
                    R.string.maternity_benefit_act_description
                )
            )

            add(
                LawsAndEscapeThreat(
                    R.string.medical_termination_of_pregnancy_act,
                    R.string.medical_termination_of_pregnancy_act_description
                )
            )

            add(
                LawsAndEscapeThreat(
                    R.string.sexual_harassment_act,
                    R.string.sexual_harassment_act_description
                )
            )

            add(
                LawsAndEscapeThreat(
                    R.string.indecent_representation_act,
                    R.string.indecent_representation_act_description
                )
            )

            add(
                LawsAndEscapeThreat(
                    R.string.national_commission_act,
                    R.string.national_commission_act_description
                )
            )

            add(
                LawsAndEscapeThreat(
                    R.string.equal_remuneration_act,
                    R.string.equal_remuneration_act_description
                )
            )
        }
    }
}