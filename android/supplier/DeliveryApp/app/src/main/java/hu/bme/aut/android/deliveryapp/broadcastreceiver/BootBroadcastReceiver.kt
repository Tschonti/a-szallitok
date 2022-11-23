package hu.bme.aut.android.deliveryapp.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import hu.bme.aut.android.deliveryapp.service.RestartLocationTrackerService


class BootBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.startService(Intent(context, RestartLocationTrackerService::class.java))
        Log.d("BROADCAST_RECEIVER", "CALLED")
    }
}