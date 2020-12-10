package com.yml.womensafety.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.yml.womensafety.R
import com.yml.womensafety.navigationdrawer.home.HomePageActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        btnLogin.setOnClickListener {
            login()
        }

        registerHere.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun login() {
        if (loginEmailId.text.toString().isEmpty()) {
            loginEmailId.error = "This field can't be empty!"
            loginEmailId.requestFocus()
            return
        }
        if (loginPassword.text.toString().isEmpty()) {
            loginPassword.error = "Please enter the password"
            loginPassword.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(loginEmailId.text.toString()).matches()) {
            loginEmailId.error = "Please enter a valid email id"
            loginEmailId.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(loginEmailId.text.toString(), loginPassword.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, HomePageActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Login failed", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            startActivity(Intent(this, HomePageActivity::class.java))
        }
    }
}