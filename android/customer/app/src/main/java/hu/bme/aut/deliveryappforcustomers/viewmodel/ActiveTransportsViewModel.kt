package hu.bme.aut.deliveryappforcustomers.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.deliveryappforcustomers.model.DeliveryWithUserAndStatus
import hu.bme.aut.deliveryappforcustomers.repository.ApiRepository

class ActiveTransportsViewModel : ViewModel() {

    private val apiRepository = ApiRepository()

    suspend fun getJobRequests(): MutableLiveData<List<DeliveryWithUserAndStatus>?> {
        val resultData = apiRepository.getJobRequests()
        return resultData
    }
}