package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.states.DeliveryListState

class InProgressJobsFragmentViewModel : ViewModel() {
    private val apiRepository = ApiRepository()

    private lateinit var data: LiveData<DeliveryListState>

    fun getJobsInProgress(clientId: String): LiveData<DeliveryListState> {
        if (!::data.isInitialized) {
            data = apiRepository.getJobsInProgress(clientId)
        }
        return data
    }
}