package com.yml.womensafety.navigationdrawer.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.yml.womensafety.FirebaseApplication
import com.yml.womensafety.R
import kotlinx.android.synthetic.main.fragment_home_page.*

class HomePageFragment : Fragment(R.layout.fragment_home_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firebaseApplication = FirebaseApplication()
        val user = firebaseApplication.u.currentUser
        val databaseReference = firebaseApplication.db.reference.child("name")
        val userReference = databaseReference.child(user?.uid!!)
        userReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userFullName = snapshot.child("fullName").value.toString()
                val userName = "Hi, $userFullName"
                tvLabel.text = userName
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
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
        cvHelplineNumbers.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_homePageFragment_to_helplineNumbersFragment)
        }
    }
}