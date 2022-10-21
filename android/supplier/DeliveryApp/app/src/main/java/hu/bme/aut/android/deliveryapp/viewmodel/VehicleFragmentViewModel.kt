package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.UserState
import hu.bme.aut.android.deliveryapp.view.VehicleState

class VehicleFragmentViewModel : ViewModel() {
    private val apiRepository = ApiRepository()

    fun getVehicleData(id: Int): LiveData<VehicleState> {
        return apiRepository.getVehicleData(id)
    }
}