package com.yml.womensafety

import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.util.Log
import java.util.*

class GetLocation(val context: Context) {
    private var message = String()

    companion object {
        const val LOG_MESSAGE = "Error"
        const val MAX_RESULTS = 1
        const val RETURNED_INDEX = 0
        const val LOCATION: String = "https://www.google.com/maps/search/?api=1&amp;query="
    }

    fun getLocation(): String {
        val gpsTracker = GPSTracker(context)
        val location: Location? = gpsTracker.getLocation()
        if (location != null) {
            val locationLatitude: Double = location.latitude
            val locationLongitude: Double = location.longitude
            var address = ""
            val geoCoder = Geocoder(context, Locale.getDefault())
            try {
                val addresses =
                    geoCoder.getFromLocation(
                        locationLatitude,
                        locationLongitude,
                        MAX_RESULTS
                    )
                if (addresses != null) {
                    val returnedAddress = addresses[RETURNED_INDEX]
                    val strReturnedAddress = StringBuilder("")
                    for (index in 0..returnedAddress.maxAddressLineIndex) {
                        strReturnedAddress.append(returnedAddress.getAddressLine(index))
                            .append("\n")
                    }
                    address = strReturnedAddress.toString()
                }
            } catch (exception: Exception) {
                Log.e(LOG_MESSAGE, exception.toString())
            }
            message = "$LOCATION$locationLatitude,$locationLongitude\n$address"
        }

        return message
    }


}
