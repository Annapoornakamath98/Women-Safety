package com.yml.womensafety

import android.app.Application

class WomenSafetyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseUtil.initializeFirebase()
        FirebaseUtil.initializeDatabase()
    }

}

