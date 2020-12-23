package com.yml.womensafety.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseUser
import com.yml.womensafety.FirebaseUtil
import com.yml.womensafety.R
import com.yml.womensafety.navigationdrawer.home.HomePageActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var loginPasswordViewModel: LoginPasswordViewModel
    private var appUser: FirebaseUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        appUser = FirebaseUtil.user?.currentUser
        loginPasswordViewModel = ViewModelProvider(this).get(LoginPasswordViewModel()::class.java)
        loginPasswordViewModel.initializeUserLoginRepository()
        btnLogin.setOnClickListener {
            login()
        }
        registerHere.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
            finish()
        }
        btnForgotPassword.setOnClickListener {
            val builder = MaterialAlertDialogBuilder(this)
            val alertView = layoutInflater.inflate(R.layout.dialog_forgot_password, null)
            val userMail = alertView.findViewById<EditText>(R.id.etUserEmail)

            if (userMail.text.toString()
                            .isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(userMail.text.toString())
                            .matches()
            ) {
                userMail.error = getString(R.string.please_enter_email_id)
                userMail.requestFocus()
            }
            builder.apply {
                setTitle(getString(R.string.forgot_password))
                setView(alertView)
                setPositiveButton(getString(R.string.alert_send)) { dialog, which ->
                    loginPasswordViewModel.forgotUserPassword(userMail.text.toString())
                    Toast.makeText(this@LoginActivity, getString(R.string.email_sent), Toast.LENGTH_LONG).show()
                }
                setNegativeButton(getString(R.string.alert_cancel)) { dialog, which ->
                    //Nothing to be done here
                }
                show()
            }
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