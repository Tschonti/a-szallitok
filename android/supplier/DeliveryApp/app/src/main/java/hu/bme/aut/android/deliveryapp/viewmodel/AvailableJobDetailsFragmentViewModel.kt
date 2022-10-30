package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.states.DeliveryState
import hu.bme.aut.android.deliveryapp.view.states.UserState

class AvailableJobDetailsFragmentViewModel : ViewModel() {
    private val apiRepository = ApiRepository()

    fun requestJob(id: String): LiveData<DeliveryState> {
        return apiRepository.requestJob(id)
    }
}