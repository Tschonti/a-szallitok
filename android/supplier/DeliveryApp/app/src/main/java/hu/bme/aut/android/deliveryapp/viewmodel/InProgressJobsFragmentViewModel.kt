package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.JobDetailState

class InProgressJobsFragmentViewModel : ViewModel() {
    private val apiRepository = ApiRepository()

    fun getJobsInProgress(id: Int): LiveData<JobDetailState> {
        return apiRepository.getAvailableJobs(id)
    }
}