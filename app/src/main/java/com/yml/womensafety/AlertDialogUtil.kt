package com.yml.womensafety


import android.Manifest
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat

object AlertDialogUtil {
    private const val positiveMessage = "OK"
    private const val LOCATION_PERMISSION_CODE = 1
    fun showAlert(context: Context, title: CharSequence, message: CharSequence) {
        val builder = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveMessage) { dialog, which ->
                ActivityCompat.requestPermissions(
                    context as Activity, arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ), LOCATION_PERMISSION_CODE
                )
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}