package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.states.VehicleState

class VehicleFragmentViewModel : ViewModel() {
    private val apiRepository = ApiRepository()
    private lateinit var data: LiveData<VehicleState>

    fun getVehicleData(id: Int): LiveData<VehicleState> {
        if (!::data.isInitialized) {
            data = apiRepository.getVehicleData(id)
        }

        return data
    }
}