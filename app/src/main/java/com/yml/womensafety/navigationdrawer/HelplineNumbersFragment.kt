package com.yml.womensafety.navigationdrawer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yml.womensafety.R
import com.yml.womensafety.adapters.HelplineNumbersAdapter
import com.yml.womensafety.viewModel.HelplineNumbersViewModel

class HelplineNumbersFragment : Fragment(R.layout.fragment_helpline_numbers) {
    private lateinit var numbersRecyclerView: RecyclerView
    lateinit var helplineNumbersViewModel: HelplineNumbersViewModel
    private lateinit var helplineNumbersAdapter: HelplineNumbersAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        helplineNumbersViewModel =
            ViewModelProvider(this).get(HelplineNumbersViewModel()::class.java)
        helplineNumbersViewModel.initializeRepository()
        helplineNumbersViewModel.getNumbers().observe(viewLifecycleOwner, {
            helplineNumbersAdapter.notifyDataSetChanged()
        })
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        helplineNumbersAdapter =
            HelplineNumbersAdapter(helplineNumbersViewModel.getNumbers().value!! as ArrayList<HelplineNumbers>)
        numbersRecyclerView = view?.findViewById(R.id.recyclerViewHelplineNumbers)!!
        numbersRecyclerView.layoutManager = LinearLayoutManager(view?.context)
        numbersRecyclerView.adapter = helplineNumbersAdapter
    }

}