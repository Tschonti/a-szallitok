package hu.bme.aut.android.deliveryapp.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat.stopForeground
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import hu.bme.aut.android.deliveryapp.MainActivity
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.model.LocationUpdate
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.repository.CurrentUser
import hu.bme.aut.android.deliveryapp.view.states.DeliveryState

class LocationTrackerService : LifecycleService() {
    private val NOTIFICATION_CHANNEL_ID = "location_service_notifications"
    private val NOTIFICATION_CHANNEL_NAME = "Location Service notifications"
    private val NOTIF_FOREGROUND_ID = 101

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        val token = CurrentUser.token
        val deliveryId = intent?.extras?.get("DELIVERY_ID") as String

        startForeground(NOTIF_FOREGROUND_ID, getMyNotification())

        val locationLiveData = LocationLiveData(this)
        locationLiveData.observe(this, object: Observer<Location> {
            override fun onChanged(t: Location) {

                ApiRepository().updateLocation(token, deliveryId, LocationUpdate(t.latitude.toFloat(), t.longitude.toFloat()))
            }
        })

        return START_STICKY
    }


    private fun getMyNotification(): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }

        val notificationIntent = Intent(this, MainActivity::class.java)
        val contentIntent =
            PendingIntent.getActivity(
                this,
                NOTIF_FOREGROUND_ID,
                notificationIntent,
                PendingIntent.FLAG_IMMUTABLE
            )

        return NotificationCompat.Builder(
            this, NOTIFICATION_CHANNEL_ID
        )
            .setContentTitle("Transporter location tracker")
            .setContentText("Deliver the product!")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(contentIntent).build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        notificationManager?.createNotificationChannel(channel)
    }


    override fun onDestroy() {
        stopForeground(false)
        super.onDestroy()
    }
}