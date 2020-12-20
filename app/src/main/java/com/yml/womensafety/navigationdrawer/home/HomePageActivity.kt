package com.yml.womensafety.navigationdrawer.home

import android.Manifest
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
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
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
        private const val PERMISSION_CODE = 10
        private const val LOG_MESSAGE = "Error"
        private const val ID_HOME = 0
        private const val ID_CONTACTS = 1
        private const val ID_PROFILE = 2
        private const val ID_LOGOUT = 3
    }

    private lateinit var contactsList: MutableList<String>
    lateinit var contactsViewModel: ContactsViewModel
    private lateinit var homepageFragment: HomePageFragment
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

        startAccelerometerService()
        receiveBroadcast()

        bottomNavigation.apply {
            show(ID_HOME)
            add(MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_outline_home))
            add(MeowBottomNavigation.Model(ID_CONTACTS, R.drawable.ic_add_contacts))
            add(MeowBottomNavigation.Model(ID_PROFILE, R.drawable.ic_outline_account))
            add(MeowBottomNavigation.Model(ID_LOGOUT, R.drawable.ic_exit_to_app))
        }
        contactsList = mutableListOf()

        contactsViewModel = ViewModelProvider(this).get(ContactsViewModel()::class.java)
        contactsViewModel.initializeRepository()


        bottomNavigation.setOnClickMenuListener {
            when (it.id) {
                ID_HOME -> {
                    homepageFragment = HomePageFragment()
                    FragmentUtil.replaceFragment(
                        supportFragmentManager,
                        homepageFragment,
                        R.id.myNavHostFragment
                    )
                }
                ID_CONTACTS -> {
                    contactsFragment = ContactsFragment()
                    FragmentUtil.replaceFragment(
                        supportFragmentManager,
                        contactsFragment,
                        R.id.myNavHostFragment
                    )
                }

                ID_PROFILE -> {
                    userProfile = UserProfile()
                    FragmentUtil.replaceFragment(
                        supportFragmentManager,
                        userProfile,
                        R.id.myNavHostFragment
                    )
                }

                ID_LOGOUT -> {
                    FirebaseUtil.user?.signOut()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }

    /**
     * This method receives the broadcast
     */
    private fun receiveBroadcast() {
        val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent?) {
                if (getString(R.string.broadcast_action) == intent?.action) {
                    val receivedText = intent.getStringExtra(getString(R.string.broadcast_name))

                    sendSms()
                    if (receivedText != null) {
                        showAlert(
                            this@HomePageActivity,
                            getString(R.string.alert_dialog_title),
                            receivedText
                        )
                    }
                }
            }
        }
        val filter = IntentFilter(getString(R.string.broadcast_action))
        registerReceiver(broadcastReceiver, filter)
    }

    /**
     * This method calls the Accelerometer Service
     */
    private fun startAccelerometerService() {
        val accelerometerService = Intent(this@HomePageActivity, AccelerometerService::class.java)
        startService(accelerometerService)
    }

    /**
     * This method will send the SMS along with location
     */
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

    /**
     *  Below method requests to enable location and SMS permissions
     */
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