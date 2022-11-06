package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.api.RetrofitClient.api
import hu.bme.aut.android.deliveryapp.model.Vehicle
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.states.VehicleState

class AddVehicleFragmentViewModel : ViewModel() {
    val api = ApiRepository()

    fun addVehicle(vehicle: Vehicle): LiveData<VehicleState> {
        return api.addVehicle(vehicle)
    }

    fun updateVehicle(vehicleId: String, vehicle: Vehicle): LiveData<VehicleState> {
        return api.updateVehicle(vehicleId, vehicle)
    }
}