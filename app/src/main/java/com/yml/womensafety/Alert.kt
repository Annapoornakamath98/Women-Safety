package com.yml.womensafety

import android.content.Context
import androidx.appcompat.app.AlertDialog

/**
 * This is used to display an Alert Dialog
 * @param context context of the activity class
 * @param title the title of alert dialog
 * @param message the message to be displayed in alert dialog
 * @param positiveMessage the message of the positive button in alert dialog
 */
object Alert {
    fun alert(
        context: Context,
        title: CharSequence,
        message: CharSequence,
        positiveMessage: CharSequence
    ) {
        val builder = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveMessage) { dialog, which ->
                //Nothing to be implemented here
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}