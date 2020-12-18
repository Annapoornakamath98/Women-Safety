package com.yml.womensafety.navigationdrawer.home

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yml.womensafety.*
import com.yml.womensafety.authentication.LoginActivity
import com.yml.womensafety.navigationdrawer.UserProfile
import com.yml.womensafety.navigationdrawer.contacts.ContactsFragment
import com.yml.womensafety.viewModel.ContactsViewModel
import kotlinx.android.synthetic.main.activity_home_page.*
import java.util.*


class HomePageActivity : AppCompatActivity() {
    companion object {
        private const val Request_Code_permission: Int = 2
        private const val LOCATION_PERMISSION_CODE = 1
        private const val LOG_MESSAGE = "Error"
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
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(
                this, getString(R.string.permission_already_granted),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            requestLocationPermission()
        }
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavBar)
        bottomNavigationView.apply {
            background = null
            menu.getItem(2).isEnabled = false
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
        checkSmsPermission()
        btn_fab.setOnClickListener {
            sendSms()
        }
    }

    //The below method is used check if sms permission is enabled
    private fun checkSmsPermission() {
        val checkPermission: String = Manifest.permission.SEND_SMS
        try {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    checkPermission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(checkPermission),
                    Request_Code_permission
                )
            }
        } catch (exception: Exception) {
            Log.e(LOG_MESSAGE, exception.toString())
        }
        val checkInternetPermission: String = Manifest.permission.INTERNET
        try {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    checkInternetPermission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(checkPermission),
                    Request_Code_permission
                )
            }
        } catch (exception: Exception) {
            Log.e(LOG_MESSAGE, exception.toString())
        }
    }

    //This method will send the SMS along with location
    private fun sendSms() {
        contactsViewModel.getContactsList(object : ContactsResponse {
            override fun onContactsReceiveSuccess(userContacts: List<String>) {
                try {
                    val gpsTracker = GPSTracker(applicationContext)
                    val location: Location? = gpsTracker.getLocation()
                    if (location != null) {
                        val locationLatitude: Double = location.latitude
                        val locationLongitude: Double = location.longitude
                        var strAdd = ""
                        val geoCoder = Geocoder(this@HomePageActivity, Locale.getDefault())
                        try {
                            val addresses =
                                geoCoder.getFromLocation(locationLatitude, locationLongitude, 1)
                            if (addresses != null) {
                                val returnedAddress = addresses[0]
                                val strReturnedAddress = StringBuilder("")
                                for (i in 0..returnedAddress.maxAddressLineIndex) {
                                    strReturnedAddress.append(returnedAddress.getAddressLine(i))
                                        .append("\n")
                                }
                                strAdd = strReturnedAddress.toString()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        val message =
                            getString(R.string.location) + "$locationLatitude,$locationLongitude\n$strAdd"
                        val smsManager = SmsManager.getDefault()
                        userContacts.forEach {
                            smsManager.sendTextMessage(it, null, message, null, null)
                        }
                        Toast.makeText(
                            applicationContext, getString(R.string.message_sent),
                            Toast.LENGTH_LONG
                        ).show()
                    }

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

    //Below method requests to enable location permission
    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            AlertDialogUtil.showAlert(
                this,
                getString(R.string.permission_needed), getString(R.string.permission_needed)
            )

        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_CODE
            )
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.SEND_SMS),
                Request_Code_permission
            )
        }
    }

}