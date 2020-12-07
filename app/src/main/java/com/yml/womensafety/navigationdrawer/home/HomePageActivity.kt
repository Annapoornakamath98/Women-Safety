package com.yml.womensafety.navigationdrawer.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.yml.womensafety.authentication.LoginActivity
import com.yml.womensafety.R
import com.yml.womensafety.navigationdrawer.TipsToEscapeFragment
import com.yml.womensafety.navigationdrawer.contacts.ContactsFragment
import com.yml.womensafety.navigationdrawer.youtube.SelfDefenseVideoFragment
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.activity_login.*

class HomePageActivity : AppCompatActivity(){
    private lateinit var auth: FirebaseAuth
    private var databaseReference: DatabaseReference? = null
    private var firebaseDatabase: FirebaseDatabase? = null
    private lateinit var homePageFragment: HomePageFragment
    private lateinit var contactsFragment: ContactsFragment
    private lateinit var tipsToEscapeFragment: TipsToEscapeFragment
    private lateinit var selfDefenseVideoFragment: SelfDefenseVideoFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavBar)
        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false
        bottomNavigationView.itemIconTintList = null;

        auth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase!!.reference.child("name")
        val user = auth.currentUser
        val userEmail = user?.email.toString()
        val userReference = databaseReference?.child(user?.uid!!)
        userReference?.addValueEventListener(object : ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                val userFullName = snapshot.child("fullName").value.toString()
                //Toast.makeText(view.context,"Welcome $userFullName",Toast.LENGTH_LONG).show()
                Snackbar.make(findViewById(android.R.id.content),"Welcome $userFullName", Snackbar.LENGTH_LONG).show()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        cvEscapeThreat.setOnClickListener {
            tipsToEscapeFragment = TipsToEscapeFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.flHomePage,tipsToEscapeFragment)
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
                .replace(R.id.flHomePage,selfDefenseVideoFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit()
        }
        cvLaws.setOnClickListener {

        }
        bottomNavigationView.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.navHome -> {
                    startActivity(Intent(this, HomePageActivity::class.java))
//                    homePageFragment = HomePageFragment()
//                    supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.flHomePage, homePageFragment)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .addToBackStack(null)
//                        .commit()
                }
                R.id.navLogout -> {
                    auth.signOut()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                R.id.navContacts -> {
                    contactsFragment = ContactsFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.flHomePage,contactsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()

                }
            }
            true
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}