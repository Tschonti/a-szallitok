package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.JobDetailState

class AvailableJobsFragmentViewModel : ViewModel() {
    private val apiRepository = ApiRepository()

    fun getAvailableJobs(id: Int): LiveData<JobDetailState> {
        return apiRepository.getAvailableJobs(id)
    }
}