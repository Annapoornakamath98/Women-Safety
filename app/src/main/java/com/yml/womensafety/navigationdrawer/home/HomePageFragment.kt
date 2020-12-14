package com.yml.womensafety.navigationdrawer.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.yml.womensafety.FirebaseUtil
import com.yml.womensafety.R
import kotlinx.android.synthetic.main.fragment_home_page.*

class HomePageFragment : Fragment(R.layout.fragment_home_page) {
    private var databaseReference: DatabaseReference? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appUser = FirebaseUtil.user?.currentUser
        databaseReference =
            FirebaseUtil.firebaseDatabase?.reference?.child(getString(R.string.name_column))
        val userReference = databaseReference?.child(appUser?.uid!!)
        userReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userFullName =
                    snapshot.child(getString(R.string.full_name_column)).value.toString()
                val userName = getString(R.string.hi_user) + userFullName
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
    }
}