package com.yml.womensafety.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.yml.womensafety.FirebaseApplication
import com.yml.womensafety.R
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    private lateinit var firebaseApplication: FirebaseApplication
    private var databaseReference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        firebaseApplication = FirebaseApplication()
        databaseReference = firebaseApplication.db.getReference("name")
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

        firebaseApplication.u.createUserWithEmailAndPassword(
            registerEmailId.text.toString(),
            registerPassword.text.toString()
        )
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = firebaseApplication.u.currentUser
                    val userName = databaseReference?.child(user?.uid!!)
                    userName?.child("fullName")?.setValue(registerFullName.text.toString())
                    userName?.child("phoneNumber")?.setValue(registerPhone.text.toString())
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.reg_success),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    startActivity(Intent(applicationContext, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.reg_fail),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
    }
}