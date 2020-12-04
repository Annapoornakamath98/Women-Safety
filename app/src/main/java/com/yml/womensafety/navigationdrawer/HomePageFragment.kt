package com.yml.womensafety.navigationdrawer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.yml.womensafety.R
import kotlinx.android.synthetic.main.fragment_home_page.*

class HomePageFragment : Fragment(R.layout.fragment_home_page) {
    private lateinit var auth: FirebaseAuth
    private var databaseReference: DatabaseReference? = null
    private var firebaseDatabase: FirebaseDatabase? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase!!.reference.child("name")
        val user = auth.currentUser
        val userEmail = user?.email.toString()
        val userReference = databaseReference?.child(user?.uid!!)
        userReference?.addValueEventListener(object : ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                val userFullName = snapshot.child("fullName").value.toString()
                //Toast.makeText(view.context,"Welcome $userFullName",Toast.LENGTH_LONG).show()
                Snackbar.make(view,"Welcome $userFullName",Snackbar.LENGTH_LONG).show()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }


}