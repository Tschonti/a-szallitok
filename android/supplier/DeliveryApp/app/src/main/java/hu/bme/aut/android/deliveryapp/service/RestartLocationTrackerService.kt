package hu.bme.aut.android.deliveryapp.service

import android.content.Intent
import android.location.Location
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.Observer
import hu.bme.aut.android.deliveryapp.model.DeliveryStatus
import hu.bme.aut.android.deliveryapp.model.LocationUpdate
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.states.DeliveryInProgressState

class RestartLocationTrackerService : LifecycleService() {

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        ApiRepository().getDeliveriesInProgress().observe(this) {
                state ->
            if (state is DeliveryInProgressState.deliveriesResponseSuccess) {
                state.data.forEach {
                    if (it.delivery.status == DeliveryStatus.IN_TRANSIT) {
                        startService(Intent(this, LocationTrackerService::class.java).putExtra("DELIVERY_ID", it.delivery._id))
                    }
                }
            }
        }

        return START_STICKY_COMPATIBILITY
    }
}