package com.yml.womensafety

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.IBinder
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.yml.womensafety.navigationdrawer.home.HomePageActivity
import kotlin.math.abs

class AccelerometerService : Service(), SensorEventListener {
    companion object {
        private const val CHANNEL_ID: String = "ForegroundService"
        private const val BROADCAST_ACTION = "com.yml.womensafety.ACTION"
        private const val BROADCAST_NAME = "com.yml.womensafety.EXTRA_TEXT"
        private const val BROADCAST_VALUE = "SMS sent"
        private var CURRENT_X = 0f
        private var CURRENT_Y = 0f
        private var CURRENT_Z = 0f
        private var LAST_X = 0f
        private var LAST_Y = 0f
        private var LAST_Z = 0f
        private const val SHAKE_THRESHOLD = 15f
        private const val ID = 1
    }

    private var sensorManager: SensorManager? = null
    private var accelerometerSensor: Sensor? = null
    private var vibrator: Vibrator? = null
    private var isAccelerometerSensorAvailable: Boolean = false
    private var itIsNotFirstTime = false

    override fun onCreate() {
        super.onCreate()

        vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        createNotificationChannel()
        val notificationIntent = Intent(this, HomePageActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 1, notificationIntent, 0)
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(getString(R.string.Shake_device_to_send_location))
            .setSmallIcon(R.drawable.ic_health_and_safety)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(ID, notification)

        if (sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            accelerometerSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            isAccelerometerSensorAvailable = true
        } else
            isAccelerometerSensorAvailable = false

        if (isAccelerometerSensorAvailable) {
            sensorManager?.registerListener(
                this,
                accelerometerSensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        } else {
            Toast.makeText(
                applicationContext,
                "Accelerometer sensor not available",
                Toast.LENGTH_SHORT
            ).show()
            isAccelerometerSensorAvailable = false
        }

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            CURRENT_X = event.values[0]
            CURRENT_Y = event.values[1]
            CURRENT_Z = event.values[2]
        }


        if (itIsNotFirstTime) {
            val xDifference = abs(LAST_X - CURRENT_X)
            val yDifference = abs(LAST_Y - CURRENT_Y)
            val zDifference = abs(LAST_Z - CURRENT_Z)

            if ((xDifference > SHAKE_THRESHOLD && yDifference > SHAKE_THRESHOLD) ||
                (xDifference > SHAKE_THRESHOLD && zDifference > SHAKE_THRESHOLD) ||
                (yDifference > SHAKE_THRESHOLD && zDifference > SHAKE_THRESHOLD)
            ) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator?.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                }
                val broadcastIntent = Intent(BROADCAST_ACTION)
                broadcastIntent.putExtra(BROADCAST_NAME, BROADCAST_VALUE)
                sendBroadcast(broadcastIntent)
            }
        }

        LAST_X = CURRENT_X
        LAST_Y = CURRENT_Y
        LAST_Z = CURRENT_Z

        itIsNotFirstTime = true
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //Not implementing any functionality based on accuracy.
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground service example",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(serviceChannel)
        }
    }
}