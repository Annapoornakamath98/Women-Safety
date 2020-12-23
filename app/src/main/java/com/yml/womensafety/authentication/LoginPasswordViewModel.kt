package com.yml.womensafety.authentication

import android.content.Context
import androidx.lifecycle.ViewModel

class LoginPasswordViewModel : ViewModel() {
    private lateinit var userLoginPassword: UserLoginPassword
    fun initializeUserLoginRepository() {
        userLoginPassword = UserLoginPassword()
    }

    fun forgotUserPassword(userEmailId: String) {
        userLoginPassword.forgotPassword(userEmailId)
    }

    fun registerUser(context: Context, registerEmailId: String, registerPassword: String, registerFullName: String, registerPhone: String) {
        userLoginPassword.userRegistration(context, registerEmailId, registerPassword, registerFullName, registerPhone)
    }
}