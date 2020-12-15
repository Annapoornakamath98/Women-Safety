package com.yml.womensafety.navigationdrawer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yml.womensafety.R
import com.yml.womensafety.Response
import com.yml.womensafety.viewModel.FirebaseViewModel
import kotlinx.android.synthetic.main.fragment_user_profile.*

class UserProfile : Fragment(R.layout.fragment_user_profile) {
    lateinit var firebaseViewModel: FirebaseViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseViewModel = ViewModelProvider(this).get(FirebaseViewModel()::class.java)
        firebaseViewModel.initializeRepository()
        firebaseViewModel.getUserName(object : Response {
            override fun onUserDetailReceiveSuccess(userDetails: String) {
                tvUserProfile.text = getString(R.string.hi_user) + userDetails
                tvUserProfileName.text = userDetails
            }
        })
        firebaseViewModel.getUserEmail(object : Response {
            override fun onUserDetailReceiveSuccess(userDetails: String) {
                tvUserProfileMail.text = userDetails
            }
        })
        firebaseViewModel.getUserContact(object : Response {
            override fun onUserDetailReceiveSuccess(userDetails: String) {
                tvUserProfileMobileNumber.text = userDetails
            }

        })
    }
}