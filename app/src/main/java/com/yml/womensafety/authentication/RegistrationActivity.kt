package com.yml.womensafety.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.yml.womensafety.R
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private var databaseReference: DatabaseReference? = null
    private var firebaseDatabase: FirebaseDatabase? = null
    private lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("name")
        btnRegister.setOnClickListener {
            register()
        }
        goToLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }

    private fun register() {
        if (registerEmailId.text.toString().isEmpty()) {
            registerEmailId.error = "Please enter email id"
            registerEmailId.requestFocus()
            return
        }
        if (registerPassword.text.toString().isEmpty()) {
            registerPassword.error = "Please enter the password"
            registerPassword.requestFocus()
            return
        }
        if (registerFullName.text.toString().isEmpty()) {
            registerFullName.error = "Please enter the password"
            registerFullName.requestFocus()
            return
        }
        if (registerPhone.text.toString().isEmpty()){
            registerPhone.error = "Please enter your phone number"
            registerPhone.requestFocus()
            return
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(registerEmailId.text.toString()).matches()) {
            registerEmailId.error = "Please enter a valid email id"
            registerEmailId.requestFocus()
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(
            registerEmailId.text.toString(),
            registerPassword.text.toString()
        )
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    val userName= databaseReference?.child((user?.uid!!))
                    //val userName= databaseReference?.child((user?.uid!!))
                    userName?.child("fullName")?.setValue(registerFullName.text.toString())
                    userName?.child("phoneNumber")?.setValue(registerPhone.text.toString())
                    Toast.makeText(applicationContext, "Registration success", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(applicationContext, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Registration failed", Toast.LENGTH_SHORT)
                        .show()
                }

            }

    }
}