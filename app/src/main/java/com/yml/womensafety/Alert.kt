package com.yml.womensafety

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AlertDialog

object Alert {
    fun Activity.alert(
        context: Context,
        title: CharSequence,
        message: CharSequence,
        positiveMessage: CharSequence
    ) {
        val builder = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveMessage) { dialog, which ->

            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}