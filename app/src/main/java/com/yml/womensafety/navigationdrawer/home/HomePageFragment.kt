package com.yml.womensafety.navigationdrawer.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.yml.womensafety.R
import com.yml.womensafety.Response
import com.yml.womensafety.viewModel.FirebaseViewModel
import kotlinx.android.synthetic.main.fragment_home_page.*

class HomePageFragment : Fragment(R.layout.fragment_home_page) {
    lateinit var firebaseViewModel: FirebaseViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseViewModel = ViewModelProvider(this).get(FirebaseViewModel()::class.java)
        firebaseViewModel.initializeRepository()
        firebaseViewModel.getUserName(object : Response {
            override fun onUserDetailReceiveSuccess(userDetails: String) {
                tvLabel.text = getString(R.string.hi_user) + userDetails
            }

        })
        cvEscapeThreat.setOnClickListener {
            view.findNavController().navigate(R.id.action_homePageFragment_to_escapeThreatFragment)
        }
        cvTipsForWomenSafety.setOnClickListener {
            view.findNavController().navigate(R.id.action_homePageFragment_to_safetyTipsFragment)
        }
        cvVideosForSelfDefense.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_homePageFragment_to_selfDefenseVideoFragment)
        }
        cvLaws.setOnClickListener {
            view.findNavController().navigate(R.id.action_homePageFragment_to_lawsFragment)
        }
    }
}