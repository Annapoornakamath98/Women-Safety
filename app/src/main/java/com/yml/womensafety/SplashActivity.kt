package com.yml.womensafety

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import com.yml.womensafety.authentication.LoginActivity
import com.yml.womensafety.navigationdrawer.home.HomePageActivity

class SplashActivity : AppCompatActivity() {
    private val splashTimeOut: Long = 3000
    private var firebaseApplication = FirebaseApplication()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, splashTimeOut)
    }

    override fun onStart() {
        super.onStart()
        val uu = firebaseApplication.u.currentUser
        updateUI(uu)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            startActivity(Intent(this, HomePageActivity::class.java))
        }
    }
}
