package com.yml.womensafety.navigationdrawer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yml.womensafety.LawsAndEscapeThreatAdapter
import com.yml.womensafety.R


class EscapeThreatFragment : Fragment(R.layout.fragment_escape_threat) {
    private val situationList = ArrayList<LawsAndEscapeThreat>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById(R.id.recyclerViewEscapeThreat) as RecyclerView
        val situationAdapter = LawsAndEscapeThreatAdapter(situationList)
        displaySituationsAndSolutions()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = situationAdapter
        }
    }

    private fun displaySituationsAndSolutions() {
        situationList.apply {
            add(
                LawsAndEscapeThreat(
                    getString(R.string.situation_1),
                    getString(R.string.situation_1_solution)
                )
            )
            add(
                LawsAndEscapeThreat(
                    getString(R.string.situation_2),
                    getString(R.string.situation_2_solution)
                )
            )
            add(
                LawsAndEscapeThreat(
                    getString(R.string.situation_3),
                    getString(R.string.situation_3_solution)
                )
            )
            add(
                LawsAndEscapeThreat(
                    getString(R.string.situation_4),
                    getString(R.string.situation_4_solution)
                )
            )
            add(
                LawsAndEscapeThreat(
                    getString(R.string.situation_5),
                    getString(R.string.situation_5_solution)
                )
            )
            add(
                LawsAndEscapeThreat(
                    getString(R.string.situation_6),
                    getString(R.string.situation_6_solution)
                )
            )
        }
    }
}