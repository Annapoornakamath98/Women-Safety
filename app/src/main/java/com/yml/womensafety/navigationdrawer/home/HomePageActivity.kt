package com.yml.womensafety.navigationdrawer.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.yml.womensafety.FirebaseApplication
import com.yml.womensafety.R
import com.yml.womensafety.authentication.LoginActivity
import com.yml.womensafety.navigationdrawer.TipsToEscapeFragment
import com.yml.womensafety.navigationdrawer.contacts.ContactsFragment
import com.yml.womensafety.navigationdrawer.youtube.SelfDefenseVideoFragment
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.activity_login.*

class HomePageActivity : AppCompatActivity() {
    private lateinit var contactsFragment: ContactsFragment
    private lateinit var tipsToEscapeFragment: TipsToEscapeFragment
    private lateinit var selfDefenseVideoFragment: SelfDefenseVideoFragment
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
            tipsToEscapeFragment = TipsToEscapeFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.flHomePage, tipsToEscapeFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit()

        }
        cvTipsForWomenSafety.setOnClickListener {

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