package com.yml.womensafety.navigationdrawer

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yml.womensafety.R
import com.yml.womensafety.adapters.LawsAndEscapeThreatAdapter
import com.yml.womensafety.viewModel.LawsViewModel

class LawsFragment : Fragment(R.layout.fragment_laws) {
    private lateinit var lawsRecyclerView: RecyclerView
    lateinit var lawsViewModel: LawsViewModel
    private lateinit var lawsAdapter: LawsAndEscapeThreatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lawsViewModel = ViewModelProvider(this).get(LawsViewModel(Application())::class.java)
        lawsViewModel.initializeRepository()
        lawsViewModel.getLaws().observe(viewLifecycleOwner, {
            lawsAdapter.notifyDataSetChanged()
        })
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        lawsAdapter =
            LawsAndEscapeThreatAdapter(lawsViewModel.getLaws().value!! as ArrayList<LawsAndEscapeThreat>)
        lawsRecyclerView = view?.findViewById(R.id.recyclerViewLaws)!!
        lawsRecyclerView.layoutManager = LinearLayoutManager(view?.context)
        lawsRecyclerView.adapter = lawsAdapter
    }
}
