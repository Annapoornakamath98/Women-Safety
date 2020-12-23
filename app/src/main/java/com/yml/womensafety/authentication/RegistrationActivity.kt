package com.yml.womensafety.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.DatabaseReference
import com.yml.womensafety.FirebaseUtil
import com.yml.womensafety.R
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    private lateinit var loginPasswordViewModel: LoginPasswordViewModel
    private var databaseReference: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        loginPasswordViewModel = ViewModelProvider(this).get(LoginPasswordViewModel()::class.java)
        loginPasswordViewModel.initializeUserLoginRepository()
        databaseReference =
                FirebaseUtil.firebaseDatabase?.getReference(getString(R.string.name_column))
        btnRegister.setOnClickListener {
            register()
        }
        goToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun register() {
        if (registerEmailId.text.toString().isEmpty()) {
            registerEmailId.error = getString(R.string.please_enter_email_id)
            registerEmailId.requestFocus()
            return
        }
        if (registerPassword.text.toString().isEmpty()) {
            registerPassword.error = getString(R.string.please_enter_the_password)
            registerPassword.requestFocus()
            return
        }
        if (registerFullName.text.toString().isEmpty()) {
            registerFullName.error = getString(R.string.please_enter_the_password)
            registerFullName.requestFocus()
            return
        }
        if (registerPhone.text.toString().isEmpty()) {
            registerPhone.error = getString(R.string.please_enter_your_phone_number)
            registerPhone.requestFocus()
            return
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(registerEmailId.text.toString()).matches()) {
            registerEmailId.error = getString(R.string.please_enter_a_valid_email_id)
            registerEmailId.requestFocus()
            return
        }
        loginPasswordViewModel.registerUser(this, registerEmailId.text.toString(), registerPassword.text.toString(),
                registerFullName.text.toString(), registerPhone.text.toString())

    }
}