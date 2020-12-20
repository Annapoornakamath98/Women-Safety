package com.yml.womensafety.navigationdrawer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yml.womensafety.R
import com.yml.womensafety.adapters.SafetyTipsAdapter
import com.yml.womensafety.viewModel.SafetyTipsViewModel

class SafetyTipsFragment : Fragment(R.layout.fragment_safety_tips) {

    private lateinit var tipsRecyclerView: RecyclerView
    lateinit var safetyTipsViewModel: SafetyTipsViewModel
    private lateinit var tipsAdapter: SafetyTipsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        safetyTipsViewModel =
            ViewModelProvider(this).get(SafetyTipsViewModel()::class.java)
        safetyTipsViewModel.initializeRepository()
        tipsRecyclerView = view.findViewById(R.id.recyclerViewSafetyTips)
        tipsAdapter = SafetyTipsAdapter()
        tipsRecyclerView.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = tipsAdapter
        }
        safetyTipsViewModel.getSafetyTips().observe(viewLifecycleOwner, {
            tipsAdapter.setList(it)
        })
    }
}