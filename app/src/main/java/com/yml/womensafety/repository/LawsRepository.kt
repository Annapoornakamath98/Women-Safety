package com.yml.womensafety.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.yml.womensafety.R
import com.yml.womensafety.navigationdrawer.LawsAndEscapeThreat

class LawsRepository(val context: Context) {
    var lawsDataSet = ArrayList<LawsAndEscapeThreat>()
    fun getLaws(): MutableLiveData<List<LawsAndEscapeThreat>> {
        displayLawsRelatedToWomen()

        val lawsData: MutableLiveData<List<LawsAndEscapeThreat>> = MutableLiveData()
        lawsData.value = lawsDataSet
        return lawsData
    }

    private fun displayLawsRelatedToWomen() {
        lawsDataSet.apply {
            add(
                LawsAndEscapeThreat(
                    context.getString(R.string.prohibition_of_child_marriage_act),
                    context.getString(R.string.prohibition_of_child_marriage_act_description)
                )
            )

            add(
                LawsAndEscapeThreat(
                    context.getString(R.string.special_marriage_act),
                    context.getString(R.string.special_marriage_act_description)
                )
            )

            add(
                LawsAndEscapeThreat(
                    context.getString(R.string.dowry_prohibition_act),
                    context.getString(R.string.dowry_prohibition_act_description)
                )
            )

            add(
                LawsAndEscapeThreat(
                    context.getString(R.string.indian_divorce_act),
                    context.getString(R.string.indian_divorce_act_description)
                )
            )

            add(
                LawsAndEscapeThreat(
                    context.getString(R.string.maternity_benefit_act),
                    context.getString(R.string.maternity_benefit_act_description)
                )
            )

            add(
                LawsAndEscapeThreat(
                    context.getString(R.string.medical_termination_of_pregnancy_act),
                    context.getString(R.string.medical_termination_of_pregnancy_act_description)
                )
            )

            add(
                LawsAndEscapeThreat(
                    context.getString(R.string.sexual_harassment_act),
                    context.getString(R.string.sexual_harassment_act_description)
                )
            )

            add(
                LawsAndEscapeThreat(
                    context.getString(R.string.indecent_representation_act),
                    context.getString(R.string.indecent_representation_act_description)
                )
            )

            add(
                LawsAndEscapeThreat(
                    context.getString(R.string.national_commission_act),
                    context.getString(R.string.national_commission_act_description)
                )
            )

            add(
                LawsAndEscapeThreat(
                    context.getString(R.string.equal_remuneration_act),
                    context.getString(R.string.equal_remuneration_act_description)
                )
            )
        }
    }
}