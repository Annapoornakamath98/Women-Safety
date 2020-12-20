package com.yml.womensafety.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.yml.womensafety.FirebaseUtil
import com.yml.womensafety.Response

class FirebaseRepository {
    private val databaseName = "name"
    private var databaseReference: DatabaseReference? =
        FirebaseUtil.firebaseDatabase?.reference?.child(databaseName)
    private val appUser = FirebaseUtil.user?.currentUser
    private val userReference = databaseReference?.child(appUser?.uid!!)
    fun getUserName(response: Response) {
        databaseReference?.keepSynced(true)
        userReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userName =
                    snapshot.child("fullName").value as String
                response.onUserDetailReceiveSuccess(userName).toString()
            }

            override fun onCancelled(error: DatabaseError) {
                //Nothing to be implemented here
            }
        })
    }

    fun getUserEmail(response: Response) {
        val userEmail = appUser?.email.toString()
        response.onUserDetailReceiveSuccess(userEmail)
    }

    fun getUserContact(response: Response) {
        databaseReference?.keepSynced(true)
        userReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userContactNumber =
                    snapshot.child("phoneNumber").value as String
                response.onUserDetailReceiveSuccess(userContactNumber).toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}