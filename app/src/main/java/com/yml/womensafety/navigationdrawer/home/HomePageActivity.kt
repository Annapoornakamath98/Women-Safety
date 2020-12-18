package com.yml.womensafety.navigationdrawer.home

import android.Manifest
import android.app.AlertDialog
import android.content.*
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.yml.womensafety.*
import com.yml.womensafety.AlertDialogUtil.showAlert
import com.yml.womensafety.authentication.LoginActivity
import com.yml.womensafety.navigationdrawer.UserProfile
import com.yml.womensafety.navigationdrawer.contacts.ContactsFragment
import com.yml.womensafety.viewModel.ContactsViewModel
import kotlinx.android.synthetic.main.activity_home_page.*
import java.util.*


class HomePageActivity : AppCompatActivity() {
    companion object {
        private const val PERMISSION_CODE = 1
        private const val LOG_MESSAGE = "Error"
        private const val INDEX = 2
    }

    private lateinit var contactsList: MutableList<String>
    lateinit var contactsViewModel: ContactsViewModel
    private lateinit var contactsFragment: ContactsFragment
    private lateinit var userProfile: UserProfile
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.SEND_SMS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(
                this, getString(R.string.permission_already_granted),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            requestPermission()
        }

        val intentService = Intent(this@HomePageActivity, AccelerometerService::class.java)
        startService(intentService)

        val myReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent?) {
                if ("com.yml.womensafety.ACTION" == intent?.action) {
                    val receivedText = intent.getStringExtra("com.yml.womensafety.EXTRA_TEXT")

                    sendSms()
                    val dialogBuilder = AlertDialog.Builder(context)
                    dialogBuilder.setMessage(receivedText)
                        .setCancelable(false)
                        .setNeutralButton("OK") { dialog, _ ->
                            dialog.cancel()
                        }
                    val alert = dialogBuilder.create()
                    alert.setTitle("Alert!")
                    alert.show()
                }
            }

        }
        val filter = IntentFilter("com.yml.womensafety.ACTION")
        registerReceiver(myReceiver, filter)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavBar)
        bottomNavigationView.apply {
            background = null
            menu.getItem(INDEX).isEnabled = false
            itemIconTintList = null
        }
        contactsList = mutableListOf()

        contactsViewModel = ViewModelProvider(this).get(ContactsViewModel()::class.java)
        contactsViewModel.initializeRepository()

        bottomNavigationView.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.navHome -> {
                    startActivity(Intent(this, HomePageActivity::class.java))
                }
                R.id.navLogout -> {
                    FirebaseUtil.user?.signOut()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                R.id.navContacts -> {
                    contactsFragment = ContactsFragment()
                    FragmentUtil.addFragmentToActivity(
                        supportFragmentManager,
                        contactsFragment,
                        R.id.myNavHostFragment
                    )
                }
                R.id.navProfile -> {
                    userProfile = UserProfile()
                    FragmentUtil.addFragmentToActivity(
                        supportFragmentManager,
                        userProfile,
                        R.id.myNavHostFragment
                    )
                }
            }
        }
        btn_fab.setOnClickListener {
            sendSms()
        }
    }

    //This method will send the SMS along with location
    private fun sendSms() {
        contactsViewModel.getContactsList(object : ContactsResponse {
            override fun onContactsReceiveSuccess(userContacts: List<String>) {
                try {
                    val location = GetLocation(this@HomePageActivity)
                    val message = location.getLocation()
                    val smsManager = SmsManager.getDefault()
                    userContacts.forEach {
                        smsManager.sendTextMessage(it, null, message, null, null)
                    }
                    Snackbar.make(
                        window.decorView.rootView,
                        R.string.message_sent,
                        Snackbar.LENGTH_LONG
                    ).show()
                } catch (ex: ActivityNotFoundException) {
                    Toast.makeText(
                        this@HomePageActivity,
                        getString(R.string.message_failed), Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onContactsReceiveFailed(throwable: Throwable) {
                Log.e(LOG_MESSAGE, throwable.toString())
            }
        })
    }

    //Below method requests to enable location and SMS permissions
    private fun requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            && ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.SEND_SMS
            )
        ) {
            showAlert(
                this,
                getString(R.string.permission_needed), getString(R.string.permission_needed)
            )

        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.SEND_SMS),
                PERMISSION_CODE
            )
        }
    }

}