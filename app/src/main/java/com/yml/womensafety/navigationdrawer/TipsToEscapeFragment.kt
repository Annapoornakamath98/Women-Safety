package com.yml.womensafety.navigationdrawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yml.womensafety.EscapeThreatAdapter
import com.yml.womensafety.R


class TipsToEscapeFragment : Fragment(R.layout.fragment_tips_to_escape) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        val tipsTextList = ArrayList<EscapeThreat>()
        val adapter = EscapeThreatAdapter(tipsTextList)
        recyclerView.adapter = adapter
    }

}