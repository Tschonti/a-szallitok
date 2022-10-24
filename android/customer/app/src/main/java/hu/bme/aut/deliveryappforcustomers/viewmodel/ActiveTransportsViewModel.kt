package hu.bme.aut.deliveryappforcustomers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.deliveryappforcustomers.repository.ApiRepository
import hu.bme.aut.deliveryappforcustomers.view.JobDetailState

class ActiveTransportsViewModel : ViewModel() {

    private val apiRepository = ApiRepository()

    fun getActiveTransports(id: Int): LiveData<JobDetailState> {
        return apiRepository.getActiveTransports(id)
    }
}