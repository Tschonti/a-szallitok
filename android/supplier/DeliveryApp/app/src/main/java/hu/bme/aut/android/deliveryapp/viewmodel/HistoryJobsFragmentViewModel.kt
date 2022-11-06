package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.states.DeliveryInProgressState
import hu.bme.aut.android.deliveryapp.view.states.DeliveryListState

class HistoryJobsFragmentViewModel : ViewModel() {
    private val apiRepository = ApiRepository()
    fun getUserHistory(): LiveData<DeliveryInProgressState> {
            return apiRepository.getUserHistory()
    }
}