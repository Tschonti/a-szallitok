package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.states.DeliveryListState

class HistoryJobsFragmentViewModel : ViewModel() {
    private val apiRepository = ApiRepository()

    private lateinit var data: LiveData<DeliveryListState>

    fun getUserHistory(id: String): LiveData<DeliveryListState> {
        if (!::data.isInitialized) {
            data = apiRepository.getUserHistory(id)
        }
        return data
    }
}