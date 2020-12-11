package com.yml.womensafety.navigationdrawer.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yml.womensafety.FirebaseApplication
import com.yml.womensafety.R
import com.yml.womensafety.authentication.LoginActivity
import com.yml.womensafety.navigationdrawer.contacts.ContactsFragment
import kotlinx.android.synthetic.main.activity_home_page.*

class HomePageActivity : AppCompatActivity() {
    private lateinit var contactsFragment: ContactsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val intentService = Intent(this@HomePageActivity, AccelerometerService::class.java)
        startService(intentService)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavBar)
        bottomNavigationView.apply {
            background = null
            menu.getItem(2).isEnabled = false
            itemIconTintList = null
        }
        val firebaseApplication = FirebaseApplication()
        bottomNavigationView.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.navHome -> {
                    startActivity(Intent(this, HomePageActivity::class.java))
                }
                R.id.navLogout -> {
                    firebaseApplication.u.signOut()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                R.id.navContacts -> {
                    contactsFragment = ContactsFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.myNavHostFragment, contactsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }
        }
    }
}