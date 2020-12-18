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
    private val CHANNEL_ID: String = "ForegroundService"
    private var sensorManager: SensorManager? = null
    private var accelerometerSensor: Sensor? = null
    private var vibrator: Vibrator? = null
    private var isAccelerometerSensorAvailable: Boolean = false
    private var itIsNotFirstTime = false
    private var currentX = 0f
    private var currentY = 0f
    private var currentZ = 0f
    private var lastX = 0f
    private var lastY = 0f
    private var lastZ = 0f
    private val shakeThreshold = 15f
    private var broadcastAction = "com.yml.womensafety.ACTION"
    private var broadcastName = "com.yml.womensafety.EXTRA_TEXT"
    private var broadcastValue = "SMS sent"

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
        startForeground(1, notification)

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
            currentX = event.values[0]
            currentY = event.values[1]
            currentZ = event.values[2]
        }


        if (itIsNotFirstTime) {
            val xDifference = abs(lastX - currentX)
            val yDifference = abs(lastY - currentY)
            val zDifference = abs(lastZ - currentZ)

            if ((xDifference > shakeThreshold && yDifference > shakeThreshold) ||
                (xDifference > shakeThreshold && zDifference > shakeThreshold) ||
                (yDifference > shakeThreshold && zDifference > shakeThreshold)
            ) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator?.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                }
                val broadcastIntent = Intent(broadcastAction)
                broadcastIntent.putExtra(broadcastName, broadcastValue)
                sendBroadcast(broadcastIntent)
            }
        }

        lastX = currentX
        lastY = currentY
        lastZ = currentZ

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