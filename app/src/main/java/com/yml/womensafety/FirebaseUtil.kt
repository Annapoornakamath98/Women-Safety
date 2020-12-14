package com.yml.womensafety

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

object FirebaseUtil {
     var firebaseDatabase: FirebaseDatabase ?= null
     var user: FirebaseAuth ?= null
    fun initializeFirebase() {
        user = FirebaseAuth.getInstance()
    }
    fun initializeDatabase() {
        firebaseDatabase = FirebaseDatabase.getInstance()
    }
}