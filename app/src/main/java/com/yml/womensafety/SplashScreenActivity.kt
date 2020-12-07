package com.yml.womensafety

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.yml.womensafety.authentication.LoginActivity
import com.yml.womensafety.navigationdrawer.home.HomePageActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val splashTimeOut:Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        auth = FirebaseAuth.getInstance()
        Handler().postDelayed({
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }, splashTimeOut)
    }
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
    private fun updateUI(currentUser: FirebaseUser?) {
        if(currentUser !=  null){
            startActivity(Intent(this, HomePageActivity::class.java))
        }
    }
}
