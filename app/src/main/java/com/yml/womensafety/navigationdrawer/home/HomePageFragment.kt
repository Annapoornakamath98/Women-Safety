package com.yml.womensafety.navigationdrawer.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.yml.womensafety.FirebaseApplication
import com.yml.womensafety.R
import com.yml.womensafety.navigationdrawer.EscapeThreatFragment
import com.yml.womensafety.navigationdrawer.LawsFragment
import com.yml.womensafety.navigationdrawer.SafetyTipsFragment
import com.yml.womensafety.navigationdrawer.youtube.SelfDefenseVideoFragment
import kotlinx.android.synthetic.main.fragment_home_page.*

class HomePageFragment : Fragment(R.layout.fragment_home_page) {
    private lateinit var tipsForWomenSafety: SafetyTipsFragment
    private lateinit var selfDefenseVideoFragment: SelfDefenseVideoFragment
    private lateinit var tipsToEscapeFragment: EscapeThreatFragment
    private lateinit var lawsForWomenFragment: LawsFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firebaseApplication = FirebaseApplication()
        val user = firebaseApplication.u.currentUser
        val databaseReference = firebaseApplication.db.reference.child("name")
        val userReference = databaseReference.child(user?.uid!!)
        userReference.addValueEventListener(object : ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                val userFullName = snapshot.child("fullName").value.toString()
                tvLabel.text = "Hi, $userFullName"
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
            view.findNavController().navigate(R.id.action_homePageFragment_to_selfDefenseVideoFragment)
        }
        cvLaws.setOnClickListener {
            view.findNavController().navigate(R.id.action_homePageFragment_to_lawsFragment)
        }
    }


}