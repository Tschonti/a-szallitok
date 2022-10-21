package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.JobDetailState

class AvailableJobsMapFragmentViewModel : ViewModel() {
    private val apiRepository = ApiRepository()

    fun getAvailableJobs(sourceCity: String?, destinationCity: String?, price: Int?, date: String?): LiveData<JobDetailState> {
        return apiRepository.getAvailableJobs(sourceCity, destinationCity, price, date)
    }

}