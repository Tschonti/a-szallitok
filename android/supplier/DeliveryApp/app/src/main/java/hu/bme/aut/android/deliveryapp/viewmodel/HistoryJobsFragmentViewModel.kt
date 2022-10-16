package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.JobDetailState

class HistoryJobsFragmentViewModel : ViewModel() {
    private val apiRepository = ApiRepository()

    fun getUserHistory(id: Int): LiveData<JobDetailState> {
        return apiRepository.getUserHistory(id)
    }
}