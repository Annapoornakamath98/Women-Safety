package com.yml.womensafety.navigationdrawer.home

import android.app.Service
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import kotlin.math.abs

class AccelerometerService : Service(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometerSensor: Sensor
    private lateinit var vibrator: Vibrator
    private var isAccelerometerSensorAvailable: Boolean = false
    private var itIsNotFirstTime = false
    private var currentX = 0f
    private var currentY = 0f
    private var currentZ = 0f
    private var lastX = 0f
    private var lastY = 0f
    private var lastZ = 0f
    private val shakeThreshold = 15f
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            isAccelerometerSensorAvailable = true
        } else {
            Toast.makeText(
                applicationContext,
                "Accelerometer sensor not available",
                Toast.LENGTH_SHORT
            ).show()
            isAccelerometerSensorAvailable = false
        }

        if (isAccelerometerSensorAvailable) {
            sensorManager.registerListener(
                this,
                accelerometerSensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        currentX = event!!.values[0]
        currentY = event.values[1]
        currentZ = event.values[2]

        if (itIsNotFirstTime) {
            val xDifference = abs(lastX - currentX)
            val yDifference = abs(lastY - currentY)
            val zDifference = abs(lastZ - currentZ)

            if ((xDifference > shakeThreshold && yDifference > shakeThreshold) ||
                (xDifference > shakeThreshold && zDifference > shakeThreshold) ||
                (yDifference > shakeThreshold && zDifference > shakeThreshold)
            ) {

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                }
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
}