package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.model.DeliveryInProgress
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.states.DeliveryInProgressState
import hu.bme.aut.android.deliveryapp.view.states.DeliveryListState

class InProgressJobsFragmentViewModel : ViewModel() {
    private val apiRepository = ApiRepository()

    fun getJobsInProgress(): LiveData<DeliveryInProgressState> {
        return apiRepository.getDeliveriesInProgress()
    }
}