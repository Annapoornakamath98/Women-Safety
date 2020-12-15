package com.yml.womensafety.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import com.yml.womensafety.FirebaseUtil
import com.yml.womensafety.R
import com.yml.womensafety.navigationdrawer.home.HomePageActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private var appUser: FirebaseUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        appUser = FirebaseUtil.user?.currentUser
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
        val currentUser = appUser
        updateUI(currentUser)
    }

    private fun login() {
        if (loginEmailId.text.toString().isEmpty()) {
            loginEmailId.error = getString(R.string.please_enter_email_id)
            loginEmailId.requestFocus()
            return
        }
        if (loginPassword.text.toString().isEmpty()) {
            loginPassword.error = getString(R.string.please_enter_the_password)
            loginPassword.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(loginEmailId.text.toString()).matches()) {
            loginEmailId.error = getString(R.string.please_enter_a_valid_email_id)
            loginEmailId.requestFocus()
            return
        }
        FirebaseUtil.user?.signInWithEmailAndPassword(
            loginEmailId.text.toString(),
            loginPassword.text.toString()
        )
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, HomePageActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.login_fail),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            startActivity(Intent(this, HomePageActivity::class.java))
        }
    }
}