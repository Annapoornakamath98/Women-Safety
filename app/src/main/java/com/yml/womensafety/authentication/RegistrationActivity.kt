package com.yml.womensafety.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.yml.womensafety.Alert.alert
import com.yml.womensafety.FirebaseUtil
import com.yml.womensafety.R
import kotlinx.android.synthetic.main.activity_registration.btnRegister
import kotlinx.android.synthetic.main.activity_registration.goToLogin
import kotlinx.android.synthetic.main.activity_registration.registerEmailId
import kotlinx.android.synthetic.main.activity_registration.registerFullName
import kotlinx.android.synthetic.main.activity_registration.registerPassword
import kotlinx.android.synthetic.main.activity_registration.registerPhone
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    private var databaseReference: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        regTabLayout.addTab(regTabLayout.newTab().setText(getString(R.string.register)))
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

        FirebaseUtil.user?.createUserWithEmailAndPassword(
            registerEmailId.text.toString(),
            registerPassword.text.toString()
        )
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val appUser = FirebaseUtil.user?.currentUser
                    appUser?.sendEmailVerification()?.addOnCompleteListener { taskEmail ->
                        if (taskEmail.isSuccessful) {
                            val userName = appUser.uid.let { databaseReference?.child(it) }
                            userName?.child(getString(R.string.full_name_column))
                                ?.setValue(registerFullName.text.toString())
                            userName?.child(getString(R.string.user_phone_number))
                                ?.setValue(registerPhone.text.toString())
                            alert(
                                this,
                                getString(R.string.reg_success),
                                getString(R.string.alert_check_mail),
                                getString(R.string.alert_ok)
                            )
                        }
                    }

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