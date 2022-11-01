package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.states.DeliveryListState

class AvailableJobsFragmentViewModel : ViewModel() {
    private val apiRepository = ApiRepository()

    fun getAvailableJobs(): LiveData<DeliveryListState> {
        return apiRepository.getAvailableJobs()
    }
}