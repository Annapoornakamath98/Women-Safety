package com.yml.womensafety.navigationdrawer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.yml.womensafety.R
import kotlinx.android.synthetic.main.activity_header.*

class HeaderActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var databaseReference: DatabaseReference? = null
    private var firebaseDatabase: FirebaseDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_header)
        auth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase!!.reference.child("name")
        val user = auth.currentUser
        val userEmail = user?.email.toString()
        val userReference = databaseReference?.child(user?.uid!!)
        headerEmail.text = userEmail
        userReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userFullName = snapshot.child("fullName").value.toString()
                headerName.text = userFullName
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}