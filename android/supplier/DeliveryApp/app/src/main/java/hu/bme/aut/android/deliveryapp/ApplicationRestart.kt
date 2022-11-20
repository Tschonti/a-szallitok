package hu.bme.aut.android.deliveryapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.example.awesomedialog.*
import hu.bme.aut.android.deliveryapp.model.DeliveryStatus
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.service.LocationTrackerService
import hu.bme.aut.android.deliveryapp.view.fragments.InProgressJobsDetailsFragment
import hu.bme.aut.android.deliveryapp.view.states.DeliveryInProgressState
import hu.bme.aut.android.deliveryapp.view.states.DeliveryState

class ApplicationRestart : AppCompatActivity() {
    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 1001
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.FOREGROUND_SERVICE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_BACKGROUND_LOCATION)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application_restart)

        if (!allPermissionsGranted()) {
            val permissionLauncher = registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                if (isGranted) {
                    ApiRepository().getDeliveriesInProgress().observe(this) {
                            state ->
                        if (state is DeliveryInProgressState.deliveriesResponseSuccess) {
                            state.data.forEach {
                                if (it.delivery.status == DeliveryStatus.IN_TRANSIT) {
                                    startService(Intent(this, LocationTrackerService::class.java))
                                }
                            }
                        }
                    }
                }
                else {
                    AwesomeDialog.build(this)
                        .title("Error")
                        .body("You have to grant the permissions to start the delivery!")
                        .icon(R.drawable.error)
                        .onPositive("Close")
                }
            }

            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            this, it
        ) == PackageManager.PERMISSION_GRANTED
    }
}