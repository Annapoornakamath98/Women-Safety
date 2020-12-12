package com.yml.womensafety.navigationdrawer

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.yml.womensafety.FirebaseApplication
import com.yml.womensafety.R
import kotlinx.android.synthetic.main.fragment_user_profile.*

class UserProfile : Fragment(R.layout.fragment_user_profile) {
    private lateinit var firebaseApplication: FirebaseApplication
    private var databaseReference: DatabaseReference? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseApplication = FirebaseApplication()
        databaseReference = firebaseApplication.db.getReference("name")
        val user = firebaseApplication.u.currentUser
        val userReference = databaseReference!!.child(user?.uid!!)
        userReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userFullName = snapshot.child("fullName").value.toString()
                val userName = "Hi, $userFullName"
                tvUserProfile.text = userName
                tvUserProfileName.text = userFullName
                val userContactNumber = snapshot.child("phoneNumber").value.toString()
                tvUserProfileMobileNumber.text = userContactNumber
                val userEmail = user.email.toString()
                tvUserProfileMail.text = userEmail
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(view.context, error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}