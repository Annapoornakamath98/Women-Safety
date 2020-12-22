package com.yml.womensafety

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat

class GPSTracker(var context: Context) : LocationListener {
    companion object {
        private const val minimumTime: Long = 6000
        private const val minimumDistance: Float = 10f
    }

    /**
     * This function returns the current location of the user
     */
    fun getLocation(): Location? {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (isGPSEnabled) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minimumTime,
                    minimumDistance,
                    this
                )
            }

        }
        return locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
    }

    override fun onLocationChanged(location: Location) {
        //Nothing to be implemented here
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        //Nothing to be implemented here
    }

    override fun onProviderEnabled(provider: String) {
        //Nothing to be implemented here
    }

    override fun onProviderDisabled(provider: String) {
        //Nothing to be implemented here
    }

}