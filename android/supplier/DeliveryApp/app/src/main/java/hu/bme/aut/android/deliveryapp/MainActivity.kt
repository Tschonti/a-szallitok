package hu.bme.aut.android.deliveryapp

import android.content.IntentFilter
import android.os.Bundle
import android.provider.Telephony
import androidx.appcompat.app.AppCompatActivity
import hu.bme.aut.android.deliveryapp.broadcastreceiver.BootBroadcastReceiver


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bootFilter = IntentFilter()
        bootFilter.addAction("android.intent.action.ACTION_BOOT_COMPLETED")
        bootFilter.addAction("android.intent.action.BOOT_COMPLETED")
        registerReceiver(BootBroadcastReceiver(), bootFilter)
    }
}