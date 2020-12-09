package com.yml.womensafety.navigationdrawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yml.womensafety.SafetyTipsAdapter
import com.yml.womensafety.R

class SafetyTipsFragment : Fragment(R.layout.fragment_safety_tips) {

    private val tipsTextList = ArrayList<SafetyTips>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displaySafetyTips()
        val recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        var adapter = SafetyTipsAdapter(tipsTextList)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(view.context)
            recyclerView.adapter = adapter
        }
    }

    private fun displaySafetyTips() {
        tipsTextList.apply {
            add(SafetyTips(getString(R.string.safety_tip_1)))
            add(SafetyTips(getString(R.string.safety_tip_2)))
            add(SafetyTips(getString(R.string.safety_tip_3)))
            add(SafetyTips(getString(R.string.safety_tip_4)))
            add(SafetyTips(getString(R.string.safety_tip_5)))
            add(SafetyTips(getString(R.string.safety_tip_6)))
            add(SafetyTips(getString(R.string.safety_tip_7)))
            add(SafetyTips(getString(R.string.safety_tip_8)))
            add(SafetyTips(getString(R.string.safety_tip_9)))
            add(SafetyTips(getString(R.string.safety_tip_10)))
            add(SafetyTips(getString(R.string.safety_tip_11)))
            add(SafetyTips(getString(R.string.safety_tip_12)))
            add(SafetyTips(getString(R.string.safety_tip_13)))
            add(SafetyTips(getString(R.string.safety_tip_14)))
        }
    }

}