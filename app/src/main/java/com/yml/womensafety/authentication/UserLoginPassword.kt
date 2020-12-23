package com.yml.womensafety.authentication

import android.content.Context
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.yml.womensafety.FirebaseUtil
import com.yml.womensafety.alert

class UserLoginPassword {
    companion object {
        private const val DATABASE_NAME = "name"
        private const val FULL_NAME = "fullName"
        private const val PHONE_NUMBER = "phoneNumber"
        private const val REGISTRATION_FAIL = "Registration failed"
        private const val REGISTRATION_SUCCESS = "Registration successful"
        private const val ALERT_CHECK_MAIL = "Please check your mail."
        private const val ALERT_OK = "OK"
    }

    private val appUser = FirebaseUtil.user
    private var databaseReference: DatabaseReference? = null
    fun forgotPassword(userMailText: String) {
        appUser?.sendPasswordResetEmail(userMailText)
    }

    fun userRegistration(context: Context, registerEmailId: String, registerPassword: String, registerFullName: String, registerPhone: String) {
        databaseReference =
                FirebaseUtil.firebaseDatabase?.getReference(DATABASE_NAME)
        appUser?.createUserWithEmailAndPassword(registerEmailId, registerPassword)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val appCurrentUser = FirebaseUtil.user?.currentUser
                appCurrentUser?.sendEmailVerification()?.addOnCompleteListener { taskEmail ->
                    if (taskEmail.isSuccessful) {
                        val userName = appCurrentUser.uid.let { databaseReference?.child(it) }
                        userName?.child(FULL_NAME)
                                ?.setValue(registerFullName)
                        userName?.child(PHONE_NUMBER)
                                ?.setValue(registerPhone)
                        alert(
                                context,
                                REGISTRATION_SUCCESS,
                                ALERT_CHECK_MAIL,
                                ALERT_OK
                        )

                    }

                }
            } else {
                Toast.makeText(context, REGISTRATION_FAIL, Toast.LENGTH_LONG).show()
            }
        }
    }


}