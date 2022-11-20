package hu.bme.aut.android.deliveryapp.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hu.bme.aut.android.deliveryapp.ApplicationRestart


class BootBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val i = Intent("hu.bme.aut.android.deliveryapp.ApplicationRestart")
        i.setClass(context, ApplicationRestart::class.java)
        context.startService(i)
    }
}