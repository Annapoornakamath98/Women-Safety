package com.yml.womensafety

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FirebaseApplication {
    val u: FirebaseAuth = FirebaseAuth.getInstance()
    val db: FirebaseDatabase = FirebaseDatabase.getInstance()
}