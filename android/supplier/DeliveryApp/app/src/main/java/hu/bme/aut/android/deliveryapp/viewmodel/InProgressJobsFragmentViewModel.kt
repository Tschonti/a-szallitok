package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.states.JobDetailState

class InProgressJobsFragmentViewModel : ViewModel() {
    private val apiRepository = ApiRepository()

    private lateinit var data: LiveData<JobDetailState>

    fun getJobsInProgress(id: Int): LiveData<JobDetailState> {
        if (!::data.isInitialized) {
            data = apiRepository.getJobsInProgress("10")
        }
        return data
    }
}