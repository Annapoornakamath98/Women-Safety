package com.yml.womensafety.navigationdrawer.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.yml.womensafety.FirebaseApplication
import com.yml.womensafety.R
import com.yml.womensafety.authentication.LoginActivity
import com.yml.womensafety.navigationdrawer.LawsFragment
import com.yml.womensafety.navigationdrawer.SafetyTipsFragment
import com.yml.womensafety.navigationdrawer.EscapeThreatFragment
import com.yml.womensafety.navigationdrawer.contacts.ContactsFragment
import com.yml.womensafety.navigationdrawer.youtube.SelfDefenseVideoFragment
import kotlinx.android.synthetic.main.activity_home_page.*

class HomePageActivity : AppCompatActivity() {
    private lateinit var contactsFragment: ContactsFragment
    private lateinit var escapeThreatFragment: EscapeThreatFragment
    private lateinit var safetyTips: SafetyTipsFragment
    private lateinit var selfDefenseVideoFragment: SelfDefenseVideoFragment
    private lateinit var laws:LawsFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavBar)
        bottomNavigationView.apply {
            background = null
            menu.getItem(2).isEnabled = false
            itemIconTintList = null;
        }


        val firebaseApplication = FirebaseApplication()
        val user = firebaseApplication.u.currentUser
        val databaseReference = firebaseApplication.db.reference.child("name")
        val userReference = databaseReference.child(user?.uid!!)
        userReference.addValueEventListener(object : ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                val userFullName = snapshot.child("fullName").value.toString()
                tvLabel.text = "Hi, $userFullName"
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        cvEscapeThreat.setOnClickListener {
            escapeThreatFragment = EscapeThreatFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.flHomePage, escapeThreatFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit()

        }
        cvTipsForWomenSafety.setOnClickListener {
            safetyTips = SafetyTipsFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.flHomePage, safetyTips)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit()
        }
        cvVideosForSelfDefense.setOnClickListener {
            selfDefenseVideoFragment = SelfDefenseVideoFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.flHomePage, selfDefenseVideoFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit()
        }
        cvLaws.setOnClickListener {
            laws = LawsFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.flHomePage, laws)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit()
        }
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
                        .replace(R.id.flHomePage, contactsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()

                }
            }
            true
        }

    }


}