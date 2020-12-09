package com.yml.womensafety.navigationdrawer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yml.womensafety.Laws
import com.yml.womensafety.LawsAdapter
import com.yml.womensafety.R

class LawsFragment : Fragment(R.layout.fragment_laws) {
    private val lawList = ArrayList<Laws>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        val lawAdapter = LawsAdapter(lawList)
        displayLawsRelatedToWomen()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = lawAdapter
        }
    }

    private fun displayLawsRelatedToWomen() {
        lawList.apply {
            add(
                Laws(
                    getString(R.string.prohibition_of_child_marriage_act),
                    getString(R.string.prohibition_of_child_marriage_act_description)
                )
            )

            add(
                Laws(
                    getString(R.string.special_marriage_act),
                    getString(R.string.special_marriage_act_description)
                )
            )

            add(
                Laws(
                    getString(R.string.dowry_prohibition_act),
                    getString(R.string.dowry_prohibition_act_description)
                )
            )

            add(
                Laws(
                    getString(R.string.indian_divorce_act),
                    getString(R.string.indian_divorce_act_description)
                )
            )

            add(
                Laws(
                    getString(R.string.maternity_benefit_act),
                    getString(R.string.maternity_benefit_act_description)
                )
            )

            add(
                Laws(
                    getString(R.string.medical_termination_of_pregnancy_act),
                    getString(R.string.medical_termination_of_pregnancy_act_description)
                )
            )

            add(
                Laws(
                    getString(R.string.sexual_harassment_act),
                    getString(R.string.sexual_harassment_act_description)
                )
            )

            add(
                Laws(
                    getString(R.string.indecent_representation_act),
                    getString(R.string.indecent_representation_act_description)
                )
            )

            add(
                Laws(
                    getString(R.string.national_commission_act),
                    getString(R.string.national_commission_act_description)
                )
            )

            add(
                Laws(
                    getString(R.string.equal_remuneration_act),
                    getString(R.string.equal_remuneration_act_description)
                )
            )
        }
    }
}