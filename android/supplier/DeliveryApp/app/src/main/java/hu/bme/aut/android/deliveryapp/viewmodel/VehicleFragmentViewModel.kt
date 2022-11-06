package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.states.VehicleState

class VehicleFragmentViewModel : ViewModel() {
    private val apiRepository = ApiRepository()

    fun getVehicleData(id: String): LiveData<VehicleState> {
        return apiRepository.getVehicleData(id)
    }
}