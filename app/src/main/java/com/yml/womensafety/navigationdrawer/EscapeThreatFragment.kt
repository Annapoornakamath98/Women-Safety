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
import com.yml.womensafety.viewModel.EscapeThreatViewModel


class EscapeThreatFragment : Fragment(R.layout.fragment_escape_threat) {

    private lateinit var escapeThreatRecyclerView: RecyclerView
    lateinit var escapeThreatViewModel: EscapeThreatViewModel
    private lateinit var escapeThreatAdapter: LawsAndEscapeThreatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        escapeThreatViewModel =
            ViewModelProvider(this).get(EscapeThreatViewModel(Application())::class.java)
        escapeThreatViewModel.initializeRepository()
        escapeThreatViewModel.getEscapeThreatData().observe(viewLifecycleOwner, {
            escapeThreatAdapter.notifyDataSetChanged()
        })
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        escapeThreatAdapter =
            LawsAndEscapeThreatAdapter(escapeThreatViewModel.getEscapeThreatData().value!! as ArrayList<LawsAndEscapeThreat>)
        escapeThreatRecyclerView = view?.findViewById(R.id.recyclerViewEscapeThreat)!!
        escapeThreatRecyclerView.layoutManager = LinearLayoutManager(view?.context)
        escapeThreatRecyclerView.adapter = escapeThreatAdapter
    }


}